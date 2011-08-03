/**
 * 
 */
package com.huawei.imp.framework.constant.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.huawei.imp.framework.constant.dao.ConstantTypeDAO;
import com.huawei.imp.framework.constant.domain.ConstantType;
import com.huawei.imp.framework.constant.service.ConstantTypeService;
import com.huawei.imp.framework.constant.util.ConstantTypeUtil;

/**
 * <一句话功能/>
 * <功能描述/>
 * @author liuguoping
 * @date 2009-8-16
 */
@Service("constantTypeService")
public class ConstantTypeServiceImpl implements ConstantTypeService {

	/**
	 * 日志对象
	 */
	private static final Logger log = Logger.getLogger(ConstantTypeServiceImpl.class);
	
	@Autowired
	@Qualifier("constantTypeDAO")
	private ConstantTypeDAO constantTypeDAO;
	
	/**
	 * 初始化常量数据
	 */
	@PostConstruct
	@Transactional
	public void init(){
		if(log.isDebugEnabled()){
			log.debug("开始从数据库加载常量数据。");
		}
		List<ConstantType> list = constantTypeDAO.selectAll(ConstantType.class);
		
		// 如果开启了Debug日志，则打印详细的常量加载信息。
		if(log.isDebugEnabled()){
			StringBuffer sb = new StringBuffer(list.size() * 50);
			sb.append(com.huawei.imp.framework.utils.Constant.LINE_SEPARATOR);
			sb.append("加载的常量数据:");
			sb.append(com.huawei.imp.framework.utils.Constant.LINE_SEPARATOR);
			for(ConstantType con : list){
				sb.append("--[");
				sb.append(con);
				sb.append("]");
				sb.append(com.huawei.imp.framework.utils.Constant.LINE_SEPARATOR);
			}
			log.debug(sb.toString());
		}
		
		log.info("load constant data from db. found " + list.size() + "records.");
		ConstantTypeUtil.initConstantType(list);
	}

	/*
	 * (non-Javadoc)
	 * @see com.huawei.imp.framework.constant.service.ConstantTypeService#query(java.util.Map, int, int)
	 */
	@Override
	public List<ConstantType> query()
	{		
		return constantTypeDAO.queryConstantType();
	}

	/*
	 * (non-Javadoc)
	 * @see com.huawei.imp.framework.constant.service.ConstantTypeService#refreshConstantType(com.huawei.imp.framework.constant.domain.ConstantType)
	 */
	@Override
	public void refreshConstantType(ConstantType ct)
	{
		constantTypeDAO.updateConstantType(ct);
	}

	/*
	 * (non-Javadoc)
	 * @see com.huawei.imp.framework.constant.service.ConstantTypeService#removeConstantType(java.lang.Integer[])
	 */
	@Override
	public void removeConstantType(Integer[] modelIds)
	{
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("modelids", modelIds);
		constantTypeDAO.deleteConstantType(params);
		init();
	}

	/*
	 * (non-Javadoc)
	 * @see com.huawei.imp.framework.constant.service.ConstantTypeService#saveConstantType(com.huawei.imp.framework.constant.domain.ConstantType)
	 */
	@Override
	public void saveConstantType(ConstantType ct)
	{
		constantTypeDAO.insertConstantType(ct);
		init();
	}
}
