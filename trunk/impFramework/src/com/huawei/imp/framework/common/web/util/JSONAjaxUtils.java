/**
 * 
 */
package com.huawei.imp.framework.common.web.util;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.huawei.imp.framework.utils.PageResult;

/**
 * 基于JSON的AJAX工具类
 * JSON对象格式如下：<br/>
 * 正常操作<br/>
 * {'resCode':'1', <br/>
 * &nbsp;'resContent':{<br/>
 * &nbsp;&nbsp;'property1':'value1', <br/>
 * &nbsp;&nbsp;'property2':'value2'<br/>
 * &nbsp;}</br>
 * }</br>
 * 
 * 异常操作<br/>
 * {'resCode':'0', <br/>
 * &nbsp;'exception':'用例异常信息'<br/>
 * }</br>
 * 
 * 分页查询<br/>
 * @author ahli
 */
public class JSONAjaxUtils {

	/**
	 * JSON类型处理，返回页面地址参数，
	 * 用于Controller方法返回
	 */
	public static final String RETURN_PAGE = "json";
	
	/**
	 * JSON属性
	 */
	public static final String MODEL_ATTRIBUTE = "json";
	
	/**
	 * JSON对象属性：返回编码
	 */
	public static final String JOBJ_PROPERTY_RESCODE = "resCode";
	
	/**
	 * JSON对象属性：返回内容
	 */
	public static final String JOBJ_PROPERTY_RESCONTENT = "resContent";
	
	/**
	 * JSON对象属性：异常
	 */
	public static final String JOBJ_PROPERTY_EXCEPTION = "exception";
	
	/**
	 * @author ahli
	 *
	 */
	public static enum ResCode{
		SUCCESS("1"), 
		FAILS("0");
		
		/**
		 * 返回码值
		 */
		private String codeValue;

		/**
		 * 私有构造函数，初始化返回码
		 * @param codeValue
		 */
		private ResCode(String codeValue){
			this.codeValue = codeValue;
		}
		
		/**
		 * 获取返回码
		 * @return
		 */
		public String getCodeValue() {
			return codeValue;
		}

	}
	
	/**
	 * 返回一个异常提示的JSONObject；<br/>
	 * 页面将直接打印该异常信息；
	 * @param errorMessage    异常、错误消息
	 * @return JSONObject对象
	 */
	public static JSONObject error(String errorMessage){
		JSONObject jobj = createJSONObject();
		jobj.put(JOBJ_PROPERTY_RESCODE, ResCode.FAILS.getCodeValue());
		jobj.put(JOBJ_PROPERTY_EXCEPTION, errorMessage);
		return jobj;
	}
	
	/**
	 * 包装对象内容
	 * @param resContentSupport    内容
	 * @return
	 */
	public static JSONObject content(ResContentSupport resContentSupport){
		JSONObject jobj = createJSONObject();
		jobj.put(JOBJ_PROPERTY_RESCODE, ResCode.SUCCESS.getCodeValue());
		JSONObject resContent = new JSONObject();
		if(null != resContentSupport){
			resContentSupport.warpResContent(resContent);
		}
		jobj.put(JOBJ_PROPERTY_RESCONTENT, resContent);
		return jobj;
	}
	
	/**
	 * 包装分页对象
	 * @param <T>                  分页记录对象类型
	 * @param pr                   分页对象
	 * @param pageResultSupport    分页行数据包装接口
	 * @return    分页的JSONObject对象
	 */
	public static <T> JSONObject pageResult(final PageResult pr, final PageResultSupport<T> pageResultSupport){
		return content(new ResContentSupport(){

			@SuppressWarnings("unchecked")
			@Override
			public void warpResContent(JSONObject resContent) {
				resContent.put("currentPageNo", pr.getCurrentPageNo());
				resContent.put("currentPageSize", pr.getCurrentPageSize());
				resContent.put("hasNextPage", pr.getHasNextPage());
				resContent.put("hasPreviousPage", pr.getHasPreviousPage());
				resContent.put("isFirstPage", pr.getIsFirstPage());
				resContent.put("isLastPage", pr.getIsLastPage());
				resContent.put("nextPageNo", pr.getNextPageNo());
				resContent.put("pageSize", pr.getPageSize());
				resContent.put("previousPageNo", pr.getPreviousPageNo());
				resContent.put("startRow", pr.getStartRow());
				resContent.put("totalPageCount", pr.getTotalPageCount());
				resContent.put("totalSize", pr.getTotalSize());
				JSONArray resultList = new JSONArray();
				if(null != pageResultSupport && pr.getPageSize() > 0){
					List<Object> list = pr.getResultList();
					for(Object obj : list){
						resultList.add(pageResultSupport.warpRecord((T)obj));
					}
				}
				resContent.put("resultList", resultList);
			}
		});
	}
	
	/**
	 * 创建一个JSONObject实例
	 * @return
	 */
	private static JSONObject createJSONObject(){
		return new JSONObject();
	}
	
	/**
	 * 返回内容包装接口
	 * 根据业务需求，直接对resContent对象节点进行操作
	 * @author ahli
	 */
	public static interface ResContentSupport{
		public void warpResContent(JSONObject resContent);
	}
	
	/**
	 * 分页数据包装接口
	 * 包装分页节点
	 * @author ahli
	 */
	public static interface PageResultSupport<T>{
		public JSONObject warpRecord(T record);
	}
}
