package com.egeo.components.product.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Description 购物卡模板与商品关系表
 * @Author lsl
 * @Version V1.0
 **/
public class StandardUnitCardBaseSuDTO implements Serializable {


    /**
     *'主键'
     **/
    private Long id;

    /**
     *购物卡（卡模板中的）id
     **/
    private Long cardBaseId;
    /**
     *'商品id'
     **/
    private Long standardUnitId;
    /**
     *'排序'
     **/
    private Integer sortValue;
    /**
     *来源渠道，默认1
     **/
    private Integer source;
    /**
     *
     **/
    private String snapshot;
    /**
     *'第三方skuId'
     **/
    private String thirdSkuId;

    /**
     *'京东产品是否可售:1-可售；0-不可售; 默认可售'
     **/
    private Integer sellState;
    /**
     *'京东商品可售状态校验时间'
     **/
    private Date checkTime;
    /**
     *'排序价格'
     **/
    private BigDecimal sortPrice;

    /**
     *'排序销售数量'
     **/
    private Integer sortSalesNum;
    /**
     *商品关键字/sku名称
     **/
    private String thirdSkuName;
    /**
     * 创建时间
     **/
    private Date createTime ;
    /**
     * 更新时间
     **/
    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCardBaseId() {
        return cardBaseId;
    }

    public void setCardBaseId(Long cardBaseId) {
        this.cardBaseId = cardBaseId;
    }

    public Long getStandardUnitId() {
        return standardUnitId;
    }

    public void setStandardUnitId(Long standardUnitId) {
        this.standardUnitId = standardUnitId;
    }

    public Integer getSortValue() {
        return sortValue;
    }

    public void setSortValue(Integer sortValue) {
        this.sortValue = sortValue;
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public String getSnapshot() {
        return snapshot;
    }

    public void setSnapshot(String snapshot) {
        this.snapshot = snapshot;
    }

    public String getThirdSkuId() {
        return thirdSkuId;
    }

    public void setThirdSkuId(String thirdSkuId) {
        this.thirdSkuId = thirdSkuId;
    }

    public Integer getSellState() {
        return sellState;
    }

    public void setSellState(Integer sellState) {
        this.sellState = sellState;
    }

    public Date getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(Date checkTime) {
        this.checkTime = checkTime;
    }

    public BigDecimal getSortPrice() {
        return sortPrice;
    }

    public void setSortPrice(BigDecimal sortPrice) {
        this.sortPrice = sortPrice;
    }

    public Integer getSortSalesNum() {
        return sortSalesNum;
    }

    public void setSortSalesNum(Integer sortSalesNum) {
        this.sortSalesNum = sortSalesNum;
    }

    public String getThirdSkuName() {
        return thirdSkuName;
    }

    public void setThirdSkuName(String thirdSkuName) {
        this.thirdSkuName = thirdSkuName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
