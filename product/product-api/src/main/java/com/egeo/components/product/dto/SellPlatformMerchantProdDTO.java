package com.egeo.components.product.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 
 * @author min
 * @date 2018-01-06 14:42:44
 */
public class SellPlatformMerchantProdDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	/**
	 * 平台id
	 */
	private Long sellPlatformId;	

	/**
	 * 商品id
	 */
	private Long merchantProductId;	

	/**
	 * 销售价格
	 */
	private BigDecimal salePrice;	

	/**
	 * 链接
	 */
	private String path;	

	/**
	 * 平台名称
	 */
	private String name;

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
	 * 平台id
	 * @return 平台id
	 */
	public Long getSellPlatformId() {
		return sellPlatformId;
	}

	/**
	 * 平台id
	 * @param sellPlatformId 平台id
	 */
	public void setSellPlatformId(Long sellPlatformId) {
		this.sellPlatformId = sellPlatformId;
	}
	/**
	 * 商品id
	 * @return 商品id
	 */
	public Long getMerchantProductId() {
		return merchantProductId;
	}

	/**
	 * 商品id
	 * @param merchantProductId 商品id
	 */
	public void setMerchantProductId(Long merchantProductId) {
		this.merchantProductId = merchantProductId;
	}
	/**
	 * 销售价格
	 * @return 销售价格
	 */
	public BigDecimal getSalePrice() {
		return salePrice;
	}

	/**
	 * 销售价格
	 * @param salePrice 销售价格
	 */
	public void setSalePrice(BigDecimal salePrice) {
		this.salePrice = salePrice;
	}
	/**
	 * 链接
	 * @return 链接
	 */
	public String getPath() {
		return path;
	}

	/**
	 * 链接
	 * @param path 链接
	 */
	public void setPath(String path) {
		this.path = path;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
	