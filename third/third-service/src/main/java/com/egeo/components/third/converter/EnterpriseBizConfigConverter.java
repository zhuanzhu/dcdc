package com.egeo.components.third.converter;

import com.egeo.components.third.dto.EnterpriseBizConfigDTO;
import com.egeo.components.third.po.EnterpriseBizConfigPO;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
public class EnterpriseBizConfigConverter {

    public static EnterpriseBizConfigPO toPO(EnterpriseBizConfigDTO src) {
        if (src == null)
            return null;
        EnterpriseBizConfigPO tar = new EnterpriseBizConfigPO();
        tar.setId(src.getId());
        tar.setEnterpriseId(src.getEnterpriseId());
        tar.setBizCode(src.getBizCode());
        tar.setSmallBizType(src.getSmallBizType());
        tar.setRuteName(src.getRuteName());
        tar.setRuteDetail(src.getRuteDetail());
        tar.setState(src.getState());
        tar.setCreateTime(src.getCreateTime());
        tar.setUpdateTime(src.getUpdateTime());
        return tar;
    }

    public static EnterpriseBizConfigDTO toDTO(EnterpriseBizConfigPO src) {
        if (src == null)
            return null;
        EnterpriseBizConfigDTO tar = new EnterpriseBizConfigDTO();
        tar.setId(src.getId());
        tar.setEnterpriseId(src.getEnterpriseId());
        tar.setBizCode(src.getBizCode());
        tar.setSmallBizType(src.getSmallBizType());
        tar.setRuteName(src.getRuteName());
        tar.setRuteDetail(src.getRuteDetail());
        tar.setState(src.getState());
        tar.setCreateTime(src.getCreateTime());
        tar.setUpdateTime(src.getUpdateTime());
        return tar;
    }

    public static List<EnterpriseBizConfigPO> toPO(List<EnterpriseBizConfigDTO> srcs) {
        if (srcs == null) return null;
        List<EnterpriseBizConfigPO> list = new ArrayList<>();
        for (EnterpriseBizConfigDTO src : srcs) {
            list.add(toPO(src));
        }
        return list;
    }

    public static List<EnterpriseBizConfigDTO> toDTO(List<EnterpriseBizConfigPO> srcs) {
        if (srcs == null) return null;
        List<EnterpriseBizConfigDTO> list = new ArrayList<>();
        for (EnterpriseBizConfigPO src : srcs) {
            list.add(toDTO(src));
        }
        return list;
    }
}
