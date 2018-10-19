package com.yidao.datacenter.service;


import com.yidao.core.service.IBaseService;
import com.yidao.datacenter.entry.MallDO;

import java.util.List;

public interface IMallService extends IBaseService {

    List<MallDO> queryMallListByMallUserId(long mallUserId)throws Exception;

}