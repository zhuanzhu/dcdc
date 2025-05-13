package com.egeo.components.utils;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.StringUtils;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class BeanUtil {


    public static <T> T beanCopy(Object source, Class<T> targetClass){

        if(source==null){
            return null;
        }
        String sourceJson  = JSON.toJSONString(source);

        T target = JSON.parseObject(sourceJson,targetClass);
        return target;
    }

    public static <T> T beanCopy(Object source, T target){

        if(source==null){
            return null;
        }

        Map sourceMap = beanCopy(source, Map.class);
        Map targetMap = beanCopy(target, Map.class);
        targetMap.putAll(sourceMap);

        String sourceJson  = JSON.toJSONString(targetMap);
        Class<T> aClass = (Class<T>) target.getClass();
        target = JSON.parseObject(sourceJson,aClass);
        return target;
    }

    public static <T> T beanCopy(Class<T> targetClass,Object source,boolean isCover){
        try {
            if (targetClass==null) {
                return null;
            }
            T target = targetClass.newInstance();
            Field[] fields = targetClass.getDeclaredFields();
            HashMap hashMap = beanCopy(source, HashMap.class);
            setField((T) target, isCover, fields, hashMap);
            return target;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T> T beanCopy(T target,Object source,boolean isCover){
        try {
            if (target==null||source==null) {
                return null;
            }
            Field[] fields = target.getClass().getDeclaredFields();
            HashMap hashMap = beanCopy(source, HashMap.class);
            setField(target, isCover, fields, hashMap);
            return target;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static <T> void setField(T target, boolean isCover, Field[] fields, HashMap hashMap) {
        for(Field field : fields){
            try {
                field.setAccessible(true);
                Object s = hashMap.get(field.getName());
                if(s==null){
                    continue;
                }
                Object t = field.get(target);

                if (!field.getType().equals(s.getClass())) {
                    s = convertType(field.getType(),s);
                }
                if(t==null){
                    field.set(target,s);
                }else if(isCover){
                    field.set(target,s);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 基础类型转换
     * @param targetType
     * @param source
     * @return
     */
    private static Object convertType(Class<?> targetType,Object source) throws ParseException {
        Object target = null;

        if(source==null|| StringUtils.isBlank(source.toString())){
            return target;
        }else if(targetType.equals(Integer.class)){
            target = Integer.valueOf(source.toString());
        }else if(targetType.equals(Long.class)){
            target = Long.valueOf(source.toString());
        }else if(targetType.equals(String.class)){
            target = source.toString();
        }else if(targetType.equals(Date.class)){
            if(source.getClass().equals(String.class)){
                SimpleDateFormat sf = new SimpleDateFormat();
                target = sf.parse(source.toString());
            }else if(source.getClass().equals(Long.class)){
                target = new Date(Long.valueOf(source.toString()));
            }
        }

        return target;
    }

    public static <T> void beanFill(T data){
        try {
            if (data==null) {
                return;
            }
            Class aClass = data.getClass();
            Field[] fields = aClass.getDeclaredFields();
            for(Field field : fields){
                field.setAccessible(true);
                Object o = field.get(data);
                if(o==null){
                    if(field.getType().getName().equals(String.class.getName())){
                        field.set(data,"");
                    }else if(field.getType().getName().equals(Integer.class.getName())){
                        field.set(data,0);
                    }else if(field.getType().getName().equals(Double.class.getName())){
                        field.set(data,0.0);
                    }else if(field.getType().getName().equals(Long.class.getName())){
                        field.set(data,0L);
                    }else if(field.getType().getName().equals(Boolean.class.getName())){
                        field.set(data,false);
                    }else if(field.getType().getName().equals(Date.class.getName())){
                        field.set(data,new Date());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
