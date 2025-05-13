package com.egeo.components.order.strategy.service.impl.push;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.egeo.components.order.strategy.service.PushUserOrderStrategy;
import com.egeo.components.order.vo.push.PushOrderRequestVO;
import com.egeo.components.order.vo.push.PushOrderRequestExtVO;
import com.egeo.components.third.client.ChannelServiceConfigClient;
import com.egeo.components.third.dto.EnterpriseBizConfigDTO;
import com.egeo.components.third.dto.RemoteExecuteDTO;
import com.egeo.components.third.enums.ChannelServiceNameEnum;
import com.egeo.components.third.enums.ChannelServiceTypeEnum;
import com.egeo.web.JsonResult;

import java.util.List;

/**
 * @Description todo
 * @Author lsl
 * @Date 2024/12/30 14:46
 * @Version V1.0
 **/
public abstract class PushUserOrderCommonImpl implements PushUserOrderStrategy {



    public JSONObject pushOrder(List<EnterpriseBizConfigDTO> bizConfigDTOList, PushOrderRequestVO requestVO,ChannelServiceConfigClient channelServiceConfigClient) {
        for (EnterpriseBizConfigDTO bizConfigDTO : bizConfigDTOList) {
            try {
                RemoteExecuteDTO dto = new RemoteExecuteDTO();
                dto.setEnterpriseId(Integer.valueOf(bizConfigDTO.getEnterpriseId()));
                dto.setChannelCode(bizConfigDTO.getBizCode());
                dto.setChannelServiceName(ChannelServiceNameEnum.ORDER_PUSH.getChannelServiceName());
                dto.setChannelServiceType(ChannelServiceTypeEnum.REQ.getChannelServiceType());
                dto.setBizCode(requestVO.getOrderCode());
                dto.setJsonString(JSON.toJSONString(requestVO));
                JsonResult jsonResult =  channelServiceConfigClient.remoteExecute(dto);
                if(jsonResult !=null && jsonResult.getCode() ==0 && jsonResult.getData() !=null){
                    return  JSONObject.parseObject(JSON.toJSONString(jsonResult.getData()));
                }
            }catch (Exception e){
                e.printStackTrace();
            }

        }
        return null;
    }

    public JSONObject pushOrder(List<EnterpriseBizConfigDTO> bizConfigDTOList, PushOrderRequestExtVO requestVO, ChannelServiceConfigClient channelServiceConfigClient) {
        for (EnterpriseBizConfigDTO bizConfigDTO : bizConfigDTOList) {
            try {
                RemoteExecuteDTO dto = new RemoteExecuteDTO();
                dto.setEnterpriseId(Integer.valueOf(bizConfigDTO.getEnterpriseId()));
                dto.setChannelCode(bizConfigDTO.getBizCode());
                dto.setChannelServiceName(ChannelServiceNameEnum.ORDER_PUSH.getChannelServiceName());
                dto.setChannelServiceType(ChannelServiceTypeEnum.REQ.getChannelServiceType());
                dto.setBizCode(requestVO.getOrderCode());
                dto.setJsonString(JSON.toJSONString(requestVO));
                JsonResult jsonResult =  channelServiceConfigClient.remoteExecute(dto);
                if(jsonResult !=null && jsonResult.getCode() ==0 && jsonResult.getData() !=null){
                    return  JSONObject.parseObject(JSON.toJSONString(jsonResult.getData()));
                }
            }catch (Exception e){
                e.printStackTrace();
            }

        }
        return null;
    }


    public JSONObject pushOrder(List<EnterpriseBizConfigDTO> bizConfigDTOList,String bizCode,String nextCode, String jsonString, ChannelServiceConfigClient channelServiceConfigClient) {
        for (EnterpriseBizConfigDTO bizConfigDTO : bizConfigDTOList) {
            try {
                RemoteExecuteDTO dto = new RemoteExecuteDTO();
                dto.setEnterpriseId(Integer.valueOf(bizConfigDTO.getEnterpriseId()));
                dto.setChannelCode(bizConfigDTO.getBizCode());
                dto.setChannelServiceName(ChannelServiceNameEnum.ORDER_PUSH.getChannelServiceName());
                dto.setChannelServiceType(ChannelServiceTypeEnum.REQ.getChannelServiceType());
                dto.setBizCode(bizCode);
                dto.setNextBizCode(nextCode);
                dto.setJsonString(jsonString);
                JsonResult jsonResult =  channelServiceConfigClient.remoteExecute(dto);
                if(jsonResult !=null && jsonResult.getCode() ==0 && jsonResult.getData() !=null){
                    return  JSONObject.parseObject(JSON.toJSONString(jsonResult.getData()));
                }
            }catch (Exception e){
                e.printStackTrace();
            }

        }
        return null;
    }
}
