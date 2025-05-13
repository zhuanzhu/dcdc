package com.egeo.components.third.business;

import com.egeo.components.third.bean.FieldRuteInfo;
import com.egeo.components.third.dto.ChannelServiceFieldConfigDTO;

import java.util.List;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
public interface ChannelRuleManage {

    /****
     * @Description 检查规则和格式化规则组合同时进行
     **/
    Object checkAndFormatValue(Object values, ChannelServiceFieldConfigDTO fieldInfo);

    /****
     * @Description 检查规则
     **/
    boolean checkValue(Object value, String ruteString);

    /****
     * @Description 格式化规则
     **/
    Object formatValue(Object vaule, String ruteString);

    /****
     * @Description 规则字符串转规则对象列表
     **/
    List<FieldRuteInfo> getFundRuteInfoList(String ruteStr);
}
