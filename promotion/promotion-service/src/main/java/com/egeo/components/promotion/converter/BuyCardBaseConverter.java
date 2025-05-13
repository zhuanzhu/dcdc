package com.egeo.components.promotion.converter;

import com.egeo.components.promotion.dto.BuyCardBaseDTO;
import com.egeo.components.promotion.po.BuyCardBasePO;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description 购物卡基本信息转换器
 * @Author lsl
 * @Version V1.0
 **/
public class BuyCardBaseConverter {


    public static BuyCardBaseDTO toDTO(BuyCardBasePO src) {
        if(src == null){
            return null;
        }
        BuyCardBaseDTO tar = new BuyCardBaseDTO();
        tar.setId(src.getId());
        tar.setCardName(src.getCardName());
        tar.setCardPrefix(src.getCardPrefix());
        tar.setCardType(src.getCardType());
        tar.setSettleMethod(src.getSettleMethod());
        tar.setCardAmount(src.getCardAmount());
        tar.setCardState(src.getCardState());
        tar.setExpirationDate(src.getExpirationDate());
        tar.setExpirationDateUnit(src.getExpirationDateUnit());
        tar.setDelFlag(src.getDelFlag());
        tar.setOperator(src.getOperator());
        tar.setCreateTime(src.getCreateTime());
        tar.setUpdateTime(src.getUpdateTime());
        tar.setDescribe(src.getDescribe());
        tar.setCombinationId(src.getCombinationId());
        tar.setRuleDescribe(src.getRuleDescribe());
        tar.setSortValue(src.getSortValue());
        return tar;
    }

    public static BuyCardBasePO toPO(BuyCardBaseDTO src) {
        if(src == null){
            return null;
        }
        BuyCardBasePO tar = new BuyCardBasePO();
        tar.setId(src.getId());
        tar.setCardName(src.getCardName());
        tar.setCardPrefix(src.getCardPrefix());
        tar.setCardType(src.getCardType());
        tar.setSettleMethod(src.getSettleMethod());
        tar.setCardAmount(src.getCardAmount());
        tar.setCardState(src.getCardState());
        tar.setExpirationDate(src.getExpirationDate());
        tar.setExpirationDateUnit(src.getExpirationDateUnit());
        tar.setDelFlag(src.getDelFlag());
        tar.setOperator(src.getOperator());
        tar.setCreateTime(src.getCreateTime());
        tar.setUpdateTime(src.getUpdateTime());
        tar.setDescribe(src.getDescribe());
        tar.setCombinationId(src.getCombinationId());
        tar.setRuleDescribe(src.getRuleDescribe());
        tar.setSortValue(src.getSortValue());
        tar.setCardNameLike(src.getCardNameLike());
        return tar;
    }

    public static List<BuyCardBaseDTO> toDTO(List<BuyCardBasePO> srcs) {
        if (srcs == null){ return null;}
        List<BuyCardBaseDTO> list = new ArrayList<>();
        for (BuyCardBasePO src : srcs) {
            list.add(toDTO(src));
        }
        return list;
    }

    public static List<BuyCardBasePO> toPO(List<BuyCardBaseDTO> srcs) {
        if (srcs == null){ return null;}
        List<BuyCardBasePO> list = new ArrayList<>();
        for (BuyCardBaseDTO src : srcs) {
            list.add(toPO(src));
        }
        return list;
    }
}
