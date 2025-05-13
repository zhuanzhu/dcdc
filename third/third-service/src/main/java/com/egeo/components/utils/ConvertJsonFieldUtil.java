package com.egeo.components.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
public class ConvertJsonFieldUtil {

    public static void main(String[] args) {
        JSONObject dataJson =  buildData();
        System.out.println(JsonUtils.objectToJson(dataJson));
        Object v = convertField("wf","data,levf1",dataJson);
        System.out.println(v);
    }

    private static JSONObject buildData(){
        JSONObject dataJson = new JSONObject();
        dataJson.put("code",1);
        dataJson.put("message","操作成功");
        JSONObject json = new JSONObject();
        json.put("userName","张三");
        json.put("age",20);
        JSONObject jsonChild = new JSONObject();
        jsonChild.put("wf","李四");
        json.put("levf1",jsonChild);
        JSONArray jsonArray = new JSONArray();
        JSONObject son1 = new JSONObject();
        son1.put("son1","第一个");
        jsonArray.add(son1);
        JSONObject son2 = new JSONObject();
        son2.put("son2","第一个");
        jsonArray.add(son2);
        json.put("son",jsonArray);
        dataJson.put("data",json);
        return dataJson;
    }

    public static Object convertField(String srcField, String srcParentField,JSONObject json){
        Object result = getValueOfKey(srcField,srcParentField,json);
        return result;
    }


    private static Object getValueOfKey(String srcField,String srcParentField,Object currObject){
        if(StringUtils.isEmpty(srcParentField)){
            return getValue(srcField,currObject);
        }
        return getObjectByParent(srcField,srcParentField,currObject);
    }

    private static Object getObjectByParent(String srcField,String srcParentFields,Object currObject){
        List<String> list = Arrays.asList(srcParentFields.split(","));
        boolean isFirst = true;
        Object result = null;
        for (String srcParentField : list) {
            if(isFirst){
                result = getValue(srcParentField,currObject);
                isFirst = false;
                continue;
            }
            result = getValue(srcParentField,result);
        }

        return getValue(srcField,result);
    }

    private static Object getValue(String key,Object currObject){
        if(currObject == null){
            return null;
        }
        if(currObject instanceof JSONObject){
            return getValueOfKey(key,(JSONObject)currObject);
        }
        if(currObject instanceof JSONArray){
            return getValueOfKey(key,(JSONArray)currObject);
        }
        return null;
    }

    private static Object getValueOfKey(String srcField,JSONArray jSONArray){
        return parseJSONArray(jSONArray,srcField);
    }

    private static Object getValueOfKey(String srcField,JSONObject json){
        //若查找对象都为null了毫无意义
        if(null == json || !json.containsKey(srcField)){
            return null;
        }
        return json.get(srcField);
    }


    private static Object parseJSONArray(JSONArray objArray,String key){
        if(objArray.size() ==1){
            Object value = objArray.get(0);
            if(value instanceof JSONObject){
                JSONObject job =  objArray.getJSONObject(0);
                return job.get(key);
            }
        }
        JSONArray resultJSONArray = new JSONArray();
        for (int i = 0; i < objArray.size(); i++) {
            Object value = objArray.get(i);
            if(value instanceof JSONObject){
                JSONObject job =  objArray.getJSONObject(i);
                if(job.containsKey(key)){
                    Object vv = job.get(key);
                    if(vv instanceof JSONArray){
                        JSONArray ja = ((JSONArray)vv);
                        for(Object o:ja){
                            resultJSONArray.add(o);
                        }
                    }else{
                        resultJSONArray.add(job.get(key));
                    }
                }
            }else{
                resultJSONArray.add(value);
            }
        }
        return resultJSONArray;
    }
}
