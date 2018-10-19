package com.yidao.datacenter.service.impl;

import com.yidao.core.service.BaseService;
import com.yidao.datacenter.entry.MallDO;
import com.yidao.datacenter.service.IMallService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@Transactional
public class MallServiceImpl extends BaseService implements IMallService {

    @Override
    public List<MallDO> queryMallListByMallUserId(long mallUserId) throws Exception {
        Map args = new HashMap();
        args.put("mallUserId",mallUserId);
        List ret  = daoTemplate.queryBySQL("select mall.* from t_third_mall mall where mall.mall_user_id=:mallUserId",args,MallDO.class);
        if(ret == null || ret.isEmpty())
            return null;
        return ret;
    }
}