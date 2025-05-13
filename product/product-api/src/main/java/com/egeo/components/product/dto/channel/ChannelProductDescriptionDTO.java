package com.egeo.components.product.dto.channel;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
public class ChannelProductDescriptionDTO implements Serializable {

    /**
     *
     */
    private Long id;
    /**
     *渠道code
     */
    private String channelCode;
    /**
     *商品ID
     */
    private String productId;
    /**
     *产品描述
     */
    private String content;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
