package com.egeo.components.product.vo;

import java.io.Serializable;
import java.util.List;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
public class StandardUnitCardBaseSuSaveBatchVO implements Serializable {

    private Long cardBaseId;

    private List<StandardUnitCardBaseSuDetailReqVO> suDetail;


    public Long getCardBaseId() {
        return cardBaseId;
    }

    public void setCardBaseId(Long cardBaseId) {
        this.cardBaseId = cardBaseId;
    }

    public List<StandardUnitCardBaseSuDetailReqVO> getSuDetail() {
        return suDetail;
    }

    public void setSuDetail(List<StandardUnitCardBaseSuDetailReqVO> suDetail) {
        this.suDetail = suDetail;
    }
}
