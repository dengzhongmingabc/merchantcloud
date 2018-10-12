package com.yidao.appstore.formbean;

import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;

public class MallBaseInfoFormBean {

    @ApiModelProperty(value="店面名称",required=true)
    @NotEmpty(message = "店面名称不能为空")
    @Length(max=15, message="店面名称最长为15")
    private String storeName; // 店面名称

    @ApiModelProperty(value="店面类型为1到4",required=true)
    @Range(min=1, max=4,message = "店面类型为1到4")
    private Integer storeType;	//店面类型

    @ApiModelProperty(value="店面头像地址",required=true)
    @NotEmpty(message = "店面头像地址不能为空")
    private String storeHeadPic;	//店面头像---

    @ApiModelProperty(value="店面实景地址(json)",required=true)
    @NotEmpty(message = "店面实景地址不能为空")
    private String storeRealPic;	//店面实景---

    @ApiModelProperty(value="店面实景地址",required=true)
    @NotEmpty(message = "商户介绍不能为空")
    private String description; // 商户介绍

    @ApiModelProperty(value="经营天数",required=true)
    @NotEmpty(message = "经营天数不能为空")
    public String manageDate;//经营天数

    @ApiModelProperty(value="经营开始时间",required=true)
    @NotEmpty(message = "经营开始时间不能为空")
    public String manageStartTimeOfDay;//一天中经营开始时间

    @ApiModelProperty(value="经营结束时间",required=true)
    @NotEmpty(message = "经营结束时间不能为空")
    public String manageEndTimeOfDay;//一天中经营结束时间

    @ApiModelProperty(value="客服电话",required=true)
    @NotEmpty(message = "客服电话不能为空")
    private String phone;//客服电话

    @ApiModelProperty(value="商户地址",required=true)
    @NotEmpty(message = "商户地址不能为空")
    private String address; // 商户地址

    @ApiModelProperty(value="纬度",required=true)
    private Double longitude;

    @ApiModelProperty(value="精度",required=true)
    private Double latitude;

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public Integer getStoreType() {
        return storeType;
    }

    public void setStoreType(Integer storeType) {
        this.storeType = storeType;
    }

    public String getStoreHeadPic() {
        return storeHeadPic;
    }

    public void setStoreHeadPic(String storeHeadPic) {
        this.storeHeadPic = storeHeadPic;
    }

    public String getStoreRealPic() {
        return storeRealPic;
    }

    public void setStoreRealPic(String storeRealPic) {
        this.storeRealPic = storeRealPic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getManageDate() {
        return manageDate;
    }

    public void setManageDate(String manageDate) {
        this.manageDate = manageDate;
    }

    public String getManageStartTimeOfDay() {
        return manageStartTimeOfDay;
    }

    public void setManageStartTimeOfDay(String manageStartTimeOfDay) {
        this.manageStartTimeOfDay = manageStartTimeOfDay;
    }

    public String getManageEndTimeOfDay() {
        return manageEndTimeOfDay;
    }

    public void setManageEndTimeOfDay(String manageEndTimeOfDay) {
        this.manageEndTimeOfDay = manageEndTimeOfDay;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }
}