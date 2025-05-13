package com.egeo.components.cms.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * Copyright (C), 2017-2019, 仁辉科技有限公司
 *
 * @Author: chenhui
 * Date:     11:50 2019/9/24
 * Description:
 * History:
 * Version: 1.0
 */
public enum AuthSignStatusEnum {

    UN_SIGN(1, "未授权"),
    SIGNED(2, "已授权"),
    SIGN_INVALID(3, "授权过期"),
    ;

    private static Map<Integer,AuthSignStatusEnum> enumMap = new HashMap<>();

    static {
        for (AuthSignStatusEnum authSignStatusEnum : AuthSignStatusEnum.values()){
            enumMap.put(authSignStatusEnum.getCode(), authSignStatusEnum);
        }
    }

    public static AuthSignStatusEnum getByCode(Integer code){
        return enumMap.get(code);
    }

    AuthSignStatusEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
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
