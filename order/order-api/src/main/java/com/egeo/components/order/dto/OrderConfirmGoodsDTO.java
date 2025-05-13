package com.egeo.components.order.dto;

/**
 * Created by 0.0 on 2018/9/13.
 */
public class OrderConfirmGoodsDTO {

    private Long LimitBuyNum;
    private Long cartItemId;
    private String skuDesc;
    public Long getCartItemId() {
        return cartItemId;
    }

    public void setCartItemId(Long cartItemId) {
        this.cartItemId = cartItemId;
    }

    //是否为电子卡券
    private boolean isCard;
    private Long puId;
    private String puImg;
    private String puName;
    private Integer num;//数量
    private double price;//单价
    private Long standardUnitId; // su商品id
    private boolean isUnit;  //是否是unit商品,true是,false不是
    private Integer buyType;
    private String externalSkuId;
    private String errMessage;

    private String externalProductId;

    private Boolean store_isCombineOrders;

    private String goodType;

    private String distribution_rule_id;

    private Integer source;

    public String getSkuDesc() {
		return skuDesc;
	}

	public void setSkuDesc(String skuDesc) {
		this.skuDesc = skuDesc;
	}

	public Long getLimitBuyNum() {
        return LimitBuyNum;
    }

    public void setLimitBuyNum(Long limitBuyNum) {
        LimitBuyNum = limitBuyNum;
    }

    public String getErrMessage() {
        return errMessage;
    }

    public void setErrMessage(String errMessage) {
        this.errMessage = errMessage;
    }

    public String getExternalSkuId() {
        return externalSkuId;
    }

    public void setExternalSkuId(String externalSkuId) {
        this.externalSkuId = externalSkuId;
    }

    public Integer getBuyType() {
        return buyType;
    }

    public void setBuyType(Integer buyType) {
        this.buyType = buyType;
    }

    private Integer saleWay;
    private Long preSaleDay;
    /**
     * 是否自营，0-否 1-是
     */
    private Integer isOwnMerchant;

    public Integer getSaleWay() {
        return saleWay;
    }

    public void setSaleWay(Integer saleWay) {
        this.saleWay = saleWay;
    }

    public Long getPreSaleDay() {
        return preSaleDay;
    }

    public void setPreSaleDay(Long preSaleDay) {
        this.preSaleDay = preSaleDay;
    }

    public boolean isUnit() {
        return isUnit;
    }

    public void setUnit(boolean unit) {
        isUnit = unit;
    }

    public Long getStandardUnitId() {
        return standardUnitId;
    }
    public void setStandardUnitId(Long standardUnitId) {
        this.standardUnitId = standardUnitId;
    }
    public Long getPuId() {
        return puId;
    }
    public void setPuId(Long puId) {
        this.puId = puId;
    }
    public String getPuImg() {
        return puImg;
    }
    public void setPuImg(String puImg) {
        this.puImg = puImg;
    }
    public String getPuName() {
        return puName;
    }
    public void setPuName(String puName) {
        this.puName = puName;
    }
    public Integer getNum() {
        return num;
    }
    public void setNum(Integer num) {
        this.num = num;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }

	public Integer getIsOwnMerchant() {
		return isOwnMerchant;
	}

	public void setIsOwnMerchant(Integer isOwnMerchant) {
		this.isOwnMerchant = isOwnMerchant;
	}

    public boolean isCard() {
        return isCard;
    }

    public void setCard(boolean card) {
        isCard = card;
    }

    public String getExternalProductId() {
        return externalProductId;
    }

    public void setExternalProductId(String externalProductId) {
        this.externalProductId = externalProductId;
    }

    public Boolean getStore_isCombineOrders() {
        return store_isCombineOrders;
    }

    public void setStore_isCombineOrders(Boolean store_isCombineOrders) {
        this.store_isCombineOrders = store_isCombineOrders;
    }

    public String getGoodType() {
        return goodType;
    }

    public void setGoodType(String goodType) {
        this.goodType = goodType;
    }

    public String getDistribution_rule_id() {
        return distribution_rule_id;
    }

    public void setDistribution_rule_id(String distribution_rule_id) {
        this.distribution_rule_id = distribution_rule_id;
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }
}
