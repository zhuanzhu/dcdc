package com.egeo.components.third.converter;

import com.egeo.components.third.dto.PushOrderInfoDTO;
import com.egeo.components.third.po.PushOrderInfoPO;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
public class PushOrderInfoConverter {

    public static PushOrderInfoPO toPO(PushOrderInfoDTO src) {
        if (src == null)
            return null;
        PushOrderInfoPO tar = new PushOrderInfoPO();
        tar.setId(src.getId());
        tar.setEnterpriseId(src.getEnterpriseId());
        tar.setBizCode(src.getBizCode());
        tar.setBizType(src.getBizType());
        tar.setPushTime(src.getPushTime());
        tar.setBizNo(src.getBizNo());
        tar.setPushState(src.getPushState());
        tar.setPushNum(src.getPushNum());
        tar.setPushResult(src.getPushResult());
        tar.setRemarks(src.getRemarks());
        tar.setCreateTime(src.getCreateTime());
        tar.setUpdateTime(src.getUpdateTime());
        return tar;
    }

    public static PushOrderInfoDTO toDTO(PushOrderInfoPO src) {
        if (src == null)
            return null;
        PushOrderInfoDTO tar = new PushOrderInfoDTO();
        tar.setId(src.getId());
        tar.setEnterpriseId(src.getEnterpriseId());
        tar.setBizCode(src.getBizCode());
        tar.setBizType(src.getBizType());
        tar.setPushTime(src.getPushTime());
        tar.setBizNo(src.getBizNo());
        tar.setPushState(src.getPushState());
        tar.setPushNum(src.getPushNum());
        tar.setPushResult(src.getPushResult());
        tar.setRemarks(src.getRemarks());
        tar.setCreateTime(src.getCreateTime());
        tar.setUpdateTime(src.getUpdateTime());
        return tar;
    }

    public static List<PushOrderInfoPO> toPO(List<PushOrderInfoDTO> srcs) {
        if (srcs == null) return null;
        List<PushOrderInfoPO> list = new ArrayList<>();
        for (PushOrderInfoDTO src : srcs) {
            list.add(toPO(src));
        }
        return list;
    }

    public static List<PushOrderInfoDTO> toDTO(List<PushOrderInfoPO> srcs) {
        if (srcs == null) return null;
        List<PushOrderInfoDTO> list = new ArrayList<>();
        for (PushOrderInfoPO src : srcs) {
            list.add(toDTO(src));
        }
        return list;
    }
}
