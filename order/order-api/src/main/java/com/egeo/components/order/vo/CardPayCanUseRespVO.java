package com.egeo.components.order.vo;

import java.io.Serializable;
import java.util.List;

/**
 * @Description todo
 * @Author lsl
 * @Date 2025/3/27 1:28
 * @Version V1.0
 **/
public class CardPayCanUseRespVO implements Serializable {

    /**
     * 畅购卡
     **/
    private List<UserCardDetailVO> widelyAllCards;

    /**
     * 礼品卡
     **/
    private List<UserCardDetailVO> giftCards;

    public List<UserCardDetailVO> getWidelyAllCards() {
        return widelyAllCards;
    }

    public void setWidelyAllCards(List<UserCardDetailVO> widelyAllCards) {
        this.widelyAllCards = widelyAllCards;
    }

    public List<UserCardDetailVO> getGiftCards() {
        return giftCards;
    }

    public void setGiftCards(List<UserCardDetailVO> giftCards) {
        this.giftCards = giftCards;
    }
}
