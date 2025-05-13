package com.egeo.components.promotion.vo;

import com.egeo.components.promotion.dto.BuyCardItemRefundDTO;

import java.io.Serializable;
import java.util.List;

/**
 * @Description todo
 * @Author lsl
 * @Date 2025/4/8 3:32
 * @Version V1.0
 **/
public class BuyCardRefundReqVO implements Serializable {

    private List<BuyCardItemRefundDTO> dtos;

    public List<BuyCardItemRefundDTO> getDtos() {
        return dtos;
    }

    public void setDtos(List<BuyCardItemRefundDTO> dtos) {
        this.dtos = dtos;
    }
}
