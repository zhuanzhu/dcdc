package com.egeo.components.third.strategy.impl.rute;

import com.egeo.components.third.bean.FieldRuteInfo;
import com.egeo.components.third.common.DateUtils;
import com.egeo.components.third.common.RuteNameEnum;
import com.egeo.components.third.strategy.FieldsFormatRuteService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;



@Service
public class DateFormatRuteStrategy implements FieldsFormatRuteService {

	@Override
	public String getRuteName() {

		return RuteNameEnum.RUTE_NAME_DATE_FORMAT.getRuteName();
	}

	@Override
	public Object formatValues(Object vaule, FieldRuteInfo fundRuteInfo) {
		if(null == vaule){
			return vaule;
		}
		if(StringUtils.isEmpty(vaule+"")){
			return vaule;
		}
		return DateUtils.parseDate(fundRuteInfo.getRuteValues(), vaule.toString());
	}

}
