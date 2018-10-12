package com.yidao.appstore.service;

import com.yidao.appstore.service.FeignUserClient;
import org.springframework.stereotype.Component;

/**
 * 回退类FeignClientFallback需实现Feign Client接口
 * FeignClientFallback也可以是public class，没有区别
 */
@Component
class FeignClientFallback implements FeignUserClient {

    @Override
    public String queryUser(long mallId) {
        return "app-goods 出错";
    }
}

