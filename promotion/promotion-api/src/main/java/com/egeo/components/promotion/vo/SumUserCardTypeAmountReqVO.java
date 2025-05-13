package com.egeo.components.promotion.vo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
public class SumUserCardTypeAmountReqVO implements Serializable {

    /**
     * '持有用户id'
     **/
    private Long userId;

    /**
     * 有效状态：0有效 1无效 默认有效
     **/
    private Integer cardState;

    /**
     * 使用状态：0未使用 1使用中 2已使用 3已过期 默认未使用
     **/
    private Integer useState;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getCardState() {
        return cardState;
    }

    public void setCardState(Integer cardState) {
        this.cardState = cardState;
    }

    public Integer getUseState() {
        return useState;
    }

    public void setUseState(Integer useState) {
        this.useState = useState;
    }
}
