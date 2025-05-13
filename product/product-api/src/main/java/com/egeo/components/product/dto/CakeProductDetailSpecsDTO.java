package com.egeo.components.product.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @Description todo
 * @Author lsl
 * @Date 2024/4/28 22:55
 * @Version V1.0
 **/
public class CakeProductDetailSpecsDTO implements Serializable {

    /**
     * string	1162869	规格id
     **/
    private String id;

    /**
     * string	1313087	商品id
     **/
    private String product_id;

    /**
     * string	2磅	规格名称
     **/
    private String name;

    /**
     * string	该规格适用于重口味小伙伴	规格描述
     **/
    private String description;

    /**
     * string	188.00	规格价格
     **/
    private String price;

    /**
     * string	188.00	规格市场价
     **/
    private String market_price;

    /**
     * string	188.30	规格结算价
     **/
    private String clearing_price;

    /**
     * string	雪人糖	赠品
     **/
    private String gift;

    /**
     * string	香草味,草莓味,朗姆味,巧克力味	口味(“,”分割格式)
     **/
    private String tastes;

    /**
     * array	[]	口味(数组格式)
     **/
    private List tastesArr;

    /**
     * string	2019-10-31 15:42:02	创建时间
     **/
    private String created_at;

    /**
     * string	1162869	活动id
     **/
    private String promotion_id;

    /**
     * string	2019-10-31 15:42:02	活动开始时间
     **/
    private String promotion_start_at;

    /**
     * string	2019-10-31 15:42:02	活动结束时间
     **/
    private String promotion_end_at;

    /**是否已删除**/
    private String deleted;

    /**库存**/
    private String stock;

    /**是否能购买**/
    private String can_buy;

    private String normal_price;

    private String have_ship_time;

    private String is_price;

    private String distribution_rule_id;

    private String red_packet;

    private String sort;

    private String sku;

    private String spec_img;

    private String is_up;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getMarket_price() {
        return market_price;
    }

    public void setMarket_price(String market_price) {
        this.market_price = market_price;
    }

    public String getClearing_price() {
        return clearing_price;
    }

    public void setClearing_price(String clearing_price) {
        this.clearing_price = clearing_price;
    }

    public String getGift() {
        return gift;
    }

    public void setGift(String gift) {
        this.gift = gift;
    }

    public String getTastes() {
        return tastes;
    }

    public void setTastes(String tastes) {
        this.tastes = tastes;
    }

    public List getTastesArr() {
        return tastesArr;
    }

    public void setTastesArr(List tastesArr) {
        this.tastesArr = tastesArr;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getPromotion_id() {
        return promotion_id;
    }

    public void setPromotion_id(String promotion_id) {
        this.promotion_id = promotion_id;
    }

    public String getPromotion_start_at() {
        return promotion_start_at;
    }

    public void setPromotion_start_at(String promotion_start_at) {
        this.promotion_start_at = promotion_start_at;
    }

    public String getPromotion_end_at() {
        return promotion_end_at;
    }

    public void setPromotion_end_at(String promotion_end_at) {
        this.promotion_end_at = promotion_end_at;
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getCan_buy() {
        return can_buy;
    }

    public void setCan_buy(String can_buy) {
        this.can_buy = can_buy;
    }

    public String getNormal_price() {
        return normal_price;
    }

    public void setNormal_price(String normal_price) {
        this.normal_price = normal_price;
    }

    public String getHave_ship_time() {
        return have_ship_time;
    }

    public void setHave_ship_time(String have_ship_time) {
        this.have_ship_time = have_ship_time;
    }

    public String getIs_price() {
        return is_price;
    }

    public void setIs_price(String is_price) {
        this.is_price = is_price;
    }

    public String getDistribution_rule_id() {
        return distribution_rule_id;
    }

    public void setDistribution_rule_id(String distribution_rule_id) {
        this.distribution_rule_id = distribution_rule_id;
    }

    public String getRed_packet() {
        return red_packet;
    }

    public void setRed_packet(String red_packet) {
        this.red_packet = red_packet;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getSpec_img() {
        return spec_img;
    }

    public void setSpec_img(String spec_img) {
        this.spec_img = spec_img;
    }

    public String getIs_up() {
        return is_up;
    }

    public void setIs_up(String is_up) {
        this.is_up = is_up;
    }
}
