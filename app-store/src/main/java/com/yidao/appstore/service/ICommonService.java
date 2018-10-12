package com.yidao.appstore.service;

import com.yidao.appstore.entry.SysUserDO;
import com.yidao.core.service.IBaseService;

public interface ICommonService extends IBaseService {


    public SysUserDO queryAgencyByAgencyNo(String agencyNo) throws Exception;
}