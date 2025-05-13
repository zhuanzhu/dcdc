package com.egeo.components.cms.enums;

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
public enum AuthResultStatusEnum {

    UN_AUTH(1, "未查询征信"),
    AUTH_ING(2, "征信查询中"),//暂无
    AUTH_PASS(3, "征信通过"),
    AUTH_REJECT(4, "征信拒绝"),
    AUTH_INVALID(5, "征信已过期"),
    AUTH_EXCEPTION(6, "征信查询中"),//异常重试场景
    ;

    private static Map<Integer,AuthResultStatusEnum> enumMap = new HashMap<>();

    static {
        for (AuthResultStatusEnum authResultStatusEnum : AuthResultStatusEnum.values()){
            enumMap.put(authResultStatusEnum.getCode(), authResultStatusEnum);
        }
    }

    AuthResultStatusEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static AuthResultStatusEnum getByCode(Integer code){
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
