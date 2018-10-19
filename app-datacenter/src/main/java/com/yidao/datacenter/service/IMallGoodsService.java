package com.yidao.datacenter.service;

import com.yidao.core.service.IBaseService;
import com.yidao.core.service.Page;
import com.yidao.datacenter.entry.MallPicturesDO;

import java.util.List;
import java.util.Map;

public interface IMallGoodsService extends IBaseService {

    Page queryUserPageList(Map<String, String> where, int currentPage, int pageSize)  throws Exception ;

    List<MallPicturesDO> queryMallPictureList(Map<String, String> where)  throws Exception;

}