package com.yidao.goods.service;

import com.yidao.core.service.IBaseService;
import com.yidao.goods.entry.GoodsCategoryDO;

import java.util.List;

public interface IMallGoodsCategoryService extends IBaseService {

    public List<GoodsCategoryDO> queryMallGoodsCategoryListByMallId(long mallId) throws Exception ;

}