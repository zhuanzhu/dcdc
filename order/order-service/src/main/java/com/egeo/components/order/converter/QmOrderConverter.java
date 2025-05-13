package com.egeo.components.order.converter;

import com.egeo.components.order.dto.QmOrderDTO;
import com.egeo.components.order.po.QmOrderPO;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class QmOrderConverter {

    public static QmOrderDTO toDTO(QmOrderPO po){
        if (po==null){
            return null;
        }
        QmOrderDTO dto = new QmOrderDTO();
        dto.setId(po.getId());
        dto.setSoId(po.getSoId());
        dto.setOutTradeNo(po.getOutTradeNo());
        dto.setReturnUrl(po.getReturnUrl());
        dto.setNotifyUrl(po.getNotifyUrl());
        dto.setRemark(po.getRemark());
        dto.setOrderCode(po.getOrderCode());
        dto.setOrderTime(po.getOrderTime());
        dto.setExpireTime(po.getExpireTime());
        dto.setCreateTime(po.getCreateTime());
        dto.setSyncPayCount(po.getSyncPayCount());
        dto.setSyncPayStatus(po.getSyncPayStatus());
        dto.setOrderPayTime(po.getOrderPayTime());
        dto.setSyncTime(po.getSyncTime());
        return dto;
    }

    public static QmOrderPO  toPo(QmOrderDTO dto){
        if (dto==null){
            return null;
        }
        QmOrderPO po = new QmOrderPO();
        po.setId(dto.getId());
        po.setSoId(dto.getSoId());
        po.setOutTradeNo(dto.getOutTradeNo());
        po.setReturnUrl(dto.getReturnUrl());
        po.setNotifyUrl(dto.getNotifyUrl());
        po.setRemark(dto.getRemark());
        po.setOrderCode(dto.getOrderCode());
        po.setOrderTime(dto.getOrderTime());
        po.setExpireTime(dto.getExpireTime());
        po.setCreateTime(dto.getCreateTime());
        po.setSyncPayCount(dto.getSyncPayCount());
        po.setSyncPayStatus(dto.getSyncPayStatus());
        po.setOrderPayTime(dto.getOrderPayTime());
        po.setSyncTime(dto.getSyncTime());
        return po;
    }

    public static List<QmOrderDTO> toDTOList(List<QmOrderPO> qmOrderPOS) {
        List<QmOrderDTO> dtos=new ArrayList<>();
        if (Objects.nonNull(qmOrderPOS) && !qmOrderPOS.isEmpty()){
            for (QmOrderPO po:qmOrderPOS){
                dtos.add(QmOrderConverter.toDTO(po));
            }
        }
        return dtos;
    }

}
