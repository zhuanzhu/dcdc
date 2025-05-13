package com.egeo.components.finance.constant;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
public enum UserPayTypeEnum {
    MANY_TYPE(-1,"混合支付 "),
    FU_BI(0,"积分或餐卡 "),
    JI_DIAN(5,"积点 "),
    ;

    private UserPayTypeEnum(int payType, String comment) {
        this.payType = payType;
        this.comment = comment;
    }

    public int getPayType() {
        return payType;
    }

    public void setPayType(int payType) {
        this.payType = payType;
    }

    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
    Integer payType;
    String comment;

    public static String translate(Integer payType){
        if(payType == null){
            return "";
        }
        for(UserPayTypeEnum f:UserPayTypeEnum.values()){
            if(f.payType.equals(payType)){
                return f.getComment();
            }
        }
        return "";
    }
}
