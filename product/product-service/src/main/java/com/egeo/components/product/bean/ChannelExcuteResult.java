package com.egeo.components.product.bean;

import com.egeo.components.product.condition.StandardUnitCondition;

import java.io.Serializable;
import java.util.List;

/**
 * @Description todo
 * @Author lsl
 * @Date 2024/7/16 12:49
 * @Version V1.0
 **/
public class ChannelExcuteResult implements Serializable {

    private List<StandardUnitCondition> list;

    public List<StandardUnitCondition> getList() {
        return list;
    }

    public void setList(List<StandardUnitCondition> list) {
        this.list = list;
    }
}
