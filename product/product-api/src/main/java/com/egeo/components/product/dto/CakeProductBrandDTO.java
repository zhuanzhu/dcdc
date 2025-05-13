package com.egeo.components.product.dto;

import java.io.Serializable;

/**
 * @Description todo
 * @Author lsl
 * @Date 2024/4/28 23:07
 * @Version V1.0
 **/
public class CakeProductBrandDTO implements Serializable {

    /**
     * string	10290	品牌id
     **/
    private String id;

    /**
     * string	幸福西饼	品牌名称
     **/
    private String name;

    /**
     * 	string	好品牌	品牌描述
     **/
    private String description;

    /**
     * string	1029292	品牌图片id
     **/
    private String image_id;

    /**
     * string	品牌图片地址 https://img.dangaoss.com/public/b/69/35/1220739_s.jpg
     **/
    private String image_path;

    /**
     * int	0	0正常 1特殊
     **/
    private String is_type;

    private String distribution_rule_id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getImage_id() {
        return image_id;
    }

    public void setImage_id(String image_id) {
        this.image_id = image_id;
    }

    public String getImage_path() {
        return image_path;
    }

    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }

    public String getIs_type() {
        return is_type;
    }

    public void setIs_type(String is_type) {
        this.is_type = is_type;
    }

    public String getDistribution_rule_id() {
        return distribution_rule_id;
    }

    public void setDistribution_rule_id(String distribution_rule_id) {
        this.distribution_rule_id = distribution_rule_id;
    }
}
