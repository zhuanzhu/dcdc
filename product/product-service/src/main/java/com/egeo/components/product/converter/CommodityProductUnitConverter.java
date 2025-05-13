package com.egeo.components.product.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.product.condition.CommodityProductUnitCondition;
import com.egeo.components.product.dto.CommodityProductUnitDTO;
import com.egeo.components.product.po.CommodityProductUnitPO;
import com.egeo.components.product.vo.CommodityProductUnitVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author min
 * @date 2018-01-08 11:16:12
 */
public class CommodityProductUnitConverter {
	
	
	public static CommodityProductUnitDTO toDTO(CommodityProductUnitVO src) {
		if (src == null)
		return null;	
		CommodityProductUnitDTO tar = new CommodityProductUnitDTO();
		tar.setId(src.getId());
		tar.setProductUnitSerialNumber(src.getProductUnitSerialNumber());	
		tar.setProductUnitId(src.getProductUnitId());	
		tar.setSkuId(src.getSkuId());	
		tar.setStandardUnitId(src.getStandardUnitId());	
		tar.setName(src.getName());	
		tar.setRemark(src.getRemark());	
		tar.setSalePrice(src.getSalePrice());	
		tar.setStatus(src.getStatus());	
		tar.setIsVendible(src.getIsVendible());	
		tar.setCode(src.getCode());	
		tar.setPuPicUrl(src.getPuPicUrl());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setPlatformId(src.getPlatformId());	
		tar.setStoreId(src.getStoreId());
		tar.setDemoSalePrice(src.getDemoSalePrice());
		tar.setCompetingSalePrice(src.getCompetingSalePrice());
		return tar;
	}

	public static CommodityProductUnitVO toVO(CommodityProductUnitDTO src) {
		if (src == null)
		return null;	
		CommodityProductUnitVO tar = new CommodityProductUnitVO();
		tar.setId(src.getId());
		tar.setProductUnitSerialNumber(src.getProductUnitSerialNumber());	
		tar.setProductUnitId(src.getProductUnitId());	
		tar.setSkuId(src.getSkuId());	
		tar.setStandardUnitId(src.getStandardUnitId());	
		tar.setName(src.getName());	
		tar.setRemark(src.getRemark());	
		tar.setSalePrice(src.getSalePrice());	
		tar.setStatus(src.getStatus());	
		tar.setIsVendible(src.getIsVendible());	
		tar.setCode(src.getCode());	
		tar.setPuPicUrl(src.getPuPicUrl());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setPlatformId(src.getPlatformId());	
		tar.setStoreId(src.getStoreId());
		tar.setDemoSalePrice(src.getDemoSalePrice());
		tar.setCompetingSalePrice(src.getCompetingSalePrice());
		return tar;
	}

