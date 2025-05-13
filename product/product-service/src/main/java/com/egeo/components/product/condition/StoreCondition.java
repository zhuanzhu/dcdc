package com.egeo.components.product.condition;

import com.egeo.components.product.po.StorePO;

/**
 * 
 * @author xia
 * @date 2018-09-11 15:11:44
 */
public class StoreCondition extends StorePO {
	private static final long serialVersionUID = 1L;
	
	private Long storeTreeId;
	
	private Long nodeId;
	
	private Long parentId;	
	
	private int listSort;	
	
	private String pids;	
	/**
	 * 门店菜单类目树Id
	 */
	private Long storeMenuTreeId;

	public Long getStoreMenuTreeId() {
		return storeMenuTreeId;
	}

	public void setStoreMenuTreeId(Long storeMenuTreeId) {
		this.storeMenuTreeId = storeMenuTreeId;
	}

	public Long getStoreTreeId() {
		return storeTreeId;
	}

	public void setStoreTreeId(Long storeTreeId) {
		this.storeTreeId = storeTreeId;
	}

	public Long getNodeId() {
		return nodeId;
	}

	public void setNodeId(Long nodeId) {
		this.nodeId = nodeId;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getPids() {
		return pids;
	}

	public void setPids(String pids) {
		this.pids = pids;
	}

	public int getListSort() {
		return listSort;
	}

	public void setListSort(int listSort) {
		this.listSort = listSort;
	}
	
		

}
	