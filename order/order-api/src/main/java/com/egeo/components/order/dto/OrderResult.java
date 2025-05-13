package com.egeo.components.order.dto;

import java.math.BigDecimal;
import java.util.List;

import com.egeo.components.product.dto.MerchantDTO;
import com.egeo.components.product.dto.StoreDTO;

/**
 * Created by 0.0 on 2018/9/13.
 */
public class OrderResult {

    private Integer isPayByFuBiOnly;

    private BigDecimal payByFuBiAmount;

    public BigDecimal getPayByFuBiAmount() {
        return payByFuBiAmount;
    }

    public void setPayByFuBiAmount(BigDecimal payByFuBiAmount) {
        this.payByFuBiAmount = payByFuBiAmount;
    }

    public Integer getIsPayByFuBiOnly() {
        return isPayByFuBiOnly;
    }

    public void setIsPayByFuBiOnly(Integer isPayByFuBiOnly) {
        this.isPayByFuBiOnly = isPayByFuBiOnly;
    }

    /**
     * 仅现金支付的金额
     */
    private BigDecimal orderAmountOnlyCash;


    public BigDecimal getOrderAmountOnlyCash() {
        return orderAmountOnlyCash;
    }

    public void setOrderAmountOnlyCash(BigDecimal orderAmountOnlyCash) {
        this.orderAmountOnlyCash = orderAmountOnlyCash;
    }

    private boolean isSuccess;
    private String error;
    private List<OrderConfirmGoodsDTO> goodsList;
    private List<OrderConfirmGoodsDTO> jdDownGoods;

    public List<OrderConfirmGoodsDTO> getJdDownGoods() {
        return jdDownGoods;
    }

    public void setJdDownGoods(List<OrderConfirmGoodsDTO> jdDownGoods) {
        this.jdDownGoods = jdDownGoods;
    }

    private List<LimitRuleRecordDTO> limitRuleRecordList;
    private int goodsAccount ;
    private BigDecimal orderAmount;
    private MerchantDTO merchant;
    private StoreDTO store;
    private boolean needCountDelivery;
    private boolean useCoupon;
    private BigDecimal deliveryAmount;

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }


    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public int getGoodsAccount() {
        return goodsAccount;
    }

    public void setGoodsAccount(int goodsAccount) {
        this.goodsAccount = goodsAccount;
    }

    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }

    public List<OrderConfirmGoodsDTO> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<OrderConfirmGoodsDTO> goodsList) {
        this.goodsList = goodsList;
    }

    public List<LimitRuleRecordDTO> getLimitRuleRecordList() {
        return limitRuleRecordList;
    }

    public void setLimitRuleRecordList(List<LimitRuleRecordDTO> limitRuleRecordList) {
        this.limitRuleRecordList = limitRuleRecordList;
    }

	public MerchantDTO getMerchant() {
		return merchant;
	}

	public void setMerchant(MerchantDTO merchant) {
		this.merchant = merchant;
	}

	public StoreDTO getStore() {
		return store;
	}

	public void setStore(StoreDTO store) {
		this.store = store;
	}

	public boolean isNeedCountDelivery() {
		return needCountDelivery;
	}

	public void setNeedCountDelivery(boolean needCountDelivery) {
		this.needCountDelivery = needCountDelivery;
	}

	public boolean isUseCoupon() {
		return useCoupon;
	}

	public void setUseCoupon(boolean useCoupon) {
		this.useCoupon = useCoupon;
	}

    public BigDecimal getDeliveryAmount() {
        return deliveryAmount;
    }

    public void setDeliveryAmount(BigDecimal deliveryAmount) {
        this.deliveryAmount = deliveryAmount;
    }


}
