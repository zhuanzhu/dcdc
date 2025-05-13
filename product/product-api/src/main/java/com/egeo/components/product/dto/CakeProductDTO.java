package com.egeo.components.product.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @Description todo
 * @Author lsl
 * @Date 2024/4/28 17:21
 * @Version V1.0
 **/
public class CakeProductDTO implements Serializable {

    /**
     *string	1	商品id
     **/
    private String id;

    /**
     *string	鲜奶蛋糕	商品名称
     **/
    private String title;

    /**
     *string	239.00	市场价
     **/
    private String market_price;

    /**
     *string	219.00	售价
     **/
    private String price;

    /**
     *	string	https://img.dangaoss.com/public/p/97/7/1226113_m.jpg	商品图片地址
     **/
    private String image_path;

    /**
     *	string	幸福西饼	品牌名称
     **/
    private String brand_name;

    /**
     *	string	10203	品牌id
     **/
    private String brand_id;

    /**
     *	int	0	是否是预售商品，1代表预售商品，默认0
     **/
    private String is_yushou;

    /**
     *	int	0	是否支持祝福语配置 0支持 1不支持
     **/
    private String is_greeting;

    /**
     *	array	规格列表
     **/
    private List<CakeSpecsDTO> specs;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMarket_price() {
        return market_price;
    }

    public void setMarket_price(String market_price) {
        this.market_price = market_price;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImage_path() {
        return image_path;
    }

    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }

    public String getBrand_name() {
        return brand_name;
    }

    public void setBrand_name(String brand_name) {
        this.brand_name = brand_name;
    }

    public String getBrand_id() {
        return brand_id;
    }

    public void setBrand_id(String brand_id) {
        this.brand_id = brand_id;
    }

    public String getIs_yushou() {
        return is_yushou;
    }

    public void setIs_yushou(String is_yushou) {
        this.is_yushou = is_yushou;
    }

    public String getIs_greeting() {
        return is_greeting;
    }

    public void setIs_greeting(String is_greeting) {
        this.is_greeting = is_greeting;
    }

    public List<CakeSpecsDTO> getSpecs() {
        return specs;
    }

    public void setSpecs(List<CakeSpecsDTO> specs) {
        this.specs = specs;
    }
}
