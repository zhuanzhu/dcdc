package com.egeo.components.promotion.converter;

import com.egeo.components.promotion.dto.BuyCardItemDTO;
import com.egeo.components.promotion.po.BuyCardItemPO;
import com.egeo.components.promotion.vo.UseBuyCardItemDetailReqVO;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
public class BuyCardItemConverter {


    public static BuyCardItemDTO toDTO(BuyCardItemPO src) {
        if(src == null){
            return null;
        }
        BuyCardItemDTO tar = new BuyCardItemDTO();
        tar.setId(src.getId());
        tar.setItemId(src.getItemId());
        tar.setChildId(src.getChildId());
        tar.setSoId(src.getSoId());
        tar.setCardId(src.getCardId());
        tar.setUseAmount(src.getUseAmount());
        tar.setUserId(src.getUserId());
        tar.setCreateTime(src.getCreateTime());
        tar.setUpdateTime(src.getUpdateTime());
        tar.setOrderCode(src.getOrderCode());
        return tar;
    }

    public static BuyCardItemPO toPO(BuyCardItemDTO src) {
        if(src == null){
            return null;
        }
        BuyCardItemPO tar = new BuyCardItemPO();
        tar.setId(src.getId());
        tar.setItemId(src.getItemId());
        tar.setChildId(src.getChildId());
        tar.setSoId(src.getSoId());
        tar.setCardId(src.getCardId());
        tar.setUseAmount(src.getUseAmount());
        tar.setUserId(src.getUserId());
        tar.setCreateTime(src.getCreateTime());
        tar.setUpdateTime(src.getUpdateTime());
        tar.setOrderCode(src.getOrderCode());
        return tar;
    }

    public static List<BuyCardItemDTO> toDTO(List<BuyCardItemPO> srcs) {
        if (srcs == null){ return null;}
        List<BuyCardItemDTO> list = new ArrayList<>();
        for (BuyCardItemPO src : srcs) {
            list.add(toDTO(src));
        }
        return list;
    }

    public static List<BuyCardItemPO> toPO(List<BuyCardItemDTO> srcs) {
        if (srcs == null){ return null;}
        List<BuyCardItemPO> list = new ArrayList<>();
        for (BuyCardItemDTO src : srcs) {
            list.add(toPO(src));
        }
        return list;
    }

    public static List<BuyCardItemDTO> toUseBuyCardItemDetailPO(List<UseBuyCardItemDetailReqVO> srcs) {
        if (srcs == null){ return null;}
        List<BuyCardItemDTO> list = new ArrayList<>();
        for (UseBuyCardItemDetailReqVO src : srcs) {
            list.add(toUseBuyCardItemDetailPO(src));
        }
        return list;
    }

    public static BuyCardItemDTO toUseBuyCardItemDetailPO(UseBuyCardItemDetailReqVO src) {
        if (src == null){ return null;}
        BuyCardItemDTO tar = new BuyCardItemDTO();
        tar.setItemId(src.getItemId());
        tar.setChildId(src.getChildId());
        tar.setSoId(src.getSoId());
        tar.setCardId(src.getCardId());
        tar.setUserId(src.getUserId());
        tar.setUseAmount(src.getUseAmount());
        tar.setOrderCode(src.getOrderCode());
        return tar;
    }
}
