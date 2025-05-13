package com.egeo.components.product.po;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description 渠道产品报文信息表
 * @Author lsl
 * @Version V1.0
 **/
public class ChannelProductTextPO implements Serializable {
    /**
     *
     */
    private Long id;
    /**
     * 渠道code
     */
    private String channelCode;
    /**
     * 产品Id
     */
    private String productId;
    /**
     * 报文text
     */
    private String info;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 平台id
     */
    private Long platformId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
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
