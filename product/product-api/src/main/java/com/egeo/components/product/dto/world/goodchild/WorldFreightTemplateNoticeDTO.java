package com.egeo.components.product.dto.world.goodchild;

import java.io.Serializable;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
public class WorldFreightTemplateNoticeDTO implements Serializable {

    /**
     * 	String	是	模板ID
     **/
    private String template_id;
    /**
     * 	String	是	首重（g）
     **/
    private String init_weight;
    /**
     * 	String	是	首重费用
     **/
    private String init_money;
    /**
     * 	String	是	续重（g）
     **/
    private String increase_weight;
    /**
     * 	String	是	续重费用
     **/
    private String increase_money;

    public String getTemplate_id() {
        return template_id;
    }

    public void setTemplate_id(String template_id) {
        this.template_id = template_id;
    }

    public String getInit_weight() {
        return init_weight;
    }

    public void setInit_weight(String init_weight) {
        this.init_weight = init_weight;
    }

    public String getInit_money() {
        return init_money;
    }

    public void setInit_money(String init_money) {
        this.init_money = init_money;
    }

    public String getIncrease_weight() {
        return increase_weight;
    }

    public void setIncrease_weight(String increase_weight) {
        this.increase_weight = increase_weight;
    }

    public String getIncrease_money() {
        return increase_money;
    }

    public void setIncrease_money(String increase_money) {
        this.increase_money = increase_money;
    }
}
