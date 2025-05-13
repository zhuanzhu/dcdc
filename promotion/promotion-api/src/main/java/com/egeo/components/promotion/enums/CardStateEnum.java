package com.egeo.components.promotion.enums;

/**
 * @Description 购物卡状态
 * @Author lsl
 * @Version V1.0
 **/
public enum CardStateEnum {
    EFFECTIVE(0,"有效"),
    UN_EFFECTIVE(1,"无效"),
    ;
     CardStateEnum(){}

   private Integer state;

    private String describe;

      CardStateEnum(Integer state, String describe) {
        this.state = state;
        this.describe = describe;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public static CardStateEnum of(Integer state){
          if(state == null){
              return null;
          }
        for (CardStateEnum value : CardStateEnum.values()) {
            if(state.equals(value.getState())){
                return value;
            }
        }
        return null;
    }
    public static String ofDescribe(Integer state){
        if(state == null){
            return null;
        }
        for (CardStateEnum value : CardStateEnum.values()) {
            if(state.equals(value.getState())){
                return value.getDescribe();
            }
        }
        return null;
    }
}
