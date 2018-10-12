package com.yidao.goods.service.impl;

import com.yidao.core.service.BaseService;
import com.yidao.core.service.Page;
import com.yidao.core.service.PageFactory;
import com.yidao.goods.entry.GoodsDO;
import com.yidao.goods.entry.MallPicturesDO;
import com.yidao.goods.service.IMallGoodsService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@Transactional
public class MallGoodsServiceImpl extends BaseService implements IMallGoodsService {

    public Page queryUserPageList(Map<String,String> where, int currentPage, int pageSize)  throws Exception {
        StringBuffer sbsql = new StringBuffer();
        sbsql.append("select * from t_third_mall_goods where 1=1 and is_delete ");
        Map args = new HashMap();
        if(!StringUtils.isBlank(where.get("goodsName"))){
            sbsql.append(" and  goods_name like :goodsName");
            args.put("goodsName", "%"+where.get("goodsName")+"%");
        }
        if(!StringUtils.isBlank(where.get("groupId"))){
            sbsql.append(" and  id = :groupId");
            args.put("groupId", where.get("groupId"));
        }
        if(!StringUtils.isBlank(where.get("status"))){
            sbsql.append(" and  status = :status");
            args.put("status", where.get("status"));
        }
        if(!StringUtils.isBlank(where.get("storeId"))){
            sbsql.append(" and  store_id = :storeId");
            args.put("storeId", where.get("storeId"));
        }
        sbsql.append(" order by goods_index");
        return PageFactory.createPageBySql(daoTemplate, sbsql.toString(), args,GoodsDO.class, currentPage, pageSize);
    }


    public List<MallPicturesDO> queryMallPictureList(Map<String,String> where)  throws Exception {
        StringBuffer sbsql = new StringBuffer();
        sbsql.append("select * from t_mall_pictures where 1=1 ");
        Map args = new HashMap();
        if(!StringUtils.isBlank(where.get("storeId"))){
            sbsql.append(" and  store_id = :storeId");
            args.put("storeId", where.get("storeId"));
        }
        sbsql.append(" order by id");
        List<MallPicturesDO> ret  = daoTemplate.queryBySQL(sbsql.toString(),args,MallPicturesDO.class);
        if(ret == null || ret.isEmpty())
            return null;
        return ret;
    }
}