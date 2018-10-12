package com.yidao.goods.entry;

import com.vividsolutions.jts.geom.Geometry;
import com.yidao.core.dao.EntityObj;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t_third_mall")
public class MallDO extends EntityObj {

    public enum MallStatus {
        BaseInfoCommit {
            @Override
            public  Short getValue() {
                return 1;
            }
        },
        AuthInfoCommit {
            @Override
            public Short getValue() {
                return 2;
            }
        },
        AuthSuccess {
            @Override
            public Short getValue() {
                return 3;
            }
        },
        AuthFailure {
            @Override
            public Short getValue() {
                return 4;
            }
        };
        public abstract Short getValue();
    }

    @Column(name="store_name")
    private String storeName; // 店面名称
    @Column(name="store_type")
    private Integer storeType;	//店面类型
    @Column(name="store_head_pic")
    private String storeHeadPic;	//店面头像---
    @Column(name="store_real_pic")
    private String storeRealPic;	//店面实景---
    @Column(name="description")
    private String description; // 商户介绍

    @Column(name="manage_date")
    public String manageDate;//经营天数
    @Column(name="manage_start_time_of_day")
    public String manageStartTimeOfDay;//一天中经营开始时间
    @Column(name="manage_end_time_of_day")
    public String manageEndTimeOfDay;//一天中经营结束时间

    @Column(name="phone")
    private String phone;//客服电话
    @Column(name="address")
    private String address; // 商户地址
    @Column(name = "location",columnDefinition = "Geometry")
    private Geometry location;
    @Column(name="geohash")
    private String geohash; // geohash值
    @Column(name="longitude")
    private Double longitude;
    @Column(name="latitude")
    private Double latitude;
    @Column(name="id_card_face_pic")
    private String idCardFacePic;//身份证正面--
    @Column(name="id_card_reverse_pic")
    private String idCardReversePic;//省份证反面--
    @Column(name="licence_pic")
    private String licencePic;//营业执照--
    @Column(name="certificate_pic")
    private String certificatePic;//营业许可证--
    @Column(name="id_card_no")
    private String idCardNo;//省份证号码--
    @Column(name="licence_no")
    private String licenceNo;//营业执照号码---
    @Column(name="agency_id")
    private Long agencyId = 0L; // 上级代理id

    @Column(name="mall_user_id")
    private Long mallUserId;//所属用户ID
    @Column(name="ratio")
    private Double ratio; // 分成比例
    @Column(name="discount_info")
    private String discountInfo="";	//优惠信息
    @Column(name="is_delete")
    private Boolean isDelete=false;	//删除标志
    @Column(name="is_real")
    private Boolean isReal=true;	//是否真实商家
    @Column(name="mobile_type")
    private Integer mobileType = 0;	//手机型号，0-android,1-苹果
    @Column(name="rank")
    private Double rank = 5.0;	//店铺评分
    @Column(name="is_first_ret")
    private Boolean isFirstRet=false;//是否首单返
    @Column(name="ret_radio")
    private Double retRadio=0.0;//首单返的比例
    @Column(name="balance")
    private Double balance=0.0;//预付费
    @Column(name="before_pay_mall")
    private Boolean beforePayMall=false;//是否是预付费商户
    @Column(name="addmin_id")
    private Long addminId;

    public Long getAddminId() {
        return addminId;
    }

    public void setAddminId(Long addminId) {
        this.addminId = addminId;
    }

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

    public Geometry getLocation() {
        return location;
    }

    public void setLocation(Geometry location) {
        this.location = location;
    }

    public String getGeohash() {
        return geohash;
    }

    public void setGeohash(String geohash) {
        this.geohash = geohash;
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

    public String getIdCardFacePic() {
        return idCardFacePic;
    }

    public void setIdCardFacePic(String idCardFacePic) {
        this.idCardFacePic = idCardFacePic;
    }

    public String getIdCardReversePic() {
        return idCardReversePic;
    }

    public void setIdCardReversePic(String idCardReversePic) {
        this.idCardReversePic = idCardReversePic;
    }

    public String getLicencePic() {
        return licencePic;
    }

    public void setLicencePic(String licencePic) {
        this.licencePic = licencePic;
    }

    public String getCertificatePic() {
        return certificatePic;
    }

    public void setCertificatePic(String certificatePic) {
        this.certificatePic = certificatePic;
    }

    public String getIdCardNo() {
        return idCardNo;
    }

    public void setIdCardNo(String idCardNo) {
        this.idCardNo = idCardNo;
    }

    public String getLicenceNo() {
        return licenceNo;
    }

    public void setLicenceNo(String licenceNo) {
        this.licenceNo = licenceNo;
    }

    public Long getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(Long agencyId) {
        this.agencyId = agencyId;
    }

    public Long getMallUserId() {
        return mallUserId;
    }

    public void setMallUserId(Long mallUserId) {
        this.mallUserId = mallUserId;
    }

    public Double getRatio() {
        return ratio;
    }

    public void setRatio(Double ratio) {
        this.ratio = ratio;
    }

    public String getDiscountInfo() {
        return discountInfo;
    }

    public void setDiscountInfo(String discountInfo) {
        this.discountInfo = discountInfo;
    }

    public Boolean getDelete() {
        return isDelete;
    }

    public void setDelete(Boolean delete) {
        isDelete = delete;
    }

    public Boolean getReal() {
        return isReal;
    }

    public void setReal(Boolean real) {
        isReal = real;
    }

    public Integer getMobileType() {
        return mobileType;
    }

    public void setMobileType(Integer mobileType) {
        this.mobileType = mobileType;
    }

    public Double getRank() {
        return rank;
    }

    public void setRank(Double rank) {
        this.rank = rank;
    }

    public Boolean getFirstRet() {
        return isFirstRet;
    }

    public void setFirstRet(Boolean firstRet) {
        isFirstRet = firstRet;
    }

    public Double getRetRadio() {
        return retRadio;
    }

    public void setRetRadio(Double retRadio) {
        this.retRadio = retRadio;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Boolean getBeforePayMall() {
        return beforePayMall;
    }

    public void setBeforePayMall(Boolean beforePayMall) {
        this.beforePayMall = beforePayMall;
    }
}