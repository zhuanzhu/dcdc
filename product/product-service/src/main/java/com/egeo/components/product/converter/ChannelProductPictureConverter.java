package com.egeo.components.product.converter;

import com.egeo.components.product.dto.channel.ChannelProductPictureDTO;
import com.egeo.components.product.po.ChannelProductPicturePO;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
public class ChannelProductPictureConverter {


    public static ChannelProductPicturePO toPO(ChannelProductPictureDTO src) {
        if (src == null)
            return null;
        ChannelProductPicturePO tar = new ChannelProductPicturePO();
        tar.setId(src.getId());
        tar.setProductId(src.getProductId());
        tar.setChannelCode(src.getChannelCode());
        tar.setType(src.getType());
        tar.setPictureUrl(src.getPictureUrl());
        tar.setSortValue(src.getSortValue());
        tar.setCreateTime(src.getCreateTime());
        tar.setUpdateTime(src.getUpdateTime());
        return tar;
    }

    public static ChannelProductPictureDTO toDTO(ChannelProductPicturePO src) {
        if (src == null)
            return null;
        ChannelProductPictureDTO tar = new ChannelProductPictureDTO();
        tar.setId(src.getId());
        tar.setProductId(src.getProductId());
        tar.setChannelCode(src.getChannelCode());
        tar.setType(src.getType());
        tar.setPictureUrl(src.getPictureUrl());
        tar.setSortValue(src.getSortValue());
        tar.setCreateTime(src.getCreateTime());
        tar.setUpdateTime(src.getUpdateTime());
        return tar;
    }

    public static List<ChannelProductPicturePO> toPO(List<ChannelProductPictureDTO> srcs) {
        if (srcs == null) return null;
        List<ChannelProductPicturePO> list = new ArrayList<>();
        for (ChannelProductPictureDTO src : srcs) {
            list.add(toPO(src));
        }
        return list;
    }

    public static List<ChannelProductPictureDTO> toDTO(List<ChannelProductPicturePO> srcs) {
        if (srcs == null) return null;
        List<ChannelProductPictureDTO> list = new ArrayList<>();
        for (ChannelProductPicturePO src : srcs) {
            list.add(toDTO(src));
        }
        return list;
    }
}
