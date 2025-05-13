package com.egeo.components.product.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.product.dto.StoreMenuTreeDTO;
import com.egeo.components.product.po.StoreMenuTreePO;
import com.egeo.components.product.vo.StoreMenuTreeVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author min
 * @date 2018-09-11 14:59:34
 */
public class StoreMenuTreeConverter {
	
	public static StoreMenuTreeDTO toDTO(StoreMenuTreePO src) {
		if (src == null)
		return null;	
		StoreMenuTreeDTO tar = new StoreMenuTreeDTO();
		tar.setId(src.getId());
		tar.setStoreId(src.getStoreId());
		tar.setType(src.getType());
		tar.setName(src.getName());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static StoreMenuTreePO toPO(StoreMenuTreeDTO src) {
		if (src == null)
		return null;	
		StoreMenuTreePO tar = new StoreMenuTreePO();
		tar.setId(src.getId());
		tar.setStoreId(src.getStoreId());
		tar.setType(src.getType());
		tar.setName(src.getName());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static List<StoreMenuTreeDTO> toDTO(List<StoreMenuTreePO> srcs) {
		if (srcs == null)
			return null;
		List<StoreMenuTreeDTO> list = new ArrayList<StoreMenuTreeDTO>();
		for (StoreMenuTreePO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<StoreMenuTreePO> toPO(List<StoreMenuTreeDTO> srcs) {
		if (srcs == null)
			return null;
		List<StoreMenuTreePO> list = new ArrayList<StoreMenuTreePO>();
		for (StoreMenuTreeDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
	public static StoreMenuTreeDTO toDTO(StoreMenuTreeVO src) {
		if (src == null)
		return null;	
		StoreMenuTreeDTO tar = new StoreMenuTreeDTO();
		tar.setId(src.getId());
		tar.setStoreId(src.getStoreId());	
		tar.setType(src.getType());	
		tar.setName(src.getName());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setPlatformId(src.getPlatformId());	
		return tar;
	}

	public static StoreMenuTreeVO toVO(StoreMenuTreeDTO src) {
		if (src == null)
		return null;	
		StoreMenuTreeVO tar = new StoreMenuTreeVO();
		tar.setId(src.getId());
		tar.setStoreId(src.getStoreId());	
		tar.setType(src.getType());	
		tar.setName(src.getName());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setPlatformId(src.getPlatformId());	
		return tar;
	}

	public static List<StoreMenuTreeDTO> toDTOs(List<StoreMenuTreeVO> srcs) {
		if (srcs == null)
			return null;
		List<StoreMenuTreeDTO> list = new ArrayList<StoreMenuTreeDTO>();
		for (StoreMenuTreeVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<StoreMenuTreeVO> toVO(List<StoreMenuTreeDTO> srcs) {
		if (srcs == null)
			return null;
		List<StoreMenuTreeVO> list = new ArrayList<StoreMenuTreeVO>();
		for (StoreMenuTreeDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
}
	