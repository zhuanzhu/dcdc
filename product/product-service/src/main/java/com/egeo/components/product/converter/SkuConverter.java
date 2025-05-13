package com.egeo.components.product.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.product.dto.SkuDTO;
import com.egeo.components.product.po.SkuPO;
import com.egeo.components.product.vo.SkuVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author min
 * @date 2018-01-06 11:34:03
 */
public class SkuConverter {

	
	public static SkuDTO toDTO(SkuVO src) {
		if (src == null)
		return null;	
		SkuDTO tar = new SkuDTO();
		tar.setId(src.getId());
		tar.setSkuSerialNumber(src.getSkuSerialNumber());	
		tar.setMerchantId(src.getMerchantId());	
		tar.setStandardProductUnitId(src.getStandardProductUnitId());	
		tar.setSkuName(src.getSkuName());	
		tar.setSkuProductName(src.getSkuProductName());	
		tar.setSkuMarketPrice(src.getSkuMarketPrice());	
		tar.setSkuCostingPrice(src.getSkuCostingPrice());	
		tar.setRemark(src.getRemark());	
		tar.setIsAvailable(src.getIsAvailable());	
		tar.setCode(src.getCode());	
		tar.setSkuPicUrl(src.getSkuPicUrl());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setPlatformId(src.getPlatformId());	
		tar.setSynchronizationTime(src.getSynchronizationTime());
		tar.setIsValid(src.getIsValid());
		tar.setStandardProductUnitName(src.getStandardProductUnitName());
		tar.setExternalSkuId(src.getExternalSkuId());
		tar.setBarCode(src.getBarCode());
		return tar;
	}

	public static SkuVO toVO(SkuDTO src) {
		if (src == null)
		return null;	
		SkuVO tar = new SkuVO();
		tar.setId(src.getId());
		tar.setSkuSerialNumber(src.getSkuSerialNumber());	
		tar.setMerchantId(src.getMerchantId());	
		tar.setStandardProductUnitId(src.getStandardProductUnitId());	
		tar.setSkuName(src.getSkuName());	
		tar.setSkuProductName(src.getSkuProductName());	
		tar.setSkuMarketPrice(src.getSkuMarketPrice());	
		tar.setSkuCostingPrice(src.getSkuCostingPrice());	
		tar.setRemark(src.getRemark());	
		tar.setIsAvailable(src.getIsAvailable());	
		tar.setCode(src.getCode());	
		tar.setSkuPicUrl(src.getSkuPicUrl());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setPlatformId(src.getPlatformId());	
		tar.setSynchronizationTime(src.getSynchronizationTime());
		tar.setIsValid(src.getIsValid());
		tar.setStandardProductUnitName(src.getStandardProductUnitName());
		tar.setExternalSkuId(src.getExternalSkuId());
		tar.setBarCode(src.getBarCode());
		return tar;
	}

	public static List<SkuDTO> toDTOs(List<SkuVO> srcs) {
		if (srcs == null)
			return null;
		List<SkuDTO> list = new ArrayList<SkuDTO>();
		for (SkuVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<SkuVO> toVO(List<SkuDTO> srcs) {
		if (srcs == null)
			return null;
		List<SkuVO> list = new ArrayList<SkuVO>();
		for (SkuDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static SkuDTO toDTO(SkuPO src) {
		if (src == null)
		return null;	
		SkuDTO tar = new SkuDTO();
		tar.setId(src.getId());
		tar.setSkuSerialNumber(src.getSkuSerialNumber());
		tar.setMerchantId(src.getMerchantId());
		tar.setStandardProductUnitId(src.getStandardProductUnitId());
		tar.setSkuName(src.getSkuName());
		tar.setSkuProductName(src.getSkuProductName());
		tar.setSkuMarketPrice(src.getSkuMarketPrice());
		tar.setSkuCostingPrice(src.getSkuCostingPrice());
		tar.setRemark(src.getRemark());
		tar.setIsAvailable(src.getIsAvailable());
		tar.setCode(src.getCode());
		tar.setSkuPicUrl(src.getSkuPicUrl());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		tar.setSynchronizationTime(src.getSynchronizationTime());
		tar.setIsValid(src.getIsValid());
		tar.setBarCode(src.getBarCode());
		tar.setExternalSkuId(src.getExternalSkuId());
		tar.setTaxRate(src.getTaxRate());
		return tar;
	}

	public static SkuPO toPO(SkuDTO src) {
		if (src == null)
		return null;	
		SkuPO tar = new SkuPO();
		tar.setId(src.getId());
		tar.setSkuSerialNumber(src.getSkuSerialNumber());
		tar.setMerchantId(src.getMerchantId());
		tar.setStandardProductUnitId(src.getStandardProductUnitId());
		tar.setSkuName(src.getSkuName());
		tar.setSkuProductName(src.getSkuProductName());
		tar.setSkuMarketPrice(src.getSkuMarketPrice());
		tar.setSkuCostingPrice(src.getSkuCostingPrice());
		tar.setRemark(src.getRemark());
		tar.setIsAvailable(src.getIsAvailable());
		tar.setCode(src.getCode());
		tar.setSkuPicUrl(src.getSkuPicUrl());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		tar.setSynchronizationTime(src.getSynchronizationTime());
		tar.setIsValid(src.getIsValid());
		tar.setSkuProductName(src.getStandardProductUnitName());
		tar.setBarCode(src.getBarCode());
		tar.setExternalSkuId(src.getExternalSkuId());
		tar.setTaxRate(src.getTaxRate());
		return tar;
	}

	public static List<SkuDTO> toDTO(List<SkuPO> srcs) {
		if (srcs == null)
			return null;
		List<SkuDTO> list = new ArrayList<SkuDTO>();
		for (SkuPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<SkuPO> toPO(List<SkuDTO> srcs) {
		if (srcs == null)
			return null;
		List<SkuPO> list = new ArrayList<SkuPO>();
		for (SkuDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	