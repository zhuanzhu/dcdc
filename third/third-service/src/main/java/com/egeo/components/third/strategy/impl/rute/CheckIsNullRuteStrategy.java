package com.egeo.components.third.strategy.impl.rute;

import com.egeo.components.third.bean.FieldRuteInfo;
import com.egeo.components.third.common.RuteNameEnum;
import com.egeo.components.third.strategy.FieldsCheckRuteService;
import org.springframework.stereotype.Service;


@Service
public class CheckIsNullRuteStrategy implements FieldsCheckRuteService {


	@Override
	public String getRuteName() {

		return RuteNameEnum.RUTE_NAME_CHECK_NULL.getRuteName();
	}

	@Override
	public boolean checkValue(Object value, FieldRuteInfo fundRuteInfo) {
		if(null == fundRuteInfo){
			return false;
		}

		if("Y".equals(fundRuteInfo.getRuteValues())){
			if(null == value ){
				return true;
			}
		}
		return false;
	}
}
