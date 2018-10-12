package com.yidao.goods.service;

import com.yidao.core.service.IBaseService;
import com.yidao.goods.entry.SysUserDO;

public interface ICommonService extends IBaseService {


    public SysUserDO queryAgencyByAgencyNo(String agencyNo) throws Exception;
}