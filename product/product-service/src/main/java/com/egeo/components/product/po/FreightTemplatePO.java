package com.egeo.components.product.po;


import java.util.Date;

/**
 * 
 * @author min
 * @date 2018-05-11 10:35:36
 */
public class FreightTemplatePO {


	private Long id;

	/**
	 * 运费模版名称
	 */
	private String name;	

	/**
	 * 运营方id
	 */
	private Long merchantId;	

	/**
	 * 发货说明
	 */
	private String shipmentsExplain;	

	/**
	 * 是否包邮
	 */
	private Integer isExemption;	

	/**
	 * 是否启用：0否1是
	 */
	private Integer isValid;	

	/**
	 * 创建时间:创建记录时数据库会自动set值
	 */
	private Date createTime;	

	/**
	 * 修改时间:更新时数据库会自动set值
	 */
	private Date updateTime;	

	/**
	 * 平台id
	 */
	private Long platformId;	
	/**
	 * 门店id
	 */
	private Long storeId;
	
	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
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
	 * 运费模版名称
	 * @return 运费模版名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * 运费模版名称
	 * @param name 运费模版名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 运营方id
	 * @return 运营方id
	 */
	public Long getMerchantId() {
		return merchantId;
	}

	/**
	 * 运营方id
	 * @param merchantId 运营方id
	 */
	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}

	/**
	 * 发货说明
	 * @return 发货说明
	 */
	public String getShipmentsExplain() {
		return shipmentsExplain;
	}

	/**
	 * 发货说明
	 * @param shipmentsExplain 发货说明
	 */
	public void setShipmentsExplain(String shipmentsExplain) {
		this.shipmentsExplain = shipmentsExplain;
	}

	/**
	 * 是否包邮
	 * @return 是否包邮
	 */
	public Integer getIsExemption() {
		return isExemption;
	}

	/**
	 * 是否包邮
	 * @param isExemption 是否包邮
	 */
	public void setIsExemption(Integer isExemption) {
		this.isExemption = isExemption;
	}

	/**
	 * 是否启用：0否1是
	 * @return 是否启用：0否1是
	 */
	public Integer getIsValid() {
		return isValid;
	}

	/**
	 * 是否启用：0否1是
	 * @param isValid 是否启用：0否1是
	 */
	public void setIsValid(Integer isValid) {
		this.isValid = isValid;
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
	