package com.egeo.components.product.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.product.dto.CommodityDetailsDTO;
import com.egeo.components.product.dto.MerchantProductDTO;
import com.egeo.components.product.po.CommodityDetailsPO;
import com.egeo.components.product.po.MerchantProductPO;
import com.egeo.components.product.vo.MerchantProductVO;

/**
 * DTO和PO相互转换工具类
 *
 * @author min
 * @date 2018-01-06 09:20:45
 */
public class MerchantProductConverter {

	public static MerchantProductDTO toDTO(MerchantProductVO src) {
		if (src == null)
		return null;	
		MerchantProductDTO tar = new MerchantProductDTO();
		tar.setId(src.getId());
		tar.setMerchantProductSerialNumber(src.getMerchantProductSerialNumber());	
		tar.setMerchantId(src.getMerchantId());	
		tar.setStandardProductUnitId(src.getStandardProductUnitId());
		tar.setMerchantCateTreeNodeId(src.getMerchantCateTreeNodeId());	
		tar.setMerchantSeriesId(src.getMerchantSeriesId());	
		tar.setName(src.getName());	
		tar.setAlias(src.getAlias());	
		tar.setIsVisible(src.getIsVisible());	
		tar.setSubtitle(src.getSubtitle());	
		tar.setSupplierId(src.getSupplierId());	
		tar.setType(src.getType());	
		tar.setRemark(src.getRemark());	
		tar.setSalePrice(src.getSalePrice());	
		tar.setSaleTaxRate(src.getSaleTaxRate());	
		tar.setReturnDays(src.getReturnDays());	
		tar.setReplacementDays(src.getReplacementDays());	
		tar.setGuaranteeDays(src.getGuaranteeDays());	
		tar.setIsVatInvoice(src.getIsVatInvoice());	
		tar.setIsVipCard(src.getIsVipCard());	
		tar.setIsEnableShelflife(src.getIsEnableShelflife());	
		tar.setShelflifeDays(src.getShelflifeDays());	
		tar.setIsVendible(src.getIsVendible());	
		tar.setGrossWeight(src.getGrossWeight());	
		tar.setNetWeight(src.getNetWeight());	
		tar.setCode(src.getCode());	
		tar.setMerchantBrandId(src.getMerchantBrandId());	
		tar.setMarketPrice(src.getMarketPrice());	
		tar.setPromotionPrice(src.getPromotionPrice());	
		tar.setStatus(src.getStatus());	
		tar.setSuStatus(src.getSuStatus());
		tar.setSaleWay(src.getSaleWay());	
		tar.setSoldBase(src.getSoldBase());	
		tar.setFreightExplain(src.getFreightExplain());	
		tar.setShipmentsExplain(src.getShipmentsExplain());	
		tar.setFreightTemplateId(src.getFreightTemplateId());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setPlatformId(src.getPlatformId());	
		tar.setMerchantName(src.getMerchantName());
		tar.setClients(src.getClients());
		tar.setCompanys(src.getCompanys());
		tar.setProductCategory(src.getProductCategory());
		tar.setSalesVolume(src.getSalesVolume());
		tar.setStockWarning(src.getStockWarning());
		tar.setCreateUserid(src.getCreateUserid());
		tar.setCreateUsername(src.getCreateUsername());
		tar.setCreateUserip(src.getCreateUserip());
		tar.setCreateUsermac(src.getCreateUsermac());
		tar.setUpdateUserid(src.getUpdateUserid());
		tar.setUpdateUsername(src.getUpdateUsername());
		tar.setUpdateUserip(src.getUpdateUserip());
		tar.setUpdateUsermac(src.getUpdateUsermac());
		tar.setIsSpuKeyword(src.getIsSpuKeyword());
		tar.setDemoSalePrice(src.getDemoSalePrice());
		tar.setCompetingSalePrice(src.getCompetingSalePrice());
		tar.setStoreId(src.getStoreId());
        tar.setPresellPeriod(src.getPresellPeriod());
        tar.setRelevanceSuId(src.getRelevanceSuId());
        tar.setStoreName(src.getStoreName());
        tar.setHeadActivityCode(src.getHeadActivityCode());
        tar.setBuyType(src.getBuyType());
        tar.setFrontSerialNumber(src.getFrontSerialNumber());
        tar.setProfit(src.getProfit());
        tar.setSupplierName(src.getSupplierName());
		return tar;
	}

