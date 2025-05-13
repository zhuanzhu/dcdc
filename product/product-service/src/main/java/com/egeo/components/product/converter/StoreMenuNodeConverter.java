package com.egeo.components.product.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.product.dto.StoreMenuNodeDTO;
import com.egeo.components.product.po.StoreMenuNodePO;
import com.egeo.components.product.vo.StoreMenuNodeVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author min
 * @date 2018-09-12 13:10:47
 */
public class StoreMenuNodeConverter {
	
	public static StoreMenuNodeDTO toDTO(StoreMenuNodePO src) {
		if (src == null)
		return null;	
		StoreMenuNodeDTO tar = new StoreMenuNodeDTO();
		tar.setId(src.getId());
		tar.setParentId(src.getParentId());
		tar.setStoreMenuTreeId(src.getStoreMenuTreeId());
		tar.setName(src.getName());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		tar.setSortValue(src.getSortValue());
		tar.setDescription(src.getDescription());
		tar.setIsAll(src.getIsAll());
		return tar;
	}

	public static StoreMenuNodePO toPO(StoreMenuNodeDTO src) {
		if (src == null)
		return null;	
		StoreMenuNodePO tar = new StoreMenuNodePO();
		tar.setId(src.getId());
		tar.setParentId(src.getParentId());
		tar.setStoreMenuTreeId(src.getStoreMenuTreeId());
		tar.setName(src.getName());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		tar.setSortValue(src.getSortValue());
		tar.setDescription(src.getDescription());
		tar.setIsAll(src.getIsAll());
		return tar;
	}

	public static List<StoreMenuNodeDTO> toDTO(List<StoreMenuNodePO> srcs) {
		if (srcs == null)
			return null;
		List<StoreMenuNodeDTO> list = new ArrayList<StoreMenuNodeDTO>();
		for (StoreMenuNodePO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<StoreMenuNodePO> toPO(List<StoreMenuNodeDTO> srcs) {
		if (srcs == null)
			return null;
		List<StoreMenuNodePO> list = new ArrayList<StoreMenuNodePO>();
		for (StoreMenuNodeDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
	public static StoreMenuNodeDTO toDTO(StoreMenuNodeVO src) {
		if (src == null)
		return null;	
		StoreMenuNodeDTO tar = new StoreMenuNodeDTO();
		tar.setId(src.getId());
		tar.setParentId(src.getParentId());	
		tar.setStoreMenuTreeId(src.getStoreMenuTreeId());	
		tar.setName(src.getName());	
		tar.setPlatformId(src.getPlatformId());	
		tar.setSortValue(src.getSortValue());	
		tar.setDescription(src.getDescription());	
		tar.setIsAll(src.getIsAll());	
		tar.setStoreId(src.getStoreId());
		return tar;
	}

	public static StoreMenuNodeVO toVO(StoreMenuNodeDTO src) {
		if (src == null)
		return null;	
		StoreMenuNodeVO tar = new StoreMenuNodeVO();
		tar.setId(src.getId());
		tar.setParentId(src.getParentId());	
		tar.setStoreMenuTreeId(src.getStoreMenuTreeId());	
		tar.setName(src.getName());	
		tar.setPlatformId(src.getPlatformId());	
		tar.setSortValue(src.getSortValue());	
		tar.setDescription(src.getDescription());	
		tar.setIsAll(src.getIsAll());	
		tar.setStoreId(src.getStoreId());
		return tar;
	}

	public static List<StoreMenuNodeDTO> toDTOs(List<StoreMenuNodeVO> srcs) {
		if (srcs == null)
			return null;
		List<StoreMenuNodeDTO> list = new ArrayList<StoreMenuNodeDTO>();
		for (StoreMenuNodeVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<StoreMenuNodeVO> toVO(List<StoreMenuNodeDTO> srcs) {
		if (srcs == null)
			return null;
		List<StoreMenuNodeVO> list = new ArrayList<StoreMenuNodeVO>();
		for (StoreMenuNodeDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
}
	