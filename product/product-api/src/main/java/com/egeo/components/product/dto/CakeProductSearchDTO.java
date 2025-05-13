package com.egeo.components.product.dto;

import java.io.Serializable;

/**
 * @Description 商品列表接口DTO
 * @Author lsl
 * @Date 2024/4/28 17:10
 * @Version V1.0
 **/
public class CakeProductSearchDTO implements Serializable {

    /**
     * 	否	int	2	城市id，比如北京城市id是2,有值的话提取当前城市商品,不传提取所有商品
     **/
    private String city_id;
    /**
     * 	否	int	1	商品类型，1是蛋糕，2是零食，3是鲜花
     **/
    private String product_type;

    /**
     * 		否	string	生日	商品关键词搜索
     **/
    private String search_title;

    /**
     * 	是	int	1	商品价格排序，1是按价格倒序 ，2是按价格升序
     **/
    private String sort_price_type;

    /**
     * 	否	string	2021-01-01	开始时间(查询商品最后更新时间,第一次全量查询商品信息时候和end_time都不必传参)
     **/
    private String start_time;
    /**
     * 	否	string	2021-01-02	结束时间
     **/
    private String end_time;
    /**
     * 		否	int	47	品牌ID,可根据此条件进行筛选
     **/
    private String brand_id;
    /**
     * 	是	int	1	页数
     **/
    private Integer page;
    /**
     * 	是	int	10	每页显示的条数(默认最大值为50)
     **/
    private Integer size;

    private String cat_id2;

    private String cat_id3;


    public String getCity_id() {
        return city_id;
    }

    public void setCity_id(String city_id) {
        this.city_id = city_id;
    }

    public String getProduct_type() {
        return product_type;
    }

    public void setProduct_type(String product_type) {
        this.product_type = product_type;
    }

    public String getSearch_title() {
        return search_title;
    }

    public void setSearch_title(String search_title) {
        this.search_title = search_title;
    }

    public String getSort_price_type() {
        return sort_price_type;
    }

    public void setSort_price_type(String sort_price_type) {
        this.sort_price_type = sort_price_type;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getBrand_id() {
        return brand_id;
    }

    public void setBrand_id(String brand_id) {
        this.brand_id = brand_id;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getCat_id2() {
        return cat_id2;
    }

    public void setCat_id2(String cat_id2) {
        this.cat_id2 = cat_id2;
    }

    public String getCat_id3() {
        return cat_id3;
    }

    public void setCat_id3(String cat_id3) {
        this.cat_id3 = cat_id3;
    }
}
