package com.egeo.components.third.strategy.impl.rute;

import java.math.BigDecimal;

import com.egeo.components.third.bean.FieldRuteInfo;
import com.egeo.components.third.common.RuteNameEnum;
import com.egeo.components.third.strategy.FieldsFormatRuteService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 *
 *保留小数点规则
 */
@Service
public class ScaleFormatRuteStrategy implements FieldsFormatRuteService {

	@Override
	public String getRuteName() {
		return RuteNameEnum.RUTE_NAME_SCALE_FORMAT.getRuteName();
	}

	@Override
	public Object formatValues(Object vaule, FieldRuteInfo fundRuteInfo) {
		if(null == vaule){
			return vaule;
		}
		if(StringUtils.isEmpty(vaule+"")){
			return vaule;
		}
		return new BigDecimal(vaule.toString()).setScale(Integer.valueOf(fundRuteInfo.getRuteValues()), BigDecimal.ROUND_HALF_UP);
	}

}
