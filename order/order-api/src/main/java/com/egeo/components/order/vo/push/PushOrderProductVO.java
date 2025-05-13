package com.egeo.components.order.vo.push;

import com.egeo.components.order.dto.SoItemDTO;
import com.egeo.utils.EmptyUtil;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
public class PushOrderProductVO implements Serializable {
    /**
     * 商品编号
     **/
    private String productId;

    /**
     * 商品skuId
     **/
    private String skuId;

    /**
     * 商品名称
     **/
    private String productName;

    /**
     * 商品税率
     **/
    private String taxRate;

    /**
     * 销售价
     **/
    private String salePrice;

    /**
     * 购买数量
     **/
    private Integer num;

    /**
     * 商品平摊运费金额
     **/
    private String deliveryFeeAver;

    /**
     * 商品最终优惠平摊金额
     **/
    private String finalPromotionAver;

    /**
     * 预留扩展
     **/
    private String ext;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(String taxRate) {
        this.taxRate = taxRate;
    }

    public String getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(String salePrice) {
        this.salePrice = salePrice;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getDeliveryFeeAver() {
        return deliveryFeeAver;
    }

    public void setDeliveryFeeAver(String deliveryFeeAver) {
        this.deliveryFeeAver = deliveryFeeAver;
    }

    public String getFinalPromotionAver() {
        return finalPromotionAver;
    }

    public void setFinalPromotionAver(String finalPromotionAver) {
        this.finalPromotionAver = finalPromotionAver;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public PushOrderProductVO() {
    }

    public PushOrderProductVO(SoItemDTO soItemDTO) {
        this.productId = EmptyUtil.isNotEmpty(soItemDTO.getExternalProductId())?soItemDTO.getExternalProductId():soItemDTO.getPuId()+"";
        this.skuId=soItemDTO.getPuId()+"";
        this.deliveryFeeAver = "0.00";//soItemDTO.getDeliveryFeeAver() !=null?soItemDTO.getDeliveryFeeAver().toPlainString(): BigDecimal.ZERO.toPlainString();
        this.finalPromotionAver = soItemDTO.getFinalPromotionAver() !=null?soItemDTO.getFinalPromotionAver().toPlainString(): BigDecimal.ZERO.toPlainString();
        this.salePrice = soItemDTO.getPrice()+"";
        this.taxRate =soItemDTO.getTaxRate() !=null?soItemDTO.getTaxRate().toPlainString():BigDecimal.ZERO.toPlainString();
        this.num = soItemDTO.getPuCount();
        String pName = soItemDTO.getPuName().replaceAll("/","|").replaceAll("'","‘");
        this.productName = pName;
    }

    public PushOrderProductVO(SoItemDTO soItemDTO,String userChannelSource) {
        this.productId = EmptyUtil.isNotEmpty(soItemDTO.getExternalProductId())?soItemDTO.getExternalProductId():soItemDTO.getPuId()+"";
        this.skuId=soItemDTO.getPuId()+"";
        this.deliveryFeeAver = soItemDTO.getDeliveryFeeAver() !=null?soItemDTO.getDeliveryFeeAver().toPlainString(): BigDecimal.ZERO.toPlainString();
        this.finalPromotionAver = soItemDTO.getFinalPromotionAver() !=null?soItemDTO.getFinalPromotionAver().toPlainString(): BigDecimal.ZERO.toPlainString();
        this.salePrice = soItemDTO.getPrice()+"";
        this.taxRate =soItemDTO.getTaxRate() !=null?soItemDTO.getTaxRate().toPlainString():BigDecimal.ZERO.toPlainString();
        this.num = soItemDTO.getPuCount();
        String pName = soItemDTO.getPuName().replaceAll("/","|").replaceAll("'","‘");
        this.productName = pName;
    }
}
