package com.yidao.goods.service;


import com.yidao.core.service.IBaseService;
import com.yidao.goods.entry.MallUserDO;

public interface IMallUserService extends IBaseService {

    MallUserDO queryUserByMobile(String mobile)throws Exception;

}