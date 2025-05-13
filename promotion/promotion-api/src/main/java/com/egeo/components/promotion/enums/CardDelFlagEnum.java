package com.egeo.components.promotion.enums;

/**
 * @Description 卡删除标识
 * @Author lsl
 * @Version V1.0
 **/
public enum CardDelFlagEnum {

    NORMAL(),
    UN_NORMAL(),
    ;

    private Integer delFlag;

    private String describe;

    CardDelFlagEnum() {
    }

    CardDelFlagEnum(Integer delFlag, String describe) {
        this.delFlag = delFlag;
        this.describe = describe;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
}
