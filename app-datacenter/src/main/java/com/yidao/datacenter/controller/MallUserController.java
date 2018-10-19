package com.yidao.datacenter.controller;

import com.yidao.core.utils.AppConst;
import com.yidao.core.utils.JSONHelper;
import com.yidao.core.web.BaseController;
import com.yidao.datacenter.entry.MallUserDO;
import com.yidao.datacenter.formbean.MallUserFormBean;
import com.yidao.datacenter.service.IMallUserService;
import com.yidao.datacenter.service.IRedisService;
import com.yidao.datacenter.service.ISendSmsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Api(description = "用户相关api")
@RestController
@RequestMapping("/api/malluser")
public class MallUserController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private IMallUserService mallUserServiceImpl;

    @Autowired
    private ISendSmsService sendSmsService;

    @Autowired
    private IRedisService redisService;
//**

    @Value("${sysconfig.smsend}")
    private String smsend;

    @ApiOperation(value="用户密码登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name="mobile",value="手机号",required=true,paramType="query"),
            @ApiImplicitParam(name="password",value="密码",required=true,paramType="query"),
    })
    @ResponseBody
    @RequestMapping(value = "/pwdLogin",method = RequestMethod.POST)
    public void pwdLogin(String mobile,String password){
        try {
            System.out.println(smsend);
            if(StringUtils.isBlank(mobile)||StringUtils.isBlank(password)){
                JSONHelper.returnInfo(JSONHelper.returnJsonString(AppConst.UNAUTH_ERR_CODE,AppConst.UNAUTH_ERR_MSG));
                return;
            }
            MallUserDO user = mallUserServiceImpl.queryUserByMobile(mobile);
            if(null==user){
                JSONHelper.returnInfo(JSONHelper.returnJsonString(AppConst.UNAUTH_ERR_CODE,AppConst.UNAUTH_ERR_MSG));
                return;
            }
            String strPwdMd5 = DigestUtils.md5Hex(password);
            if(!strPwdMd5.equals(user.getPassword())){
                JSONHelper.returnInfo(JSONHelper.returnJsonString(AppConst.UNAUTH_ERR_CODE,AppConst.UNAUTH_ERR_MSG));
                return;
            }
            this.setSession(AppConst.session_key,user);
            Map<String,Object> retMap = new HashMap<String,Object>();
            retMap.put("id",user.getId());
            retMap.put("mobile",user.getMobile());
            JSONHelper.returnInfo(JSONHelper.returnServerSuccessJsonString(retMap));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
            JSONHelper.returnInfo(JSONHelper.returnServerErrJsonString());
            return;
        }
    }

    @ApiOperation(value="用户验证码登录")
    @ResponseBody
    @RequestMapping(value = "/checkCodeLogin",method = RequestMethod.POST)
    public void checkCodeLogin(String mobile,String checkCode){
        try {
            logger.info("mobile="+AppConst.ARGS_MOBILE_ERR_CODE);
            if(StringUtils.isBlank(mobile)||StringUtils.isBlank(checkCode)){
                JSONHelper.returnInfo(JSONHelper.returnJsonString(AppConst.UNAUTH_ERR_CODE,AppConst.UNAUTH_ERR_MSG));
                return;
            }
            MallUserDO user = mallUserServiceImpl.queryUserByMobile(mobile);
            if(null==user){
                JSONHelper.returnInfo(JSONHelper.returnJsonString(AppConst.ARGS_MOBILE_ERR_CODE,"手机号码不存在"));
                return;
            }
            Map map = redisService.hmget(redis_mall_key+mobile);
            if(map==null||map.get("checkCode")==null){
                JSONHelper.returnInfo(JSONHelper.returnJsonString(AppConst.ARGS_CHECK_CODE_ERR_CODE,"验证码过期或者错误，请重新获取"));
                return;
            }
            if(!((String)map.get("checkCode")).equals(checkCode)){
                JSONHelper.returnInfo(JSONHelper.returnJsonString(AppConst.ARGS_CHECK_CODE_ERR_CODE,"验证错误"));
                return;
            }
            this.setSession(AppConst.session_key,user);
            Map<String,Object> retMap = new HashMap<String,Object>();
            retMap.put("id",user.getId());
            retMap.put("mobile",user.getMobile());
            JSONHelper.returnInfo(JSONHelper.returnServerSuccessJsonString(retMap));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
            JSONHelper.returnInfo(JSONHelper.returnServerErrJsonString());
            return;
        }
    }

    @ApiOperation(value="用户找回密码", notes="mallUser 只需mobile 和 password")
    @ResponseBody
    @RequestMapping(value = "/setNewPwd",method = RequestMethod.POST)
    public void setNewPwd(@RequestBody @Valid MallUserDO mallUser, String checkCode, BindingResult bindingResult){
        try {
            if(bindingResult.hasErrors()){
                JSONHelper.returnInfo(JSONHelper.returnServerErrJsonString(bindingResult.getFieldError().getDefaultMessage()));
                return;
            }
            MallUserDO user = mallUserServiceImpl.queryUserByMobile(mallUser.getMobile());
            if(null==user){
                JSONHelper.returnInfo(JSONHelper.returnJsonString(AppConst.ARGS_MOBILE_ERR_CODE,"手机号码不存在"));
                return;
            }
            Map map = redisService.hmget(redis_mall_key+mallUser.getMobile());
            if(map==null||map.get("checkCode")==null){
                JSONHelper.returnInfo(JSONHelper.returnJsonString(AppConst.ARGS_CHECK_CODE_ERR_CODE,"验证码过期或者错误，请重新获取"));
                return;
            }
            if(!((String)map.get("checkCode")).equals(checkCode)){
                JSONHelper.returnInfo(JSONHelper.returnJsonString(AppConst.ARGS_CHECK_CODE_ERR_CODE,"验证错误"));
                return;
            }
            String strPwdMd5 = DigestUtils.md5Hex(mallUser.getPassword());
            user.setPassword(strPwdMd5);
            mallUserServiceImpl.save(user);
            JSONHelper.returnInfo(JSONHelper.returnServerSuccessJsonString());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
            JSONHelper.returnInfo(JSONHelper.returnServerErrJsonString());
            return;
        }
    }

    @ApiOperation(value="用户注册", notes="mallUser 只需mobile 和 password")
    @ResponseBody
    @RequestMapping(value = "/regist",method = RequestMethod.POST)
    public void regist(@RequestBody @Valid MallUserDO mallUser, BindingResult bindingResult, String checkCode){
        try {
            if(bindingResult.hasErrors()){
                JSONHelper.returnInfo(JSONHelper.returnServerErrJsonString(bindingResult.getFieldError().getDefaultMessage()));
                return;
            }
            MallUserDO user = mallUserServiceImpl.queryUserByMobile(mallUser.getMobile());
            if(null!=user){
                JSONHelper.returnInfo(JSONHelper.returnJsonString(AppConst.ARGS_MOBILE_ERR_CODE,"手机号码已存在"));
                return;
            }
            Map map = redisService.hmget(redis_mall_key+mallUser.getMobile());
            if(map==null||map.get("checkCode")==null){
                JSONHelper.returnInfo(JSONHelper.returnJsonString(AppConst.ARGS_CHECK_CODE_ERR_CODE,"验证码过期或者错误，请重新获取"));
                return;
            }
            if(!((String)map.get("checkCode")).equals(checkCode)){
                JSONHelper.returnInfo(JSONHelper.returnJsonString(AppConst.ARGS_CHECK_CODE_ERR_CODE,"验证错误"));
                return;
            }
            MallUserDO user_ = new MallUserDO();
            String strPwdMd5 = DigestUtils.md5Hex(mallUser.getPassword());
            user_.setPassword(strPwdMd5);
            user_.setMobile(mallUser.getMobile());
            mallUserServiceImpl.save(user_);
            Map<String,Object> retMap = new HashMap<String,Object>();
            retMap.put("id",user_.getId());
            retMap.put("mobile",user_.getMobile());
            JSONHelper.returnInfo(JSONHelper.returnServerSuccessJsonString(user_));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
            JSONHelper.returnInfo(JSONHelper.returnServerErrJsonString());
            return;
        }
    }


    @ApiOperation(value="注册获取验证码", notes="mallUser 只需mobile")
    @ResponseBody
    @RequestMapping(value = "/checkcode4Regist",method = RequestMethod.POST)
    public void checkcode4Regist(@RequestBody @Valid MallUserFormBean mallUser, BindingResult bindingResult){
        try {
            if (!bindingResult.hasFieldErrors("mobile")) {
                MallUserDO user = mallUserServiceImpl.queryUserByMobile(mallUser.getMobile());
                if(null!=user){
                    JSONHelper.returnInfo(JSONHelper.returnServerErrJsonString("手机号码已存在"));
                    return;
                }
                Random random = new Random();
                StringBuffer sbRandomCode = new StringBuffer();
                for (int i = 0; i < 4; i++) {
                    String strRand = String.valueOf(random.nextInt(10));
                    sbRandomCode.append(strRand);
                }
                logger.info("mobile=" + mallUser.getMobile() + ", checkCode=" + sbRandomCode.toString());
                String strMsg = String.format(smsend, sbRandomCode.toString());
                //sendSmsService.sendSMS(mallUser.getMobile(), strMsg);
                // 将生成的验证码保存到缓存中
                HashMap<String, String> checkMap = new HashMap<String, String>();
                checkMap.put("checkCode", sbRandomCode.toString());
                redisService.hmset(redis_mall_key+mallUser.getMobile(), checkMap, 5 * 60L);
                JSONHelper.returnInfo(JSONHelper.returnServerSuccessJsonString(sbRandomCode.toString()));
            }else{
                JSONHelper.returnInfo(JSONHelper.returnServerErrJsonString(bindingResult.getFieldError("mobile").getDefaultMessage()));
                return;
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
            JSONHelper.returnInfo(JSONHelper.returnServerErrJsonString());
            return;
        }
    }

    @ApiOperation(value="获取验证码", notes="mallUser 只需mobile")
    @ResponseBody
    @RequestMapping(value = "/checkcode",method = RequestMethod.POST)
    public void checkcode(@RequestBody @Valid MallUserFormBean mallUser, BindingResult bindingResult){
        try {
            if (!bindingResult.hasFieldErrors("mobile")) {
                Random random = new Random();
                StringBuffer sbRandomCode = new StringBuffer();
                for (int i = 0; i < 4; i++) {
                    String strRand = String.valueOf(random.nextInt(10));
                    sbRandomCode.append(strRand);
                }
                logger.info("mobile=" + mallUser.getMobile() + ", checkCode=" + sbRandomCode.toString());
                String strMsg = String.format(smsend, sbRandomCode.toString());
                //sendSmsService.sendSMS(mallUser.getMobile(), strMsg);
                // 将生成的验证码保存到缓存中
                HashMap<String, String> checkMap = new HashMap<String, String>();
                checkMap.put("checkCode", sbRandomCode.toString());
                redisService.hmset(redis_mall_key+mallUser.getMobile(), checkMap, 5 * 60L);
                JSONHelper.returnInfo(JSONHelper.returnServerSuccessJsonString(sbRandomCode.toString()));
            }else{
                JSONHelper.returnInfo(JSONHelper.returnServerErrJsonString(bindingResult.getFieldError("mobile").getDefaultMessage()));
                return;
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
            JSONHelper.returnInfo(JSONHelper.returnServerErrJsonString());
            return;
        }
    }

    @ApiOperation(value="用户退出登录")
    @ResponseBody
    @RequestMapping(value = "/logout",method = RequestMethod.POST)
    public void logout(){
        try {
            this.removeSession(AppConst.session_key);
            JSONHelper.returnInfo(JSONHelper.returnServerSuccessJsonString());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
            JSONHelper.returnInfo(JSONHelper.returnServerErrJsonString());
            return;
        }
    }


}
