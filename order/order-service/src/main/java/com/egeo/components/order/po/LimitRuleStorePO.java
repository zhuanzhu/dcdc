package com.egeo.components.order.po;



/**
 * 
 * @author tan
 * @date 2019-02-14 10:40:32
 */
public class LimitRuleStorePO {


	private Long id;

	/**
	 * 限购规则id
	 */
	private Long limitRuleId;	

	/**
	 * 门店id
	 */
	private Long storeId;	

	public Long getId() {
		return id;
	}

	/**
	 * 主键
	 * @param id 主键
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 限购规则id
	 * @return 限购规则id
	 */
	public Long getLimitRuleId() {
		return limitRuleId;
	}

	/**
	 * 限购规则id
	 * @param limitRuleId 限购规则id
	 */
	public void setLimitRuleId(Long limitRuleId) {
		this.limitRuleId = limitRuleId;
	}

	/**
	 * 门店id
	 * @return 门店id
	 */
	public Long getStoreId() {
		return storeId;
	}

	/**
	 * 门店id
	 * @param storeId 门店id
	 */
	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}
}
	