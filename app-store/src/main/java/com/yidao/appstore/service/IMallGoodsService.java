package com.yidao.appstore.service;

import com.yidao.appstore.entry.MallPicturesDO;
import com.yidao.core.service.IBaseService;
import com.yidao.core.service.Page;

import java.util.List;
import java.util.Map;

public interface IMallGoodsService extends IBaseService {

    Page queryUserPageList(Map<String, String> where, int currentPage, int pageSize)  throws Exception ;

    List<MallPicturesDO> queryMallPictureList(Map<String, String> where)  throws Exception;

}