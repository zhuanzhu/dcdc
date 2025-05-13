package com.egeo.components.third.converter;

import com.egeo.components.third.dto.EnterpriseChannelServiceChoiceDTO;
import com.egeo.components.third.po.EnterpriseChannelServiceChoicePO;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description todo
 * @Author lsl
 * @Date 2024/6/27 15:43
 * @Version V1.0
 **/
public class EnterpriseChannelServiceChoiceConverter {

    public static EnterpriseChannelServiceChoicePO toPO(EnterpriseChannelServiceChoiceDTO src) {
        if (src == null)
            return null;
        EnterpriseChannelServiceChoicePO tar = new EnterpriseChannelServiceChoicePO();
        tar.setId(src.getId());
        tar.setEnterpriseId(src.getEnterpriseId());
        tar.setChannelServiceName(src.getChannelServiceName());
        tar.setChannelServiceType(src.getChannelServiceType());
        tar.setRuteName(src.getRuteName());
        tar.setRuteDetail(src.getRuteDetail());
        tar.setRemarks(src.getRemarks());
        tar.setState(src.getState());
        tar.setCreateTime(src.getCreateTime());
        tar.setUpdateTime(src.getUpdateTime());
        return tar;
    }

    public static EnterpriseChannelServiceChoiceDTO toDTO(EnterpriseChannelServiceChoicePO src) {
        if (src == null)
            return null;
        EnterpriseChannelServiceChoiceDTO tar = new EnterpriseChannelServiceChoiceDTO();
        tar.setId(src.getId());
        tar.setEnterpriseId(src.getEnterpriseId());
        tar.setChannelServiceName(src.getChannelServiceName());
        tar.setChannelServiceType(src.getChannelServiceType());
        tar.setRuteName(src.getRuteName());
        tar.setRuteDetail(src.getRuteDetail());
        tar.setRemarks(src.getRemarks());
        tar.setState(src.getState());
        tar.setCreateTime(src.getCreateTime());
        tar.setUpdateTime(src.getUpdateTime());
        return tar;
    }

    public static List<EnterpriseChannelServiceChoicePO> toPO(List<EnterpriseChannelServiceChoiceDTO> srcs) {
        if (srcs == null) return null;
        List<EnterpriseChannelServiceChoicePO> list = new ArrayList<>();
        for (EnterpriseChannelServiceChoiceDTO src : srcs) {
            list.add(toPO(src));
        }
        return list;
    }

    public static List<EnterpriseChannelServiceChoiceDTO> toDTO(List<EnterpriseChannelServiceChoicePO> srcs) {
        if (srcs == null) return null;
        List<EnterpriseChannelServiceChoiceDTO> list = new ArrayList<>();
        for (EnterpriseChannelServiceChoicePO src : srcs) {
            list.add(toDTO(src));
        }
        return list;
    }
}
