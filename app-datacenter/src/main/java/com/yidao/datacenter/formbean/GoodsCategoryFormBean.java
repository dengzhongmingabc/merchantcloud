package com.yidao.datacenter.formbean;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotEmpty;

/**
 * 商品类目表实体类
 */
public class GoodsCategoryFormBean{

	@ApiModelProperty(value="类目名称",required=true)
	@NotEmpty(message = "类目名称不能为空")
	private String cateName; // 类目名称



	public String getCateName() {
		return cateName;
	}

	public void setCateName(String cateName) {
		this.cateName = cateName;
	}

}