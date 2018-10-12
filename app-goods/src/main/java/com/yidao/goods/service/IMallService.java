package com.yidao.goods.service;


import com.yidao.core.service.IBaseService;
import com.yidao.goods.entry.MallDO;

import java.util.List;

public interface IMallService extends IBaseService {

    List<MallDO> queryMallListByMallUserId(long mallUserId)throws Exception;

}