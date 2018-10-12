package com.yidao.appstore.controller;

import com.yidao.appstore.entry.GoodsCategoryDO;
import com.yidao.appstore.entry.GoodsDO;
import com.yidao.appstore.entry.MallPicturesDO;
import com.yidao.appstore.entry.MallUserDO;
import com.yidao.appstore.formbean.GoodsFormBean;
import com.yidao.appstore.formbean.IdIndexFormBean;
import com.yidao.appstore.service.ICommonService;
import com.yidao.appstore.service.IMallGoodsCategoryService;
import com.yidao.appstore.service.IMallGoodsService;
import com.yidao.core.service.Page;
import com.yidao.core.utils.AppConst;
import com.yidao.core.utils.JSONHelper;
import com.yidao.core.web.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(description = "商品相关api")
@RestController
@RequestMapping("/api/goods")
public class GoodsController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ICommonService commonService;

    @Autowired
    private IMallGoodsService mallGoodsServiceImpl;

    @Autowired
    private IMallGoodsCategoryService mallGoodsCategoryServiceImpl;

    @ApiOperation(value="商品分页")
    @ResponseBody
    @RequestMapping(value = "/queryMallGoodsPageList",method = RequestMethod.POST)
    public void queryMallGoodsPageList(long groupId,Short status,String goodsName, int currentPage) {
        try {
            Map args = new HashMap<String,String>();
            MallUserDO mallUser = (MallUserDO) this.getSession(AppConst.session_key);
            /*args.put("storeId",mallUser.getCurrentMall().id);
            args.put("groupId",groupId);*/
            if(status!=null){
                args.put("status",status);
            }
            if(!StringUtils.isBlank(goodsName)){
                args.put("goodsName",goodsName);
            }
            //加入代理区分
            Page page = mallGoodsServiceImpl.queryUserPageList(args, currentPage, pageSize);
            List<Map> list = new ArrayList<Map>();
            for(Object obj:page.getResults()){
                GoodsDO goodsDO = (GoodsDO)obj;
                Map mo = new HashMap();
                mo.put("id",goodsDO.id);
                mo.put("goodsName",goodsDO.getGoodsName());
                mo.put("status",goodsDO.status);
                mo.put("price",goodsDO.getPrice());
                mo.put("goodsPics",goodsDO.getGoodsPics());
                list.add(mo);
            }
            page.setResults(list);
            JSONHelper.returnInfo(JSONHelper.returnServerSuccessJsonString(page));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
            JSONHelper.returnInfo(JSONHelper.returnServerErrJsonString());
            return;
        }
    }


    @ApiOperation(value="商品添加")
    @ResponseBody
    @RequestMapping(value = "/saveMallGoods",method = RequestMethod.POST)
    public void saveMallGoods(@RequestBody @Valid GoodsFormBean goodsFormBean, BindingResult bindingResult){
        try {
            if(bindingResult.hasErrors()){
                JSONHelper.returnInfo(JSONHelper.returnServerErrJsonString(bindingResult.getFieldError().getDefaultMessage()));
                return;
            }
            MallUserDO mallUser = (MallUserDO) this.getSession(AppConst.session_key);
            GoodsDO goods = new GoodsDO();
            goods.setStoreId(mallUser.getCurrentMall().getId());
            BeanUtils.copyProperties(goodsFormBean,goods);
            commonService.save(goods);
            JSONHelper.returnInfo(JSONHelper.returnServerSuccessJsonString());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
            JSONHelper.returnInfo(JSONHelper.returnServerErrJsonString());
            return;
        }
    }

    @ApiOperation(value="商品详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name="goodsId",value="商品ID",required=true,paramType="query")
    })
    @ResponseBody
    @RequestMapping(value = "/queryGoodsDetailByGoodsId",method = RequestMethod.POST)
    public void queryGoodsDetailByGoodsId(long goodsId){
        try {
            GoodsDO goodsDO = commonService.queryById(GoodsDO.class,goodsId);
            JSONHelper.returnInfo(JSONHelper.returnServerSuccessJsonString(goodsDO));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
            JSONHelper.returnInfo(JSONHelper.returnServerErrJsonString());
            return;
        }
    }

    @ApiOperation(value="商品修改")
    @ResponseBody
    @RequestMapping(value = "/updateMallGoods",method = RequestMethod.POST)
    public void updateMallGoods(@RequestBody @Valid GoodsFormBean goodsFormBean, BindingResult bindingResult, long id){
        try {
            if(bindingResult.hasErrors()){
                JSONHelper.returnInfo(JSONHelper.returnServerErrJsonString(bindingResult.getFieldError().getDefaultMessage()));
                return;
            }
            GoodsDO goodsDO = commonService.queryById(GoodsDO.class,id);
            BeanUtils.copyProperties(goodsFormBean,goodsDO);
            commonService.save(goodsDO);
            JSONHelper.returnInfo(JSONHelper.returnServerSuccessJsonString());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
            JSONHelper.returnInfo(JSONHelper.returnServerErrJsonString());
            return;
        }
    }

    @ApiOperation(value="商品修改分组")
    @ResponseBody
    @RequestMapping(value = "/updateMallGoodsGroup",method = RequestMethod.POST)
    public void updateMallGoodsGroup(long goodsId,long groupId){
        try {
            GoodsCategoryDO goodsCategoryDO = commonService.queryById(GoodsCategoryDO.class,groupId);
            if(goodsCategoryDO==null){
                JSONHelper.returnInfo(JSONHelper.returnServerErrJsonString("没有找到对应ID的组"));
                return;
            }
            GoodsDO goodsDO = commonService.queryById(GoodsDO.class,goodsId);
            if(goodsDO==null){
                JSONHelper.returnInfo(JSONHelper.returnServerErrJsonString("没有找到对应ID的商品"));
                return;
            }
            goodsDO.setCategoryId(groupId);
            commonService.save(goodsDO);
            JSONHelper.returnInfo(JSONHelper.returnServerSuccessJsonString());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
            JSONHelper.returnInfo(JSONHelper.returnServerErrJsonString());
            return;
        }
    }

    @ApiOperation(value="商品上架下架")
    @ApiImplicitParams({
            @ApiImplicitParam(name="goodsIds",value="商品ID数组",required=true,paramType="query"),
            @ApiImplicitParam(name="status",value="1:下架，2：上架",required=true,paramType="query")
    })
    @ResponseBody
    @RequestMapping(value = "/updateMallGoodsStatus",method = RequestMethod.POST)
    public void updateMallGoodsStatus(long[] goodsIds,short status){
        try {
            for(long goodsId:goodsIds){
                GoodsDO goodsDO = commonService.queryById(GoodsDO.class,goodsId);
                if(goodsDO==null){
                    JSONHelper.returnInfo(JSONHelper.returnServerErrJsonString("没有找到对应ID的商品"));
                    return;
                }
                goodsDO.setStatus(Integer.valueOf(status));
                commonService.save(goodsDO);
            }
            JSONHelper.returnInfo(JSONHelper.returnServerSuccessJsonString());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
            JSONHelper.returnInfo(JSONHelper.returnServerErrJsonString());
            return;
        }
    }

    @ApiOperation(value="商品排序")
    @ResponseBody
    @RequestMapping(value = "/updateMallGoodsIndex",method = RequestMethod.POST)
    public void updateMallGoodsIndex(@RequestBody @Valid IdIndexFormBean[] idIndexFormBeans, BindingResult bindingResult){
        try {
            if(bindingResult.hasErrors()){
                JSONHelper.returnInfo(JSONHelper.returnServerErrJsonString(bindingResult.getFieldError().getDefaultMessage()));
                return;
            }
            for(IdIndexFormBean idIndexFormBean:idIndexFormBeans){
                GoodsDO goodsDO = commonService.queryById(GoodsDO.class,idIndexFormBean.getId());
                if(goodsDO==null){
                    goodsDO.setIndex(idIndexFormBean.getIndex());
                    commonService.save(goodsDO);
                }
            }
            JSONHelper.returnInfo(JSONHelper.returnServerSuccessJsonString());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
            JSONHelper.returnInfo(JSONHelper.returnServerErrJsonString());
            return;
        }
    }

    @ApiOperation(value="商品删除")
    @ApiImplicitParams({
            @ApiImplicitParam(name="goodsIds",value="商品ID数组",required=true,paramType="query"),
    })
    @ResponseBody
    @RequestMapping(value = "/removeMallGoods",method = RequestMethod.POST)
    public void removeMallGoods(long[] goodsIds){
        try {
            for(long goodsId:goodsIds){
                GoodsDO goodsDO = commonService.queryById(GoodsDO.class,goodsId);
                if(goodsDO==null){
                    JSONHelper.returnInfo(JSONHelper.returnServerErrJsonString("没有找到对应ID的商品"));
                    return;
                }
                goodsDO.setDelete(true);
                commonService.save(goodsDO);
            }
            JSONHelper.returnInfo(JSONHelper.returnServerSuccessJsonString());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
            JSONHelper.returnInfo(JSONHelper.returnServerErrJsonString());
            return;
        }
    }



    @ApiOperation(value="商品图片列表")
    @ResponseBody
    @RequestMapping(value = "/queryMallPictureList",method = RequestMethod.POST)
    public void queryMallPictureList(){
        try {
            List<Map> retlist = new ArrayList<Map>();
            Map args = new HashMap();
            MallUserDO mallUser = (MallUserDO) this.getSession(AppConst.session_key);
            args.put("storeId",mallUser.getCurrentMall().id);
            List<MallPicturesDO> list = mallGoodsServiceImpl.queryMallPictureList(args);
            for(MallPicturesDO obj:list) {
                Map map = new HashMap();
                map.put("id", obj.getId());
                map.put("picturePath", obj.getPicture());
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



    @ApiOperation(value="上传图片")
    @ApiImplicitParams({
            @ApiImplicitParam(name="picturePath",value="图片路径",required=true,paramType="query")
    })
    @ResponseBody
    @RequestMapping(value = "/saveMallPicture",method = RequestMethod.POST)
    public void saveMallPicture(String picturePath){
        try {
            MallUserDO mallUser = (MallUserDO) this.getSession(AppConst.session_key);
            MallPicturesDO mallPicturesDO = new MallPicturesDO();
            mallPicturesDO.setMallId(mallUser.getCurrentMall().id);
            mallPicturesDO.setPicture(picturePath);
            commonService.save(mallPicturesDO);
            JSONHelper.returnInfo(JSONHelper.returnServerSuccessJsonString());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
            JSONHelper.returnInfo(JSONHelper.returnServerErrJsonString());
            return;
        }
    }
}
