package com.egeo.components.third.converter;

import com.egeo.components.third.dto.EnterpriseChannelBaffleDTO;
import com.egeo.components.third.po.EnterpriseChannelBafflePO;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
public class EnterpriseChannelBaffleConverter {


    public static EnterpriseChannelBafflePO toPO(EnterpriseChannelBaffleDTO src) {
        if (src == null)
            return null;
        EnterpriseChannelBafflePO tar = new EnterpriseChannelBafflePO();
        tar.setId(src.getId());
        tar.setEnterpriseId(src.getEnterpriseId());
        tar.setChannelCode(src.getChannelCode());
        tar.setChannelServiceName(src.getChannelServiceName());
        tar.setChannelServiceType(src.getChannelServiceType());
        tar.setState(src.getState());
        tar.setReturnData(src.getReturnData());
        tar.setIfDecrypt(src.getIfDecrypt());
        tar.setCreateTime(src.getCreateTime());
        tar.setUpdateTime(src.getUpdateTime());
        return tar;
    }

    public static EnterpriseChannelBaffleDTO toDTO(EnterpriseChannelBafflePO src) {
        if (src == null)
            return null;
        EnterpriseChannelBaffleDTO tar = new EnterpriseChannelBaffleDTO();
        tar.setId(src.getId());
        tar.setEnterpriseId(src.getEnterpriseId());
        tar.setChannelCode(src.getChannelCode());
        tar.setChannelServiceName(src.getChannelServiceName());
        tar.setChannelServiceType(src.getChannelServiceType());
        tar.setState(src.getState());
        tar.setReturnData(src.getReturnData());
        tar.setIfDecrypt(src.getIfDecrypt());
        tar.setCreateTime(src.getCreateTime());
        tar.setUpdateTime(src.getUpdateTime());
        return tar;
    }

    public static List<EnterpriseChannelBafflePO> toPO(List<EnterpriseChannelBaffleDTO> srcs) {
        if (srcs == null) return null;
        List<EnterpriseChannelBafflePO> list = new ArrayList<>();
        for (EnterpriseChannelBaffleDTO src : srcs) {
            list.add(toPO(src));
        }
        return list;
    }

    public static List<EnterpriseChannelBaffleDTO> toDTO(List<EnterpriseChannelBafflePO> srcs) {
        if (srcs == null) return null;
        List<EnterpriseChannelBaffleDTO> list = new ArrayList<>();
        for (EnterpriseChannelBafflePO src : srcs) {
            list.add(toDTO(src));
        }
        return list;
    }
}
