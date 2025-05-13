package com.egeo.components.product.dto;

import java.io.Serializable;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
public class ChannelCategoryDTO implements Serializable {

    /**
     *
     */
    private Long id;
    /**
     *
     */
    private String channelId;
    /**
     *渠道code码:cake 蛋糕叔叔,worldBuy 全球购
     */
    private String channelCode;
    /**
     *渠道分类名称
     */
    private String channelCategoryName;
    /**
     *上级ID
     */
    private Long parentId;
    /**
     *对应系统后台加类目ID
     */
    private Long innerCategoryId;
    /**
     *渠道类目级数
     */
    private Integer channelCategoryLevel;
    /**
     *分类类别：-1分类节点全部，-2品牌全部，1分类节点，2品牌
     */
    private Integer channelCategoryType;
    /**
     *渠道分类节点/品牌id：蛋糕叔叔渠道中：品牌类别id(1-蛋糕,2-零食,3-鲜花)
     */
    private String channelCategoryId;
    /**
     *渠道分类节点/品牌id的父节点
     */
    private String channelCategoryPId;
    /**
     *备注
     */
    private String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }

    public String getChannelCategoryName() {
        return channelCategoryName;
    }

    public void setChannelCategoryName(String channelCategoryName) {
        this.channelCategoryName = channelCategoryName;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Long getInnerCategoryId() {
        return innerCategoryId;
    }

    public void setInnerCategoryId(Long innerCategoryId) {
        this.innerCategoryId = innerCategoryId;
    }

    public Integer getChannelCategoryLevel() {
        return channelCategoryLevel;
    }

    public void setChannelCategoryLevel(Integer channelCategoryLevel) {
        this.channelCategoryLevel = channelCategoryLevel;
    }

    public Integer getChannelCategoryType() {
        return channelCategoryType;
    }

    public void setChannelCategoryType(Integer channelCategoryType) {
        this.channelCategoryType = channelCategoryType;
    }

    public String getChannelCategoryId() {
        return channelCategoryId;
    }

    public void setChannelCategoryId(String channelCategoryId) {
        this.channelCategoryId = channelCategoryId;
    }

    public String getChannelCategoryPId() {
        return channelCategoryPId;
    }

    public void setChannelCategoryPId(String channelCategoryPId) {
        this.channelCategoryPId = channelCategoryPId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
