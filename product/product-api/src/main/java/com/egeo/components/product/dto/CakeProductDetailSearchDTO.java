package com.egeo.components.product.dto;

import java.io.Serializable;

/**
 * @Description todo
 * @Author lsl
 * @Date 2024/4/28 22:45
 * @Version V1.0
 **/
public class CakeProductDetailSearchDTO implements Serializable {

    /**
     * 	是	int	1313087	商品id
     **/
    private String product_id;

    /**
     * 	否	int	2	城市id
     **/
    private String city_id;

    /**
     * 	否	int	932423	蛋糕叔叔用户id
     **/
    private String user_id;

    private Long enterpriseId;


    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getCity_id() {
        return city_id;
    }

    public void setCity_id(String city_id) {
        this.city_id = city_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }
}
