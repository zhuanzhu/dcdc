package com.egeo.components.product.converter;

import com.egeo.components.product.dto.ChannelCategoryDTO;
import com.egeo.components.product.po.ChannelCategoryPO;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
public class ChannelCategoryConverter {


    public static ChannelCategoryPO toPO(ChannelCategoryDTO src) {
        if (src == null)
            return null;
        ChannelCategoryPO tar = new ChannelCategoryPO();
        tar.setId(src.getId());
        tar.setChannelId(src.getChannelId());
        tar.setChannelCode(src.getChannelCode());
        tar.setChannelCategoryName(src.getChannelCategoryName());
        tar.setParentId(src.getParentId());
        tar.setInnerCategoryId(src.getInnerCategoryId());
        tar.setChannelCategoryLevel(src.getChannelCategoryLevel());
        tar.setChannelCategoryType(src.getChannelCategoryType());
        tar.setChannelCategoryId(src.getChannelCategoryId());
        tar.setChannelCategoryPId(src.getChannelCategoryPId());
        return tar;
    }

    public static ChannelCategoryDTO toDTO(ChannelCategoryPO src) {
        if (src == null)
            return null;
        ChannelCategoryDTO tar = new ChannelCategoryDTO();
        tar.setId(src.getId());
        tar.setChannelId(src.getChannelId());
        tar.setChannelCode(src.getChannelCode());
        tar.setChannelCategoryName(src.getChannelCategoryName());
        tar.setParentId(src.getParentId());
        tar.setInnerCategoryId(src.getInnerCategoryId());
        tar.setChannelCategoryLevel(src.getChannelCategoryLevel());
        tar.setChannelCategoryType(src.getChannelCategoryType());
        tar.setChannelCategoryId(src.getChannelCategoryId());
        tar.setChannelCategoryPId(src.getChannelCategoryPId());
        return tar;
    }

    public static List<ChannelCategoryPO> toPO(List<ChannelCategoryDTO> srcs) {
        if (srcs == null) return null;
        List<ChannelCategoryPO> list = new ArrayList<>();
        for (ChannelCategoryDTO src : srcs) {
            list.add(toPO(src));
        }
        return list;
    }

    public static List<ChannelCategoryDTO> toDTO(List<ChannelCategoryPO> srcs) {
        if (srcs == null) return null;
        List<ChannelCategoryDTO> list = new ArrayList<>();
        for (ChannelCategoryPO src : srcs) {
            list.add(toDTO(src));
        }
        return list;
    }
}
