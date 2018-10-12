package com.yidao.appstore.formbean;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotEmpty;

/**
 * 第三方商户商品实体
 *
 */
public class IdIndexFormBean{

	@ApiModelProperty(value="ID",required=true)
	@NotEmpty(message = "id不能为空")
	private long id;//ID

	@ApiModelProperty(value="index 排序序号",required=true)
	@NotEmpty(message = "排序序号不能为空")
	private int index;//排序序号

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
}