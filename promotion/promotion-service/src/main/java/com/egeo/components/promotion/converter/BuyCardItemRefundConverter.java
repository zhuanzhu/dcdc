package com.egeo.components.promotion.converter;

import com.egeo.components.promotion.dto.BuyCardItemRefundDTO;
import com.egeo.components.promotion.po.BuyCardItemRefundPO;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
public class BuyCardItemRefundConverter {


    public static BuyCardItemRefundDTO toDTO(BuyCardItemRefundPO src) {
        if(src == null){
            return null;
        }
        BuyCardItemRefundDTO tar = new BuyCardItemRefundDTO();
        tar.setId(src.getId());
        tar.setItemId(src.getItemId());
        tar.setChildId(src.getChildId());
        tar.setSoId(src.getSoId());
        tar.setCardId(src.getCardId());
        tar.setRefundNo(src.getRefundNo());
        tar.setRefundAmount(src.getRefundAmount());
        tar.setRemark(src.getRemark());
        tar.setUserId(src.getUserId());
        tar.setCreateTime(src.getCreateTime());
        tar.setUpdateTime(src.getUpdateTime());
        tar.setOrderCode(src.getOrderCode());
        tar.setCardUseId(src.getCardUseId());
        tar.setRefundId(src.getRefundId());
        return tar;
    }

    public static BuyCardItemRefundPO toPO(BuyCardItemRefundDTO src) {
        if(src == null){
            return null;
        }
        BuyCardItemRefundPO tar = new BuyCardItemRefundPO();
        tar.setId(src.getId());
        tar.setItemId(src.getItemId());
        tar.setChildId(src.getChildId());
        tar.setSoId(src.getSoId());
        tar.setCardId(src.getCardId());
        tar.setRefundNo(src.getRefundNo());
        tar.setRefundAmount(src.getRefundAmount());
        tar.setRemark(src.getRemark());
        tar.setUserId(src.getUserId());
        tar.setCreateTime(src.getCreateTime());
        tar.setUpdateTime(src.getUpdateTime());
        tar.setOrderCode(src.getOrderCode());
        tar.setCardUseId(src.getCardUseId());
        tar.setRefundId(src.getRefundId());
        return tar;
    }

    public static List<BuyCardItemRefundDTO> toDTO(List<BuyCardItemRefundPO> srcs) {
        if (srcs == null){ return null;}
        List<BuyCardItemRefundDTO> list = new ArrayList<>();
        for (BuyCardItemRefundPO src : srcs) {
            list.add(toDTO(src));
        }
        return list;
    }

    public static List<BuyCardItemRefundPO> toPO(List<BuyCardItemRefundDTO> srcs) {
        if (srcs == null){ return null;}
        List<BuyCardItemRefundPO> list = new ArrayList<>();
        for (BuyCardItemRefundDTO src : srcs) {
            list.add(toPO(src));
        }
        return list;
    }

}
