package com.egeo.components.order.po;



/**
 * 
 * @author min
 * @date 2018-06-13 10:49:48
 */
public class LimitRuleCompanyPO {


	private Long id;

	/**
	 * 限购规则id
	 */
	private Long limitRuleId;	

	/**
	 * 公司id
	 */
	private Long companyId;	

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
	 * 公司id
	 * @return 公司id
	 */
	public Long getCompanyId() {
		return companyId;
	}

	/**
	 * 公司id
	 * @param companyId 公司id
	 */
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
}
	