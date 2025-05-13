package com.egeo.components.third.converter;

import com.egeo.components.third.dto.ChannelServiceConfigDTO;
import com.egeo.components.third.po.ChannelServiceConfigPO;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
public class ChannelServiceConfigConverter {

    public static ChannelServiceConfigPO toPO(ChannelServiceConfigDTO src) {
        if (src == null)
            return null;
        ChannelServiceConfigPO tar = new ChannelServiceConfigPO();
        tar.setId(src.getId());
       tar.setChannelCode(src.getChannelCode());
       tar.setChannelServiceName(src.getChannelServiceName());
       tar.setChannelServiceType(src.getChannelServiceType());
       tar.setChannelServiceMethod(src.getChannelServiceMethod());
       tar.setChannelServiceUrl(src.getChannelServiceUrl());
       tar.setIfEncrypted(src.getIfEncrypted());
       tar.setIfDecrypt(src.getIfDecrypt());
       tar.setEncryptType(src.getEncryptType());
       tar.setState(src.getState());
       tar.setCreateTime(src.getCreateTime());
       tar.setUpdateTime(src.getUpdateTime());
       tar.setIfLog(src.getIfLog());
        return tar;
    }

    public static ChannelServiceConfigDTO toDTO(ChannelServiceConfigPO src) {
        if (src == null)
            return null;
        ChannelServiceConfigDTO tar = new ChannelServiceConfigDTO();
        tar.setId(src.getId());
        tar.setChannelCode(src.getChannelCode());
        tar.setChannelServiceName(src.getChannelServiceName());
        tar.setChannelServiceType(src.getChannelServiceType());
        tar.setChannelServiceMethod(src.getChannelServiceMethod());
        tar.setChannelServiceUrl(src.getChannelServiceUrl());
        tar.setIfEncrypted(src.getIfEncrypted());
        tar.setIfDecrypt(src.getIfDecrypt());
        tar.setEncryptType(src.getEncryptType());
        tar.setState(src.getState());
        tar.setCreateTime(src.getCreateTime());
        tar.setUpdateTime(src.getUpdateTime());
        tar.setIfLog(src.getIfLog());
        return tar;
    }

    public static List<ChannelServiceConfigPO> toPO(List<ChannelServiceConfigDTO> srcs) {
        if (srcs == null) return null;
        List<ChannelServiceConfigPO> list = new ArrayList<>();
        for (ChannelServiceConfigDTO src : srcs) {
            list.add(toPO(src));
        }
        return list;
    }

    public static List<ChannelServiceConfigDTO> toDTO(List<ChannelServiceConfigPO> srcs) {
        if (srcs == null) return null;
        List<ChannelServiceConfigDTO> list = new ArrayList<>();
        for (ChannelServiceConfigPO src : srcs) {
            list.add(toDTO(src));
        }
        return list;
    }
}
