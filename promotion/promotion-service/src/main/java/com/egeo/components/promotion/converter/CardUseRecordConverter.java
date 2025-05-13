package com.egeo.components.promotion.converter;

import com.egeo.components.promotion.dto.CardUseRecordDTO;
import com.egeo.components.promotion.po.CardUseRecordPO;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description todo
 * @Author lsl
 * @Date 2025/3/17 2:03
 * @Version V1.0
 **/
public class CardUseRecordConverter {



    public static CardUseRecordDTO toDTO(CardUseRecordPO src) {
        if(src == null){
            return null;
        }
        CardUseRecordDTO tar = new CardUseRecordDTO();
        tar.setId(src.getId());
        tar.setCardId(src.getCardId());
        tar.setCardNo(src.getCardNo());
        tar.setUserId(src.getUserId());
        tar.setUseAmount(src.getUseAmount());
        tar.setSoId(src.getSoId());
        tar.setCompanyId(src.getCompanyId());
        tar.setCreateTime(src.getCreateTime());
        tar.setUpdateTime(src.getUpdateTime());
        tar.setOrderCode(src.getOrderCode());
        tar.setCardName(src.getCardName());
        tar.setCardType(src.getCardType());
        tar.setSettleMethod(src.getSettleMethod());
        return tar;
    }

    public static CardUseRecordPO toPO(CardUseRecordDTO src) {
        if(src == null){
            return null;
        }
        CardUseRecordPO tar = new CardUseRecordPO();
        tar.setId(src.getId());
        tar.setCardId(src.getCardId());
        tar.setCardNo(src.getCardNo());
        tar.setUserId(src.getUserId());
        tar.setUseAmount(src.getUseAmount());
        tar.setSoId(src.getSoId());
        tar.setCompanyId(src.getCompanyId());
        tar.setCreateTime(src.getCreateTime());
        tar.setUpdateTime(src.getUpdateTime());
        tar.setOrderCode(src.getOrderCode());
        tar.setEndTime(src.getEndTime());
        tar.setStartTime(src.getStartTime());
        tar.setCardNameLike(src.getCardNameLike());
        tar.setCardNoLike(src.getCardNoLike());
        tar.setCardName(src.getCardName());
        tar.setOrderCodeLike(src.getOrderCodeLike());
        tar.setCardType(src.getCardType());
        tar.setSettleMethod(src.getSettleMethod());
        return tar;
    }

    public static List<CardUseRecordDTO> toDTO(List<CardUseRecordPO> srcs) {
        if (srcs == null){ return null;}
        List<CardUseRecordDTO> list = new ArrayList<>();
        for (CardUseRecordPO src : srcs) {
            list.add(toDTO(src));
        }
        return list;
    }

    public static List<CardUseRecordPO> toPO(List<CardUseRecordDTO> srcs) {
        if (srcs == null){ return null;}
        List<CardUseRecordPO> list = new ArrayList<>();
        for (CardUseRecordDTO src : srcs) {
            list.add(toPO(src));
        }
        return list;
    }
}
