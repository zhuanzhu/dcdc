package com.egeo.components.product.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author xiaping
 * @date 2017-08-12 13:41:08
 */
public class ProductCauseVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	/**
	 * 产品Id
	 */

	private Long productId;		 
	/**
	 * 产品未通过原因
	 */

	private String cause;		 
	/**
	 * 排序号
	 */

	private Integer sortValue;		 
	/**
	 * 平台id
	 */

	private Long platformId;		 
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
	 * 产品Id
	 * @return 产品Id
	 */
	public Long getProductId() {
		return productId;
	}

	/**
	 * 产品Id
	 * @param productId 产品Id
	 */
	public void setProductId(Long productId) {
		this.productId = productId;
	}	
	/**
	 * 产品未通过原因
	 * @return 产品未通过原因
	 */
	public String getCause() {
		return cause;
	}

	/**
	 * 产品未通过原因
	 * @param cause 产品未通过原因
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
	