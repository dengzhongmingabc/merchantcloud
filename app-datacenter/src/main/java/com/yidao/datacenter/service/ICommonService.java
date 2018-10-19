package com.yidao.datacenter.service;

import com.yidao.core.service.IBaseService;
import com.yidao.datacenter.entry.SysUserDO;

public interface ICommonService extends IBaseService {


    public SysUserDO queryAgencyByAgencyNo(String agencyNo) throws Exception;
}