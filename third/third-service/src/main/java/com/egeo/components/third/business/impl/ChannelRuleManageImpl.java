package com.egeo.components.third.business.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.egeo.components.third.bean.FieldRuteInfo;
import com.egeo.components.third.business.ChannelRuleManage;
import com.egeo.components.third.dto.ChannelServiceFieldConfigDTO;
import com.egeo.components.third.strategy.FieldsFormatRuteService;
import com.egeo.components.third.strategy.factory.FieldsConvertFactory;
import com.egeo.exception.BusinessException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description 检查规则和格式化规则组合服务
 * @Author lsl
 * @Version V1.0
 **/
@Service
public class ChannelRuleManageImpl implements ChannelRuleManage {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private FieldsConvertFactory fieldsConvertFactory;


    @Override
    public Object checkAndFormatValue(Object values, ChannelServiceFieldConfigDTO fieldInfo) {
        if(checkValue(values, fieldInfo.getCheckRute())){
            logger.error("接口:{},字段:{},描述:{},规则未通过,请核查..",fieldInfo.getChannelServiceName(),fieldInfo.getSrcFieldName(),fieldInfo.getFieldDescription());
            throw new BusinessException("接口:"+fieldInfo.getChannelServiceName()+"字段:"+fieldInfo.getSrcFieldName()+fieldInfo.getFieldDescription()+"规则未通过,请核查..");
        }
        return formatValue(values, fieldInfo.getFormatRute());
    }

    @Override
    public boolean checkValue(Object value, String ruteString){
        if(StringUtils.isEmpty(ruteString)){
            return false;
        }
        List<FieldRuteInfo> fundRuteInfoList = getFundRuteInfoList(ruteString);
        for(FieldRuteInfo fundRuteInfo:fundRuteInfoList){
            if(fieldsConvertFactory.getFieldsCheckRuteService(fundRuteInfo.getRuteName()).checkValue(value, fundRuteInfo)){
                return true;
            }
        }
        return false;
    }

    @Override
    public Object formatValue(Object vaule, String ruteString) {
        if(StringUtils.isEmpty(ruteString)){
            return vaule;
        }
        List<FieldRuteInfo> fundRuteInfoList = getFundRuteInfoList(ruteString);
        for(FieldRuteInfo fundRuteInfo:fundRuteInfoList){
            FieldsFormatRuteService fieldsFormatRuteService = fieldsConvertFactory.getFieldsFormatRuteService(fundRuteInfo.getRuteName());
            vaule = fieldsFormatRuteService.formatValues(vaule, fundRuteInfo);
        }
        return vaule;
    }

    @Override
    public List<FieldRuteInfo> getFundRuteInfoList(String ruteStr){
        try {
            if(StringUtils.isEmpty(ruteStr)){
                return new ArrayList<>();
            }
            JSONArray jsonArr =new JSONArray();
            List<FieldRuteInfo> list = new ArrayList<>();
            jsonArr = JSONObject.parseArray(ruteStr);
            list = JSONObject.parseArray(jsonArr.toJSONString(), FieldRuteInfo.class);
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("规则格式有误，转换成规则集出错!{}",e);
        }
        return null;
    }
}
