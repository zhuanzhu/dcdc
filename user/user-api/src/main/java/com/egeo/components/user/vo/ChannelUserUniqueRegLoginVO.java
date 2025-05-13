package com.egeo.components.user.vo;

import java.io.Serializable;

/**
 * @Description todo
 * @Author lsl
 * @Date 2024/12/20 16:50
 * @Version V1.0
 **/
public class ChannelUserUniqueRegLoginVO implements Serializable {

    private String type;
    private String channelUserUnique;
    private String identityCode;
    private Boolean checkIdentityCode;
    private String channelName;
    private String campaignCode;
    private String registerShopCode;
    private String loginType;
    private Long keyMessage;
    private Long platformId;
    private String channelsource;

    private Long companyId;

    private String deviceId;

    private String nickName;

    /**
     * 头像
     */
    private String headPicUrl;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getChannelUserUnique() {
        return channelUserUnique;
    }

    public void setChannelUserUnique(String channelUserUnique) {
        this.channelUserUnique = channelUserUnique;
    }

    public String getIdentityCode() {
        return identityCode;
    }

    public void setIdentityCode(String identityCode) {
        this.identityCode = identityCode;
    }

    public Boolean getCheckIdentityCode() {
        return checkIdentityCode;
    }

    public void setCheckIdentityCode(Boolean checkIdentityCode) {
        this.checkIdentityCode = checkIdentityCode;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getCampaignCode() {
        return campaignCode;
    }

    public void setCampaignCode(String campaignCode) {
        this.campaignCode = campaignCode;
    }

    public String getRegisterShopCode() {
        return registerShopCode;
    }

    public void setRegisterShopCode(String registerShopCode) {
        this.registerShopCode = registerShopCode;
    }

    public String getLoginType() {
        return loginType;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }

    public Long getKeyMessage() {
        return keyMessage;
    }

    public void setKeyMessage(Long keyMessage) {
        this.keyMessage = keyMessage;
    }

    public Long getPlatformId() {
        return platformId;
    }

    public void setPlatformId(Long platformId) {
        this.platformId = platformId;
    }

    public String getChannelsource() {
        return channelsource;
    }

    public void setChannelsource(String channelsource) {
        this.channelsource = channelsource;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getHeadPicUrl() {
        return headPicUrl;
    }

    public void setHeadPicUrl(String headPicUrl) {
        this.headPicUrl = headPicUrl;
    }
}
