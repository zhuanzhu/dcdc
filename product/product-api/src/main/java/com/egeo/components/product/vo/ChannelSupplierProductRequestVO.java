package com.egeo.components.product.vo;

import com.egeo.utils.StringUtils;

import java.io.Serializable;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
public class ChannelSupplierProductRequestVO implements Serializable {

    /**
     * 渠道:cake 蛋糕叔叔 worldBuy全球购
     **/
    private String channelCode;

    /**
     * 渠道分类节点/品牌id：蛋糕叔叔渠道中：品牌类别id(1-蛋糕,5-零食,8-鲜花)
     **/
    private String productType;

    /**
     * 节点id
     **/
    private String channelCategoryId;

    /**
     * 商品名称
     **/
    private String keyword;

    private Integer pageNo;
    private Integer pageSize;
    private String catId1;
    private String catId2;

    private String catId3;

    private Long enterpriseId;

    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }

    public String getProductType() {
        if(StringUtils.isNotEmpty(productType)){
            return productType;
        }
        return catId1;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getChannelCategoryId() {
        return channelCategoryId;
    }

    public void setChannelCategoryId(String channelCategoryId) {
        this.channelCategoryId = channelCategoryId;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getCatId2() {
        return catId2;
    }

    public void setCatId2(String catId2) {
        this.catId2 = catId2;
    }

    public String getCatId3() {
        return catId3;
    }

    public void setCatId3(String catId3) {
        this.catId3 = catId3;
    }

    public String getCatId1() {
        return catId1;
    }

    public void setCatId1(String catId1) {
        this.catId1 = catId1;
    }

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }
}
