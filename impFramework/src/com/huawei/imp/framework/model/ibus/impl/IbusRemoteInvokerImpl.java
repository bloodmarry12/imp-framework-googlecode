package com.huawei.imp.framework.model.ibus.impl;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.httpclient.HttpException;
import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import com.huawei.imp.framework.config.ConfigUtil;
import com.huawei.imp.framework.logger.Logger;
import com.huawei.imp.framework.model.ibus.IbusRemoteInvoker;
import com.huawei.imp.framework.model.ibus.IbusResponse;
import com.huawei.imp.framework.model.ibus.exception.IbusException;
import com.huawei.imp.framework.model.ibus.log.domain.IbusLogerEntity;
import com.huawei.imp.framework.model.ibus.log.service.IbusLogService;
import com.huawei.imp.framework.model.ibus.xmlparser.XMLParser;
import com.huawei.imp.framework.utils.ProcIDGenerator;
import com.huawei.imp.framework.utils.StringXmlTool;
import com.huawei.imp.framework.utils.http.HttpInvoke;

/**
 * Description: Ibus远程调用实现类
 * 
 * @author ahli May 7, 2009
 */
public class IbusRemoteInvokerImpl implements IbusRemoteInvoker
{

	/**
	 * 运行日志对象
	 */
	private final static Logger log = com.huawei.imp.framework.logger.LogFactory.getLogger(IbusRemoteInvokerImpl.class);

	/**
	 * 单例对象
	 */
	private static final IbusRemoteInvokerImpl invoker = new IbusRemoteInvokerImpl();

	/**
	 * 默认超时时间（毫秒）
	 */
	private static final int DEFAULT_TIMEOUT = 120000;

	/**
	 * 请求流名称
	 */
	protected final static String REQUEST_STREAM_NAME = "xmldata";
	
	private IbusLogService ibusLogService = null;
	
	/**
	 * 私有化构造函数
	 */
	private IbusRemoteInvokerImpl()
	{
	}

	/**
	 * 获取一个实例
	 * 
	 * @return
	 */
	public static IbusRemoteInvoker getIbusRemoteInvoker()
	{
		return invoker;
	}

	/* (non-Javadoc)
	 * @see com.huawei.imp.framework.model.ibus.IbusRemoteInvoker#postData(java.lang.String, org.dom4j.Element)
	 */
	@Override
	public IbusResponse postData(String ibusServ, XMLParser xmlParser)
			throws IbusException {
		return postData(ibusServ, xmlParser, null);
	}

	/* (non-Javadoc)
	 * @see com.huawei.imp.framework.model.ibus.IbusRemoteInvoker#postData(java.lang.String, org.dom4j.Element, java.lang.String)
	 */
	@Override
	public IbusResponse postData(String ibusServ, XMLParser xmlParser, String _procID)
			throws IbusException {
		final String procID = _procID == null?ProcIDGenerator.generateProcID():_procID;
		final String ibusServUrl = getIbusServUrl(ibusServ);
		final String xmldata = parseInnerBusResElementToXMLStr(xmlParser, procID);
		final String transID = StringXmlTool.getNodeValue(xmldata, "TransactionID");
		long time1 = 0L;
		long time2 = 0L;
		
		Map<String, Object> params = new HashMap<String, Object>(1);
		params.put(REQUEST_STREAM_NAME, xmldata);

		// 直接获取返回结果
		String ret = null;
		// 异常标志，如果出现异常，该标志为true
		boolean exceptionFlag = false;
		String exceptionStr = null;
		try
		{
			if(log.isDebugEnabled()){
				log.debug("requestXML " + ibusServUrl + "?" + REQUEST_STREAM_NAME + "=" + xmldata);
			}
			// 远程调用
			time1 = System.currentTimeMillis();
			Integer timeOut = ConfigUtil.getSysConfigIntegerValue("imp.framework.ibus.timeout") ;
			ret = HttpInvoke.postData(ibusServUrl, params, timeOut == null? DEFAULT_TIMEOUT:timeOut);
			time2 = System.currentTimeMillis();
			if(log.isDebugEnabled()){
				log.debug("responseXML:"+ret);
			}
			return new IbusResoponseImpl(ret);
		}
		catch (HttpException e)
		{
			exceptionFlag = true;
			exceptionStr = e.toString();
			throw new IbusException("远程调用异常", e);
		}
		catch (IOException e)
		{
			exceptionFlag = true;
			exceptionStr = e.toString();
			throw new IbusException("IO异常", e);
		} catch (DocumentException e) {
			exceptionFlag = true;
			exceptionStr = e.toString();
			throw new IbusException("返回报文解析失败", e);
		} catch (Exception e) {
			exceptionFlag = true;
			exceptionStr = e.toString();
			log.error(e.toString());
			throw new IbusException("未知异常", e);
		}finally{
			if(exceptionFlag){
				ibusErrorLog(procID, transID, ibusServUrl, xmldata, time1, "[" + exceptionStr + "]:" + ret);
			}else{
				// 记录数据交互日志
				ibusLog(procID, transID, ibusServUrl, xmldata, ret, time1, time2);
			}
		}
	}
	
