package com.egeo.components.third.strategy.impl.rute;

import com.egeo.components.third.bean.FieldRuteInfo;
import com.egeo.components.third.common.RuteNameEnum;
import com.egeo.components.third.strategy.FieldsFormatRuteService;
import com.egeo.components.utils.Base64Tools;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 *
 *将字符串转base64
 */
@Service
public class Base64EnRuteStrategy implements FieldsFormatRuteService {

	@Override
	public String getRuteName() {
		// TODO Auto-generated method stub
		return RuteNameEnum.RUTE_NAME_BASE64_DE.getRuteName();
	}

	@Override
	public Object formatValues(Object vaule, FieldRuteInfo fundRuteInfo)  {
		if(null == vaule){
			return vaule;
		}
		if(StringUtils.isEmpty(vaule+"")){
			return vaule;
		}
		return Base64Tools.decodeStr(vaule.toString());
	}

}
