package com.egeo.components.order.dto;

import java.io.Serializable;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
public class WorldBuyGoodsItemsResponseDTO implements Serializable {

    private String goodsItemId;

    private String goodsBatchNo;

    private String goodsSku;

    private String goodsNum;

    private String goodsPrice;

    private String goodsSpecNum;

    private String startExpDate;

    private String endExpDate;

    public String getGoodsItemId() {
        return goodsItemId;
    }

    public void setGoodsItemId(String goodsItemId) {
        this.goodsItemId = goodsItemId;
    }

    public String getGoodsBatchNo() {
        return goodsBatchNo;
    }

    public void setGoodsBatchNo(String goodsBatchNo) {
        this.goodsBatchNo = goodsBatchNo;
    }

    public String getGoodsSku() {
        return goodsSku;
    }

    public void setGoodsSku(String goodsSku) {
        this.goodsSku = goodsSku;
    }

    public String getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(String goodsNum) {
        this.goodsNum = goodsNum;
    }

    public String getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(String goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public String getGoodsSpecNum() {
        return goodsSpecNum;
    }

    public void setGoodsSpecNum(String goodsSpecNum) {
        this.goodsSpecNum = goodsSpecNum;
    }

    public String getStartExpDate() {
        return startExpDate;
    }

    public void setStartExpDate(String startExpDate) {
        this.startExpDate = startExpDate;
    }

    public String getEndExpDate() {
        return endExpDate;
    }

    public void setEndExpDate(String endExpDate) {
        this.endExpDate = endExpDate;
    }
}
