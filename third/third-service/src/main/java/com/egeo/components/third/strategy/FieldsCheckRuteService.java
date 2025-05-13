package com.egeo.components.third.strategy;

import com.egeo.components.third.bean.FieldRuteInfo;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
public interface FieldsCheckRuteService {

    String getRuteName();

    public boolean checkValue(Object value, FieldRuteInfo fundRuteInfo);
}
