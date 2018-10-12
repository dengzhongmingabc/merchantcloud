package com.yidao.appstore.controller;

import com.vividsolutions.jts.geom.*;
import com.yidao.appstore.entry.MallDO;
import com.yidao.appstore.entry.MallUserDO;
import com.yidao.appstore.entry.SysUserDO;
import com.yidao.appstore.formbean.MallBaseInfoFormBean;
import com.yidao.appstore.service.ICommonService;
import com.yidao.appstore.service.IMallService;
import com.yidao.appstore.formbean.MallAuthInfoFormBean;
import com.yidao.core.utils.AppConst;
import com.yidao.core.utils.GeoHash;
import com.yidao.core.utils.JSONHelper;
import com.yidao.core.web.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(description = "商店相关api")
@RestController
@RequestMapping("/api/mall")
public class MallController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    public static final int GEOHASH_LENGTH = 4;

    @Autowired
    private ICommonService commonService;

    @Autowired
    private IMallService mallServiceImpl;

    @FeignClient(name="app-user")
    public interface FeignUserClient {
        @RequestMapping( value = "//api/mall/queryMallDetailByMallId" , method = RequestMethod.GET)
        String queryUser(@RequestParam("mallId") String mallId);
    }


    @ApiOperation(value="商店基本资料提交")
    @ResponseBody
    @RequestMapping(value = "/saveMallBaseInfo",method = RequestMethod.POST)
    public void saveMallBaseInfo(@RequestBody @Valid MallBaseInfoFormBean mallBaseInfoFormBean, BindingResult bindingResult){
        try {
            if(bindingResult.hasErrors()){
                JSONHelper.returnInfo(JSONHelper.returnServerErrJsonString(bindingResult.getFieldError().getDefaultMessage()));
                return;
            }
            MallDO mall = new MallDO();
            BeanUtils.copyProperties(mallBaseInfoFormBean,mall);
            String strGeohash = GeoHash.encode(mallBaseInfoFormBean.getLatitude(), mallBaseInfoFormBean.getLongitude()).substring(0, GEOHASH_LENGTH);
            Geometry geometry = new GeometryFactory().createPoint(new Coordinate(mallBaseInfoFormBean.getLongitude(), mallBaseInfoFormBean.getLatitude()));
            mall.setGeohash(strGeohash);
            mall.setLocation(geometry);

            MallUserDO user=(MallUserDO) getSession(AppConst.session_key);
            mall.setMallUserId(user.getId());

            mall.setStatus(Integer.valueOf(MallDO.MallStatus.BaseInfoCommit.getValue()));
            commonService.save(mall);
            JSONHelper.returnInfo(JSONHelper.returnServerSuccessJsonString(mall.getId()));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
            JSONHelper.returnInfo(JSONHelper.returnServerErrJsonString());
            return;
        }
    }


    @ApiOperation(value="商店认证资料提交")
    @ResponseBody
    @RequestMapping(value = "/saveMallAuthInfo",method = RequestMethod.POST)
    public void saveMallAuthInfo(@RequestBody @Valid MallAuthInfoFormBean mallAuthInfoFormBean, BindingResult bindingResult){
        try {
            if(bindingResult.hasErrors()){
                JSONHelper.returnInfo(JSONHelper.returnServerErrJsonString(bindingResult.getFieldError().getDefaultMessage()));
                return;
            }
            MallDO mall = commonService.queryById(MallDO.class,mallAuthInfoFormBean.getMallId());
            BeanUtils.copyProperties(mallAuthInfoFormBean,mall);
            mall.setStatus(Integer.valueOf(MallDO.MallStatus.AuthInfoCommit.getValue()));
            SysUserDO sysUserDO = commonService.queryAgencyByAgencyNo(mallAuthInfoFormBean.getAgencyNo());
            mall.setAgencyId(sysUserDO.getId());
            commonService.save(mall);
            JSONHelper.returnInfo(JSONHelper.returnServerSuccessJsonString(mall.getId()));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
            JSONHelper.returnInfo(JSONHelper.returnServerErrJsonString());
            return;
        }
    }

    @ApiOperation(value="商店列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name="mallUserId",value="商家用户ID",required=true,paramType="query")
    })
    @ResponseBody
    @RequestMapping(value = "/queryMallListByMallUserId",method = RequestMethod.POST)
    public void queryMallListByMallUserId(long mallUserId){
        try {
            List<Map> retlist = new ArrayList<Map>();
            List<MallDO> list = mallServiceImpl.queryMallListByMallUserId(mallUserId);
            for(MallDO obj:list) {
                Map map = new HashMap();
                map.put("id", obj.getId());
                map.put("status", obj.getStatus());
                map.put("mallName", obj.getStoreName());
                map.put("storeType", obj.getStoreType());
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

    @ApiOperation(value="商店详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name="mallId",value="商店ID",required=true,paramType="query")
    })
    @ResponseBody
    @RequestMapping(value = "/queryMallDetailByMallId",method = RequestMethod.POST)
    public void queryMallDetailByMallId(long mallId){
        try {
            MallDO mall = mallServiceImpl.queryById(MallDO.class,mallId);
            /*Map<String,Object> retMap = new HashMap<String,Object>();
            retMap.put("id",mall.getId());*/

            JSONHelper.returnInfo(JSONHelper.returnServerSuccessJsonString(mall));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
            JSONHelper.returnInfo(JSONHelper.returnServerErrJsonString());
            return;
        }
    }


    @ApiOperation(value="商店首页（最后做）")
    @ApiImplicitParams({
            @ApiImplicitParam(name="mallId",value="商店ID",required=true,paramType="query")
    })
    @ResponseBody
    @RequestMapping(value = "/queryMallIndexData",method = RequestMethod.POST)
    public void queryMallIndexData(long mallId){
        try {
            MallDO mall = mallServiceImpl.queryById(MallDO.class,mallId);
            /*Map<String,Object> retMap = new HashMap<String,Object>();
            retMap.put("id",mall.getId());*/

            MallUserDO mallUser = (MallUserDO) this.getSession(AppConst.session_key);
            mallUser.setCurrentMall(mall);

            this.setSession(AppConst.session_key,mallUser);
            JSONHelper.returnInfo(JSONHelper.returnServerSuccessJsonString(mall));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
            JSONHelper.returnInfo(JSONHelper.returnServerErrJsonString());
            return;
        }
    }
}
