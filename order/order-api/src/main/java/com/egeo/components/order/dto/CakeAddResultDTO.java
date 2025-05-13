package com.egeo.components.order.dto;

import java.io.Serializable;

/**
 * @Description todo
 * @Author lsl
 * @Date 2024/4/29 11:15
 * @Version V1.0
 **/
public class CakeAddResultDTO implements Serializable {

    /**
     * 地址ID
     **/
    private String id;

    /**
     * 蛋糕叔叔用户Id
     **/
    private String user_id;

    /**
     * 张三
     **/
    private String name;

    /**
     * 省份名称 如：辽宁省
     **/
    private String province;

    /**
     * 城市ID 如：9
     **/
    private String city_id;

    /**
     * 城市 如：大连市
     **/
    private String city;

    /**
     * 区/县  如：西岗区
     **/
    private String area;

    /**
     * 详情地址  淮河西路61号
     **/
    private String addr;

    /**
     *邮编
     **/
    private String zip;

    /**
     * 	手机号
     **/
    private String phone;

    /**
     * 送货时间：0-任意时间；1-仅工作日；2-仅周末
     **/
    private String ship_time;

    /**
     * 是否设置为默认地址 0否 1是
     **/
    private String is_default;

    /**
     * 创建时间
     **/
    private String created_at;

    /**
     * 最后创建者
     **/
    private String last_used_at;

    /**
     * 纬度（百度地图）
     **/
    private String lat;

    /**
     * 经度（百度地图）
     **/
    private String lng;

    /**
     * 附近标志物
     **/
    private String landmark;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity_id() {
        return city_id;
    }

    public void setCity_id(String city_id) {
        this.city_id = city_id;
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

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getShip_time() {
        return ship_time;
    }

    public void setShip_time(String ship_time) {
        this.ship_time = ship_time;
    }

    public String getIs_default() {
        return is_default;
    }

    public void setIs_default(String is_default) {
        this.is_default = is_default;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getLast_used_at() {
        return last_used_at;
    }

    public void setLast_used_at(String last_used_at) {
        this.last_used_at = last_used_at;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }
}
