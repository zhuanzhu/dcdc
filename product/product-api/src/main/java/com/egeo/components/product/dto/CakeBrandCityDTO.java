package com.egeo.components.product.dto;

import java.io.Serializable;

/**
 * @Description todo
 * @Author lsl
 * @Date 2024/4/28 23:54
 * @Version V1.0
 **/
public class CakeBrandCityDTO implements Serializable {

    /**
     * 	int	品牌ID
     **/
    private String id;

    /**
     * 	string	品牌名称
     **/
    private String name;

    /**
     * 		int	品牌类别(1-蛋糕,5-零食,8-鲜花)
     **/
    private String cat_id;

    /**
     * 		string	logo地址
     **/
    private String image_path;

    /**
     * 	string	品牌相册地址
     **/
    private String album_image_path;

    /**
     * 		string	品牌开通城市ID
     **/
    private String city_id;

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

    public String getCat_id() {
        return cat_id;
    }

    public void setCat_id(String cat_id) {
        this.cat_id = cat_id;
    }

    public String getImage_path() {
        return image_path;
    }

    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }

    public String getAlbum_image_path() {
        return album_image_path;
    }

    public void setAlbum_image_path(String album_image_path) {
        this.album_image_path = album_image_path;
    }

    public String getCity_id() {
        return city_id;
    }

    public void setCity_id(String city_id) {
        this.city_id = city_id;
    }
}
