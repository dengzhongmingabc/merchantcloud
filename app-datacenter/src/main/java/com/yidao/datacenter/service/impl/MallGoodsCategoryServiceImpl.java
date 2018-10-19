package com.yidao.datacenter.service.impl;

import com.yidao.core.service.BaseService;
import com.yidao.datacenter.entry.GoodsCategoryDO;
import com.yidao.datacenter.service.IMallGoodsCategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@Transactional
public class MallGoodsCategoryServiceImpl extends BaseService implements IMallGoodsCategoryService {

    @Override
    public List<GoodsCategoryDO> queryMallGoodsCategoryListByMallId(long mallId) throws Exception {
        Map<String,String> args = new HashMap<String,String>();
        args.put("mallId",mallId+"");
        List ret  = daoTemplate.queryBySQL("select category.* from t_goods_category category where category.invalid and category.mallId=:mallId",args,GoodsCategoryDO.class);
        if(ret == null || ret.isEmpty())
            return null;
        return ret;
    }
}