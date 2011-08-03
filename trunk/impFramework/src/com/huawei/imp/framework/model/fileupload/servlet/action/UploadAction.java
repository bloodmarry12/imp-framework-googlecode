/**
 * 
 */
package com.huawei.imp.framework.model.fileupload.servlet.action;

import java.util.regex.Matcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.huawei.imp.framework.model.fileupload.domain.TempFileBean;
import com.huawei.imp.framework.model.fileupload.service.factory.FileUploadServiceFactory;
import com.huawei.imp.framework.model.fileupload.servlet.ActionSupport;

/**
 * @author ahli
 *
 */
public class UploadAction implements ActionSupport {

//	private static final Logger log = LogFactory.getLogger(FileUploadAction.class);
	
	private static final String PAGE = "/WEB-INF/jsp/framework/jsp/model/fileupload/upload";
	
	/* (non-Javadoc)
	 * @see com.huawei.imp.framework.model.fileupload.servlet.ActionSupport#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String doGet(HttpServletRequest request, HttpServletResponse response) {
		return PAGE;
	}

	/* (non-Javadoc)
	 * @see com.huawei.imp.framework.model.fileupload.servlet.ActionSupport#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String doPost(HttpServletRequest request, HttpServletResponse response) {
		
		try{
			TempFileBean tempFileBean = FileUploadServiceFactory.getFileUploadService().uploadTmpFile(request);
			if(null != tempFileBean)
			{
				request.setAttribute("message", "1");
				request.setAttribute("uuid", tempFileBean.getUuid());
				String filePath = tempFileBean.getPath();
				filePath = Matcher.quoteReplacement(filePath);
				request.setAttribute("filePath",filePath);
				request.setAttribute("fileName", tempFileBean.getName());
			}
			else
			{
				request.setAttribute("message", "0");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return PAGE;
	}
}
