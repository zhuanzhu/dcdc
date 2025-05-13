package com.egeo.components.product.dto;

import java.io.Serializable;

/**
 * @Description 商品详情中商品对象
 * @Author lsl
 * @Date 2024/4/28 23:12
 * @Version V1.0
 **/
public class CakeProductDetailProductsDTO implements Serializable {
    /**
     * 	string	1313087	商品id
     **/
    private String id;

    /**
     * 	string	1	所属分类：1-蛋糕，5-零食小吃，8-鲜花
     **/
    private String cat_id;

    /**
     * 	int	1	是否是预售商品，1代表预售商品，默认0
     **/
    private String is_yushou;

    /**
     * 	int	1	是否支持祝福语配置 0支持 1不支持
     **/
    private String is_greeting;

    /**
     * string	https://img.dangaoss.com/l.jpg	图片路径
     **/
    private String image_path;

    /**
     * 	string	320	图片宽度
     **/
    private String image_width;

    /**
     * string	320	图片高度
     **/
    private String image_height;

    /**
     * 	string	四重奏	商品名称
     **/
    private String title;

    /**
     * 	string	榴莲、芒果、巧克力、提拉米苏，多重享受一次到“味”	商品信息
     **/
    private String description;

    /**
     * 	string	0	评论次数
     **/
    private String comment_count;

    /**
     * 	string	0	喜欢次数
     **/
    private String like_count;

    /**
     * 	string	0	购买次数
     **/
    private String buy_count;

    /**
     * string	1	状态：0-未发布，1-已发布
     **/
    private String status;

    /**
     * string	188.00	市场价
     **/
    private String market_price;

    /**
     * string	188.00	售价
     **/
    private String price;

    /**
     * string	188	是否可自提：0-否，1-是
     **/
    private String can_take;

    /**
     * 	string	188	是否可配送：0-否，1-是
     **/
    private String can_ship;

    /**
     * string	3090	配规id
     **/
    private String distribution_rule_id;

    /**
     * 	商品标签
     **/
    private String can_buy;

    private String label_name;

    /**
     * 	商品口味、材料等信息(即将弃用)
     **/
    private CakeProducTextraDataDTO extra_data;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCat_id() {
        return cat_id;
    }

    public void setCat_id(String cat_id) {
        this.cat_id = cat_id;
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

    public String getImage_path() {
        return image_path;
    }

    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }

    public String getImage_width() {
        return image_width;
    }

    public void setImage_width(String image_width) {
        this.image_width = image_width;
    }

    public String getImage_height() {
        return image_height;
    }

    public void setImage_height(String image_height) {
        this.image_height = image_height;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getComment_count() {
        return comment_count;
    }

    public void setComment_count(String comment_count) {
        this.comment_count = comment_count;
    }

    public String getLike_count() {
        return like_count;
    }

    public void setLike_count(String like_count) {
        this.like_count = like_count;
    }

    public String getBuy_count() {
        return buy_count;
    }

    public void setBuy_count(String buy_count) {
        this.buy_count = buy_count;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getCan_take() {
        return can_take;
    }

    public void setCan_take(String can_take) {
        this.can_take = can_take;
    }

    public String getCan_ship() {
        return can_ship;
    }

    public void setCan_ship(String can_ship) {
        this.can_ship = can_ship;
    }

    public String getDistribution_rule_id() {
        return distribution_rule_id;
    }

    public void setDistribution_rule_id(String distribution_rule_id) {
        this.distribution_rule_id = distribution_rule_id;
    }

    public String getCan_buy() {
        return can_buy;
    }

    public void setCan_buy(String can_buy) {
        this.can_buy = can_buy;
    }

    public CakeProducTextraDataDTO getExtra_data() {
        return extra_data;
    }

    public void setExtra_data(CakeProducTextraDataDTO extra_data) {
        this.extra_data = extra_data;
    }

    public String getLabel_name() {
        return label_name;
    }

    public void setLabel_name(String label_name) {
        this.label_name = label_name;
    }
}
