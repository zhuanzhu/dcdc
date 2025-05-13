package com.egeo.components.product.po;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * @Description 渠道产品图片关联表
 * @Author lsl
 * @Version V1.0
 **/
public class ChannelProductPicturePO implements Serializable {

    /**
     *
     */
    private Long id;
    /**
     *
     */
    private String productId;
    /**
     *渠道code
     */
    private String channelCode;
    /**
     *类型：1、列表图片 2、轮播图片
     */
    private Integer type;
    /**
     *图片url
     */
    private String pictureUrl;
    /**
     *排序号
     */
    private Integer sortValue;
    /**
     *
     */
    private Date createTime;
    /**
     *
     */
    private Date updateTime;
    /**
     *平台id
     */
    private Long platformId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public Integer getSortValue() {
        return sortValue;
    }

    public void setSortValue(Integer sortValue) {
        this.sortValue = sortValue;
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

    public Long getPlatformId() {
        return platformId;
    }

    public void setPlatformId(Long platformId) {
        this.platformId = platformId;
    }
}
