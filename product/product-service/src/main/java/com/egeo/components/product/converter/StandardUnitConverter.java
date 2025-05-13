package com.egeo.components.product.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.product.condition.StandardUnitCondition;
import com.egeo.components.product.dto.JdProductDTO;
import com.egeo.components.product.dto.StandardUnitDTO;
import com.egeo.components.product.po.StandardUnitPO;
import com.egeo.components.product.vo.StandardUnitVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author min
 * @date 2018-01-06 09:20:45
 */
public class StandardUnitConverter {
	
	public static StandardUnitDTO toDTOFromCondition(StandardUnitCondition src) {
		StandardUnitDTO dto = toDTO(src);
		if(dto!=null) {
			dto.setSource(src.getSource());
		}
		return dto;
	}
	public static StandardUnitDTO toDTOFromJdProduct(JdProductDTO src) {
		StandardUnitDTO dto = toDTO(src);
		
		return dto;
	}
	public static StandardUnitDTO toDTO(JdProductDTO src) {
		if (src == null)
			return null;	
		StandardUnitDTO tar = new StandardUnitDTO();
		tar.setId(src.getId());
		tar.setSource(3);
		tar.setSaleWay(1);
		tar.setStatus(src.upState()?3:1);
		tar.setAlias(src.getName());
		tar.setStandardProductUnitId(src.getId());
		tar.setName(src.getName());
		/*tar.setSubtitle(src.getSubtitle());
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
		tar.setCategoryTreeNodeId(src.getCategoryTreeNodeId());
		tar.setClientId(src.getClientId());
		tar.setCompanyId(src.getCompanyId());
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
        tar.setSalePriceStart(src.getSalePriceStart());
        tar.setSalePriceStop(src.getSalePriceStop());
        tar.setPromotionPriceStart(src.getPromotionPriceStart());
        tar.setPromotionPriceStop(src.getPromotionPriceStop());
        tar.setBuyType(src.getBuyType());
        tar.setFrontSerialNumber(src.getFrontSerialNumber());
		tar.setJdProfit(src.getJdProfit());*/
		return tar;
	}

	public static StandardUnitDTO toDTO(StandardUnitPO src) {
		if (src == null)
		return null;	
		StandardUnitDTO tar = new StandardUnitDTO();
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
		tar.setEnterpriseId(src.getEnterpriseId());
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
		tar.setCategoryTreeNodeId(src.getCategoryTreeNodeId());
		tar.setClientId(src.getClientId());
		tar.setCompanyId(src.getCompanyId());
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
        tar.setSalePriceStart(src.getSalePriceStart());
        tar.setSalePriceStop(src.getSalePriceStop());
        tar.setPromotionPriceStart(src.getPromotionPriceStart());
        tar.setPromotionPriceStop(src.getPromotionPriceStop());
        tar.setBuyType(src.getBuyType());
        tar.setFrontSerialNumber(src.getFrontSerialNumber());
		tar.setJdProfit(src.getJdProfit());
		tar.setSource(src.getSource());
		return tar;
	}
	public static StandardUnitPO toPO(StandardUnitDTO src) {
		if (src == null)
		return null;	
		StandardUnitPO tar = new StandardUnitPO();
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
		tar.setEnterpriseId(src.getEnterpriseId());
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
		tar.setCategoryTreeNodeId(src.getCategoryTreeNodeId());
		tar.setClientId(src.getClientId());
		tar.setCompanyId(src.getCompanyId());
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
        tar.setSalePriceStart(src.getSalePriceStart());
        tar.setSalePriceStop(src.getSalePriceStop());
        tar.setPromotionPriceStart(src.getPromotionPriceStart());
        tar.setPromotionPriceStop(src.getPromotionPriceStop());
        tar.setBuyType(src.getBuyType());
        tar.setFrontSerialNumber(src.getFrontSerialNumber());
		tar.setJdProfit(src.getJdProfit());

		return tar;
	}

	public static List<StandardUnitDTO> toDTO(List<StandardUnitPO> srcs) {
		if (srcs == null)
			return null;
		List<StandardUnitDTO> list = new ArrayList<StandardUnitDTO>();
		for (StandardUnitPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<StandardUnitPO> toPO(List<StandardUnitDTO> srcs) {
		if (srcs == null)
			return null;
		List<StandardUnitPO> list = new ArrayList<StandardUnitPO>();
		for (StandardUnitDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
	public static StandardUnitDTO toDTO(StandardUnitVO src) {
		if (src == null)
		return null;	
		StandardUnitDTO tar = new StandardUnitDTO();
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
		tar.setEnterpriseId(src.getEnterpriseId());
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
		tar.setClientId(src.getClientId());
		tar.setCompanyId(src.getCompanyId());
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
		return tar;
	}

	public static StandardUnitVO toVO(StandardUnitDTO src) {
		if (src == null)
		return null;	
		StandardUnitVO tar = new StandardUnitVO();
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
		tar.setEnterpriseId(src.getEnterpriseId());
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
		tar.setClientId(src.getClientId());
		tar.setCompanyId(src.getCompanyId());
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
		return tar;
	}

	public static List<StandardUnitDTO> toDTOs(List<StandardUnitVO> srcs) {
		if (srcs == null)
			return null;
		List<StandardUnitDTO> list = new ArrayList<StandardUnitDTO>();
		for (StandardUnitVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<StandardUnitVO> toVO(List<StandardUnitDTO> srcs) {
		if (srcs == null)
			return null;
		List<StandardUnitVO> list = new ArrayList<StandardUnitVO>();
		for (StandardUnitDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
}
	