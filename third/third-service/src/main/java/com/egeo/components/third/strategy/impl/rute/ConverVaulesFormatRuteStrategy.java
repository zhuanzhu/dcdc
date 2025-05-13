package com.egeo.components.third.strategy.impl.rute;


import com.egeo.components.third.bean.FieldRuteInfo;
import com.egeo.components.third.common.RuteNameEnum;
import com.egeo.components.third.strategy.FieldsFormatRuteService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

@Service
public class ConverVaulesFormatRuteStrategy implements FieldsFormatRuteService {

	@Override
	public String getRuteName() {

		return RuteNameEnum.RUTE_NAME_CONVER_STATUS.getRuteName();
	}

	@Override
	public Object formatValues(Object vaule, FieldRuteInfo fundRuteInfo) {
		if(null == vaule){
			return vaule;
		}
		if(StringUtils.isEmpty(vaule+"")){
			return vaule;
		}
		vaule = replaceValue(vaule, fundRuteInfo.getRuteValues());
		return vaule;
	}

	private Object replaceValue(Object values,String ruteValues){
		JSONArray jsonArr = JSONObject.parseArray(ruteValues);
		for(int i=0;i<jsonArr.size();i++){
			JSONObject json = jsonArr.getJSONObject(i);
			String newValues = json.getString(values.toString());
			if(null !=newValues){
				return newValues;
			}
		}

		return values;
	}
}
