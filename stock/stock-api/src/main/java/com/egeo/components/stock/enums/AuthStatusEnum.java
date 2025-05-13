package com.egeo.components.stock.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * Copyright (C), 2017-2019, 仁辉科技有限公司
 *
 * @Author: chenhui
 * Date:     11:42 2019/9/24
 * Description:
 * History:
 * Version: 1.0
 */
public enum AuthStatusEnum {

    UN_AUTH(1, "未认证"),
    AUTH_ING(2, "认证中"),
    AUTH_PASS(3, "认证通过"),
    AUTH_REJECT(4, "认证不通过"),
    AUTH_INVALID(5, "认证失效"),
    AUTH_EXCEPTION(6, "认证异常");

    private static Map<Integer,AuthStatusEnum> enumMap = new HashMap<>();

    static {
        for (AuthStatusEnum authResultStatusEnum : AuthStatusEnum.values()){
            enumMap.put(authResultStatusEnum.getCode(), authResultStatusEnum);
        }
    }

    AuthStatusEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static AuthStatusEnum getByCode(Integer code){
        return enumMap.get(code);
    }

    private int code;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
