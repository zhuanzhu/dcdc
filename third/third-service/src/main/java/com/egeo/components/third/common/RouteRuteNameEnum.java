package com.egeo.components.third.common;

import lombok.Getter;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
public enum RouteRuteNameEnum {
    /**暂时仅支持列表排序**/
    LIST_SORT("LIST_SORT","列表排序返回列表"),

    LIST_RANDOM("LIST_RANDOM","列表中随机多选一"),
    LIST_WEIGHT("LIST_WEIGHT","列表中权重多选一"),
    LIST_POLL("LIST_POLL","列表中轮询多选一"),


    ;
    @Getter
    private String ruteName;

    @Getter
    private String msg;

    private RouteRuteNameEnum(String ruteName, String msg) {
        this.ruteName = ruteName;
        this.msg = msg;
    }

}
