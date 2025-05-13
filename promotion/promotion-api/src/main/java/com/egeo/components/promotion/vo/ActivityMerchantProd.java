package com.egeo.components.promotion.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class ActivityMerchantProd implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2010251774018494356L;

	private Long id;
	
	private String activityName;
	
	/**
	 * 排序
	 */
	private Integer sortValue;
	/**
	 * 活动结束时间
	 */
	private Long time;
	
	private List<MerchantProd> merchantProdList;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public Integer getSortValue() {
		return sortValue;
	}

	public void setSortValue(Integer sortValue) {
		this.sortValue = sortValue;
	}

	public Long getTime() {
		return time;
	}

	public void setTime(Long time) {
		this.time = time;
	}

	public List<MerchantProd> getMerchantProdList() {
		return merchantProdList;
	}

	public void setMerchantProdList(List<MerchantProd> merchantProdList) {
		this.merchantProdList = merchantProdList;
	}
	
	
}
