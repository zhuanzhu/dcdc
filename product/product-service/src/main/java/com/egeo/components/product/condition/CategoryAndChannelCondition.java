package com.egeo.components.product.condition;

import com.egeo.components.product.po.CategoryPO;

/**
 *
 * @author tan
 * @date 2019-03-26 10:43:15
 */
public class CategoryAndChannelCondition extends CategoryPO {
	private static final long serialVersionUID = 1L;

	/**
	 * 渠道code码:cake 蛋糕叔叔,worldBuy 全球购
	 **/
	private String channelCode;

	/**
	 * 渠道分类表的id
	 **/
	private Long channelCategory;

	/**
	 * 分类名
	 **/
	private String channelCategoryName;

	/**
	 * 渠道分类节点/品牌id：蛋糕叔叔渠道中：品牌类别id(1-蛋糕,5-零食,8-鲜花)
	 **/
	private String channelCategoryId;

	/**
	 * 渠道类目级数
	 **/
	private Integer channelCategoryLevel;

	/**
	 * 分类类别：-1分类节点全部，-2品牌全部，1分类节点，2品牌
	 **/
	private Integer channelCategoryType;

	/**
	 * 渠道分类节点/品牌id的父节点
	 **/
	private String channelCategoryPId;

	/**
	 * 备注
	 **/
	private String remark;

	public String getChannelCode() {
		return channelCode;
	}

	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}

	public String getChannelCategoryName() {
		return channelCategoryName;
	}

	public void setChannelCategoryName(String channelCategoryName) {
		this.channelCategoryName = channelCategoryName;
	}

	public String getChannelCategoryId() {
		return channelCategoryId;
	}

	public void setChannelCategoryId(String channelCategoryId) {
		this.channelCategoryId = channelCategoryId;
	}

	public Integer getChannelCategoryLevel() {
		return channelCategoryLevel;
	}

	public void setChannelCategoryLevel(Integer channelCategoryLevel) {
		this.channelCategoryLevel = channelCategoryLevel;
	}

	public Integer getChannelCategoryType() {
		return channelCategoryType;
	}

	public void setChannelCategoryType(Integer channelCategoryType) {
		this.channelCategoryType = channelCategoryType;
	}

	public String getChannelCategoryPId() {
		return channelCategoryPId;
	}

	public void setChannelCategoryPId(String channelCategoryPId) {
		this.channelCategoryPId = channelCategoryPId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Long getChannelCategory() {
		return channelCategory;
	}

	public void setChannelCategory(Long channelCategory) {
		this.channelCategory = channelCategory;
	}
}
