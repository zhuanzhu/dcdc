package com.egeo.components.product.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.product.dto.StandardUnitRecordDTO;
import com.egeo.components.product.po.StandardUnitRecordPO;
import com.egeo.components.product.vo.StandardUnitRecordVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author min
 * @date 2018-01-08 06:29:09
 */
public class StandardUnitRecordConverter {
	
	public static StandardUnitRecordDTO toDTO(StandardUnitRecordPO src) {
		if (src == null)
		return null;	
		StandardUnitRecordDTO tar = new StandardUnitRecordDTO();
		tar.setId(src.getId());
		tar.setMerchantProductSerialNumber(src.getMerchantProductSerialNumber());
		tar.setStandardUnitId(src.getStandardUnitId());
		tar.setMerchantId(src.getMerchantId());
		tar.setStandardProductUnitId(src.getStandardProductUnitId());
		tar.setMerchantCateTreeNodeId(src.getMerchantCateTreeNodeId());
		tar.setProductCategory(src.getProductCategory());
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
		return tar;
	}

	public static StandardUnitRecordPO toPO(StandardUnitRecordDTO src) {
		if (src == null)
		return null;	
		StandardUnitRecordPO tar = new StandardUnitRecordPO();
		tar.setId(src.getId());
		tar.setMerchantProductSerialNumber(src.getMerchantProductSerialNumber());
		tar.setStandardUnitId(src.getStandardUnitId());
		tar.setMerchantId(src.getMerchantId());
		tar.setStandardProductUnitId(src.getStandardProductUnitId());
		tar.setMerchantCateTreeNodeId(src.getMerchantCateTreeNodeId());
		tar.setProductCategory(src.getProductCategory());
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
		return tar;
	}

	public static List<StandardUnitRecordDTO> toDTO(List<StandardUnitRecordPO> srcs) {
		if (srcs == null)
			return null;
		List<StandardUnitRecordDTO> list = new ArrayList<StandardUnitRecordDTO>();
		for (StandardUnitRecordPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<StandardUnitRecordPO> toPO(List<StandardUnitRecordDTO> srcs) {
		if (srcs == null)
			return null;
		List<StandardUnitRecordPO> list = new ArrayList<StandardUnitRecordPO>();
		for (StandardUnitRecordDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
	public static StandardUnitRecordDTO toDTO(StandardUnitRecordVO src) {
		if (src == null)
		return null;	
		StandardUnitRecordDTO tar = new StandardUnitRecordDTO();
		tar.setId(src.getId());
		tar.setMerchantProductSerialNumber(src.getMerchantProductSerialNumber());	
		tar.setStandardUnitId(src.getStandardUnitId());	
		tar.setMerchantId(src.getMerchantId());	
		tar.setStandardProductUnitId(src.getStandardProductUnitId());	
		tar.setMerchantCateTreeNodeId(src.getMerchantCateTreeNodeId());	
		tar.setProductCategory(src.getProductCategory());	
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
		tar.setCreateUserid(src.getCreateUserid());
		tar.setCreateUsername(src.getCreateUsername());
		tar.setCreateUserip(src.getCreateUserip());
		tar.setCreateUsermac(src.getCreateUsermac());
		tar.setUpdateUserid(src.getUpdateUserid());
		tar.setUpdateUsername(src.getUpdateUsername());
		tar.setUpdateUserip(src.getUpdateUserip());
		tar.setUpdateUsermac(src.getUpdateUsermac());
		tar.setDemoSalePrice(src.getDemoSalePrice());
		tar.setCompetingSalePrice(src.getCompetingSalePrice());
		tar.setStoreId(src.getStoreId());
        tar.setPresellPeriod(src.getPresellPeriod());
        tar.setRelevanceSuId(src.getRelevanceSuId());
        tar.setBuyType(src.getBuyType());
		return tar;
	}

	public static StandardUnitRecordVO toVO(StandardUnitRecordDTO src) {
		if (src == null)
		return null;	
		StandardUnitRecordVO tar = new StandardUnitRecordVO();
		tar.setId(src.getId());
		tar.setMerchantProductSerialNumber(src.getMerchantProductSerialNumber());	
		tar.setStandardUnitId(src.getStandardUnitId());	
		tar.setMerchantId(src.getMerchantId());	
		tar.setStandardProductUnitId(src.getStandardProductUnitId());	
		tar.setMerchantCateTreeNodeId(src.getMerchantCateTreeNodeId());	
		tar.setProductCategory(src.getProductCategory());	
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
		tar.setCreateUserid(src.getCreateUserid());
		tar.setCreateUsername(src.getCreateUsername());
		tar.setCreateUserip(src.getCreateUserip());
		tar.setCreateUsermac(src.getCreateUsermac());
		tar.setUpdateUserid(src.getUpdateUserid());
		tar.setUpdateUsername(src.getUpdateUsername());
		tar.setUpdateUserip(src.getUpdateUserip());
		tar.setUpdateUsermac(src.getUpdateUsermac());
		tar.setDemoSalePrice(src.getDemoSalePrice());
		tar.setCompetingSalePrice(src.getCompetingSalePrice());
		tar.setStoreId(src.getStoreId());
        tar.setPresellPeriod(src.getPresellPeriod());
        tar.setRelevanceSuId(src.getRelevanceSuId());
        tar.setBuyType(src.getBuyType());
		return tar;
	}

	public static List<StandardUnitRecordDTO> toDTOs(List<StandardUnitRecordVO> srcs) {
		if (srcs == null)
			return null;
		List<StandardUnitRecordDTO> list = new ArrayList<StandardUnitRecordDTO>();
		for (StandardUnitRecordVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<StandardUnitRecordVO> toVO(List<StandardUnitRecordDTO> srcs) {
		if (srcs == null)
			return null;
		List<StandardUnitRecordVO> list = new ArrayList<StandardUnitRecordVO>();
		for (StandardUnitRecordDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
}
	