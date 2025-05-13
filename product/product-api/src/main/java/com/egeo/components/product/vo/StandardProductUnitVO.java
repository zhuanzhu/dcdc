package com.egeo.components.product.vo;

import java.io.Serializable;

/**
 * 
 * @author min
 * @date 2018-01-05 18:51:02
 */
public class StandardProductUnitVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	/**
	 * spu编号
	 */
	private String productSerialNumber;
	/**
	 * 产品类目
	 */
	private String productCategory;	
	private String name;
	/**
	 * 
	 */
	private String chineseName;

	private String referlink;
	
	private String extend;
	
	public String getReferlink() {
		return referlink;
	}

	public void setReferlink(String referlink) {
		this.referlink = referlink;
	}

	public String getExtend() {
		return extend;
	}

	public void setExtend(String extend) {
		this.extend = extend;
	}

	private Long enterpriseId;
	private Long supplierId;
	public Long getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Long supplierId) {
		this.supplierId = supplierId;
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
	 * spu编号
	 * @return spu编号
	 */
	public String getProductSerialNumber() {
		return productSerialNumber;
	}

	public Long getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	/**
	 * spu编号
	 * @param productSerialNumber spu编号
	 */
	public void setProductSerialNumber(String productSerialNumber) {
		this.productSerialNumber = productSerialNumber;
	}	
	/**
	 * 
	 * @return 
	 */
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @param name 
	 */
	public void setName(String name) {
		this.name = name;
	}	
	/**
	 * 
	 * @return 
	 */
	public String getChineseName() {
		return chineseName;
	}

	/**
	 * 
	 * @param chineseName 
	 */
	public void setChineseName(String chineseName) {
		this.chineseName = chineseName;
	}	

	public String getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}	
	
}
	