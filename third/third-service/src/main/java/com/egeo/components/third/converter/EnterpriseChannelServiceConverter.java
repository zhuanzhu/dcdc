package com.egeo.components.third.converter;

import com.egeo.components.third.dto.EnterpriseChannelServiceDTO;
import com.egeo.components.third.po.EnterpriseChannelServicePO;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
public class EnterpriseChannelServiceConverter {


    public static EnterpriseChannelServicePO toPO(EnterpriseChannelServiceDTO src) {
        if (src == null)
            return null;
        EnterpriseChannelServicePO tar = new EnterpriseChannelServicePO();
        tar.setId(src.getId());
        tar.setEnterpriseId(src.getEnterpriseId());
        tar.setChannelCode(src.getChannelCode());
        tar.setChannelServiceName(src.getChannelServiceName());
        tar.setChannelServiceType(src.getChannelServiceType());
        tar.setDataSourceType(src.getDataSourceType());
        tar.setIfDefault(src.getIfDefault());
        tar.setBizSort(src.getBizSort());
        tar.setRemarks(src.getRemarks());
        tar.setState(src.getState());
        tar.setCreateTime(src.getCreateTime());
        tar.setUpdateTime(src.getUpdateTime());
        return tar;
    }

    public static EnterpriseChannelServiceDTO toDTO(EnterpriseChannelServicePO src) {
        if (src == null)
            return null;
        EnterpriseChannelServiceDTO tar = new EnterpriseChannelServiceDTO();
        tar.setId(src.getId());
        tar.setEnterpriseId(src.getEnterpriseId());
        tar.setChannelCode(src.getChannelCode());
        tar.setChannelServiceName(src.getChannelServiceName());
        tar.setChannelServiceType(src.getChannelServiceType());
        tar.setDataSourceType(src.getDataSourceType());
        tar.setIfDefault(src.getIfDefault());
        tar.setBizSort(src.getBizSort());
        tar.setRemarks(src.getRemarks());
        tar.setState(src.getState());
        tar.setCreateTime(src.getCreateTime());
        tar.setUpdateTime(src.getUpdateTime());
        return tar;
    }

    public static List<EnterpriseChannelServicePO> toPO(List<EnterpriseChannelServiceDTO> srcs) {
        if (srcs == null) return null;
        List<EnterpriseChannelServicePO> list = new ArrayList<>();
        for (EnterpriseChannelServiceDTO src : srcs) {
            list.add(toPO(src));
        }
        return list;
    }

    public static List<EnterpriseChannelServiceDTO> toDTO(List<EnterpriseChannelServicePO> srcs) {
        if (srcs == null) return null;
        List<EnterpriseChannelServiceDTO> list = new ArrayList<>();
        for (EnterpriseChannelServicePO src : srcs) {
            list.add(toDTO(src));
        }
        return list;
    }
}
