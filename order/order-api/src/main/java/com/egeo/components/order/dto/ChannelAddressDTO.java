package com.egeo.components.order.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description todo
 * @Author lsl
 * @Date 2024/12/16 11:48
 * @Version V1.0
 **/
public class ChannelAddressDTO implements Serializable {


    /**
     * 主键
     */
    private Long id;
    /**
     * 渠道编码
     */
    private String channelCode;
    /**
     * 收货地址i
     */
    private Long receiverAddressId;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 省份
     */
    private String province;
    /**
     * 城市
     */
    private String city;
    /**
     * 区域
     */
    private String area;
    /**
     * 不包含省市的详情地址
     */
    private String addr;

    /**
     * 省份code
     */
    private String provinceCode;

    /**
     * 城市id
     */
    private String cityId;
    /**
     * 区域code
     */
    private String areaCode;
    /**
     * 渠道地址id
     */
    private String channelAddressId;
    /**
     * 渠道用户id
     */
    private String channelUserId;
    /**
     * 渠道用户名称
     */
    private String channelUserName;
    /**
     * 渠道用户手机号
     */
    private String phone;
    /**
     * 是否默认：0未默认 1默认
     */
    private Integer isDefault;
    /**
     * 是否删除：0正常 1删除
     */
    private Integer deleted;
    /**
     * 经度
     */
    private String lng;
    /**
     * 纬度
     */
    private String lat;
    /**
     * 备注
     */
    private String landmark;

    /**
     * 修改时间:更新时数据库会自动set值
     */
    private Date updateTime;

    /**
     * 创建时间:创建记录时数据库会自动set值
     */
    private Date createTime;

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

    public Long getReceiverAddressId() {
        return receiverAddressId;
    }

    public void setReceiverAddressId(Long receiverAddressId) {
        this.receiverAddressId = receiverAddressId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getChannelAddressId() {
        return channelAddressId;
    }

    public void setChannelAddressId(String channelAddressId) {
        this.channelAddressId = channelAddressId;
    }

    public String getChannelUserId() {
        return channelUserId;
    }

    public void setChannelUserId(String channelUserId) {
        this.channelUserId = channelUserId;
    }

    public String getChannelUserName() {
        return channelUserName;
    }

    public void setChannelUserName(String channelUserName) {
        this.channelUserName = channelUserName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Integer isDefault) {
        this.isDefault = isDefault;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
