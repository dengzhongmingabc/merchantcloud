package com.yidao.appstore.entry;


import com.yidao.core.dao.EntityObj;

import javax.persistence.*;

/**
 * 第三方商户商品实体
 *
 */
@Entity
@Table(name = "t_third_mall_goods")
public class GoodsDO extends EntityObj {

	public enum MallStatus {
		OnSale {
			@Override
			public  Short getValue() {
				return 1;
			}
		},
		offSale {
			@Override
			public Short getValue() {
				return 2;
			}
		};
		public abstract Short getValue();
	}

	@Column(name="store_id")
	private Long storeId; // 商户ID

	@Column(name="goods_pics")
	private String goodsPics;//商品图片路径

	@Column(name="goods_name")
	private String goodsName; // 商品名称

	@Column(name="description")
	private String description;	//商品介绍

	@Column(name="units")
	private String units;	//计量单位

	@Column(name="price")
	private Double price; // 单价

	@Column(name="price_flag", nullable = false)
	private Integer priceFlag = 1;	//是否需要输入商品数量标志，0-不需要,1-需要

	@Column(name="stored_number")
	private Integer storedNumber; // 库存数量

	@Column(name="category_id")
	private Long categoryId;	//商品分类ID

	@Column(name="is_delete", nullable = false)
	private Boolean isDelete;	//删除标志

	@Column(name="cost_price")
	private Double costPrice;	//成本价

	@Column(name="ratio")
	private Double ratio; // 补贴率（每笔交易补贴给用户的驿道币比率）【配比减率】

	@Column(name="return_ratio")
	private Double returnRatio; // 返现比率（每成功完成一笔交易，按成交额返用户驿道币的比率）(满足条件condition的返点比率) 【大于条件数返点率】

	@Column(name="use_point_ratio")
	private Double usePointRatio;	//每笔交易最多可使用的驿道币数量

	@Column(name="agent_ratio")
	private Double agentRatio;	//每笔交易代理的分成比率（用利润减去补贴的驿道币后计算）

	@Column(name="default_ratio")
	private Double defaultRatio;

	@Column(name="condition")
	private Double condition;//满多少条件

	@Column(name="return_ratio1")
	private Double returnRatio1; // 返现比率（每成功完成一笔交易，按成交额返用户驿道币的比率）(不满足条件condition的返点比率)

	@Column(name="is_perfect")
	private Boolean isPerfect;//是否在我公司增加返点率之类的参数

	@Column(name="special_balance_ratio")
	private Double specialBalanceRatio; //【专项补贴率】

	@Column(name="litte_ratio")
	private Double litteRatio; // 返现比率（每成功完成一笔交易，按成交额返用户驿道币的比率）(不满足条件condition的返点比率)	【小于条件数返点率】

	@Column(name="discount_off")
	private Double discountOff;//【折扣率】

	@Column(name="goods_index")
	private Integer index;

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

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

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public Boolean getDelete() {
		return isDelete;
	}

	public void setDelete(Boolean delete) {
		isDelete = delete;
	}

	public Integer getPriceFlag() {
		return priceFlag;
	}

	public void setPriceFlag(Integer priceFlag) {
		this.priceFlag = priceFlag;
	}

	public Double getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(Double costPrice) {
		this.costPrice = costPrice;
	}

	public Double getRatio() {
		return ratio;
	}

	public void setRatio(Double ratio) {
		this.ratio = ratio;
	}

	public Double getReturnRatio() {
		return returnRatio;
	}

	public void setReturnRatio(Double returnRatio) {
		this.returnRatio = returnRatio;
	}

	public Double getUsePointRatio() {
		return usePointRatio;
	}

	public void setUsePointRatio(Double usePointRatio) {
		this.usePointRatio = usePointRatio;
	}

	public Double getAgentRatio() {
		return agentRatio;
	}

	public void setAgentRatio(Double agentRatio) {
		this.agentRatio = agentRatio;
	}

	public Double getDefaultRatio() {
		return defaultRatio;
	}

	public void setDefaultRatio(Double defaultRatio) {
		this.defaultRatio = defaultRatio;
	}

	public Double getCondition() {
		return condition;
	}

	public void setCondition(Double condition) {
		this.condition = condition;
	}

	public Double getReturnRatio1() {
		return returnRatio1;
	}

	public void setReturnRatio1(Double returnRatio1) {
		this.returnRatio1 = returnRatio1;
	}

	public Boolean getPerfect() {
		return isPerfect;
	}

	public void setPerfect(Boolean perfect) {
		isPerfect = perfect;
	}

	public Double getSpecialBalanceRatio() {
		return specialBalanceRatio;
	}

	public void setSpecialBalanceRatio(Double specialBalanceRatio) {
		this.specialBalanceRatio = specialBalanceRatio;
	}

	public Double getLitteRatio() {
		return litteRatio;
	}

	public void setLitteRatio(Double litteRatio) {
		this.litteRatio = litteRatio;
	}

	public Double getDiscountOff() {
		return discountOff;
	}

	public void setDiscountOff(Double discountOff) {
		this.discountOff = discountOff;
	}
}