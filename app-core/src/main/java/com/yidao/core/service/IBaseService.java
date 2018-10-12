package com.yidao.core.service;





import com.yidao.core.dao.EntityObj;

import java.util.List;
import java.util.Map;


public interface IBaseService{


	public BaseService setClass(Class class1);

	public void remove(Long id)throws Exception;

	public void remove(EntityObj data)throws Exception ;

	public void removeByID(Long id)throws Exception ;
	
	public <T> void remove(Class<T> clazz, Long id) throws Exception;

	public Long save(EntityObj data) throws Exception;

	public void save(Object entity);

	public void update(Object entity);
	
	public <T> T queryById(Class<T> clazz, Object id);
	
	public Page queryDataPageBySQL(String sql, Class clazz, Map<String, String> args, int curPage, int pageSize)
			throws Exception;

	public Page queryDataPageByJPQL(String sql, Map<String, String> args, int curPage, int pageSize)
			throws Exception;
	
	public List queryMapBySQL(final String sql, final Map<String, String> args)throws Exception;

	public List queryBySQL(final String sql, final Map<String, String> args, Class clazz)throws Exception;
}
