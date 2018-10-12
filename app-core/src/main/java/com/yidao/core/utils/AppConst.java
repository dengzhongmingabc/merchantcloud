package com.yidao.core.utils;


//
public final class AppConst {

	public static final String session_key="session_key";

	public static final String SERVER_OK_CODE = "0200";
	public static final String SERVER_OK_MSG = "操作成功";

	public static final String SERVER_ERR_CODE = "0500";
	public static final String SERVER_ERR_MSG = "服务器错误";

	public static final String ERR_CODE = "0400";

	public static final String UNAUTH_ERR_CODE = "0401";
	public static final String UNAUTH_ERR_MSG = "账号或者密码错误";

	public static final String FORBIDDEN_ERR_CODE = "0403";
	public static final String FORBIDDEN_ERR_MSG = "没有权限";

	public static final String ARGS_ERR_CODE = "0402";
	public static final String ARGS_ERR_MSG = "必要参数为空";

	public static final String ARGS_MOBILE_ERR_CODE = "0412";
	public static final String ARGS_MOBILE_ERR_MSG = "手机号码错误";

	public static final String ARGS_CHECK_CODE_ERR_CODE = "0422";
	public static final String ARGS_CHECK_CODE_MOBILE_ERR_MSG = "验证码错误";

	public static final String NOFIND_ERR_CODE = "0404";
	public static final String NOFIND_ERR_MSG = "没有找到对应的数据";

	public static final String NOSESSION_ERR_CODE = "0414";
	public static final String NOSESSION_ERR_MSG = "会话过期或者没有登录";

}
