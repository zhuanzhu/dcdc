package com.egeo.components.order.dto;

import com.google.common.collect.Lists;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 清美子订单信息
 */
public class QingMeiChildOrderDTO implements Serializable {

    /**
     * 子订单号
     */
    @NotBlank(message = "orderNo不可为空")
    private String orderNo;

    /**
     * 清美单号
     */
    private String thirdTradeNo;

    /**
     * 子订单金额（商品金额+运费-优惠金额）
     */
    @NotNull(message = "orderAmount不可为空")
    private BigDecimal orderAmount;

    /**
     * 子订单商品金额
     */
    @NotNull(message = "goodsAmount不可为空")
    private BigDecimal goodsAmount;

    /**
     * 子订单运费
     */
    @NotNull(message = "shippingFee不可为空")
    private BigDecimal shippingFee;

    /**
     * 子订单优惠金额
     */
    @NotNull(message = "discountAmount不可为空")
    private BigDecimal discountAmount;

    /**
     * 订单详情的url
     */
    private String orderUrl;

    /**
     * 子订单商品信息
     */
    @Valid
    @NotEmpty(message = "goodsList不可为空")
    private List<QingMeiChildItemDTO> goodsList;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }

    public BigDecimal getGoodsAmount() {
        return goodsAmount;
    }

    public void setGoodsAmount(BigDecimal goodsAmount) {
        this.goodsAmount = goodsAmount;
    }

    public BigDecimal getShippingFee() {
        return shippingFee;
    }

    public void setShippingFee(BigDecimal shippingFee) {
        this.shippingFee = shippingFee;
    }

    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }

    public List<QingMeiChildItemDTO> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<QingMeiChildItemDTO> goodsList) {
        this.goodsList = goodsList;
    }

    public String getOrderUrl() {
        return orderUrl;
    }

    public void setOrderUrl(String orderUrl) {
        this.orderUrl = orderUrl;
    }

    public String getThirdTradeNo() {
        return thirdTradeNo;
    }

    public void setThirdTradeNo(String thirdTradeNo) {
        this.thirdTradeNo = thirdTradeNo;
    }

    /**
     * 合并相同sku商品明细
     * @return
     */
    public List<QingMeiChildItemDTO> mergeItems(){
        Map<String,QingMeiChildItemDTO> itemMap=new HashMap<>();
        for (QingMeiChildItemDTO item:this.getGoodsList()){
            QingMeiChildItemDTO mergeItem=itemMap.getOrDefault(item.getSkuCode(),item);
            if (itemMap.containsKey(item.getSkuCode())){
                mergeItem.setQuantity(mergeItem.getQuantity()+item.getQuantity());
                mergeItem.setGoodsPayPrice(mergeItem.getGoodsPayPrice().add(item.getGoodsPayPrice()));
            }else {
                itemMap.put(item.getSkuCode(),item);
            }
        }
        return Lists.newArrayList(itemMap.values());
    }
}
