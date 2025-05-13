package com.egeo.components.order.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
public class CreateOrderRequestExtendsVO extends CreateOrderRequestVO implements Serializable {

    /**
     * 根据IP获取到的mac地址
     **/
    private String mac;


    /**
     * 渠道来源=orderChannel
     **/
    private Integer f;

    /**
     * 应用版本号
     **/
    private String v;

    /**
     * 手机型号
     **/
    private String phoneModel;

    /**
     * 设备编号
     **/
    private String deviceId;

    /**
     * 操作系统
     **/
    private String os;

    /**
     * 用户ID
     **/
    private Long userId;

    /**
     * 用户名称
     **/
    private String userName;

    /**
     * 公司ID
     **/
    private Long companyId;

    /**
     * 平台ID
     **/
    private Long platformId;

    /**
     * IP地址
     **/
    private String ip;

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public Integer getF() {
        return f;
    }

    public void setF(Integer f) {
        this.f = f;
    }

    public String getV() {
        return v;
    }

    public void setV(String v) {
        this.v = v;
    }

    public String getPhoneModel() {
        return phoneModel;
    }

    public void setPhoneModel(String phoneModel) {
        this.phoneModel = phoneModel;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getPlatformId() {
        return platformId;
    }

    public void setPlatformId(Long platformId) {
        this.platformId = platformId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * 额外处理
     **/
    private List<Long> puIdList = new ArrayList<>();

    public List<Long> getPuIdList() {
        return puIdList;
    }

    public void setPuIdList(List<Long> puIdList) {
        this.puIdList = puIdList;
    }
}
