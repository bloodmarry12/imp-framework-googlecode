package com.huawei.imp.framework.model.fileupload.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.huawei.imp.framework.model.fileupload.domain.TempFileBean;
import com.huawei.imp.framework.model.fileupload.service.FileUploadService;
import com.huawei.imp.framework.utils.BeanHolder;

/**
 * Ajax附件上传Servlet
 * 
 * @author ahli
 * @version IMPV100R001DA0, 2009-12-10 
 * @see [相关类/方法]
 * @since CMS IMPV100R001DA0
 */
public class Fileupload extends HttpServlet {

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
	 * 
	 */
	private static final String PAGE = "/WEB-INF/jsp/framework/jsp/model/fileupload/upload.jsp";

	/**
	 * 文件上传处理类 
	 */
	private FileUploadService fileUploadService = null;
	
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
		
		super.init(config);
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 * 初始化隐藏的文件上传页面
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=" + encoding);
		forward(request, response, PAGE);
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=" + encoding);
		try{
			TempFileBean tempFileBean = getFileUploadService().uploadTmpFile(request);
			if(null != tempFileBean)
			{
				request.setAttribute("message", "1");
				request.setAttribute("uuid", tempFileBean.getUuid());
				request.setAttribute("filePath", tempFileBean.getPath());
				request.setAttribute("fileName", tempFileBean.getName());
			}
			else
			{
				request.setAttribute("message", "0");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		forward(request, response, PAGE);
	}
	
	private FileUploadService getFileUploadService(){
		synchronized (this)
		{
			if(null == fileUploadService){
				fileUploadService = (FileUploadService)BeanHolder.getBean("fileUploadService");
			}
			return fileUploadService;
		}
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
