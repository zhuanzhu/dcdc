package com.egeo.components.product.converter;

import com.egeo.components.product.dto.StandardUnitCardBaseSuDTO;
import com.egeo.components.product.po.StandardUnitCardBaseSuPO;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description 购物卡模板与商品关系表转换器
 * @Author lsl
 * @Version V1.0
 **/
public class StandardUnitCardBaseSuConverter {

    public static StandardUnitCardBaseSuDTO toDTO(StandardUnitCardBaseSuPO src) {
        if (src == null){
            return null;}
        StandardUnitCardBaseSuDTO tar = new StandardUnitCardBaseSuDTO();
        tar.setId(src.getId());
        tar.setCardBaseId(src.getCardBaseId());
        tar.setStandardUnitId(src.getStandardUnitId());
        tar.setSortValue(src.getSortValue());
        tar.setSource(src.getSource());
        tar.setSnapshot(src.getSnapshot());
        tar.setThirdSkuId(src.getThirdSkuId());
        tar.setSellState(src.getSellState());
        tar.setCheckTime(src.getCheckTime());
        tar.setSortPrice(src.getSortPrice());
        tar.setSortSalesNum(src.getSortSalesNum());
        tar.setThirdSkuName(src.getThirdSkuName());
        tar.setCreateTime(src.getCreateTime());
        tar.setUpdateTime(src.getUpdateTime());
        return tar;
    }

    public static StandardUnitCardBaseSuPO toPO(StandardUnitCardBaseSuDTO src) {
        if (src == null){
            return null;}
        StandardUnitCardBaseSuPO tar = new StandardUnitCardBaseSuPO();
        tar.setId(src.getId());
        tar.setCardBaseId(src.getCardBaseId());
        tar.setStandardUnitId(src.getStandardUnitId());
        tar.setSortValue(src.getSortValue());
        tar.setSource(src.getSource());
        tar.setSnapshot(src.getSnapshot());
        tar.setThirdSkuId(src.getThirdSkuId());
        tar.setSellState(src.getSellState());
        tar.setCheckTime(src.getCheckTime());
        tar.setSortPrice(src.getSortPrice());
        tar.setSortSalesNum(src.getSortSalesNum());
        tar.setThirdSkuName(src.getThirdSkuName());
        tar.setCreateTime(src.getCreateTime());
        tar.setUpdateTime(src.getUpdateTime());
        return tar;
    }

    public static List<StandardUnitCardBaseSuDTO> toDTO(List<StandardUnitCardBaseSuPO> srcs) {
        if (srcs == null){
            return null;}
        List<StandardUnitCardBaseSuDTO> list = new ArrayList<>();
        for (StandardUnitCardBaseSuPO src : srcs) {
            list.add(toDTO(src));
        }
        return list;
    }

    public static List<StandardUnitCardBaseSuPO> toPO(List<StandardUnitCardBaseSuDTO> srcs) {
        if (srcs == null){
            return null;}
        List<StandardUnitCardBaseSuPO> list = new ArrayList<>();
        for (StandardUnitCardBaseSuDTO src : srcs) {
            list.add(toPO(src));
        }
        return list;
    }

}
