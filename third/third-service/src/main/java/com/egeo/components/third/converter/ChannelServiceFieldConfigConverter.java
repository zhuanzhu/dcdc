package com.egeo.components.third.converter;

import com.egeo.components.third.dto.ChannelServiceFieldConfigDTO;
import com.egeo.components.third.po.ChannelServiceFieldConfigPO;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
public class ChannelServiceFieldConfigConverter {

    public static ChannelServiceFieldConfigPO toPO(ChannelServiceFieldConfigDTO src) {
        if (src == null)
            return null;
        ChannelServiceFieldConfigPO tar = new ChannelServiceFieldConfigPO();
        tar.setId(src.getId());
        tar.setChannelCode(src.getChannelCode());
        tar.setChannelServiceName(src.getChannelServiceName());
        tar.setChannelServiceType(src.getChannelServiceType());

        tar.setState(src.getState());
        tar.setCreateTime(src.getCreateTime());
        tar.setUpdateTime(src.getUpdateTime());
        return tar;
    }

    public static ChannelServiceFieldConfigDTO toDTO(ChannelServiceFieldConfigPO src) {
        if (src == null)
            return null;
        ChannelServiceFieldConfigDTO tar = new ChannelServiceFieldConfigDTO();
        tar.setId(src.getId());
        tar.setChannelCode(src.getChannelCode());
        tar.setChannelServiceName(src.getChannelServiceName());
        tar.setChannelServiceType(src.getChannelServiceType());

        tar.setState(src.getState());
        tar.setCreateTime(src.getCreateTime());
        tar.setUpdateTime(src.getUpdateTime());
        return tar;
    }

    public static List<ChannelServiceFieldConfigPO> toPO(List<ChannelServiceFieldConfigDTO> srcs) {
        if (srcs == null) return null;
        List<ChannelServiceFieldConfigPO> list = new ArrayList<>();
        for (ChannelServiceFieldConfigDTO src : srcs) {
            list.add(toPO(src));
        }
        return list;
    }

    public static List<ChannelServiceFieldConfigDTO> toDTO(List<ChannelServiceFieldConfigPO> srcs) {
        if (srcs == null) return null;
        List<ChannelServiceFieldConfigDTO> list = new ArrayList<>();
        for (ChannelServiceFieldConfigPO src : srcs) {
            list.add(toDTO(src));
        }
        return list;
    }
}
