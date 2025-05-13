package com.egeo.components.third.strategy.impl.rute;

import com.egeo.components.third.bean.FieldRuteInfo;
import com.egeo.components.third.common.RuteNameEnum;
import com.egeo.components.third.strategy.FieldsFormatRuteService;
import com.egeo.components.utils.FileDownUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 *
 *通过url下载文件二进制转base64
 */
@Service
public class FileUrlDownBase64RuteStrategy implements FieldsFormatRuteService {

	@Override
	public String getRuteName() {

		return RuteNameEnum.RUTE_NAME_FILE_BASE64.getRuteName();
	}

	@Override
	public Object formatValues(Object vaule, FieldRuteInfo fundRuteInfo) {
		if(null == vaule){
			return vaule;
		}
		if(StringUtils.isEmpty(vaule+"")){
			return vaule;
		}
		return FileDownUtils.downLoadInputStreamFromUrlBase64Str(vaule.toString());
	}

}
