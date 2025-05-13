package com.egeo.components.order.vo;

import java.math.BigDecimal;
import java.util.List;

/**
 * 后台查询商品详情时使用的订单项详情vo
 * @author GRACIA
 *
 */
public class SoDetailItemVo {

	private Long id;
	private Long skuId;
	private String code;
	private String name;
	private List<String> skuAttValues;
	/**
	 * 单价
	 */
	private BigDecimal orderAmount ;
	private Integer buyCount;
	/**
	 * 总额
	 */
	private BigDecimal productAmount ;
	private boolean checked = false;
	
	
	public List<String> getSkuAttValues() {
		return skuAttValues;
	}
	public void setSkuAttValues(List<String> skuAttValues) {
		this.skuAttValues = skuAttValues;
	}
	public Long getSkuId() {
		return skuId;
	}
	public void setSkuId(Long skuId) {
		this.skuId = skuId;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getBuyCount() {
		return buyCount;
	}
	public void setBuyCount(Integer buyCount) {
		this.buyCount = buyCount;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public BigDecimal getOrderAmount() {
		return orderAmount;
	}
	public void setOrderAmount(BigDecimal orderAmount) {
		this.orderAmount = orderAmount;
	}
	public BigDecimal getProductAmount() {
		return productAmount;
	}
	public void setProductAmount(BigDecimal productAmount) {
		this.productAmount = productAmount;
	}
	
	
	
}
