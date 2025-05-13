package com.egeo.components.user.business.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.egeo.components.third.client.ChannelServiceConfigClient;
import com.egeo.components.third.dto.RemoteExecuteDTO;
import com.egeo.components.third.enums.ChannelServiceNameEnum;
import com.egeo.components.third.enums.ChannelServiceTypeEnum;
import com.egeo.components.user.enums.UserChannelSourceEnum;
import com.egeo.utils.log.XLogger;
import com.egeo.web.JsonResult;
import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class DLFUserUtils {
    private static final XLogger logger = XLogger.getLogger(DLFUserUtils.class);

    @Resource
    private ChannelServiceConfigClient channelServiceConfigClient;


    public static final Long companyId=68l;

    public boolean checkUser(String mobile){
        RemoteExecuteDTO executeDTO = new RemoteExecuteDTO();
        executeDTO.setChannelCode(UserChannelSourceEnum.DLF.getChannelSource());
        executeDTO.setChannelServiceName(ChannelServiceNameEnum.USER_CASH.getChannelServiceName());
        executeDTO.setChannelServiceType(ChannelServiceTypeEnum.REQ.getChannelServiceType());
        executeDTO.setBizCode(mobile);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("mobile",mobile);
        executeDTO.setJsonString(JSON.toJSONString(jsonObject));
        JsonResult jsonResult = channelServiceConfigClient.remoteExecute(executeDTO);
        logger.info("channelServiceConfigClient.remoteExecute,请求结果:mobile:{}-{}" ,mobile,JSON.toJSONString(jsonResult));
        if(jsonResult !=null && jsonResult.getData() !=null && jsonResult.getCode()==0){
            JSONObject parseObject =JSONObject.parseObject(JSON.toJSONString(jsonResult.getData()));
            if("1".equals(parseObject.getString("status"))){
                return true;
            }
        }
        return false;
    }
}
