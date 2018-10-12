package com.yidao.goods.service.impl;

import com.yidao.core.dao.DaoTemplate;
import com.yidao.core.service.BaseService;
import com.yidao.goods.entry.SysUserDO;
import com.yidao.goods.service.ICommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@Transactional
public class CommonServiceImpl extends BaseService implements ICommonService {

    @Override
    public SysUserDO queryAgencyByAgencyNo(String agencyNo) throws Exception{
        Map<String,String> args = new HashMap<String,String>();
        args.put("agencyNo",agencyNo);
        List ret  = daoTemplate.queryBySQL("select u.* from sys_user u where u.user_name=:agencyNo",args,SysUserDO.class);
        if(ret == null || ret.isEmpty())
            return null;
        return (SysUserDO)ret.get(0);
    }


}