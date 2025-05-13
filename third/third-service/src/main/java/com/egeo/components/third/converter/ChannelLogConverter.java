package com.egeo.components.third.converter;

import com.egeo.components.third.dto.ChannelLogDTO;
import com.egeo.components.third.po.ChannelLogPO;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description todo
 * @Author lsl
 * @Date 2024/10/30 14:26
 * @Version V1.0
 **/
public class ChannelLogConverter {

    public static ChannelLogPO toPO(ChannelLogDTO src) {
        if (src == null)
            return null;
        ChannelLogPO tar = new ChannelLogPO();
        tar.setId(src.getId());
        tar.setChannelCode(src.getChannelCode());
        tar.setEnterpriseId(src.getEnterpriseId());
        tar.setChannelServiceName(src.getChannelServiceName());
        tar.setChannelServiceType(src.getChannelServiceType());
        tar.setBizCode(src.getBizCode());
        tar.setNextBizCode(src.getNextBizCode());
        tar.setState(src.getState());
        tar.setResponseCode(src.getResponseCode());
        tar.setRequestJson(src.getRequestJson());
        tar.setResponseJson(src.getResponseJson());
        tar.setCreateTime(src.getCreateTime());
        tar.setUpdateTime(src.getUpdateTime());
        tar.setResponseMsg(src.getResponseMsg());
        return tar;
    }

    public static ChannelLogDTO toDTO(ChannelLogPO src) {
        if (src == null)
            return null;
        ChannelLogDTO tar = new ChannelLogDTO();
        tar.setId(src.getId());
        tar.setChannelCode(src.getChannelCode());
        tar.setEnterpriseId(src.getEnterpriseId());
        tar.setChannelServiceName(src.getChannelServiceName());
        tar.setChannelServiceType(src.getChannelServiceType());
        tar.setBizCode(src.getBizCode());
        tar.setNextBizCode(src.getNextBizCode());
        tar.setState(src.getState());
        tar.setResponseCode(src.getResponseCode());
        tar.setRequestJson(src.getRequestJson());
        tar.setResponseJson(src.getResponseJson());
        tar.setCreateTime(src.getCreateTime());
        tar.setUpdateTime(src.getUpdateTime());
        tar.setResponseMsg(src.getResponseMsg());
        return tar;
    }

    public static List<ChannelLogPO> toPO(List<ChannelLogDTO> srcs) {
        if (srcs == null) return null;
        List<ChannelLogPO> list = new ArrayList<>();
        for (ChannelLogDTO src : srcs) {
            list.add(toPO(src));
        }
        return list;
    }

    public static List<ChannelLogDTO> toDTO(List<ChannelLogPO> srcs) {
        if (srcs == null) return null;
        List<ChannelLogDTO> list = new ArrayList<>();
        for (ChannelLogPO src : srcs) {
            list.add(toDTO(src));
        }
        return list;
    }
}
