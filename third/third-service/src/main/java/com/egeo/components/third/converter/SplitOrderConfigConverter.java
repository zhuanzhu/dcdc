package com.egeo.components.third.converter;

import com.egeo.components.third.dto.SplitOrderConfigDTO;
import com.egeo.components.third.po.SplitOrderConfigPO;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
public class SplitOrderConfigConverter {

    public static SplitOrderConfigPO toPO(SplitOrderConfigDTO src) {
        if (src == null)
            return null;
        SplitOrderConfigPO tar = new SplitOrderConfigPO();
        tar.setId(src.getId());
        tar.setEnterpriseId(src.getEnterpriseId());
        tar.setEnterpriseId(src.getEnterpriseId());
        tar.setSplitRuteName(src.getSplitRuteName());
        tar.setSplitRuteDetail(src.getSplitRuteDetail());
        tar.setFreightRuteName(src.getFreightRuteName());
        tar.setFreightRuteDetail(src.getFreightRuteDetail());
        tar.setState(src.getState());
        tar.setRemarks(src.getRemarks());
        tar.setCreateTime(src.getCreateTime());
        tar.setUpdateTime(src.getUpdateTime());
        return tar;
    }

    public static SplitOrderConfigDTO toDTO(SplitOrderConfigPO src) {
        if (src == null)
            return null;
        SplitOrderConfigDTO tar = new SplitOrderConfigDTO();
        tar.setId(src.getId());
        tar.setEnterpriseId(src.getEnterpriseId());
        tar.setEnterpriseId(src.getEnterpriseId());
        tar.setSplitRuteName(src.getSplitRuteName());
        tar.setSplitRuteDetail(src.getSplitRuteDetail());
        tar.setFreightRuteName(src.getFreightRuteName());
        tar.setFreightRuteDetail(src.getFreightRuteDetail());
        tar.setState(src.getState());
        tar.setRemarks(src.getRemarks());
        tar.setCreateTime(src.getCreateTime());
        tar.setUpdateTime(src.getUpdateTime());
        return tar;
    }

    public static List<SplitOrderConfigPO> toPO(List<SplitOrderConfigDTO> srcs) {
        if (srcs == null) return null;
        List<SplitOrderConfigPO> list = new ArrayList<>();
        for (SplitOrderConfigDTO src : srcs) {
            list.add(toPO(src));
        }
        return list;
    }

    public static List<SplitOrderConfigDTO> toDTO(List<SplitOrderConfigPO> srcs) {
        if (srcs == null) return null;
        List<SplitOrderConfigDTO> list = new ArrayList<>();
        for (SplitOrderConfigPO src : srcs) {
            list.add(toDTO(src));
        }
        return list;
    }
}
