package com.yidao.appstore.service;

import com.yidao.appstore.entry.GoodsCategoryDO;
import com.yidao.core.service.IBaseService;

import java.util.List;

public interface IMallGoodsCategoryService extends IBaseService {

    public List<GoodsCategoryDO> queryMallGoodsCategoryListByMallId(long mallId) throws Exception ;

}