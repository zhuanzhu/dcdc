package com.egeo.components.third.strategy.impl.rute;

import com.egeo.components.third.bean.FieldRuteInfo;
import com.egeo.components.third.common.RuteNameEnum;
import com.egeo.components.third.strategy.FieldsFormatRuteService;
import org.springframework.stereotype.Service;


@Service
public class SetDefaultValueRuteStrategy implements FieldsFormatRuteService {

	@Override
	public String getRuteName() {
		return RuteNameEnum.RUTE_NAME_SET_DEFAULT_VALUE.getRuteName();
	}

	@Override
	public Object formatValues(Object vaule, FieldRuteInfo fundRuteInfo) {

		return replaceValue(vaule, fundRuteInfo.getRuteValues());
	}

	private Object replaceValue(Object values,String ruteValues){
		return ruteValues;
	}

}
