package com.yidao.core.service;

import com.yidao.core.dao.DaoTemplate;
import com.yidao.core.dao.EntityObj;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;


public abstract class BaseService implements IBaseService {

	@Autowired
	protected DaoTemplate daoTemplate;

	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	

	public BaseService setClass(Class class1){
		daoTemplate.setClass(class1);
		return this;
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public void remove(Long id)throws Exception {
		daoTemplate.remove(id);
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public void remove(EntityObj data)throws Exception {
		daoTemplate.remove(data);
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public void removeByID(Long id)throws Exception {
		daoTemplate.remove(id);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public Long save(EntityObj data) throws Exception {
		if (data.id == null || data.id.toString().length() < 1)
			data.lastModifiedDate = new Date();
		daoTemplate.save(data);
		return data.id;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void save(Object entity) {
		// TODO Auto-generated method stub
		daoTemplate.save(entity);
	}

	/**
	 * 修改
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void update(Object entity) {
		// TODO Auto-generated method stub
		daoTemplate.update(entity);
	}
	
	/**
	 * 删除
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public <T> void remove(Class<T> clazz, Long id) throws Exception {
		daoTemplate.remove(clazz, id);
		
	}
	
	/**
	 * 查询实体
	 */
	@Override
	@Transactional(readOnly = true)
	public <T> T queryById(Class<T> clazz, Object id) {
		return daoTemplate.queryById(clazz, id);
	}
	
	public Page queryDataPageBySQL(String sql, Class clazz, Map<String, String> args, int curPage, int pageSize)
			throws Exception// 返回视图
	{
		return PageFactory.createPageBySql(daoTemplate, sql,  args, clazz, curPage, pageSize);
	}

	public Page queryDataPageByJPQL(String sql, Map<String, String> args, int curPage, int pageSize)
			throws Exception// 返回视图
	{
		return PageFactory.createPageByJPQL(daoTemplate, sql,  args, curPage, pageSize);
	}
	
	public List queryMapBySQL(final String sql,final Map<String,String> args)throws Exception {
		return daoTemplate.queryMapBySQL(sql, args);
	}
	
	public List queryBySQL(final String sql, final Map<String,String> args,Class clazz)throws Exception {
		return daoTemplate.queryBySQL(sql, args, clazz);
	}
}