	/**
	 * 拼装xml报文
	 * @param xmlParser
	 * @param procID
	 * @return
	 * @throws IbusException 
	 */
	private String parseInnerBusResElementToXMLStr(XMLParser xmlParser, String procID) throws IbusException {
		// 创建XML文档对象
		Document doc = DocumentHelper.createDocument();
		Element ele_root = doc.addElement("InnerBus");    // 创建root节点
		doc.setRootElement(ele_root);
		// 创建流水号节点
		Element ele_ProcID = ele_root.addElement("ProcID");
		ele_ProcID.addText(StringUtils.isNotBlank(procID)?procID:ProcIDGenerator.generateProcID());
		
		// 创建SvcCont节点
		Element ele_svcCont = ele_root.addElement("SvcCont");
		
		// 设置svcCont节点内容
		xmlParser.parseXML(ele_svcCont);
		StringWriter sw = new StringWriter();
		// 设置xml排版格式：紧凑格式
		XMLWriter writer = new XMLWriter(sw, OutputFormat.createCompactFormat());
		try {
			writer.write(doc);
		} catch (IOException e) {
			throw new IbusException(e);
		}finally{
			if(null!=writer){
				try {
					writer.close();
				} catch (IOException e) {
					// 什么都不需要做
				}
			}
		}
		return sw.toString();
	}

	private String getIbusServUrl(String ibusServ)
	{
		return ConfigUtil.getSysConfigValue("dist.ibus." + ibusServ);
	}
	
	/**
	 * <p>
	 * Ibus回执对象实现类
	 * </p>
	 * @see com.huawei.imp.framework.model.ibus.IbusResponse
	 * @author aohai.li
	 * @version CMSV100R001DB0SP05, 2010-9-16
	 * @since CMSV100R001DB0SP05
	 */
	public static class IbusResoponseImpl implements IbusResponse{

		/**
		 * xml报文
		 */
		private String source;
		
		/**
		 * 报文对象
		 */
		private Document doc;
		
		/**
		 * 节点
		 */
		private Element innerBusEle = null;
		
		/**
		 * 节点
		 */
		private Element responseEle = null;
		
		private String rspCode = null;
		
		private String resDesc = null;
		
		protected IbusResoponseImpl(String source) throws DocumentException{
			this.source = source;
			
			// 初始化
			doc = DocumentHelper.parseText(source);
			innerBusEle = doc.getRootElement();
			responseEle = innerBusEle.element("Response");
			rspCode = responseEle.elementText("RspCode");
			resDesc = responseEle.elementText("RspDesc");
		}
		
		@Override
		public String getRspCode() {
			return rspCode;
		}

		@Override
		public String getRspDesc() {
			return resDesc;
		}

		@Override
		public String getSource() {
			return source;
		}
	}
	
	private void ibusErrorLog(String procID, String transID, String url, String requestXML, long time1, String exceptionMessage){
		if(null != ibusLogService){
			IbusLogerEntity logerEntity = new IbusLogerEntity ();
			logerEntity.setProcID(procID);
			logerEntity.setTransID(transID);
			logerEntity.setUrl(url);
			logerEntity.setMessage1(requestXML);
			logerEntity.setDate1(new Date(time1));
			logerEntity.setDate2(new Date(System.currentTimeMillis()));
			logerEntity.setException(exceptionMessage);
			ibusLogService.addLog(logerEntity);
		}
	}
	
	private void ibusLog(String procID, String transID, String url, String requestXML, String responseXML, long time1, long time2){
		if(null != ibusLogService){
			IbusLogerEntity logerEntity = new IbusLogerEntity ();
			logerEntity.setProcID(procID);
			logerEntity.setTransID(transID);
			logerEntity.setUrl(url);
			logerEntity.setMessage1(requestXML);
			logerEntity.setMessage2(responseXML);
			logerEntity.setDate1(new Date(time1));
			logerEntity.setDate2(new Date(time2));
			ibusLogService.addLog(logerEntity);
		}
	}

	public void setIbusLogService(IbusLogService ibusLogService)
	{
		this.ibusLogService = ibusLogService;
	}
}