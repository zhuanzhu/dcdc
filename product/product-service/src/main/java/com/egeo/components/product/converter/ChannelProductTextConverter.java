package com.egeo.components.product.converter;

import com.egeo.components.product.dto.channel.ChannelProductTextDTO;
import com.egeo.components.product.po.ChannelProductTextPO;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
public class ChannelProductTextConverter {


    public static ChannelProductTextPO toPO(ChannelProductTextDTO src) {
        if (src == null)
            return null;
        ChannelProductTextPO tar = new ChannelProductTextPO();
        tar.setId(src.getId());
        tar.setChannelCode(src.getChannelCode());
        tar.setProductId(src.getProductId());
        tar.setPlatformId(src.getPlatformId());
        tar.setInfo(src.getInfo());
        tar.setCreateTime(src.getCreateTime());
        tar.setUpdateTime(src.getUpdateTime());
        return tar;
    }

    public static ChannelProductTextDTO toDTO(ChannelProductTextPO src) {
        if (src == null)
            return null;
        ChannelProductTextDTO tar = new ChannelProductTextDTO();
        tar.setId(src.getId());
        tar.setChannelCode(src.getChannelCode());
        tar.setProductId(src.getProductId());
        tar.setPlatformId(src.getPlatformId());
        tar.setInfo(src.getInfo());
        tar.setCreateTime(src.getCreateTime());
        tar.setUpdateTime(src.getUpdateTime());
        return tar;
    }

    public static List<ChannelProductTextPO> toPO(List<ChannelProductTextDTO> srcs) {
        if (srcs == null) return null;
        List<ChannelProductTextPO> list = new ArrayList<>();
        for (ChannelProductTextDTO src : srcs) {
            list.add(toPO(src));
        }
        return list;
    }

    public static List<ChannelProductTextDTO> toDTO(List<ChannelProductTextPO> srcs) {
        if (srcs == null) return null;
        List<ChannelProductTextDTO> list = new ArrayList<>();
        for (ChannelProductTextPO src : srcs) {
            list.add(toDTO(src));
        }
        return list;
    }
}
