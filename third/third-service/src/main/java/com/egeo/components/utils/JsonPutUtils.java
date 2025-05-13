package com.egeo.components.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class JsonPutUtils {


	public static Object getStringValue(){
		String str = "杀杀杀";
		return str;
	}

	public static Object getArrValue(){
		Integer arr[] = {1,2,3};
		return arr;
	}

	public static JSONArray getJSONArrayValue(){
		JSONArray jSONArray = new JSONArray();
		jSONArray.add(8);
		jSONArray.add(9);
		jSONArray.add(10);
		return jSONArray;
	}

	public static JSONArray getJSONArrayKVValue(){
		JSONArray jSONArray = new JSONArray();
		for(int i=0;i<3;i++){
			JSONObject v1 = new JSONObject();
			v1.put("key", "value"+i);
			jSONArray.add(v1);
		}
		return jSONArray;
	}

	public static JSONArray getJSONArrayKVValue1(){
		JSONArray jSONArray = new JSONArray();
		for(int i=0;i<3;i++){
			JSONObject v1 = new JSONObject();
			v1.put("NAEM"+i, "张三"+i);
			jSONArray.add(v1);
		}
		return jSONArray;
	}

	public static JSONObject getJSONObjectValue(){
		JSONObject v1 = new JSONObject();
		v1.put("key123", "123");
		return v1;
	}

	public static JSONArray mergeJSONArray(JSONArray jSONArray1,JSONArray jSONArray2){
		if(jSONArray1.isEmpty() || jSONArray2.isEmpty()){
			return null;
		}
		for(int i=0;i<jSONArray1.size();i++){
			jSONArray1.getJSONObject(i).putAll(jSONArray2.getJSONObject(i));
		}
		return jSONArray1;
	}

	public static void main(String[] args) {
//		JSONObject resultJsonObject  = new JSONObject();
//		/**
//		 *将对象放入大对象中
//		 */
//		saveObject(resultJsonObject, "data,result", "JSONObject,JSONArray",getJSONArrayValue(),"JSONArray","ttt");
//		System.out.println("最终结果值="+JSON.toJSONString(resultJsonObject));
		JSONArray jSONArray1 = getJSONArrayKVValue();
		JSONArray jSONArray2 = getJSONArrayKVValue1();
		System.out.println("jSONArray1="+jSONArray1);
		System.out.println("jSONArray2="+jSONArray2);
		//jSONArray1.addAll(jSONArray2);
		System.out.println(mergeJSONArray(jSONArray1, jSONArray2));
	}

	/**
	 * 将对象放入大对象中
	 * @param targetParentObject
	 * 		 存放结果目的对象(父节点)
	 * @param targetParentKeys
	 * 		存放目标的keys对象,用逗号分隔
	 * @param targetParentType
	 * 		存放父节点的类型
	 * @param valueObject
	 * 		目标对象
	 * @param fieldType
	 * 		目标类型
	 * @param key
	 * 		目标key
	 * @throws Exception
	 */
	public static void saveObject(Object targetParentObject,String targetParentKeys,String targetParentType,Object valueObject,String fieldType,String key) throws Exception{
		checkAndInitManyParent(targetParentKeys, targetParentType, targetParentObject);
		Object parentObject = getLastParentObject(targetParentKeys, targetParentType, targetParentObject);
		realPutDataObject(parentObject, valueObject,fieldType,key);
	}

	public static void realPutDataObject(Object parentObject,Object valueObject,String fieldType,String key) throws Exception{
		if(parentObject == null){
			throw new Exception("key="+key+"父节点不能为空");
		}

		if(parentObject instanceof JSONObject){
			Object value =createVauleObjectByJSONObject(fieldType, valueObject,key);
			jsonObjectAdd(value, (JSONObject)parentObject, key, fieldType);
		}

		if(parentObject instanceof JSONArray){
			Object value = createVauleObject(fieldType, valueObject,key);
			jsonArrayAdd(value,  ((JSONArray)parentObject));
		}
	}

	public static void jsonObjectAdd(Object value,JSONObject parentObject,String key,String fieldType){
		if(StringUtils.isEmpty(fieldType) || "JSONObject".equals(fieldType) || "simple".equals(fieldType)){
			parentObject.put(key, value);
			return;
		}

		if("Array".equals(fieldType) || "List".equals(fieldType)){
			if(value instanceof JSONArray){
				parentObject.put(key, value);
			}else{
				List<Object> list = new ArrayList<>();
				list.add(value);
				parentObject.put(key, list);
			}

			return;
		}
		if("JSONArray".equals(fieldType)){
			if(value instanceof JSONArray){
				parentObject.put(key, value);
			}else{
				JSONArray jSONArray  =new JSONArray();
				JSONObject json = new JSONObject();
				json.put(key, value);
				jSONArray.add(json);
				parentObject.put(key, jSONArray);
			}
		}
	}


	public static void jsonArrayAdd(Object value,JSONArray parentObject){
		if(parentObject.isEmpty()){
			if(value instanceof JSONArray){
				JSONArray values = ((JSONArray)value);
				for(Object o :values){
					parentObject.add(o);
				}
			}else{
				parentObject.add(value);
			}
			return;
		}
		if(value instanceof JSONArray){
			mergeJSONArray(parentObject,  ((JSONArray)value));
		}

		if(value instanceof JSONObject){
			boolean isJsonObject = false;
			for(int i=0;i<parentObject.size();i++){
				Object vv = parentObject.get(i);
				if(vv instanceof JSONObject){
					((JSONObject)vv).putAll((JSONObject)value);
					isJsonObject = true;
				}
			}

			if(!isJsonObject){
				parentObject.add(value);
			}
		}


	}

	public static Object createVauleObject(String fieldType,Object values,String key){
		if(null == values){
			values = "";
		}
		if(StringUtils.isEmpty(fieldType) || "JSONObject".equals(fieldType) || "simple".equals(fieldType)){
			JSONObject json = new JSONObject();
			json.put(key, values);
			return json;
		}
		if("Array".equals(fieldType) || "List".equals(fieldType)){
			List<Object> list = new ArrayList<>();
			list.add(values);
			JSONObject json = new JSONObject();
			json.put(key, list);
			return json;
		}
		if("JSONArray".equals(fieldType)){

			if(values instanceof JSONObject){
				JSONObject json = new JSONObject();
				json.put(key, values);
				return json;
			}
			if(values instanceof JSONArray){
				JSONArray jSONArray  =new JSONArray();
				JSONArray valuesJSONArray = ((JSONArray)values);
				for(Object o:valuesJSONArray){
					JSONObject json = new JSONObject();
					json.put(key, o);
					jSONArray.add(json);
				}
				return jSONArray;
			}
			if(!(values instanceof JSONObject || values instanceof JSONArray)){
				JSONArray jSONArray  =new JSONArray();
				JSONObject json = new JSONObject();
				json.put(key, values);
				jSONArray.add(json);
				return jSONArray;
			}
		}
		return null;
	}


	public static Object createVauleObjectByJSONObject(String fieldType,Object values,String key){
		if(null == values){
			values = "";
		}
		return values;
	}

	public static void checkAndInitManyParent(String targetParentKeys,String targetParentType,Object targetParentObject){
		if(StringUtils.isEmpty(targetParentKeys)){
			return;
		}
		Object nextParent = null;
		if(targetParentKeys.indexOf(",") !=-1){
			String targetParentKeysArr[] = targetParentKeys.split(",");
			String targetParentTypeArr[] = targetParentType.split(",");
			for(int i=0;i<targetParentKeysArr.length;i++){
				if(i == 0){
					checkAndInitSingleParent(targetParentKeysArr[i], targetParentTypeArr[i], targetParentObject);
					nextParent = getNextObject(targetParentObject, targetParentKeysArr[i], targetParentTypeArr[i]);
					continue;
				}
				checkAndInitSingleParent(targetParentKeysArr[i], targetParentTypeArr[i], nextParent);
				nextParent = getNextObject(nextParent, targetParentKeysArr[i], targetParentTypeArr[i]);
			}
		}else{
			checkAndInitSingleParent(targetParentKeys, targetParentType, targetParentObject);
		}

	}

	public static Object getLastParentObject(String targetParentKeys,String targetParentType,Object targetParentObject){
		if(StringUtils.isEmpty(targetParentKeys)){
			return targetParentObject;
		}
		Object nextParent = null;
		if(targetParentKeys.indexOf(",") !=-1){
			String targetParentKeysArr[] = targetParentKeys.split(",");
			String targetParentTypeArr[] = targetParentType.split(",");
			for(int i=0;i<targetParentKeysArr.length;i++){
				if(i == 0){
					nextParent = getNextObject(targetParentObject, targetParentKeysArr[i], targetParentTypeArr[i]);
					continue;
				}
				nextParent = getNextObject(nextParent, targetParentKeysArr[i], targetParentTypeArr[i]);

			}
		}else{
			nextParent = getNextObject(targetParentObject, targetParentKeys, targetParentType);
		}
		return nextParent;
	}

	public static Object getNextObject(Object targetParentObject,String targetParentKey,String parentType){
		if(targetParentObject instanceof JSONObject){
			return ((JSONObject)targetParentObject).get(targetParentKey);
		}

		if(targetParentObject instanceof JSONArray){
			JSONArray jSONArray = ((JSONArray)targetParentObject);
				for(Object object:jSONArray){
					return getNextObject(object, targetParentKey,parentType);
				}

		}

		return createNullObject(targetParentKey, parentType);
	}

	/**
	 * 检查父节点key是否存在：存在跳过，否则按照父节点类型初始化一个对象
	 * @param targetParentKey
	 * @param parentType
	 * @param targetObject
	 */
	public static void checkAndInitSingleParent(String targetParentKey,String parentType,Object targetObject){
		boolean isExists = checkSingleParentContainsKey(targetParentKey,targetObject);
		if(isExists){
			return ;
		}
		initSingleParentKey(targetParentKey, parentType, targetObject);
	}

	public static void initSingleParentKey(String targetParentKey,String parentType,Object targetObject){
		Object nextObject = createNullObject(targetParentKey, parentType);
		if(targetObject instanceof JSONObject){
			 ((JSONObject)targetObject).put(targetParentKey, nextObject);
		}

		if(targetObject instanceof JSONArray){
			JSONObject json = new JSONObject();
			json.put(targetParentKey,nextObject);
			((JSONArray)targetObject).add(json);
		}
	}

	public static Object createNullObject(String targetParentKey,String parentType){
		if("JSONObject".equals(parentType)){
			return new JSONObject();
		}
		if("JSONArray".equals(parentType)){
			return new JSONArray();
		}

		return null;
	}

	/**
	 * 检查父节点是否存在
	 * @param targetParentKey
	 * @param targetObject
	 * @return
	 */
	public static boolean checkSingleParentContainsKey(String targetParentKey,Object targetObject){
		if(StringUtils.isEmpty(targetParentKey)){
			return false;
		}
		if(null == targetObject){
			return false;
		}
		if(!(targetObject instanceof JSONObject || targetObject instanceof JSONArray)){
			return false;
		}
		if(targetObject instanceof JSONObject){
			return ((JSONObject)targetObject).containsKey(targetParentKey);
		}
		if(targetObject instanceof JSONArray){
			JSONArray parentNodes = ((JSONArray)targetObject);
			if(parentNodes.isEmpty()){
				return false;
			}
			for(Object parentNode:parentNodes){
				//只解析当前层
				return checkSingleParentContainsKey(targetParentKey,parentNode);

			}
		}

		return true;
	}
}