	public static List<CommodityProductUnitDTO> toDTOs(List<CommodityProductUnitVO> srcs) {
		if (srcs == null)
			return null;
		List<CommodityProductUnitDTO> list = new ArrayList<CommodityProductUnitDTO>();
		for (CommodityProductUnitVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<CommodityProductUnitVO> toVO(List<CommodityProductUnitDTO> srcs) {
		if (srcs == null)
			return null;
		List<CommodityProductUnitVO> list = new ArrayList<CommodityProductUnitVO>();
		for (CommodityProductUnitDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}

	public static CommodityProductUnitDTO toDTO(CommodityProductUnitPO src) {
		if (src == null)
		return null;	
		CommodityProductUnitDTO tar = new CommodityProductUnitDTO();
		tar.setId(src.getId());
		tar.setProductUnitSerialNumber(src.getProductUnitSerialNumber());
		tar.setProductUnitId(src.getProductUnitId());
		tar.setSkuId(src.getSkuId());
		tar.setStandardUnitId(src.getStandardUnitId());
		tar.setName(src.getName());
		tar.setRemark(src.getRemark());
		tar.setSalePrice(src.getSalePrice());
		tar.setStatus(src.getStatus());
		tar.setIsVendible(src.getIsVendible());
		tar.setCode(src.getCode());
		tar.setPuPicUrl(src.getPuPicUrl());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		tar.setStoreId(src.getStoreId());
		tar.setDemoSalePrice(src.getDemoSalePrice());
		tar.setCompetingSalePrice(src.getCompetingSalePrice());
		tar.setLimitBuyNum(src.getLimitBuyNum());
		tar.setJdSpuId(src.getJdSpuId());

		return tar;
	}
	public static CommodityProductUnitDTO conditionToDTO(CommodityProductUnitCondition src) {
		if (src == null)
			return null;	
		CommodityProductUnitDTO tar = new CommodityProductUnitDTO();
		tar.setId(src.getId());
		tar.setProductUnitSerialNumber(src.getProductUnitSerialNumber());
		tar.setProductUnitId(src.getProductUnitId());
		tar.setSkuId(src.getSkuId());
		tar.setStandardUnitId(src.getStandardUnitId());
		tar.setName(src.getName());
		tar.setRemark(src.getRemark());
		tar.setSalePrice(src.getSalePrice());
		tar.setStatus(src.getStatus());
		tar.setIsVendible(src.getIsVendible());
		tar.setCode(src.getCode());
		tar.setPuPicUrl(src.getPuPicUrl());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		tar.setStoreId(src.getStoreId());
		tar.setDemoSalePrice(src.getDemoSalePrice());
		tar.setCompetingSalePrice(src.getCompetingSalePrice());
		tar.setExternalSkuId(src.getExternalSkuId());
		tar.setSkuName(src.getSkuName());
		tar.setMerchantId(src.getMerchantId());
		tar.setPuId(src.getPuId());
		tar.setPuSalePrice(src.getPuSalePrice());
		tar.setMaxSalePrice(src.getMaxSalePrice());
		tar.setMinSalePrice(src.getMinSalePrice());
		tar.setLimitBuyNum(src.getLimitBuyNum());
		tar.setJdSpuId(src.getJdSpuId());
		return tar;
	}

	public static CommodityProductUnitPO toPO(CommodityProductUnitDTO src) {
		if (src == null)
		return null;	
		CommodityProductUnitPO tar = new CommodityProductUnitPO();
		tar.setId(src.getId());
		tar.setProductUnitSerialNumber(src.getProductUnitSerialNumber());
		tar.setProductUnitId(src.getProductUnitId());
		tar.setSkuId(src.getSkuId());
		tar.setStandardUnitId(src.getStandardUnitId());
		tar.setName(src.getName());
		tar.setRemark(src.getRemark());
		tar.setSalePrice(src.getSalePrice());
		tar.setStatus(src.getStatus());
		tar.setIsVendible(src.getIsVendible());
		tar.setCode(src.getCode());
		tar.setPuPicUrl(src.getPuPicUrl());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		tar.setStoreId(src.getStoreId());
		tar.setDemoSalePrice(src.getDemoSalePrice());
		tar.setCompetingSalePrice(src.getCompetingSalePrice());
		tar.setLimitBuyNum(src.getLimitBuyNum());
tar.setJdSpuId(src.getJdSpuId());
		return tar;
	}
	
	public static CommodityProductUnitCondition toCondition(CommodityProductUnitDTO src) {
		if (src == null)
		return null;	
		CommodityProductUnitCondition tar = new CommodityProductUnitCondition();
		tar.setId(src.getId());
		tar.setProductUnitSerialNumber(src.getProductUnitSerialNumber());
		tar.setProductUnitId(src.getProductUnitId());
		tar.setSkuId(src.getSkuId());
		tar.setStandardUnitId(src.getStandardUnitId());
		tar.setName(src.getName());
		tar.setRemark(src.getRemark());
		tar.setSalePrice(src.getSalePrice());
		tar.setStatus(src.getStatus());
		tar.setIsVendible(src.getIsVendible());
		tar.setCode(src.getCode());
		tar.setPuPicUrl(src.getPuPicUrl());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		tar.setStoreId(src.getStoreId());
		tar.setDemoSalePrice(src.getDemoSalePrice());
		tar.setCompetingSalePrice(src.getCompetingSalePrice());
		tar.setExternalSkuId(src.getExternalSkuId());
		tar.setSkuName(src.getSkuName());
		tar.setMerchantId(src.getMerchantId());
		tar.setPuId(src.getPuId());
		tar.setPuSalePrice(src.getPuSalePrice());
		tar.setMaxSalePrice(src.getMaxSalePrice());
		tar.setMinSalePrice(src.getMinSalePrice());
		tar.setLimitBuyNum(src.getLimitBuyNum());
tar.setJdSpuId(src.getJdSpuId());
		return tar;
	}

	public static List<CommodityProductUnitDTO> toDTO(List<CommodityProductUnitPO> srcs) {
		if (srcs == null)
			return null;
		List<CommodityProductUnitDTO> list = new ArrayList<CommodityProductUnitDTO>();
		for (CommodityProductUnitPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<CommodityProductUnitPO> toPO(List<CommodityProductUnitDTO> srcs) {
		if (srcs == null)
			return null;
		List<CommodityProductUnitPO> list = new ArrayList<CommodityProductUnitPO>();
		for (CommodityProductUnitDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
	
	public static List<CommodityProductUnitDTO> conditionToDTO(List<CommodityProductUnitCondition> srcs) {
		if (srcs == null)
			return null;
		List<CommodityProductUnitDTO> list = new ArrayList<CommodityProductUnitDTO>();
		for (CommodityProductUnitCondition src : srcs) {
			list.add(conditionToDTO(src));
		}
		return list;
	}
}
	