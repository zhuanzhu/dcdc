package com.egeo.components.order.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author xiaping
 * @date 2017-08-12 13:00:00
 */
public class DistributionModeItemVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	/**
	 * 商家id
	 */

	private Long merchantId;		 
	/**
	 * 配送方式code
	 */

	private String shippingCode;		 
	/**
	 * 配送方式
	 */

	private String distributionMode;		 
	/**
	 * 0:公司默认配送方式1:商家配送方式
	 */

	private Integer isDefault;		 
	/**
	 * 平台id
	 */

	private Long platformId;		 
	/**
	 * 修改时间:更新时数据库会自动set值
	 */

	private Date updateTime;		 
	/**
	 * 创建时间:创建记录时数据库会自动set值
	 */

	private Date createTime;		 

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
	 * 商家id
	 * @return 商家id
	 */
	public Long getMerchantId() {
		return merchantId;
	}

	/**
	 * 商家id
	 * @param merchantId 商家id
	 */
	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}	
	/**
	 * 配送方式code
	 * @return 配送方式code
	 */
	public String getShippingCode() {
		return shippingCode;
	}

	/**
	 * 配送方式code
	 * @param shippingCode 配送方式code
	 */
	public void setShippingCode(String shippingCode) {
		this.shippingCode = shippingCode;
	}	
	/**
	 * 配送方式
	 * @return 配送方式
	 */
	public String getDistributionMode() {
		return distributionMode;
	}

	/**
	 * 配送方式
	 * @param distributionMode 配送方式
	 */
	public void setDistributionMode(String distributionMode) {
		this.distributionMode = distributionMode;
	}	
	/**
	 * 0:公司默认配送方式1:商家配送方式
	 * @return 0:公司默认配送方式1:商家配送方式
	 */
	public Integer getIsDefault() {
		return isDefault;
	}

	/**
	 * 0:公司默认配送方式1:商家配送方式
	 * @param isDefault 0:公司默认配送方式1:商家配送方式
	 */
	public void setIsDefault(Integer isDefault) {
		this.isDefault = isDefault;
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
	/**
	 * 修改时间:更新时数据库会自动set值
	 * @return 修改时间:更新时数据库会自动set值
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * 修改时间:更新时数据库会自动set值
	 * @param updateTime 修改时间:更新时数据库会自动set值
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}	
	/**
	 * 创建时间:创建记录时数据库会自动set值
	 * @return 创建时间:创建记录时数据库会自动set值
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 创建时间:创建记录时数据库会自动set值
	 * @param createTime 创建时间:创建记录时数据库会自动set值
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}	
}
	