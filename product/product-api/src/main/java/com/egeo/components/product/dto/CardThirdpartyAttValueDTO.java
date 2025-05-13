package com.egeo.components.product.dto;

import java.io.Serializable;

/**
 * 
 * @author min
 * @date 2018-08-10 15:19:51
 */
public class CardThirdpartyAttValueDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	/**
	 * 卡类型：1、普通卡 2、充值卡 3、体检卡
	 */
	private Integer cardType;	

	/**
	 * 
	 */
	private Long thirdpartyAttValueId;	

	/**
	 * 备注
	 */
	private String remark;	

	/**
	 * 平台id
	 */
	private Long platformId;	

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
	 * 卡类型：1、普通卡 2、充值卡 3、体检卡
	 * @return 卡类型：1、普通卡 2、充值卡 3、体检卡
	 */
	public Integer getCardType() {
		return cardType;
	}

	/**
	 * 卡类型：1、普通卡 2、充值卡 3、体检卡
	 * @param cardType 卡类型：1、普通卡 2、充值卡 3、体检卡
	 */
	public void setCardType(Integer cardType) {
		this.cardType = cardType;
	}
	/**
	 * 
	 * @return 
	 */
	public Long getThirdpartyAttValueId() {
		return thirdpartyAttValueId;
	}

	/**
	 * 
	 * @param thirdpartyAttValueId 
	 */
	public void setThirdpartyAttValueId(Long thirdpartyAttValueId) {
		this.thirdpartyAttValueId = thirdpartyAttValueId;
	}
	/**
	 * 备注
	 * @return 备注
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * 备注
	 * @param remark 备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * 平台id
	 * @return 平台id
	 */
	public Long getPlatformId() {
		return platformId;
	}

	/**
	 * 平台id
	 * @param platformId 平台id
	 */
	public void setPlatformId(Long platformId) {
		this.platformId = platformId;
	}
}
	