package com.egeo.components.third.business.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.egeo.components.third.business.ChannelRuleManage;
import com.egeo.components.third.business.ChannelServiceConfigManage;
import com.egeo.components.third.dto.ChannelServiceFieldConfigDTO;
import com.egeo.components.third.facade.ChannelServiceConfigFacade;
import com.egeo.components.third.facade.ChannelServiceFieldConfigFacade;
import com.egeo.components.utils.ConvertJsonFieldUtil;
import com.egeo.components.utils.JsonPutUtils;
import com.egeo.components.utils.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
@Service("channelServiceConfigManage")
public class ChannelServiceConfigManageImpl implements ChannelServiceConfigManage {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private ChannelServiceConfigFacade channelServiceConfigFacade;

    @Resource
    private ChannelServiceFieldConfigFacade channelServiceFieldConfigFacade;

    @Resource
    private ChannelRuleManage channelRuleManage;

    @Override
    public String convertParam(String channelCode,String channelServiceName,String channelServiceType,String jsonString){
        if(StringUtils.isEmpty(jsonString)){
            return null;
        }
        List<ChannelServiceFieldConfigDTO> serviceFieldConfigDTOS = getChannelServiceFieldConfigDTOS(channelCode, channelServiceName, channelServiceType);
        if(CollectionUtils.isEmpty(serviceFieldConfigDTOS)){
            return jsonString;
        }
        JSONObject json = JSONObject.parseObject(jsonString);
        JSONObject  resultMap = new JSONObject();
        for (ChannelServiceFieldConfigDTO serviceField : serviceFieldConfigDTOS) {
            Object value = ConvertJsonFieldUtil.convertField(serviceField.getSrcFieldName(),serviceField.getSrcParentFieldName(),json);
            value = channelRuleManage.checkAndFormatValue(value,serviceField);
            putValue(serviceField,value,resultMap);
        }
        logger.info("渠道方:{},方法:{},方法类型:{},转换结果:{}",channelCode,channelServiceName, channelServiceType,JSON.toJSONString(resultMap));
        return JSON.toJSONString(resultMap);
    }

    private List<ChannelServiceFieldConfigDTO> getChannelServiceFieldConfigDTOS(String channelCode, String channelServiceName, String channelServiceType) {
        ChannelServiceFieldConfigDTO dto = new ChannelServiceFieldConfigDTO();
        dto.setChannelCode(channelCode);
        dto.setChannelServiceName(channelServiceName);
        dto.setChannelServiceType(channelServiceType);
        dto.setState(0);
        List<ChannelServiceFieldConfigDTO> serviceFieldConfigDTOS = channelServiceFieldConfigFacade.findChannelServiceFieldConfigAll(dto);
        return serviceFieldConfigDTOS;
    }

    private  void putValue(ChannelServiceFieldConfigDTO fieldInfo,Object valueObject,Object targetParentObject){
        try {
            JsonPutUtils.saveObject(targetParentObject, fieldInfo.getTargetFieldName(), fieldInfo.getTargetParentFieldType(), valueObject, fieldInfo.getTargetFieldType(), fieldInfo.getTargetFieldName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
