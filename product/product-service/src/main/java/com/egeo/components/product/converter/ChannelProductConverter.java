package com.egeo.components.product.converter;

import com.egeo.components.product.dto.channel.ChannelProductDTO;
import com.egeo.components.product.po.ChannelProductPO;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
public class ChannelProductConverter {


    public static ChannelProductPO toPO(ChannelProductDTO src) {
        if (src == null)
            return null;
        ChannelProductPO tar = new ChannelProductPO();
        tar.setId(src.getId());
        tar.setChannelCode(src.getChannelCode());
        tar.setProductId(src.getProductId());
        tar.setChineseName(src.getChineseName());
        tar.setCategoryId(src.getCategoryId());
        tar.setCategoryPid(src.getCategoryPid());
        tar.setCategoryFPid(src.getCategoryFPid());
        tar.setBrandId(src.getBrandId());
        tar.setCategoryTreeNodeId(src.getCategoryTreeNodeId());
        tar.setTitle(src.getTitle());
        tar.setName(src.getName());
        tar.setGoodsType(src.getGoodsType());
        tar.setBrandName(src.getBrandName());
        tar.setGoodsLevel(src.getGoodsLevel());
        tar.setMarketPrice(src.getMarketPrice());
        tar.setPrice(src.getPrice());
        tar.setTaxNo(src.getTaxNo());
        tar.setEanNo(src.getEanNo());
        tar.setPlaceOfOrigin(src.getPlaceOfOrigin());
        tar.setCountryName(src.getCountryName());
        tar.setCalculationUnit(src.getCalculationUnit());
        tar.setUnitName(src.getUnitName());
        tar.setStatus(src.getStatus());
        tar.setIsAvailable(src.getIsAvailable());
        tar.setProductDetails(src.getProductDetails());
        tar.setSupplierId(src.getSupplierId());
        tar.setEnterpriseId(src.getEnterpriseId());
        tar.setPlatformId(src.getPlatformId());
        tar.setCommodityTemplateId(src.getCommodityTemplateId());
        tar.setPriceDetail(src.getPriceDetail());
        tar.setReferlink(src.getReferlink());
        tar.setExtend(src.getExtend());
        tar.setCreateTime(src.getCreateTime());
        tar.setUpdateTime(src.getUpdateTime());
        return tar;
    }

    public static ChannelProductDTO toDTO(ChannelProductPO src) {
        if (src == null)
            return null;
        ChannelProductDTO tar = new ChannelProductDTO();
        tar.setId(src.getId());
        tar.setChannelCode(src.getChannelCode());
        tar.setProductId(src.getProductId());
        tar.setChineseName(src.getChineseName());
        tar.setCategoryId(src.getCategoryId());
        tar.setCategoryPid(src.getCategoryPid());
        tar.setCategoryFPid(src.getCategoryFPid());
        tar.setBrandId(src.getBrandId());
        tar.setCategoryTreeNodeId(src.getCategoryTreeNodeId());
        tar.setTitle(src.getTitle());
        tar.setName(src.getName());
        tar.setGoodsType(src.getGoodsType());
        tar.setBrandName(src.getBrandName());
        tar.setGoodsLevel(src.getGoodsLevel());
        tar.setMarketPrice(src.getMarketPrice());
        tar.setPrice(src.getPrice());
        tar.setTaxNo(src.getTaxNo());
        tar.setEanNo(src.getEanNo());
        tar.setPlaceOfOrigin(src.getPlaceOfOrigin());
        tar.setCountryName(src.getCountryName());
        tar.setCalculationUnit(src.getCalculationUnit());
        tar.setUnitName(src.getUnitName());
        tar.setStatus(src.getStatus());
        tar.setIsAvailable(src.getIsAvailable());
        tar.setProductDetails(src.getProductDetails());
        tar.setSupplierId(src.getSupplierId());
        tar.setEnterpriseId(src.getEnterpriseId());
        tar.setPlatformId(src.getPlatformId());
        tar.setCommodityTemplateId(src.getCommodityTemplateId());
        tar.setPriceDetail(src.getPriceDetail());
        tar.setReferlink(src.getReferlink());
        tar.setExtend(src.getExtend());
        tar.setCreateTime(src.getCreateTime());
        tar.setUpdateTime(src.getUpdateTime());
        return tar;
    }

    public static List<ChannelProductPO> toPO(List<ChannelProductDTO> srcs) {
        if (srcs == null) return null;
        List<ChannelProductPO> list = new ArrayList<>();
        for (ChannelProductDTO src : srcs) {
            list.add(toPO(src));
        }
        return list;
    }

    public static List<ChannelProductDTO> toDTO(List<ChannelProductPO> srcs) {
        if (srcs == null) return null;
        List<ChannelProductDTO> list = new ArrayList<>();
        for (ChannelProductPO src : srcs) {
            list.add(toDTO(src));
        }
        return list;
    }
}
