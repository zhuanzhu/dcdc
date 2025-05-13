package com.egeo.components.product.vo;

import java.io.Serializable;

/**
 * 
 * @author min
 * @date 2018-09-11 14:59:34
 */
public class StandardUnitRecordStoreVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	/**
	 * 门店id
	 */
	private Long storeId;
	/**
	 * 商品记录id
	 */
	private Long standardUnitRecordId;
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
	 * 门店id
	 * @return 门店id
	 */
	public Long getStoreId() {
		return storeId;
	}

	/**
	 * 门店id
	 * @param storeId 门店id
	 */
	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}	
	/**
	 * 商品记录id
	 * @return 商品记录id
	 */
	public Long getStandardUnitRecordId() {
		return standardUnitRecordId;
	}

	/**
	 * 商品记录id
	 * @param standardUnitRecordId 商品记录id
	 */
	public void setStandardUnitRecordId(Long standardUnitRecordId) {
		this.standardUnitRecordId = standardUnitRecordId;
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
	