package com.egeo.components.third.common;

import lombok.Getter;

/**
 * @Description 加解密方式枚举
 * @Author lsl
 * @Version V1.0
 **/
public enum EncryptTypeEnum {
    DEFAULT("default","默认"),
    CAKE("cake","蛋糕叔叔加解密方式"),
    DLF("dlf","德律风加解密方式"),
    YD("yd","仪电加解密方式"),
    ;

    @Getter
    private String encryptType;

    @Getter
    private String msg;

    private EncryptTypeEnum(String encryptType, String msg) {
        this.encryptType = encryptType;
        this.msg = msg;
    }
}
