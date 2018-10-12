package com.yidao.appstore.formbean;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotEmpty;

/**
 * 第三方商户商品实体
 *
 */
public class GoodsFormBean{


	@ApiModelProperty(value="商品图片路径",required=true)
	@NotEmpty(message = "商品图片路径不能为空")
	private String goodsPics;//商品图片路径

	@ApiModelProperty(value="商品名称",required=true)
	@NotEmpty(message = "商品名称不能为空")
	private String goodsName; // 商品名称

	@ApiModelProperty(value="商品介绍",required=true)
	@NotEmpty(message = "商品介绍不能为空")
	private String description;	//商品介绍

	@ApiModelProperty(value="计量单位",required=true)
	@NotEmpty(message = "计量单位不能为空")
	private String units;	//计量单位

	@ApiModelProperty(value="单价",required=true)
	@NotEmpty(message = "单价不能为空")
	private Double price; // 单价

	@ApiModelProperty(value="输入商品数量标志",required=true)
	@NotEmpty(message = "输入商品数量标志不能为空")
	private Integer priceFlag = 1;	//是否需要输入商品数量标志，0-不需要,1-需要

	@ApiModelProperty(value="库存数量",required=true)
	@NotEmpty(message = "库存数量不能为空")
	private Integer storedNumber; // 库存数量

	@ApiModelProperty(value="商品分类ID",required=true)
	@NotEmpty(message = "商品分类ID不能为空")
	private Integer categoryId;	//商品分类ID


	public String getGoodsPics() {
		return goodsPics;
	}

	public void setGoodsPics(String goodsPics) {
		this.goodsPics = goodsPics;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUnits() {
		return units;
	}

	public void setUnits(String units) {
		this.units = units;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getStoredNumber() {
		return storedNumber;
	}

	public void setStoredNumber(Integer storedNumber) {
		this.storedNumber = storedNumber;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public Integer getPriceFlag() {
		return priceFlag;
	}

	public void setPriceFlag(Integer priceFlag) {
		this.priceFlag = priceFlag;
	}

}