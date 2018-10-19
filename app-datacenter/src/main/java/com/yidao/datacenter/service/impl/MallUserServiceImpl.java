package com.yidao.datacenter.service.impl;

import com.yidao.core.service.BaseService;
import com.yidao.datacenter.entry.MallUserDO;
import com.yidao.datacenter.service.IMallUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@Transactional
public class MallUserServiceImpl extends BaseService implements IMallUserService {

    @Override
    public MallUserDO queryUserByMobile(String mobile) throws Exception{
        Map<String,String> args = new HashMap<String,String>();
        args.put("mobile",mobile);
        List ret  = daoTemplate.queryBySQL("select u.* from t_mall_user u where u.mobile=:mobile",args,MallUserDO.class);
        if(ret == null || ret.isEmpty())
            return null;
        return (MallUserDO)ret.get(0);
    }



}