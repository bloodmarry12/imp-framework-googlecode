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

import com.huawei.imp.framework.constant.ConstantValue;
import com.huawei.imp.framework.constant.dao.ConstantDAO;
import com.huawei.imp.framework.constant.domain.Constant;
import com.huawei.imp.framework.constant.service.ConstantService;
import com.huawei.imp.framework.utils.PageResult;

/**
 * <一句话功能/>
 * <功能描述/>
 * @author ahli liuguoping
 * @date 2009-8-16
 */
@Service("constantService")
public class ConstantServiceImpl implements ConstantService {

	/**
	 * 日志对象
	 */
	private static final Logger log = Logger.getLogger(ConstantServiceImpl.class);
	
	@Autowired
	@Qualifier("constantDAO")
	private ConstantDAO constantDAO;
	
	/**
	 * 初始化常量数据
	 */
	@PostConstruct
	@Transactional
	public void init(){
		Map<String, Object> params = new HashMap<String, Object>();
		refrushMemery(params);
	}

	@Override
	public PageResult query(Map<String, Object> params, int pageNum,
			int pageSize)
	{
		return constantDAO.query(params, pageNum, pageSize);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.huawei.imp.framework.constant.service.ConstantService#queryConstants(java.util.Map)
	 */
	public List<Constant> queryConstants(Map<String, Object> params)
	{
		return constantDAO.selectByModel(params);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.huawei.imp.framework.constant.dao.ConstantDAO#remove(java.util.Map)
	 */
	@Override
	public void remove(Map<String, Object> params)
	{
		constantDAO.delete(params);
		List<Constant> list = constantDAO.selectByModel(params);
		Map<String,Object> paramM = new HashMap<String,Object>();
		for(Constant c : list)
		{
			paramM.put("modelid", c.getModelID());
			
		}	
		refrushMemery(paramM);
	}

	/*
	 * (non-Javadoc)
	 * @see com.huawei.imp.framework.constant.dao.ConstantDAO#save(com.huawei.imp.framework.constant.domain.Constant)
	 */
	@Override
	public void save(Constant c)
	{
//		c.setId(constantDAO.selectSequence(Constant.class));
		constantDAO.insert(c);
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("modelid", c.getModelID());
		refrushMemery(params);
	}

	/*
	 * (non-Javadoc)
	 * @see com.huawei.imp.framework.constant.dao.ConstantDAO#update(com.huawei.imp.framework.constant.domain.Constant)
	 */	
	@Override
	public void update(Constant c)
	{
		constantDAO.update(c);
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("modelid", c.getModelID());
		refrushMemery(params);
	}
	
	/**
	 * 刷新内存
	 * @param params
	 */
	private void refrushMemery(Map<String,Object> params)
	{
		if(log.isDebugEnabled()){
			log.debug("开始从数据库加载常量数据。");
		}
		List<Constant> list = constantDAO.selectByModel(params);
		// 如果开启了Debug日志，则打印详细的常量加载信息。
		if(log.isDebugEnabled()){
			StringBuffer sb = new StringBuffer(list.size() * 50);
			sb.append(com.huawei.imp.framework.utils.Constant.LINE_SEPARATOR);
			sb.append("加载的常量数据:");
			sb.append(com.huawei.imp.framework.utils.Constant.LINE_SEPARATOR);
			for(Constant con : list){
				sb.append("--[");
				sb.append(con);
				sb.append("]");
				sb.append(com.huawei.imp.framework.utils.Constant.LINE_SEPARATOR);
			}
			log.debug(sb.toString());
		}
		
		log.info("load constant data from db. found " + list.size() + "records.");
		ConstantValue.initValueObjectBeans(list);
	}
}
