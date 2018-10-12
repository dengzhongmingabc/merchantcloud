package com.yidao.core.service;


import com.yidao.core.dao.DaoTemplate;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;


@SuppressWarnings("unchecked")
@Component
@ServletComponentScan
public class PageFactory {

	private static  String pagefix="offset";

	public static Page createPageByJPQL(DaoTemplate dao, String jpql, Map<String,String> parameter, int currentPage, int pageSize)throws Exception {
		jpql = jpql.split(" order ")[0];
		int totalCount = dao.queryJPQLTotalCnt(jpql, parameter);
		
		int startIndex = getStartIndex(currentPage, pageSize,totalCount);
		List results = dao.queryByJPQL(jpql, parameter, startIndex, pageSize);
		return createPage(currentPage, pageSize, totalCount, results);
	}


	public static Page createPageByJPQL(DaoTemplate dao, String jpql, int currentPage, int pageSize)throws Exception {
		jpql = jpql.split(" order ")[0];
		int totalCount = dao.queryJPQLTotalCnt(jpql,null);
		int startIndex = getStartIndex(currentPage, pageSize,totalCount);
		List results = dao.queryByJPQL(jpql, null, startIndex, pageSize);
		return createPage(currentPage, pageSize, totalCount, results);
	}
	
	public static Page createMapPageBySql(DaoTemplate dao, String sql_,Map<String,String> args, int currentPage, int pageSize)throws Exception {
		String sql = sql_.split(" order ")[0];
		int selectindex = sql.indexOf("select");
		int fromIndex = sql.indexOf("from");
		String sqlselect = sql.substring(0, selectindex + 6);
		String sqlFrom = sql.substring(fromIndex);
		sql = sqlselect + " count(*) " + sqlFrom;
		int totalCount = dao.querySQLTotalCnt(sql,args);
		
		int startIndex = getStartIndex(currentPage, pageSize,totalCount);
		
		String listsql = sql_+" limit "+pageSize+" offset "+startIndex;
		List results = dao.queryMapBySQL(listsql,args);
		return createPage(currentPage, pageSize, totalCount, results);
	}
	
	public static Page createMapPageBySql2(DaoTemplate dao, String sql_,Map<String,String> args, int currentPage, int pageSize)throws Exception {
		StringBuffer sqlcount = new StringBuffer();
		sqlcount.append("select count(*) from (").append(sql_).append(") t");
		int totalCount = dao.querySQLTotalCnt(sqlcount.toString(),args);
		int startIndex = getStartIndex(currentPage, pageSize,totalCount);
		String listsql = sql_+" limit "+pageSize+" offset "+startIndex;
		List results = dao.queryMapBySQL(listsql,args);
		return createPage(currentPage, pageSize, totalCount, results);
	}

	public static Page createPageBySql(DaoTemplate dao, String sql_,Map<String,String> args, Class clazz, int currentPage, int pageSize)throws Exception {
		String sql = sql_.split(" order ")[0];
		int selectindex = sql.indexOf("select");
		int fromIndex = sql.indexOf("from");
		String sqlselect = sql.substring(0, selectindex + 6);
		String sqlFrom = sql.substring(fromIndex);
		sql = sqlselect + " count(*) " + sqlFrom;
		int totalCount = dao.querySQLTotalCnt(sql,args);
		
		int startIndex = getStartIndex(currentPage, pageSize,totalCount);
		
		String listsql = sql_+" limit "+pageSize+" offset "+startIndex;
		List results = dao.queryBySQL(listsql,args,clazz);
		return createPage(currentPage, pageSize, totalCount, results);
	}



	public static Page createQueryPage(DaoTemplate dao, String hql, String rowhql, Map<String,String> parameter, int currentPage, int pageSize)throws Exception {
		int totalCount = dao.queryJPQLTotalCnt(rowhql, parameter);
		
		int startIndex = getStartIndex(currentPage, pageSize,totalCount);
		List results = dao.queryByJPQL(hql, parameter, startIndex, pageSize);
		

		return createPage(currentPage, pageSize, totalCount, results);
	}

	public static Page createQueryPage(DaoTemplate dao, String hql, String rowhql, int currentPage, int pageSize)throws Exception {
		int totalCount = dao.queryJPQLTotalCnt(rowhql, null);
		int startIndex = getStartIndex(currentPage, pageSize,totalCount);
		List results = dao.queryByJPQL(hql, null, startIndex, pageSize);
		

		return createPage(currentPage, pageSize, totalCount, results);
	}

	public static int getStartIndex(int currentPage, int pageSize,int total)throws Exception {
		
		int pageCount=0;	
	    pageCount=total/pageSize;
	    if((total%pageSize)>0)
	       pageCount+=1;
	    
		int cPageNo = currentPage;
	     int totals = pageCount;
	     if(cPageNo>totals)
	        cPageNo = totals; 
	     else if(cPageNo<=0)
	     	cPageNo = 1;
	     int index =  (cPageNo-1)*pageSize;
	     if(index<0)
	    	 index = 0;
	     return index;
		
/*		if (currentPage < 1)
			currentPage = 1;
		return (currentPage - 1) * pageSize;*/
	}



	public static Page createPage(int currentPage, int pageSize, int totalCount, List results)throws Exception {
		return new Page(currentPage, pageSize, totalCount, results);
	}
}
