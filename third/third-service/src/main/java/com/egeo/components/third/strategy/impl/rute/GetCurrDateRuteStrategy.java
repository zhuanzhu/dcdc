package com.egeo.components.third.strategy.impl.rute;

import com.egeo.components.third.bean.FieldRuteInfo;
import com.egeo.components.third.common.RuteNameEnum;
import com.egeo.components.third.strategy.FieldsFormatRuteService;
import com.egeo.utils.DateUtils;
import org.springframework.stereotype.Service;

@Service
public class GetCurrDateRuteStrategy  implements FieldsFormatRuteService {

	@Override
	public String getRuteName() {
		return RuteNameEnum.RUTE_NAME_CURR_DATE.getRuteName();
	}

	@Override
	public Object formatValues(Object vaule, FieldRuteInfo fundRuteInfo) {
		if(null == vaule){
			return DateUtils.getDate();
		}
		return null;
	}

}
