package com.egeo.components.order.vo.cake;

import java.io.Serializable;

/**
 * @Description todo
 * @Author lsl
 * @Date 2024/11/12 8:11
 * @Version V1.0
 **/
public class CakeRuleIdsVO implements Serializable {

    private String product_id;

    private String city_id;

    private String distribution_rule_id;

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

    public String getDistribution_rule_id() {
        return distribution_rule_id;
    }

    public void setDistribution_rule_id(String distribution_rule_id) {
        this.distribution_rule_id = distribution_rule_id;
    }
}
