package com.egeo.components.product.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.product.dto.StoreMenuNodeStandardUnitDTO;
import com.egeo.components.product.po.StoreMenuNodeStandardUnitPO;
import com.egeo.components.product.vo.StoreMenuNodeStandardUnitVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author min
 * @date 2018-09-11 14:59:35
 */
public class StoreMenuNodeStandardUnitConverter {
	
	public static StoreMenuNodeStandardUnitDTO toDTO(StoreMenuNodeStandardUnitPO src) {
		if (src == null)
		return null;	
		StoreMenuNodeStandardUnitDTO tar = new StoreMenuNodeStandardUnitDTO();
		tar.setId(src.getId());
		tar.setStoreMenuNodeId(src.getStoreMenuNodeId());
		tar.setStandardUnitId(src.getStandardUnitId());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static StoreMenuNodeStandardUnitPO toPO(StoreMenuNodeStandardUnitDTO src) {
		if (src == null)
		return null;	
		StoreMenuNodeStandardUnitPO tar = new StoreMenuNodeStandardUnitPO();
		tar.setId(src.getId());
		tar.setStoreMenuNodeId(src.getStoreMenuNodeId());
		tar.setStandardUnitId(src.getStandardUnitId());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static List<StoreMenuNodeStandardUnitDTO> toDTO(List<StoreMenuNodeStandardUnitPO> srcs) {
		if (srcs == null)
			return null;
		List<StoreMenuNodeStandardUnitDTO> list = new ArrayList<StoreMenuNodeStandardUnitDTO>();
		for (StoreMenuNodeStandardUnitPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<StoreMenuNodeStandardUnitPO> toPO(List<StoreMenuNodeStandardUnitDTO> srcs) {
		if (srcs == null)
			return null;
		List<StoreMenuNodeStandardUnitPO> list = new ArrayList<StoreMenuNodeStandardUnitPO>();
		for (StoreMenuNodeStandardUnitDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
	public static StoreMenuNodeStandardUnitDTO toDTO(StoreMenuNodeStandardUnitVO src) {
		if (src == null)
		return null;	
		StoreMenuNodeStandardUnitDTO tar = new StoreMenuNodeStandardUnitDTO();
		tar.setId(src.getId());
		tar.setStoreMenuNodeId(src.getStoreMenuNodeId());	
		tar.setStandardUnitId(src.getStandardUnitId());	
		tar.setPlatformId(src.getPlatformId());	
		return tar;
	}

	public static StoreMenuNodeStandardUnitVO toVO(StoreMenuNodeStandardUnitDTO src) {
		if (src == null)
		return null;	
		StoreMenuNodeStandardUnitVO tar = new StoreMenuNodeStandardUnitVO();
		tar.setId(src.getId());
		tar.setStoreMenuNodeId(src.getStoreMenuNodeId());	
		tar.setStandardUnitId(src.getStandardUnitId());	
		tar.setPlatformId(src.getPlatformId());	
		return tar;
	}

	public static List<StoreMenuNodeStandardUnitDTO> toDTOs(List<StoreMenuNodeStandardUnitVO> srcs) {
		if (srcs == null)
			return null;
		List<StoreMenuNodeStandardUnitDTO> list = new ArrayList<StoreMenuNodeStandardUnitDTO>();
		for (StoreMenuNodeStandardUnitVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<StoreMenuNodeStandardUnitVO> toVO(List<StoreMenuNodeStandardUnitDTO> srcs) {
		if (srcs == null)
			return null;
		List<StoreMenuNodeStandardUnitVO> list = new ArrayList<StoreMenuNodeStandardUnitVO>();
		for (StoreMenuNodeStandardUnitDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
}
	