package com.egeo.components.promotion.po;



/**
 * 
 * @author feng
 * @date 2018-12-14 10:57:18
 */
public class RuleDescriptionPO {


	private Long id;

	/**
	 * 规则说明
	 */
	private String description;
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
	 * 规则说明
	 * @return 规则说明
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 规则说明
	 * @param description 规则说明
	 */
	public void setDescription(String description) {
		this.description = description;
	}
}
	