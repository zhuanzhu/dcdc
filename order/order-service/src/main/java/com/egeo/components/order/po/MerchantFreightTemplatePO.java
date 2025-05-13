package com.egeo.components.order.po;


import java.util.Date;

/**
 * 
 * @author xiaping
 * @date 2017-08-12 12:57:52
 */
public class MerchantFreightTemplatePO {


	private Long id;

	/**
	 * 商家id
	 */
	private Long merchantId;	

	/**
	 * 运费模板
	 */
	private Long freightTemplateId;	

	/**
	 * 添加此数据的商家
	 */
	private Long createMerchantId;	

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
	 * 运费模板
	 * @return 运费模板
	 */
	public Long getFreightTemplateId() {
		return freightTemplateId;
	}

	/**
	 * 运费模板
	 * @param freightTemplateId 运费模板
	 */
	public void setFreightTemplateId(Long freightTemplateId) {
		this.freightTemplateId = freightTemplateId;
	}

	/**
	 * 添加此数据的商家
	 * @return 添加此数据的商家
	 */
	public Long getCreateMerchantId() {
		return createMerchantId;
	}

	/**
	 * 添加此数据的商家
	 * @param createMerchantId 添加此数据的商家
	 */
	public void setCreateMerchantId(Long createMerchantId) {
		this.createMerchantId = createMerchantId;
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
	