package com.egeo.components.user.business.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.egeo.components.third.client.ChannelServiceConfigClient;
import com.egeo.components.third.dto.RemoteExecuteDTO;
import com.egeo.components.third.enums.ChannelServiceNameEnum;
import com.egeo.components.third.enums.ChannelServiceTypeEnum;
import com.egeo.components.user.enums.UserChannelSourceEnum;
import com.egeo.components.user.vo.ChannelUserUniqueRegLoginVO;
import com.egeo.utils.StringUtils;
import com.egeo.utils.log.XLogger;
import com.egeo.web.JsonResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

@Service
public class ChannelUserUtils {
    private static final XLogger logger = XLogger.getLogger(ChannelUserUtils.class);

    @Resource
    private ChannelServiceConfigClient channelServiceConfigClient;



    public boolean checkUser(String channelUserUnique, String channelSource, ChannelUserUniqueRegLoginVO vo){
        RemoteExecuteDTO executeDTO = new RemoteExecuteDTO();
        executeDTO.setChannelCode(channelSource);
        executeDTO.setChannelServiceName(ChannelServiceNameEnum.USER_CASH.getChannelServiceName());
        executeDTO.setChannelServiceType(ChannelServiceTypeEnum.REQ.getChannelServiceType());
        executeDTO.setBizCode(channelUserUnique);
        JSONObject jsonObject = new JSONObject();
        if(Objects.equals(channelSource,UserChannelSourceEnum.DLF.getChannelSource())){
            jsonObject.put("mobile",channelUserUnique);
        }else{
            executeDTO.setChannelServiceName(ChannelServiceNameEnum.USER_INFO.getChannelServiceName());
            jsonObject.put("user_id",channelUserUnique);
        }
        executeDTO.setJsonString(JSON.toJSONString(jsonObject));
        JsonResult jsonResult = channelServiceConfigClient.remoteExecute(executeDTO);
        logger.info("channelServiceConfigClient.remoteExecute,请求结果:channelUserUnique:{}-{}-{}" ,channelSource,channelUserUnique,JSON.toJSONString(jsonResult));
        if(jsonResult !=null && jsonResult.getData() !=null && jsonResult.getCode()==0){
            JSONObject parseObject =JSONObject.parseObject(JSON.toJSONString(jsonResult.getData()));
            if("1".equals(parseObject.getString("status"))){
                if(Objects.equals(channelSource,UserChannelSourceEnum.DLF.getChannelSource())) {
                    return true;
                }else if(Objects.equals(channelSource,UserChannelSourceEnum.YD.getChannelSource())){
                    JSONObject dataJson = parseObject.getJSONObject("data");
                    if(dataJson == null){
                        return false;
                    }
                    if(!dataJson.containsKey("id") || StringUtils.isEmpty(dataJson.getString("id"))){
                        return false;
                    }
                    vo.setNickName(dataJson.getString("nickname"));
                    vo.setHeadPicUrl(dataJson.getString("avatar"));
                    return true;
                }
            }
        }
        return false;
    }
}