	public static MerchantProductVO toVO(MerchantProductDTO src) {
		if (src == null)
		return null;	
		MerchantProductVO tar = new MerchantProductVO();
		tar.setId(src.getId());
		tar.setMerchantProductSerialNumber(src.getMerchantProductSerialNumber());	
		tar.setMerchantId(src.getMerchantId());	
		tar.setStandardProductUnitId(src.getStandardProductUnitId());
		tar.setMerchantCateTreeNodeId(src.getMerchantCateTreeNodeId());	
		tar.setMerchantSeriesId(src.getMerchantSeriesId());	
		tar.setName(src.getName());	
		tar.setAlias(src.getAlias());	
		tar.setIsVisible(src.getIsVisible());	
		tar.setSubtitle(src.getSubtitle());	
		tar.setSupplierId(src.getSupplierId());	
		tar.setType(src.getType());	
		tar.setRemark(src.getRemark());	
		tar.setSalePrice(src.getSalePrice());	
		tar.setSaleTaxRate(src.getSaleTaxRate());	
		tar.setReturnDays(src.getReturnDays());	
		tar.setReplacementDays(src.getReplacementDays());	
		tar.setGuaranteeDays(src.getGuaranteeDays());	
		tar.setIsVatInvoice(src.getIsVatInvoice());	
		tar.setIsVipCard(src.getIsVipCard());	
		tar.setIsEnableShelflife(src.getIsEnableShelflife());	
		tar.setShelflifeDays(src.getShelflifeDays());	
		tar.setIsVendible(src.getIsVendible());	
		tar.setGrossWeight(src.getGrossWeight());	
		tar.setNetWeight(src.getNetWeight());	
		tar.setCode(src.getCode());	
		tar.setMerchantBrandId(src.getMerchantBrandId());	
		tar.setMarketPrice(src.getMarketPrice());	
		tar.setPromotionPrice(src.getPromotionPrice());	
		tar.setStatus(src.getStatus());	
		tar.setSuStatus(src.getSuStatus());
		tar.setSaleWay(src.getSaleWay());	
		tar.setSoldBase(src.getSoldBase());	
		tar.setFreightExplain(src.getFreightExplain());	
		tar.setShipmentsExplain(src.getShipmentsExplain());	
		tar.setFreightTemplateId(src.getFreightTemplateId());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setPlatformId(src.getPlatformId());	
		tar.setMerchantName(src.getMerchantName());
		tar.setClients(src.getClients());
		tar.setCompanys(src.getCompanys());
		tar.setProductCategory(src.getProductCategory());
		tar.setSalesVolume(src.getSalesVolume());
		tar.setStockWarning(src.getStockWarning());
		tar.setCreateUserid(src.getCreateUserid());
		tar.setCreateUsername(src.getCreateUsername());
		tar.setCreateUserip(src.getCreateUserip());
		tar.setCreateUsermac(src.getCreateUsermac());
		tar.setUpdateUserid(src.getUpdateUserid());
		tar.setUpdateUsername(src.getUpdateUsername());
		tar.setUpdateUserip(src.getUpdateUserip());
		tar.setUpdateUsermac(src.getUpdateUsermac());
		tar.setIsSpuKeyword(src.getIsSpuKeyword());
		tar.setDemoSalePrice(src.getDemoSalePrice());
		tar.setCompetingSalePrice(src.getCompetingSalePrice());
		tar.setStoreId(src.getStoreId());
        tar.setPresellPeriod(src.getPresellPeriod());
        tar.setRelevanceSuId(src.getRelevanceSuId());
        tar.setStoreName(src.getStoreName());
        tar.setHeadActivityCode(src.getHeadActivityCode());
        tar.setBuyType(src.getBuyType());
        tar.setFrontSerialNumber(src.getFrontSerialNumber());
		tar.setProfit(src.getProfit());

		return tar;
	}

