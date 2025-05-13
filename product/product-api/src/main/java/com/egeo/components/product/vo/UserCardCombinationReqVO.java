package com.egeo.components.product.vo;

import java.io.Serializable;
import java.util.List;

/**
 * @Description todo
 * @Author lsl
 * @Date 2025/3/27 2:27
 * @Version V1.0
 **/
public class UserCardCombinationReqVO implements Serializable {

    private List<Long> combinationIds;

    private List<CardCombinationVO> cardCombinations;

    public List<Long> getCombinationIds() {
        return combinationIds;
    }

    public void setCombinationIds(List<Long> combinationIds) {
        this.combinationIds = combinationIds;
    }

    public List<CardCombinationVO> getCardCombinations() {
        return cardCombinations;
    }

    public void setCardCombinations(List<CardCombinationVO> cardCombinations) {
        this.cardCombinations = cardCombinations;
    }
}
