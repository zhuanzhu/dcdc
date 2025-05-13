package com.egeo.components.third.strategy.impl.rute;
import com.egeo.components.third.bean.FieldRuteInfo;
import com.egeo.components.third.common.RuteNameEnum;
import com.egeo.components.third.strategy.FieldsFormatRuteService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;


/**
 *
 *String ruteStr = "[{\"ruteValues\":[{\"上海市,北京市,重庆,重庆市\":\"市辖区\"},{\"海南\":\"海南省\"}],\"ruteName\":\"containConvert\"}]";
 */
@Service
public class IncludeConvertRuteStrategy implements FieldsFormatRuteService {

	@Override
	public String getRuteName() {

		return RuteNameEnum.RUTE_NAME_INCLUDE.getRuteName();
	}

	@Override
	public Object formatValues(Object value, FieldRuteInfo fundRuteInfo){
		if(null == value){
			return value;
		}
		if(StringUtils.isEmpty(value.toString())){
			return value;
		}

		return replaceValue(value, fundRuteInfo.getRuteValues());
	}


	private static Object replaceValue(Object values,String ruteValues){
		JSONArray jsonArr = JSONObject.parseArray(ruteValues);
		for(int i=0;i<jsonArr.size();i++){
			JSONObject json = jsonArr.getJSONObject(i);
			Object arr[] = json.keySet().toArray();
			if(arr[0].toString().indexOf(",") !=-1){
				String keys[] = arr[0].toString().split(",");
				for(String key:keys){
					if(key.indexOf(values.toString()) !=-1){
						return json.values().toArray()[0];
					}
				}
			}else{
				if(arr[0].toString().indexOf(values.toString()) !=-1){
					return json.values().toArray()[0];
				}
			}

		}
		return values;
	}
}
