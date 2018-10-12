package com.yidao.goods.controller;

import com.yidao.core.entry.CommomDO;
import com.yidao.core.utils.JSONHelper;
import com.yidao.core.web.BaseController;
import com.yidao.goods.entry.MallDO;
import com.yidao.goods.service.FeignUserClient;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api(description = "商店相关api")
@RestController
@RequestMapping("/api/feign")
@RefreshScope
public class FeignController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    FeignUserClient feignUserClient;

    @Value("${sysconfig.smsend}")
    private String smsend;

    @RequestMapping(value = "/testFeign",method = RequestMethod.POST)
    public void testFeign(){
        try {
            System.out.println(smsend);
            CommomDO commomDo = new CommomDO();
            MallDO mall = new MallDO();
            String rest = feignUserClient.queryUser(1L);
            JSONHelper.returnInfo(JSONHelper.returnServerSuccessJsonString(rest));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
            JSONHelper.returnInfo(JSONHelper.returnServerErrJsonString());
            return;
        }
    }
}
