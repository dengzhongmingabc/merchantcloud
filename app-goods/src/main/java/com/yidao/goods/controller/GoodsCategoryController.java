package com.yidao.goods.controller;

import com.yidao.core.utils.AppConst;
import com.yidao.core.utils.JSONHelper;
import com.yidao.core.web.BaseController;
import com.yidao.goods.entry.GoodsCategoryDO;
import com.yidao.goods.entry.MallUserDO;
import com.yidao.goods.formbean.GoodsCategoryFormBean;
import com.yidao.goods.service.ICommonService;
import com.yidao.goods.service.IMallGoodsCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(description = "商品组相关api")
@RestController
@RequestMapping("/api/goodsCategory")
public class GoodsCategoryController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ICommonService commonService;

    @Autowired
    private IMallGoodsCategoryService mallGoodsCategoryServiceImpl;

    @ApiOperation(value="商品组添加")
    @ResponseBody
    @RequestMapping(value = "/saveMallGoodsCategory",method = RequestMethod.POST)
    public void saveMallGoodsCategory(@RequestBody @Valid GoodsCategoryFormBean goodsCategoryFormBean, BindingResult bindingResult){
        try {
            if(bindingResult.hasErrors()){
                JSONHelper.returnInfo(JSONHelper.returnServerErrJsonString(bindingResult.getFieldError().getDefaultMessage()));
                return;
            }
            MallUserDO mallUser = (MallUserDO) this.getSession(AppConst.session_key);
            GoodsCategoryDO goodsCategory = new GoodsCategoryDO();
            goodsCategory.setMallId(mallUser.getCurrentMall().getId());
            goodsCategory.setCateName(goodsCategoryFormBean.getCateName());
            commonService.save(goodsCategory);
            JSONHelper.returnInfo(JSONHelper.returnServerSuccessJsonString());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
            JSONHelper.returnInfo(JSONHelper.returnServerErrJsonString());
            return;
        }
    }

    @ApiOperation(value="商品组修改")
    @ResponseBody
    @RequestMapping(value = "/updateMallGoodsCategory",method = RequestMethod.POST)
    public void updateMallGoodsCategory(@RequestBody @Valid GoodsCategoryFormBean goodsCategoryFormBean, BindingResult bindingResult, long id){
        try {
            if(bindingResult.hasErrors()){
                JSONHelper.returnInfo(JSONHelper.returnServerErrJsonString(bindingResult.getFieldError().getDefaultMessage()));
                return;
            }
            GoodsCategoryDO goodsCategory = commonService.queryById(GoodsCategoryDO.class,id);
            goodsCategory.setCateName(goodsCategoryFormBean.getCateName());
            commonService.save(goodsCategory);
            JSONHelper.returnInfo(JSONHelper.returnServerSuccessJsonString());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
            JSONHelper.returnInfo(JSONHelper.returnServerErrJsonString());
            return;
        }
    }

    @ApiOperation(value="商品组删除")
    @ResponseBody
    @RequestMapping(value = "/removeMallGoodsCategory",method = RequestMethod.POST)
    public void removeMallGoodsCategory(long id){
        try {
            GoodsCategoryDO goodsCategory = commonService.queryById(GoodsCategoryDO.class,id);
            goodsCategory.setInvalid(false);
            commonService.save(goodsCategory);
            JSONHelper.returnInfo(JSONHelper.returnServerSuccessJsonString());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
            JSONHelper.returnInfo(JSONHelper.returnServerErrJsonString());
            return;
        }
    }

    @ApiOperation(value="商品组列表")
    @ResponseBody
    @RequestMapping(value = "/queryMallGoodsCategoryList",method = RequestMethod.POST)
    public void queryMallGoodsCategoryList(){
        try {
            List<Map> retlist = new ArrayList<Map>();
            MallUserDO mallUser = (MallUserDO) this.getSession(AppConst.session_key);
            List<GoodsCategoryDO> list = mallGoodsCategoryServiceImpl.queryMallGoodsCategoryListByMallId(mallUser.getCurrentMall().getId());
            for(GoodsCategoryDO obj:list) {
                Map map = new HashMap();
                map.put("id", obj.getId());
                map.put("status", obj.getStatus());
                map.put("cateName", obj.getCateName());
                retlist.add(map);
            }
            JSONHelper.returnInfo(JSONHelper.returnServerSuccessJsonString(retlist));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
            JSONHelper.returnInfo(JSONHelper.returnServerErrJsonString());
            return;
        }
    }
}
