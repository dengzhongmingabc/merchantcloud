package com.yidao.appstore.entry;


import com.yidao.core.dao.EntityObj;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t_mall_pictures")
public class MallPicturesDO extends EntityObj {
    private long mallId;//商家ID

    private String picture;//上传的图片

    public long getMallId() {
        return mallId;
    }

    public void setMallId(long mallId) {
        this.mallId = mallId;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}