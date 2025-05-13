package com.egeo.components.product.converter;

import com.egeo.components.product.dto.channel.ChannelProductDescriptionDTO;
import com.egeo.components.product.po.ChannelProductDescriptionPO;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
public class ChannelProductDescriptionConverter {


    public static ChannelProductDescriptionPO toPO(ChannelProductDescriptionDTO src) {
        if (src == null)
            return null;
        ChannelProductDescriptionPO tar = new ChannelProductDescriptionPO();
        tar.setId(src.getId());
        tar.setChannelCode(src.getChannelCode());
        tar.setProductId(src.getProductId());
        tar.setContent(src.getContent());
        tar.setPlatformId(src.getPlatformId());
        tar.setCreateTime(src.getCreateTime());
        tar.setUpdateTime(src.getUpdateTime());
        return tar;
    }

    public static ChannelProductDescriptionDTO toDTO(ChannelProductDescriptionPO src) {
        if (src == null)
            return null;
        ChannelProductDescriptionDTO tar = new ChannelProductDescriptionDTO();
        tar.setId(src.getId());
        tar.setChannelCode(src.getChannelCode());
        tar.setProductId(src.getProductId());
        tar.setContent(src.getContent());
        tar.setPlatformId(src.getPlatformId());
        tar.setCreateTime(src.getCreateTime());
        tar.setUpdateTime(src.getUpdateTime());
        return tar;
    }

    public static List<ChannelProductDescriptionPO> toPO(List<ChannelProductDescriptionDTO> srcs) {
        if (srcs == null) return null;
        List<ChannelProductDescriptionPO> list = new ArrayList<>();
        for (ChannelProductDescriptionDTO src : srcs) {
            list.add(toPO(src));
        }
        return list;
    }

    public static List<ChannelProductDescriptionDTO> toDTO(List<ChannelProductDescriptionPO> srcs) {
        if (srcs == null) return null;
        List<ChannelProductDescriptionDTO> list = new ArrayList<>();
        for (ChannelProductDescriptionPO src : srcs) {
            list.add(toDTO(src));
        }
        return list;
    }
}
