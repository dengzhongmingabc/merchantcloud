package com.yidao.datacenter.formbean;

import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

public class MallAuthInfoFormBean {
    @ApiModelProperty(value="商店ID",required=true)
    private long mallId;//商店ID不能为空

    @ApiModelProperty(value="身份证正面",required=true)
    @NotEmpty(message = "身份证正面不能为空")
    private String idCardFacePic;//身份证正面--

    @ApiModelProperty(value="身份证反面",required=true)
    @NotEmpty(message = "身份证反面不能为空")
    private String idCardReversePic;//省份证反面--

    @ApiModelProperty(value="营业执照",required=true)
    @NotEmpty(message = "营业执照不能为空")
    private String licencePic;//营业执照--

    @ApiModelProperty(value="营业许可证",required=true)
    @NotEmpty(message = "营业许可证不能为空")
    private String certificatePic;//营业许可证--

    @ApiModelProperty(value="身份证号码",required=true)
    @NotEmpty(message = "身份证号码不能为空")
    private String idCardNo;//省份证号码--

    @ApiModelProperty(value="营业执照号码",required=true)
    @NotEmpty(message = "营业执照号码不能为空")
    private String licenceNo;//营业执照号码---

    @ApiModelProperty(value="代理号码",required=true)
    @NotEmpty(message = "代理号码不能为空")
    @Length(min=4, max=4,message = "代理号码长度4")
    private String agencyNo;//代理号码---

    public String getAgencyNo() {
        return agencyNo;
    }

    public void setAgencyNo(String agencyNo) {
        this.agencyNo = agencyNo;
    }

    public long getMallId() {
        return mallId;
    }

    public void setMallId(long mallId) {
        this.mallId = mallId;
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
}