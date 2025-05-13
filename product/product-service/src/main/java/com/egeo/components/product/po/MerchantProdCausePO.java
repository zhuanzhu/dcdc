package com.egeo.components.product.po;


import java.util.Date;

/**
 * 
 * @author min
 * @date 2018-01-06 09:20:44
 */
public class MerchantProdCausePO {


	private Long id;

	/**
	 * 商品未通过原因
	 */
	private String cause;	

	/**
	 * 排序号
	 */
	private Integer sortValue;	

	/**
	 * 创建时间:创建记录时数据库会自动set值
	 */
	private Date createTime;	

	/**
	 * 平台id
	 */
	private Long platformId;	

	/**
	 * 商品id
	 */
	private Long merchantProdId;	

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
	 * 商品未通过原因
	 * @return 商品未通过原因
	 */
	public String getCause() {
		return cause;
	}

	/**
	 * 商品未通过原因
	 * @param cause 商品未通过原因
	 */
	public void setCause(String cause) {
		this.cause = cause;
	}

	/**
	 * 排序号
	 * @return 排序号
	 */
	public Integer getSortValue() {
		return sortValue;
	}

	/**
	 * 排序号
	 * @param sortValue 排序号
	 */
	public void setSortValue(Integer sortValue) {
		this.sortValue = sortValue;
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
	 * 商品id
	 * @return 商品id
	 */
	public Long getMerchantProdId() {
		return merchantProdId;
	}

	/**
	 * 商品id
	 * @param merchantProdId 商品id
	 */
	public void setMerchantProdId(Long merchantProdId) {
		this.merchantProdId = merchantProdId;
	}
}
	