package com.yidao.goods.formbean;

import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public class MallUserFormBean {
    @ApiModelProperty(value="手机号码",required=true)
    @NotEmpty
    @Pattern(regexp="^((17[0-9])|(14[0-9])|(13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$",message = "不是规范的手机号码")
    private String mobile;

    @ApiModelProperty(value="密码",required=true)
    @Length(min=6, message="密码长度不能少于6位")
    @NotEmpty(message = "密码不能为空")
    private String password;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}