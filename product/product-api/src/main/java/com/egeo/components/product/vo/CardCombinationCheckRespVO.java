package com.egeo.components.product.vo;

import java.io.Serializable;
import java.util.List;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
public class CardCombinationCheckRespVO implements Serializable {

    private List<CardCombinationVO> cardCombinations;

    public List<CardCombinationVO> getCardCombinations() {
        return cardCombinations;
    }

    public void setCardCombinations(List<CardCombinationVO> cardCombinations) {
        this.cardCombinations = cardCombinations;
    }
}
