package com.egeo.components.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description todo
 * @Author lsl
 * @Date 2024/6/3 10:57
 * @Version V1.0
 **/
public class OrderNoticeHelper {

    public static Map<String,Object> returnResult(String codeKey, Integer code, String messageKey, String message){
        Map<String,Object> map = new HashMap<>();
        map.put(codeKey,code);
        map.put(messageKey,message);
        return map;
    }
}
