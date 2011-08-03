/**
 * 
 */
package com.huawei.imp.framework.model.fileupload;

import org.apache.commons.fileupload.ProgressListener;

import com.huawei.imp.framework.model.fileupload.exception.UserInterruptedException;
import com.huawei.imp.framework.model.fileupload.service.ProgressMap;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author ahli
 * @version IMPV100R001DA0, 2009-12-10
 * @see [相关类/方法]
 * @since CMS IMPV100R001DA0
 */

public class IMPProgressListenerImpl implements ProgressListener
{

	/**
	 * 当前线程UUID
	 */
	private final static ThreadLocal<String> localUUID = new ThreadLocal<String>();
	
	/**
	 * 进度缓存容器
	 */
	private final ProgressMap progressMap;

	/**
	 * 默认构造函数
	 * @param progressMap    进度映射对象
	 * @exception IllegalArgumentException progressMap为null时，抛出该异常
	 */
	public IMPProgressListenerImpl(ProgressMap progressMap)
	{
		if(null == progressMap){
			throw new IllegalArgumentException("progressMap can not be null.");
		}
		
		this.progressMap = progressMap;
	}

	/**
	 * 开始新的uuid下载进度缓存
	 * @param uuid    操作UUID
	 */
	protected void start(String uuid){
		localUUID.set(uuid);
		this.progressMap.set(uuid, 0);
	}
	
	/**
	 * 清理当前线程的下载操作数据
	 */
	public void clean(){
		if(null != localUUID.get()){
			this.progressMap.remove(localUUID.get());
			localUUID.set(null);
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.apache.commons.fileupload.ProgressListener#update(long, long,
	 * int)
	 */
	@Override
	public void update(long pBytesRead, long pContentLength, int pItems)
	{
		if(null != localUUID.get()){
			if (pContentLength == -1)
			{
				this.progressMap.set(localUUID.get(), 0);
			}
			else
			{
				boolean flag = this.progressMap.set(localUUID.get(), Long.valueOf(
						pBytesRead * 100L / pContentLength).intValue());
				// 如果没有替换成功，表示外部中断了文件上传，这里抛出异常
				if(!flag){
					throw new UserInterruptedException("uploading interrupted!");
					//throw new BusinessException("文件上传被中断");
				}
			}
		}
	}
}
