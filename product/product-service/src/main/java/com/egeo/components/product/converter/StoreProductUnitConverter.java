package com.egeo.components.product.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.product.dto.StoreProductUnitDTO;
import com.egeo.components.product.po.StoreProductUnitPO;
import com.egeo.components.product.vo.StoreProductUnitVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author min
 * @date 2018-09-13 05:07:27
 */
public class StoreProductUnitConverter {
	
	public static StoreProductUnitDTO toDTO(StoreProductUnitPO src) {
		if (src == null)
		return null;	
		StoreProductUnitDTO tar = new StoreProductUnitDTO();
		tar.setId(src.getId());
		tar.setStoreId(src.getStoreId());
		tar.setStandardUnitId(src.getStandardUnitId());
		tar.setCommodityProductUnitId(src.getCommodityProductUnitId());
		tar.setStatus(src.getStatus());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		tar.setStoreName(src.getStoreName());
		tar.setCommodityProductUnitName(src.getCommodityProductUnitName());
		tar.setProductUnitSerialNumber(src.getProductUnitSerialNumber());
		tar.setSalePrice(src.getSalePrice());
		tar.setPuPicUrl(src.getPuPicUrl());
		tar.setSaleWay(src.getSaleWay());
		return tar;
	}

	public static StoreProductUnitPO toPO(StoreProductUnitDTO src) {
		if (src == null)
		return null;	
		StoreProductUnitPO tar = new StoreProductUnitPO();
		tar.setId(src.getId());
		tar.setStoreId(src.getStoreId());
		tar.setStandardUnitId(src.getStandardUnitId());
		tar.setCommodityProductUnitId(src.getCommodityProductUnitId());
		tar.setStatus(src.getStatus());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		tar.setStoreName(src.getStoreName());
		tar.setCommodityProductUnitName(src.getCommodityProductUnitName());
		tar.setProductUnitSerialNumber(src.getProductUnitSerialNumber());
		tar.setSalePrice(src.getSalePrice());
		tar.setPuPicUrl(src.getPuPicUrl());
		tar.setSaleWay(src.getSaleWay());
		return tar;
	}

	public static List<StoreProductUnitDTO> toDTO(List<StoreProductUnitPO> srcs) {
		if (srcs == null)
			return null;
		List<StoreProductUnitDTO> list = new ArrayList<StoreProductUnitDTO>();
		for (StoreProductUnitPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<StoreProductUnitPO> toPO(List<StoreProductUnitDTO> srcs) {
		if (srcs == null)
			return null;
		List<StoreProductUnitPO> list = new ArrayList<StoreProductUnitPO>();
		for (StoreProductUnitDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
	public static StoreProductUnitDTO toDTO(StoreProductUnitVO src) {
		if (src == null)
		return null;	
		StoreProductUnitDTO tar = new StoreProductUnitDTO();
		tar.setId(src.getId());
		tar.setStoreId(src.getStoreId());	
		tar.setStandardUnitId(src.getStandardUnitId());	
		tar.setCommodityProductUnitId(src.getCommodityProductUnitId());	
		tar.setStatus(src.getStatus());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setPlatformId(src.getPlatformId());	
		tar.setStoreName(src.getStoreName());
		tar.setCommodityProductUnitName(src.getCommodityProductUnitName());
		tar.setProductUnitSerialNumber(src.getProductUnitSerialNumber());
		return tar;
	}

	public static StoreProductUnitVO toVO(StoreProductUnitDTO src) {
		if (src == null)
		return null;	
		StoreProductUnitVO tar = new StoreProductUnitVO();
		tar.setId(src.getId());
		tar.setStoreId(src.getStoreId());	
		tar.setStandardUnitId(src.getStandardUnitId());	
		tar.setCommodityProductUnitId(src.getCommodityProductUnitId());	
		tar.setStatus(src.getStatus());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setPlatformId(src.getPlatformId());	
		tar.setStoreName(src.getStoreName());
		tar.setCommodityProductUnitName(src.getCommodityProductUnitName());
		tar.setProductUnitSerialNumber(src.getProductUnitSerialNumber());
		return tar;
	}

	public static List<StoreProductUnitDTO> toDTOs(List<StoreProductUnitVO> srcs) {
		if (srcs == null)
			return null;
		List<StoreProductUnitDTO> list = new ArrayList<StoreProductUnitDTO>();
		for (StoreProductUnitVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<StoreProductUnitVO> toVO(List<StoreProductUnitDTO> srcs) {
		if (srcs == null)
			return null;
		List<StoreProductUnitVO> list = new ArrayList<StoreProductUnitVO>();
		for (StoreProductUnitDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
}
	