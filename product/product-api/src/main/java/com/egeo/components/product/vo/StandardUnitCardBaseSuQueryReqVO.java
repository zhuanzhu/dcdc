package com.egeo.components.product.vo;

import java.io.Serializable;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
public class StandardUnitCardBaseSuQueryReqVO implements Serializable {

    private Long cardBaseId;

    private Integer source;

    private String keyWord;

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public Long getCardBaseId() {
        return cardBaseId;
    }

    public void setCardBaseId(Long cardBaseId) {
        this.cardBaseId = cardBaseId;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }
}
