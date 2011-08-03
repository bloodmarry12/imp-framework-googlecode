package com.huawei.imp.framework.model.fileupload;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.RequestContext;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;

import com.huawei.imp.framework.jee.Path;
import com.huawei.imp.framework.logger.LogFactory;
import com.huawei.imp.framework.logger.Logger;
import com.huawei.imp.framework.model.fileupload.domain.TempFileBean;
import com.huawei.imp.framework.model.fileupload.exception.UserInterruptedException;
import com.huawei.imp.framework.model.fileupload.service.ProgressMap;
import com.huawei.imp.framework.utils.BeanHolder;
import com.huawei.imp.framework.utils.StreamToString;

/**
 * IMPservlet文件上传处理类
 * @author ahli
 *
 */
public class IMPServletFileUpload extends ServletFileUpload {
	
	private static final Logger log = LogFactory.getLogger(IMPServletFileUpload.class);
	
	/**
	 * 文件上传表单，uuid属性名称
	 */
	public static final String FIELD_NAME_UUID = "uuid";

	/**
	 * 文件上传表单，file属性名称
	 */
	public static final String FIELD_NAME_FILE = "fileObj";
	
	/**
	 * 进度处理器
	 */
	private final ProgressMap progressMap;
	
	/**
	 * 构造函数
	 * @param fileItemFactory         文件工厂对象
	 * @param progressMap             文件上传进度缓存映射
	 * @param progressListenerImpl    文件上传进度监听器
	 */
	public IMPServletFileUpload(FileItemFactory fileItemFactory, ProgressMap progressMap, IMPProgressListenerImpl progressListenerImpl) {
		super(fileItemFactory);
		this.progressMap = progressMap;
		this.setProgressListener(progressListenerImpl);
	}

	/**
	 * 处理文件上传业务方法
	 * @param ctx    请求上下文
	 * @return
	 * @throws FileUploadException    文件上传异常
	 */
	public TempFileBean parseRequestForIMPFileupload(RequestContext ctx)throws FileUploadException{
		try {
			FileItemIterator iter = getItemIterator(ctx);
			FileItemFactory fac = getFileItemFactory();
			if (fac == null) {
				throw new NullPointerException(
						"No FileItemFactory has been set.");
			}
			TempFileBean tfb = new TempFileBean();
			FileItemStream uuidItem = null;
			FileItemStream fileItem = null;
			if(iter.hasNext()){
				uuidItem = iter.next();
				if (!uuidItem.isFormField()
						|| !FIELD_NAME_UUID.equals(uuidItem.getFieldName())) {
					throw new IllegalArgumentException(BeanHolder.getMessage("imp.fileupload.firstParaError"));
				}
				tfb.setUuid(StreamToString.convertStreamToString(uuidItem.openStream()));
				getProgressListenerImpl().start(tfb.getUuid());
				this.progressMap.set(tfb.getUuid(), 0);
			}else{
				throw new IllegalArgumentException(BeanHolder.getMessage("imp.fileupload.noInputBox"));
			}
			
			if(iter.hasNext()){
				fileItem = iter.next();
				if (fileItem.isFormField()
						|| !FIELD_NAME_FILE.equals(fileItem.getFieldName())) {
					throw new IllegalArgumentException(BeanHolder.getMessage("imp.fileupload.secondParaError"));
				}
				
				String fileName = fileItem.getName();
				
				fileName = fileName.substring(fileName.lastIndexOf("\\") + 1, fileName.length());
//				log.info("upload file name = " + fileName);
//				String encodeTypeName = EncodingDetectUtils.getInstance().detectEncoding(fileName.getBytes()).name();
//				log.info("encodeTypeName=" + encodeTypeName);
				tfb.setName(fileName);    // 设置文件名
				// 将文件上传本地
				File file = createTempFile(tfb.getName(), tfb.getUuid());
				if(!file.createNewFile()){
					log.error("file.createNewFile() return false.");
				}
				
				try {
					long size = Streams.copy(fileItem.openStream(), new FileOutputStream(file), true);
					tfb.setPath(file.getPath());
					tfb.setSize(size);
				}catch(UserInterruptedException e)
				{
					//用户中断上传、删除文件
					if(!file.delete()){
						log.error("file.delete() return false.");
					}
					throw e;
				}
				catch (FileUploadIOException e) {
					throw (FileUploadException) e.getCause();
				} catch (IOException e) {
					throw new IOFileUploadException("Processing of "
							+ MULTIPART_FORM_DATA + " request failed. "
							+ e.getMessage(), e);
				}
			}else{
				throw new IllegalArgumentException(BeanHolder.getMessage("imp.fileupload.firstParaError"));
			}
			
			return tfb;
		} catch (IOException e) {
			throw new FileUploadException(e.getMessage(), e);
		}
	}
	
	/**
	 * 创建临时文件
	 * @param fileName
	 * @param uuid
	 * @return
	 */
	private File createTempFile(String fileName, String uuid){
		String configPath = "fileupload/temp";
		String filePath;
		if (null != configPath && !configPath.startsWith("/")) {
			configPath = "/" + configPath;
		}
		filePath = Path.getWebRootPath() + configPath;

		final String _fileName = uuid + "_" + fileName;
		// 获取当前日期
		Calendar cal = Calendar.getInstance();
		filePath = filePath.concat("/");
		filePath = filePath.concat(String.valueOf(cal.get(Calendar.YEAR)));
		filePath = filePath.concat(String.valueOf("-"));
		filePath = filePath.concat(String.valueOf(cal.get(Calendar.MONTH)));
		filePath = filePath.concat(String.valueOf("-"));
		filePath = filePath.concat(String.valueOf(cal.get(Calendar.DAY_OF_MONTH)));
		filePath = filePath.concat("/");

		if(new File(filePath).mkdirs()){
			log.error("new File(filePath).mkdirs() return false.");
		}

		filePath = filePath.concat(_fileName);
		return new File(filePath);
	}
	
	private IMPProgressListenerImpl getProgressListenerImpl(){
		return (IMPProgressListenerImpl)super.getProgressListener();
	}
	
}
