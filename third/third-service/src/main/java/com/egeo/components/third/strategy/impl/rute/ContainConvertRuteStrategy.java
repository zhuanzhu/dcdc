package com.egeo.components.third.strategy.impl.rute;

import java.util.Arrays;

import com.egeo.components.third.bean.FieldRuteInfo;
import com.egeo.components.third.common.RuteNameEnum;
import com.egeo.components.third.strategy.FieldsFormatRuteService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;


@Service
public class ContainConvertRuteStrategy implements FieldsFormatRuteService {

	@Override
	public String getRuteName() {

		return RuteNameEnum.RUTE_NAME_CONTAIN.getRuteName();
	}

	@Override
	public Object formatValues(Object vaule, FieldRuteInfo fundRuteInfo) {
		if(null == vaule){
			return vaule;
		}
		if(StringUtils.isEmpty(vaule.toString())){
			return vaule;
		}
		return replaceValue(vaule, fundRuteInfo.getRuteValues());
	}


	private static Object replaceValue(Object values,String ruteValues){
		JSONArray jsonArr = JSONObject.parseArray(ruteValues);
		for(int i=0;i<jsonArr.size();i++){
			JSONObject json = jsonArr.getJSONObject(i);
			Object arr[] = json.keySet().toArray();
			if(arr[0].toString().indexOf(",") !=-1){
				String keys[] = arr[0].toString().split(",");
				if(Arrays.asList(keys).contains(values)){
					return json.values().toArray()[0];
				}
			}else{
				if(arr[0].toString().equals(values.toString())){
					return json.values().toArray()[0];
				}
			}

		}
		return values;
	}
}
