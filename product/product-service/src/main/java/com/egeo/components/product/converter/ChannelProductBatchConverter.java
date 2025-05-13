package com.egeo.components.product.converter;

import com.egeo.components.product.dto.channel.ChannelProductBatchDTO;
import com.egeo.components.product.po.ChannelProductBatchPO;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
public class ChannelProductBatchConverter {


    public static ChannelProductBatchPO toPO(ChannelProductBatchDTO src) {
        if (src == null)
            return null;
        ChannelProductBatchPO tar = new ChannelProductBatchPO();
        tar.setId(src.getId());
        tar.setProductId(src.getProductId());
        tar.setChannelCode(src.getChannelCode());
        tar.setLinkSkuId(src.getLinkSkuId());
        tar.setNum(src.getNum());
        tar.setBatchNo(src.getBatchNo());
        tar.setBatchId(src.getBatchId());
        tar.setStatus(src.getStatus());
        tar.setSpecList(src.getSpecList());
        tar.setSpecNum(src.getSpecNum());
        tar.setSpecName(src.getSpecName());
        tar.setExpiredDate(src.getExpiredDate());
        tar.setMakeDate(src.getMakeDate());
        tar.setPrice(src.getPrice());
        tar.setPriceControl(src.getPriceControl());
        tar.setPriceVip(src.getPriceVip());
        tar.setPriceSettleMent(src.getPriceSettleMent());
        tar.setPriceMarket(src.getPriceMarket());
        tar.setIsMinStockSpecificationItem(src.getIsMinStockSpecificationItem());
        tar.setCreateTime(src.getCreateTime());
        tar.setUpdateTime(src.getUpdateTime());
        return tar;
    }

    public static ChannelProductBatchDTO toDTO(ChannelProductBatchPO src) {
        if (src == null)
            return null;
        ChannelProductBatchDTO tar = new ChannelProductBatchDTO();
        tar.setId(src.getId());
        tar.setProductId(src.getProductId());
        tar.setChannelCode(src.getChannelCode());
        tar.setLinkSkuId(src.getLinkSkuId());
        tar.setNum(src.getNum());
        tar.setBatchNo(src.getBatchNo());
        tar.setBatchId(src.getBatchId());
        tar.setStatus(src.getStatus());
        tar.setSpecList(src.getSpecList());
        tar.setSpecNum(src.getSpecNum());
        tar.setSpecName(src.getSpecName());
        tar.setExpiredDate(src.getExpiredDate());
        tar.setMakeDate(src.getMakeDate());
        tar.setPrice(src.getPrice());
        tar.setPriceControl(src.getPriceControl());
        tar.setPriceVip(src.getPriceVip());
        tar.setPriceSettleMent(src.getPriceSettleMent());
        tar.setPriceMarket(src.getPriceMarket());
        tar.setIsMinStockSpecificationItem(src.getIsMinStockSpecificationItem());
        tar.setCreateTime(src.getCreateTime());
        tar.setUpdateTime(src.getUpdateTime());
        return tar;
    }

    public static List<ChannelProductBatchPO> toPO(List<ChannelProductBatchDTO> srcs) {
        if (srcs == null) return null;
        List<ChannelProductBatchPO> list = new ArrayList<>();
        for (ChannelProductBatchDTO src : srcs) {
            list.add(toPO(src));
        }
        return list;
    }

    public static List<ChannelProductBatchDTO> toDTO(List<ChannelProductBatchPO> srcs) {
        if (srcs == null) return null;
        List<ChannelProductBatchDTO> list = new ArrayList<>();
        for (ChannelProductBatchPO src : srcs) {
            list.add(toDTO(src));
        }
        return list;
    }
}
