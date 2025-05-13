package com.egeo.components.product.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.product.dto.StandardUnitStoreDTO;
import com.egeo.components.product.po.StandardUnitStorePO;
import com.egeo.components.product.vo.StandardUnitStoreVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author min
 * @date 2018-09-11 14:59:33
 */
public class StandardUnitStoreConverter {
	
	public static StandardUnitStoreDTO toDTO(StandardUnitStorePO src) {
		if (src == null)
		return null;	
		StandardUnitStoreDTO tar = new StandardUnitStoreDTO();
		tar.setId(src.getId());
		tar.setStoreId(src.getStoreId());
		tar.setStandardUnitId(src.getStandardUnitId());
		tar.setPlatformId(src.getPlatformId());
		tar.setStandardUnitName(src.getStandardUnitName());
		tar.setStandardUnitSerialNumber(src.getStandardUnitSerialNumber());
		tar.setSalePriceStart(src.getSalePriceStart());
		tar.setSalePriceStop(src.getSalePriceStop());
		tar.setPromotionPriceStart(src.getPromotionPriceStart());
		tar.setPromotionPriceStop(src.getPromotionPriceStop());
		tar.setStatus(src.getStatus());
		tar.setIsVisible(src.getIsVisible());
		tar.setStoreName(src.getStoreName());
		return tar;
	}

	public static StandardUnitStorePO toPO(StandardUnitStoreDTO src) {
		if (src == null)
		return null;	
		StandardUnitStorePO tar = new StandardUnitStorePO();
		tar.setId(src.getId());
		tar.setStoreId(src.getStoreId());
		tar.setStandardUnitId(src.getStandardUnitId());
		tar.setPlatformId(src.getPlatformId());
		tar.setStandardUnitName(src.getStandardUnitName());
		tar.setStandardUnitSerialNumber(src.getStandardUnitSerialNumber());
		tar.setSalePriceStart(src.getSalePriceStart());
		tar.setSalePriceStop(src.getSalePriceStop());
		tar.setPromotionPriceStart(src.getPromotionPriceStart());
		tar.setPromotionPriceStop(src.getPromotionPriceStop());
		tar.setStatus(src.getStatus());
		tar.setIsVisible(src.getIsVisible());
		tar.setStoreName(src.getStoreName());
		return tar;
	}

	public static List<StandardUnitStoreDTO> toDTO(List<StandardUnitStorePO> srcs) {
		if (srcs == null)
			return null;
		List<StandardUnitStoreDTO> list = new ArrayList<StandardUnitStoreDTO>();
		for (StandardUnitStorePO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<StandardUnitStorePO> toPO(List<StandardUnitStoreDTO> srcs) {
		if (srcs == null)
			return null;
		List<StandardUnitStorePO> list = new ArrayList<StandardUnitStorePO>();
		for (StandardUnitStoreDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
	public static StandardUnitStoreDTO toDTO(StandardUnitStoreVO src) {
		if (src == null)
		return null;	
		StandardUnitStoreDTO tar = new StandardUnitStoreDTO();
		tar.setId(src.getId());
		tar.setStoreId(src.getStoreId());	
		tar.setStandardUnitId(src.getStandardUnitId());	
		tar.setPlatformId(src.getPlatformId());	
		tar.setStoreMenuNodeId(src.getStoreMenuNodeId());
		tar.setStandardUnitName(src.getStandardUnitName());
		tar.setStandardUnitSerialNumber(src.getStandardUnitSerialNumber());
		tar.setSalePriceStart(src.getSalePriceStart());
		tar.setSalePriceStop(src.getSalePriceStop());
		tar.setPromotionPriceStart(src.getPromotionPriceStart());
		tar.setPromotionPriceStop(src.getPromotionPriceStop());
		tar.setStatus(src.getStatus());
		tar.setIsVisible(src.getIsVisible());
		tar.setStoreName(src.getStoreName());
		return tar;
	}

	public static StandardUnitStoreVO toVO(StandardUnitStoreDTO src) {
		if (src == null)
		return null;	
		StandardUnitStoreVO tar = new StandardUnitStoreVO();
		tar.setId(src.getId());
		tar.setStoreId(src.getStoreId());	
		tar.setStandardUnitId(src.getStandardUnitId());	
		tar.setPlatformId(src.getPlatformId());	
		tar.setStoreMenuNodeId(src.getStoreMenuNodeId());
		tar.setStandardUnitName(src.getStandardUnitName());
		tar.setStandardUnitSerialNumber(src.getStandardUnitSerialNumber());
		tar.setSalePriceStart(src.getSalePriceStart());
		tar.setSalePriceStop(src.getSalePriceStop());
		tar.setPromotionPriceStart(src.getPromotionPriceStart());
		tar.setPromotionPriceStop(src.getPromotionPriceStop());
		tar.setStatus(src.getStatus());
		tar.setIsVisible(src.getIsVisible());
		tar.setStoreName(src.getStoreName());
		return tar;
	}

	public static List<StandardUnitStoreDTO> toDTOs(List<StandardUnitStoreVO> srcs) {
		if (srcs == null)
			return null;
		List<StandardUnitStoreDTO> list = new ArrayList<StandardUnitStoreDTO>();
		for (StandardUnitStoreVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<StandardUnitStoreVO> toVO(List<StandardUnitStoreDTO> srcs) {
		if (srcs == null)
			return null;
		List<StandardUnitStoreVO> list = new ArrayList<StandardUnitStoreVO>();
		for (StandardUnitStoreDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
}
	