package com.egeo.components.product.vo;

import java.io.Serializable;

/**
 * 
 * @author min
 * @date 2018-09-11 14:59:36
 */
public class StoreMenuNodeStandardUnitVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	/**
	 * 门店菜单节点id
	 */
	private Long storeMenuNodeId;
	/**
	 * 商品id
	 */
	private Long standardUnitId;
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
	 * 门店菜单节点id
	 * @return 门店菜单节点id
	 */
	public Long getStoreMenuNodeId() {
		return storeMenuNodeId;
	}

	/**
	 * 门店菜单节点id
	 * @param storeMenuNodeId 门店菜单节点id
	 */
	public void setStoreMenuNodeId(Long storeMenuNodeId) {
		this.storeMenuNodeId = storeMenuNodeId;
	}	
	/**
	 * 商品id
	 * @return 商品id
	 */
	public Long getStandardUnitId() {
		return standardUnitId;
	}

	/**
	 * 商品id
	 * @param standardUnitId 商品id
	 */
	public void setStandardUnitId(Long standardUnitId) {
		this.standardUnitId = standardUnitId;
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
	