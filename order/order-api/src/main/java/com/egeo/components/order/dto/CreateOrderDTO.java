package com.egeo.components.order.dto;

import java.io.Serializable;
import java.util.List;

public class CreateOrderDTO implements Serializable {
    private List<Long> puIdList;
    private Long exchangeId;
    private  Long exchangeCouponUnitId;
    Long exchangeCouponBatchId;
    private Integer orderType;
    private Long storeId;
    private Long receiveAddressId;
    private Integer type;
    private String cartItemIds;
    private Long puId;
    private Integer num;
    private Integer useFubi;
    private String remark;
    private Long invoiceId;
    private Long userId;
    private Long platformId;
    private String deviceId;
    private Integer orderChannel;
    private String ip;
    private String os;
    private String phoneModel;
    private String versionCode;
    private String userName;
    private String mac;
    private Long companyId;
    private String phone;
    private Integer couponType;
    private Long couponUnitId;
    private String deliveryPrice;
    private String channelProductId;
    private String thirdOrderJsonStr;
    private Integer source;

    public List<Long> getPuIdList() {
        return puIdList;
    }

    public void setPuIdList(List<Long> puIdList) {
        this.puIdList = puIdList;
    }

    public Long getExchangeId() {
        return exchangeId;
    }

    public void setExchangeId(Long exchangeId) {
        this.exchangeId = exchangeId;
    }

    public Long getExchangeCouponUnitId() {
        return exchangeCouponUnitId;
    }

    public void setExchangeCouponUnitId(Long exchangeCouponUnitId) {
        this.exchangeCouponUnitId = exchangeCouponUnitId;
    }

    public Long getExchangeCouponBatchId() {
        return exchangeCouponBatchId;
    }

    public void setExchangeCouponBatchId(Long exchangeCouponBatchId) {
        this.exchangeCouponBatchId = exchangeCouponBatchId;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public Long getReceiveAddressId() {
        return receiveAddressId;
    }

    public void setReceiveAddressId(Long receiveAddressId) {
        this.receiveAddressId = receiveAddressId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getCartItemIds() {
        return cartItemIds;
    }

    public void setCartItemIds(String cartItemIds) {
        this.cartItemIds = cartItemIds;
    }

    public Long getPuId() {
        return puId;
    }

    public void setPuId(Long puId) {
        this.puId = puId;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getUseFubi() {
        return useFubi;
    }

    public void setUseFubi(Integer useFubi) {
        this.useFubi = useFubi;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Long invoiceId) {
        this.invoiceId = invoiceId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getPlatformId() {
        return platformId;
    }

    public void setPlatformId(Long platformId) {
        this.platformId = platformId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public Integer getOrderChannel() {
        return orderChannel;
    }

    public void setOrderChannel(Integer orderChannel) {
        this.orderChannel = orderChannel;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getPhoneModel() {
        return phoneModel;
    }

    public void setPhoneModel(String phoneModel) {
        this.phoneModel = phoneModel;
    }

    public String getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(String versionCode) {
        this.versionCode = versionCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getCouponType() {
        return couponType;
    }

    public void setCouponType(Integer couponType) {
        this.couponType = couponType;
    }

    public Long getCouponUnitId() {
        return couponUnitId;
    }

    public void setCouponUnitId(Long couponUnitId) {
        this.couponUnitId = couponUnitId;
    }

    public String getDeliveryPrice() {
        return deliveryPrice;
    }

    public void setDeliveryPrice(String deliveryPrice) {
        this.deliveryPrice = deliveryPrice;
    }

    public String getThirdOrderJsonStr() {
        return thirdOrderJsonStr;
    }

    public void setThirdOrderJsonStr(String thirdOrderJsonStr) {
        this.thirdOrderJsonStr = thirdOrderJsonStr;
    }


    public String getChannelProductId() {
        return channelProductId;
    }

    public void setChannelProductId(String channelProductId) {
        this.channelProductId = channelProductId;
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }
}
