package com.yidao.appstore.entry;


import com.yidao.core.dao.EntityObj;

import javax.persistence.*;

/**
 * 商品类目表实体类
 */
@Entity
@Table(name = "t_goods_category")
public class GoodsCategoryDO extends EntityObj {

	@Column(name="cate_name", length=64, nullable=false)
	private String cateName; // 类目名称

	@Column(name="mall_id")
	private Long mallId;//所属商店ID

	@Column(name="type")
	private Integer type;

	@Column(name="is_fix")
	private Boolean isFix;

	public String getCateName() {
		return cateName;
	}

	public void setCateName(String cateName) {
		this.cateName = cateName;
	}

	public Long getMallId() {
		return mallId;
	}

	public void setMallId(Long mallId) {
		this.mallId = mallId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Boolean getFix() {
		return isFix;
	}

	public void setFix(Boolean fix) {
		isFix = fix;
	}
}