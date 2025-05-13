package com.egeo.components.promotion.enums;


/**
 * @Description 购物卡类型
 * @Author lsl
 * @Version V1.0
 **/
public enum CardTypeEnum {

    ALL(1, "畅购卡"),
    GIFT(2, "礼品卡"),
    ;

    private Integer cardType;
    private String cardTypeName;

     CardTypeEnum(){}

    CardTypeEnum(Integer cardType, String cardTypeName) {
        this.cardType = cardType;
        this.cardTypeName = cardTypeName;
    }

    public static CardTypeEnum getByType(Integer cardType){
         if(cardType ==null){
             return null;
         }
        for (CardTypeEnum value : CardTypeEnum.values()) {
            if(cardType.equals(value.getCardType())){
                return value;
            }
        }
        return null;
    }

    public static String getByTypeName(Integer cardType){
        if(cardType ==null){
            return null;
        }
        for (CardTypeEnum value : CardTypeEnum.values()) {
            if(cardType.equals(value.getCardType())){
                return value.getCardTypeName();
            }
        }
        return null;
    }

    public Integer getCardType() {
        return cardType;
    }

    public void setCardType(Integer cardType) {
        this.cardType = cardType;
    }

    public String getCardTypeName() {
        return cardTypeName;
    }

    public void setCardTypeName(String cardTypeName) {
        this.cardTypeName = cardTypeName;
    }
}
