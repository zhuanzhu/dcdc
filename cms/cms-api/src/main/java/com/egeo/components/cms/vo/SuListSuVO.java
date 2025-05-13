package com.egeo.components.cms.vo;

/**
 * 商品列表商品VO
 * 
 * @author graci
 *
 */
public class SuListSuVO {

	/**
	 * 是否自营,0不是,1是
	 */
	private Integer isOwnMerchant;

	public Integer getIsOwnMerchant() {
		return isOwnMerchant;
	}

	public void setIsOwnMerchant(Integer isOwnMerchant) {
		this.isOwnMerchant = isOwnMerchant;
	}

	/**
	 * suid
	 */
	private Long suId;
	/**
	 * su图片
	 */
	private String suImgUrl;
	/**
	 * su名
	 */
	private String suName;
	/**
	 * 售价
	 */
	private Double salePrice;
	/**
	 * 市场价
	 */
	private Double marketPrice;
	
	private Long suTmplId;
	/**
	 * 销售方式：1正常销售、2团购、3预售
	 */
	private Integer saleWay;	

	public Integer getSaleWay() {
		return saleWay;
	}

	public void setSaleWay(Integer saleWay) {
		this.saleWay = saleWay;
	}

	public Long getSuTmplId() {
		return suTmplId;
	}

	public void setSuTmplId(Long suTmplId) {
		this.suTmplId = suTmplId;
	}

	public Long getSuId() {
		return suId;
	}

	public void setSuId(Long suId) {
		this.suId = suId;
	}

	public String getSuImgUrl() {
		return suImgUrl;
	}

	public void setSuImgUrl(String suImgUrl) {
		this.suImgUrl = suImgUrl;
	}

	public String getSuName() {
		return suName;
	}

	public void setSuName(String suName) {
		this.suName = suName;
	}

	public Double getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(Double salePrice) {
		this.salePrice = salePrice;
	}

	public Double getMarketPrice() {
		return marketPrice;
	}

	public void setMarketPrice(Double marketPrice) {
		this.marketPrice = marketPrice;
	}

}
