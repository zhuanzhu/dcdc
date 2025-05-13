package com.egeo.components.promotion.converter;

import com.egeo.components.promotion.dto.UserCardRecordDTO;
import com.egeo.components.promotion.po.UserCardRecordPO;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description 购物卡记录信息表(卡和用户关联关系表)转换器
 * @Author lsl
 * @Version V1.0
 **/
public class UserCardRecordConverter {



    public static UserCardRecordDTO toDTO(UserCardRecordPO src) {
        if(src == null){
            return null;
        }
        UserCardRecordDTO tar = new UserCardRecordDTO();
        tar.setId(src.getId());
        tar.setCardNo(src.getCardNo());
        tar.setUserId(src.getUserId());
        tar.setCardName(src.getCardName());
        tar.setCardType(src.getCardType());
        tar.setSettleMethod(src.getSettleMethod());
        tar.setCardAmount(src.getCardAmount());
        tar.setCardCash(src.getCardCash());
        tar.setExpireDateStart(src.getExpireDateStart());
        tar.setExpireDateEnd(src.getExpireDateEnd());
        tar.setCardState(src.getCardState());
        tar.setUseState(src.getUseState());
        tar.setCompanyId(src.getCompanyId());
        tar.setSourceCardId(src.getSourceCardId());
        tar.setOperator(src.getOperator());
        tar.setCreateTime(src.getCreateTime());
        tar.setUpdateTime(src.getUpdateTime());

        return tar;
    }

    public static UserCardRecordPO toPO(UserCardRecordDTO src) {
        if(src == null){
            return null;
        }
        UserCardRecordPO tar = new UserCardRecordPO();
        tar.setId(src.getId());
        tar.setCardNo(src.getCardNo());
        tar.setUserId(src.getUserId());
        tar.setCardName(src.getCardName());
        tar.setCardType(src.getCardType());
        tar.setSettleMethod(src.getSettleMethod());
        tar.setCardAmount(src.getCardAmount());
        tar.setCardCash(src.getCardCash());
        tar.setExpireDateStart(src.getExpireDateStart());
        tar.setExpireDateEnd(src.getExpireDateEnd());
        tar.setCardState(src.getCardState());
        tar.setUseState(src.getUseState());
        tar.setCompanyId(src.getCompanyId());
        tar.setSourceCardId(src.getSourceCardId());
        tar.setOperator(src.getOperator());
        tar.setCreateTime(src.getCreateTime());
        tar.setUpdateTime(src.getUpdateTime());
        tar.setUserIds(src.getUserIds());
        tar.setUseStates(src.getUseStates());
        tar.setCardNameLike(src.getCardNameLike());
        tar.setCardNoLike(src.getCardNoLike());
        tar.setIds(src.getIds());
        return tar;
    }

    public static List<UserCardRecordDTO> toDTO(List<UserCardRecordPO> srcs) {
        if (srcs == null){ return null;}
        List<UserCardRecordDTO> list = new ArrayList<>();
        for (UserCardRecordPO src : srcs) {
            list.add(toDTO(src));
        }
        return list;
    }

    public static List<UserCardRecordPO> toPO(List<UserCardRecordDTO> srcs) {
        if (srcs == null){ return null;}
        List<UserCardRecordPO> list = new ArrayList<>();
        for (UserCardRecordDTO src : srcs) {
            list.add(toPO(src));
        }
        return list;
    }
}
