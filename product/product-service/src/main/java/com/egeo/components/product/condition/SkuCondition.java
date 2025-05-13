package com.egeo.components.product.condition;

import com.egeo.components.product.po.SkuPO;

/**
 * 
 * @author min
 * @date 2018-01-06 11:34:03
 */
public class SkuCondition extends SkuPO {
	private static final long serialVersionUID = 1L;
	private Long attValueId;

	public Long getAttValueId() {
		return attValueId;
	}

	public void setAttValueId(Long attValueId) {
		this.attValueId = attValueId;
	}

	/**
	 * spu名称
	 */
	private Long merchantProductId;
	private Long standardUnitId;
	private Long productUnitId;
	private Long commodityProductUnitId;
	private String name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public Long getMerchantProductId() {
		return merchantProductId;
	}

	public void setMerchantProductId(Long merchantProductId) {
		this.merchantProductId = merchantProductId;
	}

	public Long getStandardUnitId() {
		return standardUnitId;
	}

	public void setStandardUnitId(Long standardUnitId) {
		this.standardUnitId = standardUnitId;
	}

	public Long getProductUnitId() {
		return productUnitId;
	}

	public void setProductUnitId(Long productUnitId) {
		this.productUnitId = productUnitId;
	}

	public Long getCommodityProductUnitId() {
		return commodityProductUnitId;
	}

	public void setCommodityProductUnitId(Long commodityProductUnitId) {
		this.commodityProductUnitId = commodityProductUnitId;
	}
}
	