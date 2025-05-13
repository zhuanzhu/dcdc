package com.egeo.components.third.strategy.impl.rute;

import com.egeo.components.third.bean.FieldRuteInfo;
import com.egeo.components.third.common.RuteNameEnum;
import com.egeo.components.third.strategy.FieldsCheckRuteService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
/**
 *
 *检查等于某个阈值
 */
@Service
public class CheckIsUnEqRuteStrategy implements FieldsCheckRuteService {


	@Override
	public String getRuteName() {

		return RuteNameEnum.RUTE_NAME_CHECK_UNEQ.getRuteName();
	}

	@Override
	public boolean checkValue(Object value, FieldRuteInfo fundRuteInfo) {
		if(null == fundRuteInfo){
			return false;
		}
		if(StringUtils.isEmpty(value+"")){
			return false;
		}
		if(!value.equals(fundRuteInfo.getRuteValues())){
				return true;
		}
		return false;
	}
}
