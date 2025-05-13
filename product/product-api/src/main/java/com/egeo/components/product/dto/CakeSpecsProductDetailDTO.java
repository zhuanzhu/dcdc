package com.egeo.components.product.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * @Description todo
 * @Author lsl
 * @Date 2024/5/13 16:29
 * @Version V1.0
 **/
public class CakeSpecsProductDetailDTO implements Serializable {

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

    private String clearing_price;

    private String spuId;

    /**
     * 规格名称
     **/
    private String name;

    public CakeSpecsProductDetailDTO() {
    }

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

    public String getClearing_price() {
        return clearing_price;
    }

    public void setClearing_price(String clearing_price) {
        this.clearing_price = clearing_price;
    }

    public String getSpuId() {
        return spuId;
    }

    public void setSpuId(String spuId) {
        this.spuId = spuId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CakeSpecsProductDetailDTO(CakeProductDTO dto, CakeSpecsDTO specsDTO) {
        if(Objects.nonNull(specsDTO)){
            this.id= dto.getId()+"000"+specsDTO.getSpec_id();
            this.title=dto.getTitle();
            this.market_price = specsDTO.getMarket_price();
            this.price= specsDTO.getPrice();
            this.image_path=dto.getImage_path();
            this.brand_name=dto.getBrand_name();
            this.brand_id=dto.getBrand_id();
            this.is_yushou=dto.getIs_yushou();
            this.is_greeting=dto.getIs_greeting();
            this.clearing_price = specsDTO.getClearing_price();
            this.spuId= dto.getId();
            this.name = specsDTO.getName();
        }

    }
}
