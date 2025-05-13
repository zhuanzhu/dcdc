package com.egeo.components.order.dto;

import java.io.Serializable;

/**
 * @Description todo
 * @Author lsl
 * @Date 2024/4/29 10:21
 * @Version V1.0
 **/
public class CakeAddressDTO implements Serializable {

    /**
     * 	是	int	8408762	请使用渠道免登录返回的user_id
     **/
    private String user_id;

    /**
     * 	是	string	北京市	城市名称(带 ‘市’ 字)
     **/
    private String city_name;

    /**
     * 	是	string	海淀区	区县名称
     **/
    private String area;

    /**
     * 	是	string	玉泉路3号	具体地址(不含 市、区信息)
     **/
    private String addr	;

    /**
     * 	是	string	22.733206652813	纬度（百度地图）
     **/
    private String lat;

    /**
     * 是	string	114.38496357799	经度（百度地图）
     **/
    private String lng;

    /**
     * 	是	string	张三	收货人名称
     **/
    private String name;

    /**
     * 	是	string	18909897823	收货人手机号
     **/
    private String phone;

    /**
     * 否(修改时必填)	int	344	收货地址id
     **/
    private String id;

    /**
     * 	否	string	车道沟兵器大厦	附近标志物
     **/
    private String landmark;

    /**
     * 	否	int	1	是否设置为默认地址
     **/
    private Integer is_default;

    /**
     * 	否	string	100059	邮政编码
     **/
    private String zip;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    public Integer getIs_default() {
        return is_default;
    }

    public void setIs_default(Integer is_default) {
        this.is_default = is_default;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }
}
