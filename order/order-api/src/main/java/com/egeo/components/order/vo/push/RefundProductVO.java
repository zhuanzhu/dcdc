package com.egeo.components.order.vo.push;

import com.egeo.components.order.dto.SoItemDTO;
import com.egeo.utils.EmptyUtil;

import java.io.Serializable;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
public class RefundProductVO implements Serializable {

    /**
     * 产品ID
     **/
    private String productId;

    /**
     * skuID
     **/
    private String skuId;

    /**
     * 产品名称
     **/
    private String productName;

    /**
     * 售价
     **/
    private String salePrice;

    /**
     * 退款数量
     **/
    private Integer refundNum;

    /**
     * 退款金额
     **/
    private String refundAmount;

    /**
     * 预留
     **/
    private String ext;

    public RefundProductVO(){}

    public RefundProductVO(SoItemDTO soItemDTO){
        this.productId = soItemDTO.getExternalProductId();
        this.skuId = soItemDTO.getPuId()+"";
        this.productName = soItemDTO.getPuName();
        this.refundNum = soItemDTO.getRefundCount();
        this.salePrice = soItemDTO.getPrice()+"";
        if(EmptyUtil.isNotEmpty(soItemDTO.getPuName()) && soItemDTO.getPuName().indexOf("/") !=-1){
            this.productName  = soItemDTO.getPuName().replaceAll("/","|");
        }
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(String salePrice) {
        this.salePrice = salePrice;
    }

    public Integer getRefundNum() {
        return refundNum;
    }

    public void setRefundNum(Integer refundNum) {
        this.refundNum = refundNum;
    }

    public String getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(String refundAmount) {
        this.refundAmount = refundAmount;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }
}
