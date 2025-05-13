package com.egeo.components.product.dto;

import java.io.Serializable;

/**
 * @Description 品牌详情接口
 * @Author lsl
 * @Date 2024/4/28 23:50
 * @Version V1.0
 **/
public class CakeBrandDetailDTO implements Serializable {

    /**
     * string	47	品牌id
     **/
    private String id;

    /**
     * string	蛋糕叔叔	品牌名称
     **/
    private String name;

    /**
     * 让生活更甜蜜	47	品牌简介
     **/
    private String short_description;

    /**
     * string	蛋糕叔叔	品牌详情介绍
     **/
    private String description;

    /**
     * string	https://img.dangaoss.com/public/b/79/15/1226491_s.jpg	品牌logo图
     **/
    private String image_path;

    /**
     * string	品牌宣传图 https://img.dangaoss.com/public/b/79/15/1226491_s.jpg
     **/
    private String album_image_path;
}
