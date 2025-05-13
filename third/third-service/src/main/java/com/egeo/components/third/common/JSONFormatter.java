package com.egeo.components.third.common;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;

import java.util.*;


public final class JSONFormatter {

    /**
     * JSONObject转为map
     * @param object json对象
     * @return 转化后的Map
     */
    public static Map<String, Object> toMap(JSONObject object){
        Map<String, Object> map = new HashMap<String, Object>();

        for (Iterator<?> it = object.keySet().iterator(); it.hasNext();) {
            String key = (String)it.next();
            Object value;
            try {
                value = object.get(key);
                if(value instanceof JSONArray) {
                    value = toList((JSONArray) value);
                }

                else if(value instanceof JSONObject) {
                    value = toMap((JSONObject) value);
                }
                map.put(key, value);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        return map;
    }

    /**
     * JSONArray转为List
     * @param array json数组
     * @return 转化后的List
     */
    public static List<Object> toList(JSONArray array){
        List<Object> list = new ArrayList<Object>();
        for(int i = 0; i < array.size(); i++) {
            Object value;
            try {
                value = array.get(i);
                if(value instanceof JSONArray) {
                    value = toList((JSONArray) value);
                }

                else if(value instanceof JSONObject) {
                    value = toMap((JSONObject) value);
                }
                list.add(value);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        return list;
    }
}
