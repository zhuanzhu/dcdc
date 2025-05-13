package com.egeo.components.promotion.po;



/**
 * 
 * @author feng
 * @date 2018-12-14 10:57:18
 */
public class ExchangeCouponUnitStatusPO {


	private Long id;

	/**
	 * 以旧换新活动id
	 */
	private Long exchangeId;	

	/**
	 * 允许以旧换新的优惠券unit状态
	 */
	private Integer allowExchangeUnitStatus;

	/**
	 * 平台id
	 */
	private Long platformId;

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
	 * 允许以旧换新的优惠券unit状态
	 * @return 允许以旧换新的优惠券unit状态
	 */
	public Integer getAllowExchangeUnitStatus() {
		return allowExchangeUnitStatus;
	}

	/**
	 * 允许以旧换新的优惠券unit状态
	 * @param allowExchangeUnitStatus 允许以旧换新的优惠券unit状态
	 */
	public void setAllowExchangeUnitStatus(Integer allowExchangeUnitStatus) {
		this.allowExchangeUnitStatus = allowExchangeUnitStatus;
	}
}
	