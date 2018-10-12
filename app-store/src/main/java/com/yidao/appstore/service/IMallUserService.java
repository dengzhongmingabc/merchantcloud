package com.yidao.appstore.service;


import com.yidao.appstore.entry.MallUserDO;
import com.yidao.core.service.IBaseService;

public interface IMallUserService extends IBaseService {

    MallUserDO queryUserByMobile(String mobile)throws Exception;

}