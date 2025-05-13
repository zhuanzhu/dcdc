package com.egeo.components.third.strategy.impl.rute;

import com.egeo.components.third.bean.FieldRuteInfo;
import com.egeo.components.third.common.RuteNameEnum;
import com.egeo.components.third.strategy.FieldsFormatRuteService;
import com.egeo.components.utils.BigDecimalUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 *
 *人民币将元转换为分
 */
@Service
public class RmbConvertFToYRuteStrategy implements FieldsFormatRuteService {

	@Override
	public String getRuteName() {

		return RuteNameEnum.RUTE_NAME_RMB_F_TO_Y.getRuteName();
	}

	@Override
	public Object formatValues(Object vaule, FieldRuteInfo fundRuteInfo){
		if(null == vaule){
			return vaule;
		}
		if(StringUtils.isEmpty(vaule+"")){
			return vaule;
		}
		return BigDecimalUtils.rmbConvertFToY(vaule.toString());
	}

}
