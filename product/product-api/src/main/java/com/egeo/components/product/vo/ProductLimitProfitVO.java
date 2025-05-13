package com.egeo.components.product.vo;

import java.io.Serializable;

/**
 * 
 * @author xia
 * @date 2019-04-16 10:17:35
 */
public class ProductLimitProfitVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	/**
	 * 京东商品同步最低利润率,乘以了100
	 */
	private Integer productLimitProfit;

	public Long getId() {
		return id;
	}

	/**
	 * 主键
	 * @param id 主键
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 京东商品同步最低利润率,乘以了100
	 * @return 京东商品同步最低利润率,乘以了100
	 */
	public Integer getProductLimitProfit() {
		return productLimitProfit;
	}

	/**
	 * 京东商品同步最低利润率,乘以了100
	 * @param productLimitProfit 京东商品同步最低利润率,乘以了100
	 */
	public void setProductLimitProfit(Integer productLimitProfit) {
		this.productLimitProfit = productLimitProfit;
	}	
}
	