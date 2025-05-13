package com.egeo.components.promotion.enums;

/**
 * @Description 购物卡使用状态
 * @Author lsl
 * @Version V1.0
 **/
public enum CardUseStateEnum {

    UN_USE(0,"未使用"),
    USE_ING(1,"使用中"),
    USED(2,"已使用"),
    EXPIRE(3,"已过期"),
    ;

    private Integer useState;

    private String describe;

    CardUseStateEnum() {
    }

    CardUseStateEnum(Integer useState, String describe) {
        this.useState = useState;
        this.describe = describe;
    }

    public Integer getUseState() {
        return useState;
    }

    public void setUseState(Integer useState) {
        this.useState = useState;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public static String ofDescribe(Integer useState){
        if(useState == null){
            return null;
        }
        for (CardUseStateEnum value : CardUseStateEnum.values()) {
            if(useState.equals(value.getUseState())){
                return value.getDescribe();
            }
        }
        return null;
    }
}
