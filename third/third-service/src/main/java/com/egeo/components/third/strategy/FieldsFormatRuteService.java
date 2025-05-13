package com.egeo.components.third.strategy;

import com.egeo.components.third.bean.FieldRuteInfo;
import com.egeo.exception.BusinessException;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
public interface FieldsFormatRuteService {

    String getRuteName();

    Object formatValues(Object vaule, FieldRuteInfo fundRuteInfo) throws BusinessException;
}
