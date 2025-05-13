package com.egeo.components.product.vo;

import com.egeo.components.product.dto.ChannelPriceDTO;

/**
 *
 * @author tan
 * @date 2019-03-29 10:17:52
 */
public class ChannelPriceAuditingVO extends ChannelPriceDTO {
	private static final long serialVersionUID = 1L;

	/**
	 * 产品名称
	 */
	private String name;
	/**
	 * 类目名称
	 */
	private String categoryName;
	/**
	 *渠道价格
	 */
	private String channelPrice;
	/**
	 *市场价
	 */
	private String marketPrice;
	/**
	 *企业价格
	 */
	private String enterprisePrice;
	/**
	 * 毛利
	 */
	private String profit;

	/**
	 *是否自营
	 */
	private Integer isSelf;
	/**
	 *所属渠道
	 */
	private String channelCode;

	private String productId;

	/**
	 *协议价格
	 */
	private String price;
	/**
	 *销售价
	 */
	private String salePrice;


	public ChannelPriceAuditingVO() {
	}

	public ChannelPriceAuditingVO(ChannelPriceDTO data) {
		this.setAudit(data.getAudit());
		this.setEnterpriseId(data.getEnterpriseId());
		this.setId(data.getId());
		this.setPid(data.getPid());
		this.setPriceType(data.getPriceType());
		this.setPriceValue(data.getPriceValue());
		this.setSpuId(data.getSpuId());
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getChannelPrice() {
		return channelPrice;
	}

	public void setChannelPrice(String channelPrice) {
		this.channelPrice = channelPrice;
	}

	@Override
	public String getChannelCode() {
		return channelCode;
	}

	@Override
	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}

	public String getMarketPrice() {
		return marketPrice;
	}
	public void setMarketPrice(String marketPrice) {
		this.marketPrice = marketPrice;
	}
	public String getEnterprisePrice() {
		return enterprisePrice;
	}
	public void setEnterprisePrice(String enterprisePrice) {
		this.enterprisePrice = enterprisePrice;
	}
	public String getProfit() {
		return profit;
	}
	public void setProfit(String profit) {
		this.profit = profit;
	}
	public Integer getIsSelf() {
		return isSelf;
	}
	public void setIsSelf(Integer isSelf) {
		this.isSelf = isSelf;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(String salePrice) {
		this.salePrice = salePrice;
	}
}
