package com.egeo.components.product.converter;

import com.egeo.components.product.dto.channel.ChannelProductSkuDTO;
import com.egeo.components.product.po.ChannelProductSkuPO;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
public class ChannelProductSkuConverter {


    public static ChannelProductSkuPO toPO(ChannelProductSkuDTO src) {
        if (src == null)
            return null;
        ChannelProductSkuPO tar = new ChannelProductSkuPO();
        tar.setId(src.getId());
        tar.setChannelCode(src.getChannelCode());
        tar.setProductId(src.getProductId());
        tar.setSkuSerialNumber(src.getSkuSerialNumber());
        tar.setMerchantId(src.getMerchantId());
        tar.setStandardProductUnitId(src.getStandardProductUnitId());
        tar.setSkuName(src.getSkuName());
        tar.setSkuProductName(src.getSkuProductName());
        tar.setSkuMarketPrice(src.getSkuMarketPrice());
        tar.setSkuPrice(src.getSkuPrice());
        tar.setSkuCostingPrice(src.getSkuCostingPrice());
        tar.setRemark(src.getRemark());
        tar.setIsAvailable(src.getIsAvailable());
        tar.setIsValid(src.getIsValid());
        tar.setCode(src.getCode());
        tar.setSkuPicUrl(src.getSkuPicUrl());
        tar.setSynchronizationTime(src.getSynchronizationTime());
        tar.setPlatformId(src.getPlatformId());
        tar.setExternalSkuId(src.getExternalSkuId());
        tar.setBarCode(src.getBarCode());
        tar.setSkuCode(src.getSkuCode());
        tar.setCreateTime(src.getCreateTime());
        tar.setUpdateTime(src.getUpdateTime());
        tar.setTaxRate(src.getTaxRate());
        tar.setHasRate(src.getHasRate());
        tar.setStoreListText(src.getStoreListText());
        tar.setState(src.getState());
        return tar;
    }

    public static ChannelProductSkuDTO toDTO(ChannelProductSkuPO src) {
        if (src == null)
            return null;
        ChannelProductSkuDTO tar = new ChannelProductSkuDTO();
        tar.setId(src.getId());
        tar.setChannelCode(src.getChannelCode());
        tar.setProductId(src.getProductId());
        tar.setSkuSerialNumber(src.getSkuSerialNumber());
        tar.setMerchantId(src.getMerchantId());
        tar.setStandardProductUnitId(src.getStandardProductUnitId());
        tar.setSkuName(src.getSkuName());
        tar.setSkuProductName(src.getSkuProductName());
        tar.setSkuMarketPrice(src.getSkuMarketPrice());
        tar.setSkuPrice(src.getSkuPrice());
        tar.setSkuCostingPrice(src.getSkuCostingPrice());
        tar.setRemark(src.getRemark());
        tar.setIsAvailable(src.getIsAvailable());
        tar.setIsValid(src.getIsValid());
        tar.setCode(src.getCode());
        tar.setSkuPicUrl(src.getSkuPicUrl());
        tar.setSynchronizationTime(src.getSynchronizationTime());
        tar.setPlatformId(src.getPlatformId());
        tar.setExternalSkuId(src.getExternalSkuId());
        tar.setBarCode(src.getBarCode());
        tar.setSkuCode(src.getSkuCode());
        tar.setCreateTime(src.getCreateTime());
        tar.setUpdateTime(src.getUpdateTime());
        tar.setTaxRate(src.getTaxRate());
        tar.setHasRate(src.getHasRate());
        tar.setStoreListText(src.getStoreListText());
        tar.setState(src.getState());
        return tar;
    }

    public static List<ChannelProductSkuPO> toPO(List<ChannelProductSkuDTO> srcs) {
        if (srcs == null) return null;
        List<ChannelProductSkuPO> list = new ArrayList<>();
        for (ChannelProductSkuDTO src : srcs) {
            list.add(toPO(src));
        }
        return list;
    }

    public static List<ChannelProductSkuDTO> toDTO(List<ChannelProductSkuPO> srcs) {
        if (srcs == null) return null;
        List<ChannelProductSkuDTO> list = new ArrayList<>();
        for (ChannelProductSkuPO src : srcs) {
            list.add(toDTO(src));
        }
        return list;
    }
}
