package com.huawei.imp.framework.model.fileupload.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.huawei.imp.framework.model.fileupload.servlet.action.CancelAction;
import com.huawei.imp.framework.model.fileupload.servlet.action.DelAction;
import com.huawei.imp.framework.model.fileupload.servlet.action.ProcessAction;
import com.huawei.imp.framework.model.fileupload.servlet.action.TestAction;
import com.huawei.imp.framework.model.fileupload.servlet.action.UploadAction;
import com.huawei.imp.framework.utils.BeanHolder;

/**
 * Ajax附件上传Servlet请求适配器
 * 通过该适配器，暴露一个servlet服务。
 * @author ahli
 * @version IMPV100R001DA0, 2009-12-10 
 * @see [相关类/方法]
 * @since CMS IMPV100R001DA0
 */
/**
 * @author ahli
 *
 */
public class FileuploadAdapter extends HttpServlet {

	/**
	 * 默认序列化ID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 默认的编码格式
	 */
	private static final String DEFAULT_ENCDING = "UTF-8";
	
	/**
	 * 编码格式
	 */
	private String encoding = DEFAULT_ENCDING;
	
	/**
	 * 存储
	 */
	private final Map<String, ActionSupport> actionMap = new HashMap<String, ActionSupport>();
	
	/* (non-Javadoc)
	 * @see javax.servlet.GenericServlet#init(javax.servlet.ServletConfig)
	 * 初始化，加载配置项目
	 */
	@Override
	public void init(ServletConfig config) throws ServletException {
		
		String encodiong = config.getInitParameter("encoding");
		if(null != encodiong && !encodiong.trim().equals("")){
			this.encoding = encodiong;
		}
		
		actionMap.put("upload", new UploadAction());
		actionMap.put("demo", new TestAction());
		actionMap.put("process", new ProcessAction());
		actionMap.put("cancel", new CancelAction());
		actionMap.put("del", new DelAction());
		super.init(config);
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 * 初始化隐藏的文件上传页面
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding(encoding);
		response.setContentType("text/html; charset=" + encoding);
		final String curURL = request.getRequestURI();
		final String returnURL = getActionSupport(curURL).doGet(request, response);
		if(null != returnURL){
			forward(request, response, returnURL + ".jsp");
		}
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding(encoding);
		response.setContentType("text/html; charset=" + encoding);
		final String curURL = request.getRequestURI();
		final String returnURL = getActionSupport(curURL).doPost(request, response);
		if(null != returnURL){
			forward(request, response, returnURL + ".jsp");
		}
	}
	
	private ActionSupport getActionSupport(String requestURL){
		
		final String currentURL = requestURL.substring(requestURL.indexOf("/", 1),
				requestURL.length());
		final String currentURL2 = currentURL.substring(currentURL.indexOf("/", 1),
				currentURL.length());
		ActionSupport action = actionMap.get(currentURL2.substring(1));
		if(null == action){
			action = new ActionSupport(){

				@Override
				public String doGet(HttpServletRequest request,
						HttpServletResponse response) throws IOException
				{
					response.setContentType("text/html; charset=UTF-8");
					PrintWriter out = response.getWriter();
					out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
					out.println("<HTML>");
					out.println("<HEAD><TITLE>The request action not exist</TITLE></HEAD>");
					out.println("<BODY>");
					out.print(BeanHolder.getMessage("imp.fileupload.requestActionNotExist"));
					out.println("</BODY>");
					out.println("</HTML>");
					out.flush();
					out.close();
					
					return null;
				}

				@Override
				public String doPost(HttpServletRequest request,
						HttpServletResponse response) throws IOException
				{
					return doGet(request, response);
				}
				
			};
		}
		return action;
	}
	
	/**
	 * 跳转到指定页面
	 * @param request
	 * @param response
	 * @param url
	 * @throws ServletException
	 * @throws IOException
	 */
	private void forward(HttpServletRequest request, HttpServletResponse response, String url) throws ServletException, IOException{
		ServletContext sc = getServletContext();
		RequestDispatcher rd = null;
		rd = sc.getRequestDispatcher(url); //定向的页面
		rd.forward(request, response);
	}
}
