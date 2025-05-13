package com.egeo.components.product.dto;

import java.io.Serializable;
import java.util.List;

public class PuIdAndPuAttName implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5902140415474917836L;
	/**
	 * puid
	 */
	private Long productUnitId;
	/**
	 * pu属性id
	 */
	private List<Long> puAttNameIds;
	/**
	 * pu库存数量
	 */
	private Long realStockNum;
	/**
	 * 商品状态（1、待上架 2、审核中 3、已上架 4、已下架 5、审核未通过）
	 */
	private Integer status;	
	public Long getProductUnitId() {
		return productUnitId;
	}
	public void setProductUnitId(Long productUnitId) {
		this.productUnitId = productUnitId;
	}
	public List<Long> getPuAttNameIds() {
		return puAttNameIds;
	}
	public void setPuAttNameIds(List<Long> puAttNameIds) {
		this.puAttNameIds = puAttNameIds;
	}
	public Long getRealStockNum() {
		return realStockNum;
	}
	public void setRealStockNum(Long realStockNum) {
		this.realStockNum = realStockNum;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
}
