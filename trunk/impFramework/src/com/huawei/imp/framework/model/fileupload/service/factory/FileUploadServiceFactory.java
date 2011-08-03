package com.huawei.imp.framework.model.fileupload.service.factory;

import com.huawei.imp.framework.model.fileupload.service.FileUploadService;
import com.huawei.imp.framework.model.fileupload.service.impl.FileUploadServiceImpl;

public class FileUploadServiceFactory {

	private final static FileUploadService service = new FileUploadServiceImpl();
	
	public static FileUploadService getFileUploadService(){
		return service;
	}
}
