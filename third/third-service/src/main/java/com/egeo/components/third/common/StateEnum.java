package com.egeo.components.third.common;

import lombok.Getter;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
public enum StateEnum {
    NORMAL(0,"正常"),
    INVALID(1,"无效"),
    ;


    @Getter
    private Integer code;

    @Getter
    private String msg;

    private StateEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
