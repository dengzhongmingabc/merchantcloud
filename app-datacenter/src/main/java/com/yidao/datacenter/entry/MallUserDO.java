package com.yidao.datacenter.entry;

import com.yidao.core.dao.EntityObj;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "t_mall_user")
public class MallUserDO extends EntityObj {
    @NotEmpty
    @Pattern(regexp="^((17[0-9])|(14[0-9])|(13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$",message = "不是规范的手机号码")
    @Column(name = "mobile")
    private String mobile;

    @Length(min=6, message="密码长度不能少于6位")
    @Column(name = "password")
    @NotEmpty(message = "密码不能为空")
    private String password;


    private MallDO currentMall;//当前商店

    public MallDO getCurrentMall() {
        return currentMall;
    }

    public void setCurrentMall(MallDO currentMall) {
        this.currentMall = currentMall;
    }

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