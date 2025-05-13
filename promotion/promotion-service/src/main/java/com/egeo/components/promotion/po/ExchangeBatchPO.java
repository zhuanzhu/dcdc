package com.egeo.components.promotion.po;


import java.math.BigDecimal;

/**
 * 
 * @author feng
 * @date 2018-12-14 10:57:17
 */
public class ExchangeBatchPO {


	private Long id;

	/**
	 * 以旧换新活动id
	 */
	private Long exchangeId;	

	/**
	 * 旧优惠券批次id
	 */
	private Long batchId;	

	/**
	 * 当前批次对应是新旧批次,0.旧批次,1.新批次
	 */
	private Integer type;	

	/**
	 * 
	 */
	private BigDecimal addPrice;	

	/**
	 * 排序
	 */
	private Long sort;


	/**
	 * 平台id
	 */
	private Long platformId;

	/**
	 * 平台id
	 * @return
	 */
	public Long getPlatformId() {
		return platformId;
	}

	public void setPlatformId(Long platformId) {
		this.platformId = platformId;
	}

	public Long getId() {
		return id;
	}

	/**
	 * 
	 * @param id 
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 以旧换新活动id
	 * @return 以旧换新活动id
	 */
	public Long getExchangeId() {
		return exchangeId;
	}

	/**
	 * 以旧换新活动id
	 * @param exchangeId 以旧换新活动id
	 */
	public void setExchangeId(Long exchangeId) {
		this.exchangeId = exchangeId;
	}

	/**
	 * 旧优惠券批次id
	 * @return 旧优惠券批次id
	 */
	public Long getBatchId() {
		return batchId;
	}

	/**
	 * 旧优惠券批次id
	 * @param batchId 旧优惠券批次id
	 */
	public void setBatchId(Long batchId) {
		this.batchId = batchId;
	}

	/**
	 * 当前批次对应是新旧批次,0.旧批次,1.新批次
	 * @return 当前批次对应是新旧批次,0.旧批次,1.新批次
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * 当前批次对应是新旧批次,0.旧批次,1.新批次
	 * @param type 当前批次对应是新旧批次,0.旧批次,1.新批次
	 */
	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * 
	 * @return 
	 */
	public BigDecimal getAddPrice() {
		return addPrice;
	}

	/**
	 * 
	 * @param addPrice 
	 */
	public void setAddPrice(BigDecimal addPrice) {
		this.addPrice = addPrice;
	}

	/**
	 * 排序
	 * @return 排序
	 */
	public Long getSort() {
		return sort;
	}

	/**
	 * 排序
	 * @param sort 排序
	 */
	public void setSort(Long sort) {
		this.sort = sort;
	}
}
	