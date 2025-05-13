package com.egeo.components.product.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author xiaping
 * @date 2017-08-14 13:46:25
 */
public class MerchantSeriesDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	/**
	 * 商家Id
	 */
	private Long merchantId;	

	/**
	 * 主商品Id
	 */
	private Long mainMerchantProductId;	

	/**
	 * 系列产品名称
	 */
	private String name;	

	/**
	 * 平台id
	 */
	private Long platformId;	

	/**
	 * 系列品编码
	 */
	private String code;	

	/**
	 * 创建时间:创建记录时数据库会自动set值
	 */
	private Date createTime;	

	/**
	 * 修改时间:更新时数据库会自动set值
	 */
	private Date updateTime;	

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
	 * 商家Id
	 * @return 商家Id
	 */
	public Long getMerchantId() {
		return merchantId;
	}

	/**
	 * 商家Id
	 * @param merchantId 商家Id
	 */
	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}
	/**
	 * 主商品Id
	 * @return 主商品Id
	 */
	public Long getMainMerchantProductId() {
		return mainMerchantProductId;
	}

	/**
	 * 主商品Id
	 * @param mainMerchantProductId 主商品Id
	 */
	public void setMainMerchantProductId(Long mainMerchantProductId) {
		this.mainMerchantProductId = mainMerchantProductId;
	}
	/**
	 * 系列产品名称
	 * @return 系列产品名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * 系列产品名称
	 * @param name 系列产品名称
	 */
	public void setName(String name) {
		this.name = name;
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
	 * 系列品编码
	 * @return 系列品编码
	 */
	public String getCode() {
		return code;
	}

	/**
	 * 系列品编码
	 * @param code 系列品编码
	 */
	public void setCode(String code) {
		this.code = code;
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
}
	