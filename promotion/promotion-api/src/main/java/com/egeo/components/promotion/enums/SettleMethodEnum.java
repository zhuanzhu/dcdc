package com.egeo.components.promotion.enums;

/**
 * @Description 购物卡结算方式:1预付费 2后付费
 * @Author lsl
 * @Version V1.0
 **/
public enum SettleMethodEnum {

    PREPAID_FEES(1,"预付费"),
    POSTPAID(2,"后付费"),
    ;

    private Integer settleMethod;

    private String describe;



    SettleMethodEnum() {
    }

    SettleMethodEnum(Integer settleMethod, String describe) {
        this.settleMethod = settleMethod;
        this.describe = describe;
    }

    public Integer getSettleMethod() {
        return settleMethod;
    }

    public void setSettleMethod(Integer settleMethod) {
        this.settleMethod = settleMethod;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public static SettleMethodEnum of(Integer settleMethod){
        if(settleMethod == null){
            return null;
        }
        for (SettleMethodEnum value : SettleMethodEnum.values()) {
            if(settleMethod.equals(value.getSettleMethod())){
                return value;
            }
        }
        return null;
    }

    public static String ofDescribe(Integer settleMethod){
        if(settleMethod == null){
            return null;
        }
        for (SettleMethodEnum value : SettleMethodEnum.values()) {
            if(settleMethod.equals(value.getSettleMethod())){
                return value.getDescribe();
            }
        }
        return null;
    }
}
