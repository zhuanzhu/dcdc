package com.egeo.components.product.dto;

import java.io.Serializable;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
public class CakeProducTextraDataDTO implements Serializable {

    /**
     * 		string	北京市五环内免费配送	配规描述
     **/
    private String distrubtion;

    /**
     * 	string	鸡蛋、奶油、小麦粉、芒果、榴莲肉等	商品材料
     **/
    private String material;

    /**
     * 	string	芒果	口感
     **/
    private String taste;

    /**
     * string	2	甜度
     **/
    private String sweetness;

    /**
     * 		string	星星越多越甜哦~
     **/
    private String sweetness_text;

    public String getDistrubtion() {
        return distrubtion;
    }

    public void setDistrubtion(String distrubtion) {
        this.distrubtion = distrubtion;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getTaste() {
        return taste;
    }

    public void setTaste(String taste) {
        this.taste = taste;
    }

    public String getSweetness() {
        return sweetness;
    }

    public void setSweetness(String sweetness) {
        this.sweetness = sweetness;
    }

    public String getSweetness_text() {
        return sweetness_text;
    }

    public void setSweetness_text(String sweetness_text) {
        this.sweetness_text = sweetness_text;
    }
}
