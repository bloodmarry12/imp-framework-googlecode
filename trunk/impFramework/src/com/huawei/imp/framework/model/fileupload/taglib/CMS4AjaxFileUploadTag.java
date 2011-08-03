package com.huawei.imp.framework.model.fileupload.taglib;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.huawei.imp.framework.model.fileupload.domain.FileBean;

/**
 * AJAX方式文件上传标签、首先将文件上传到web服务器、再上传至FTP
 * 
 * @author issuser
 */
public class CMS4AjaxFileUploadTag extends TagSupport
{

	/**
	 * uid
	 */
	private static final long serialVersionUID = 1L;

	// 上传文件类型
	private String fileType;

	// 是否必须上传
	private boolean isNeed;

	// 在controller层request对象根据该名称来取得上传文件名称
	private String reqParaName;
	
	// 是否是页面表单提交失败
	private boolean submitError;
	
	//用来存放上传文件、以表单中文件上传组件中reqParaName为key、对应FileBean为value；作用是当提交失败时返回页面任然能正确显示上次提交文件
	@SuppressWarnings("unchecked")
	private Map fileMap;
	

	public int doEndTag() throws JspException
	{
		if(null == fileType || null == reqParaName)
		{
			return EVAL_BODY_INCLUDE;
		}
		
		StringBuffer sb = new StringBuffer(300);
		
		/**
		 * 表单提交失败、取出提交时的文件上传内容
		 */
		if(submitError && null != fileMap && !fileMap.isEmpty())
		{
			FileBean fileBean = (FileBean)fileMap.get(reqParaName);
			if(null != fileBean)
			{
				String filePath = fileBean.getFilePath();
				String realFileName = fileBean.getRealFileName();
				createHTML(sb,filePath,realFileName);
			}
			else
			{
				createHTML(sb,null,null);
			}
		}
		else
		{
			createHTML(sb,null,null);
		}
		
		JspWriter out = pageContext.getOut();
		
		try
		{
			out.print(sb.toString());
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return EVAL_PAGE;
		
	}
	
	/**
	 * 生成要打印到页面的HTML
	 * @param sb 
	 * @param submitFail 是否是提交失败
	 */
	private void createHTML(StringBuffer sb,String filePath,String realFileName)
	{
		String uuid = UUID.randomUUID().toString().replace("-","");
		
		sb.append("<div id=\"" + uuid + "\" class=\"ajaxFileUploader\">")
		  .append("<input type=\"hidden\" class=\"upload_hidden\" name=\"" + reqParaName + "\" value=\"");  
		if(filePath != null)
		{
			sb.append(filePath);
		}
		sb.append("\"/>").append("<div class=\"upload_word\" >");
		
		String contextPath = ((HttpServletRequest)pageContext.getRequest()).getContextPath();
		sb.append("<img src=\"" + contextPath  + "/framework/css/images/icon1.gif\" />");
		// 必须上传文件
		if(isNeed)
		{
			sb.append("<font color=\"red\">*</font>");
		}
		sb.append("<font>("+ fileType+ ")</font>")
		  .append("<span class=\"upload_title\">");
		if(null != realFileName)
		{
			sb.append(realFileName);
		}
		sb.append("</span></div>");
		sb.append("</div>");
	}
	

	public String getFileType()
	{
		return fileType;
	}

	public void setFileType(String fileType)
	{
		this.fileType = fileType;
	}

	public boolean isNeed()
	{
		return isNeed;
	}

	public void setNeed(boolean isNeed)
	{
		this.isNeed = isNeed;
	}

	public String getReqParaName()
	{
		return reqParaName;
	}

	public void setReqParaName(String reqParaName)
	{
		this.reqParaName = reqParaName;
	}

	public boolean isSubmitError()
	{
		return submitError;
	}

	public void setSubmitError(boolean submitError)
	{
		this.submitError = submitError;
	}

	@SuppressWarnings("unchecked")
	public Map getFileMap()
	{
		return fileMap;
	}

	@SuppressWarnings("unchecked")
	public void setFileMap(Map fileMap)
	{
		this.fileMap = fileMap;
	}
}
