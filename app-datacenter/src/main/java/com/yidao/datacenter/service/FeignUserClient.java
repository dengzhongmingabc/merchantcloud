package com.yidao.datacenter.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="app-store", fallback = FeignClientFallback.class)
@Component
public interface FeignUserClient {
    @RequestMapping( value = "/api/mall/queryMallDetailByMallId" , method = RequestMethod.POST)
    String queryUser(@RequestParam("mallId") long mallId);
}
