package com.yidao.appstore.service;


import com.yidao.appstore.entry.MallDO;
import com.yidao.core.service.IBaseService;

import java.util.List;

public interface IMallService extends IBaseService {

    List<MallDO> queryMallListByMallUserId(long mallUserId)throws Exception;

}