	public static List<MerchantProductDTO> toDTOs(List<MerchantProductVO> srcs) {
		if (srcs == null)
			return null;
		List<MerchantProductDTO> list = new ArrayList<MerchantProductDTO>();
		for (MerchantProductVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<MerchantProductVO> toVO(List<MerchantProductDTO> srcs) {
		if (srcs == null)
			return null;
		List<MerchantProductVO> list = new ArrayList<MerchantProductVO>();
		for (MerchantProductDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}

    public static MerchantProductDTO toDTO(MerchantProductPO src) {
        if (src == null)
            return null;
        MerchantProductDTO tar = new MerchantProductDTO();
        tar.setId(src.getId());
        tar.setMerchantProductSerialNumber(src.getMerchantProductSerialNumber());
        tar.setMerchantId(src.getMerchantId());
        tar.setStandardProductUnitId(src.getStandardProductUnitId());
        tar.setMerchantCateTreeNodeId(src.getMerchantCateTreeNodeId());
        tar.setMerchantSeriesId(src.getMerchantSeriesId());
        tar.setName(src.getName());
        tar.setAlias(src.getAlias());
        tar.setIsVisible(src.getIsVisible());
        tar.setSubtitle(src.getSubtitle());
        tar.setSupplierId(src.getSupplierId());
        tar.setType(src.getType());
        tar.setRemark(src.getRemark());
        tar.setSalePrice(src.getSalePrice());
        tar.setSaleTaxRate(src.getSaleTaxRate());
        tar.setReturnDays(src.getReturnDays());
        tar.setReplacementDays(src.getReplacementDays());
        tar.setGuaranteeDays(src.getGuaranteeDays());
        tar.setIsVatInvoice(src.getIsVatInvoice());
        tar.setIsVipCard(src.getIsVipCard());
        tar.setIsEnableShelflife(src.getIsEnableShelflife());
        tar.setShelflifeDays(src.getShelflifeDays());
        tar.setIsVendible(src.getIsVendible());
        tar.setGrossWeight(src.getGrossWeight());
        tar.setNetWeight(src.getNetWeight());
        tar.setCode(src.getCode());
        tar.setMerchantBrandId(src.getMerchantBrandId());
        tar.setMarketPrice(src.getMarketPrice());
        tar.setPromotionPrice(src.getPromotionPrice());
        tar.setStatus(src.getStatus());
        tar.setSaleWay(src.getSaleWay());
        tar.setSoldBase(src.getSoldBase());
        tar.setFreightExplain(src.getFreightExplain());
        tar.setShipmentsExplain(src.getShipmentsExplain());
        tar.setFreightTemplateId(src.getFreightTemplateId());
        tar.setCreateTime(src.getCreateTime());
        tar.setUpdateTime(src.getUpdateTime());
        tar.setPlatformId(src.getPlatformId());
        tar.setProductCategory(src.getProductCategory());
        tar.setContent(src.getContent());
        tar.setStockWarning(src.getStockWarning());
        tar.setCreateUserid(src.getCreateUserid());
        tar.setCreateUsername(src.getCreateUsername());
        tar.setCreateUserip(src.getCreateUserip());
        tar.setCreateUsermac(src.getCreateUsermac());
        tar.setUpdateUserid(src.getUpdateUserid());
        tar.setUpdateUsername(src.getUpdateUsername());
        tar.setUpdateUserip(src.getUpdateUserip());
        tar.setUpdateUsermac(src.getUpdateUsermac());
        tar.setIsSpuKeyword(src.getIsSpuKeyword());
        tar.setDemoSalePrice(src.getDemoSalePrice());
        tar.setCompetingSalePrice(src.getCompetingSalePrice());
        tar.setStoreId(src.getStoreId());
        tar.setPresellPeriod(src.getPresellPeriod());
        tar.setRelevanceSuId(src.getRelevanceSuId());
        tar.setBuyType(src.getBuyType());
        tar.setFrontSerialNumber(src.getFrontSerialNumber());
        tar.setJdProfit(src.getJdProfit());
        return tar;
    }

    public static MerchantProductPO toPO(MerchantProductDTO src) {
        if (src == null)
            return null;
        MerchantProductPO tar = new MerchantProductPO();
        tar.setId(src.getId());
        tar.setMerchantProductSerialNumber(src.getMerchantProductSerialNumber());
        tar.setMerchantId(src.getMerchantId());
        tar.setStandardProductUnitId(src.getStandardProductUnitId());
        tar.setMerchantCateTreeNodeId(src.getMerchantCateTreeNodeId());
        tar.setMerchantSeriesId(src.getMerchantSeriesId());
        tar.setName(src.getName());
        tar.setAlias(src.getAlias());
        tar.setIsVisible(src.getIsVisible());
        tar.setSubtitle(src.getSubtitle());
        tar.setSupplierId(src.getSupplierId());
        tar.setType(src.getType());
        tar.setRemark(src.getRemark());
        tar.setSalePrice(src.getSalePrice());
        tar.setSaleTaxRate(src.getSaleTaxRate());
        tar.setReturnDays(src.getReturnDays());
        tar.setReplacementDays(src.getReplacementDays());
        tar.setGuaranteeDays(src.getGuaranteeDays());
        tar.setIsVatInvoice(src.getIsVatInvoice());
        tar.setIsVipCard(src.getIsVipCard());
        tar.setIsEnableShelflife(src.getIsEnableShelflife());
        tar.setShelflifeDays(src.getShelflifeDays());
        tar.setIsVendible(src.getIsVendible());
        tar.setGrossWeight(src.getGrossWeight());
        tar.setNetWeight(src.getNetWeight());
        tar.setCode(src.getCode());
        tar.setMerchantBrandId(src.getMerchantBrandId());
        tar.setMarketPrice(src.getMarketPrice());
        tar.setPromotionPrice(src.getPromotionPrice());
        tar.setStatus(src.getStatus());
        tar.setSaleWay(src.getSaleWay());
        tar.setSoldBase(src.getSoldBase());
        tar.setFreightExplain(src.getFreightExplain());
        tar.setShipmentsExplain(src.getShipmentsExplain());
        tar.setFreightTemplateId(src.getFreightTemplateId());
        tar.setCreateTime(src.getCreateTime());
        tar.setUpdateTime(src.getUpdateTime());
        tar.setPlatformId(src.getPlatformId());
        tar.setProductCategory(src.getProductCategory());
        tar.setContent(src.getContent());
        tar.setStockWarning(src.getStockWarning());
        tar.setCreateUserid(src.getCreateUserid());
        tar.setCreateUsername(src.getCreateUsername());
        tar.setCreateUserip(src.getCreateUserip());
        tar.setCreateUsermac(src.getCreateUsermac());
        tar.setUpdateUserid(src.getUpdateUserid());
        tar.setUpdateUsername(src.getUpdateUsername());
        tar.setUpdateUserip(src.getUpdateUserip());
        tar.setUpdateUsermac(src.getUpdateUsermac());
        tar.setIsSpuKeyword(src.getIsSpuKeyword());
        tar.setDemoSalePrice(src.getDemoSalePrice());
        tar.setCompetingSalePrice(src.getCompetingSalePrice());
        tar.setStoreId(src.getStoreId());
        tar.setPresellPeriod(src.getPresellPeriod());
        tar.setRelevanceSuId(src.getRelevanceSuId());
        tar.setBuyType(src.getBuyType());
        tar.setFrontSerialNumber(src.getFrontSerialNumber());
        tar.setJdProfit(src.getJdProfit());
        return tar;
    }

    public static List<MerchantProductDTO> toDTO(List<MerchantProductPO> srcs) {
        if (srcs == null)
            return null;
        List<MerchantProductDTO> list = new ArrayList<MerchantProductDTO>();
        for (MerchantProductPO src : srcs) {
            list.add(toDTO(src));
        }
        return list;
    }

    public static List<MerchantProductPO> toPO(List<MerchantProductDTO> srcs) {
        if (srcs == null)
            return null;
        List<MerchantProductPO> list = new ArrayList<MerchantProductPO>();
        for (MerchantProductDTO src : srcs) {
            list.add(toPO(src));
        }
        return list;
    }

    public static CommodityDetailsPO cdToPO(CommodityDetailsDTO src) {
        if (src == null) {
            return null;
        }
        CommodityDetailsPO tar = new CommodityDetailsPO();
        tar.setIds(src.getIds());
        tar.setMerchantProductSerialNumber(src.getMerchantProductSerialNumber());
        tar.setName(src.getName());
        tar.setBeginSalePrice(src.getBeginSalePrice());
        tar.setEndSalePrice(src.getEndSalePrice());
        tar.setBeginPromotionPrice(src.getBeginPromotionPrice());
        tar.setEndPromotionPrice(src.getEndPromotionPrice());
        tar.setPlatformId(src.getPlatformId());
        tar.setStatus(src.getStatus());
        tar.setSaleWay(src.getSaleWay());
        return tar;
    }
}
	