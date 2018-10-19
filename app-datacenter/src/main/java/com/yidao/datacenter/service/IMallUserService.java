package com.yidao.datacenter.service;


import com.yidao.core.service.IBaseService;
import com.yidao.datacenter.entry.MallUserDO;

public interface IMallUserService extends IBaseService {

    MallUserDO queryUserByMobile(String mobile)throws Exception;

}