package com.egeo.components.order.vo;

import java.io.Serializable;
import java.util.List;

/**
 * @Description todo
 * @Author lsl
 * @Date 2025/3/25 10:21
 * @Version V1.0
 **/
public class BuyCardUseCheckRespVO implements Serializable {

    private List<String> puName;

    public List<String> getPuName() {
        return puName;
    }

    public void setPuName(List<String> puName) {
        this.puName = puName;
    }
}
