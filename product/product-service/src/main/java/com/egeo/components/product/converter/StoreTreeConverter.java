package com.egeo.components.product.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.product.dto.StoreTreeDTO;
import com.egeo.components.product.po.StoreTreePO;
import com.egeo.components.product.vo.StoreTreeVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author xia
 * @date 2018-09-10 09:50:02
 */
public class StoreTreeConverter {
	
	public static StoreTreeDTO toDTO(StoreTreePO src) {
		if (src == null)
		return null;	
		StoreTreeDTO tar = new StoreTreeDTO();
		tar.setId(src.getId());
		tar.setName(src.getName());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static StoreTreePO toPO(StoreTreeDTO src) {
		if (src == null)
		return null;	
		StoreTreePO tar = new StoreTreePO();
		tar.setId(src.getId());
		tar.setName(src.getName());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static List<StoreTreeDTO> toDTO(List<StoreTreePO> srcs) {
		if (srcs == null)
			return null;
		List<StoreTreeDTO> list = new ArrayList<StoreTreeDTO>();
		for (StoreTreePO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<StoreTreePO> toPO(List<StoreTreeDTO> srcs) {
		if (srcs == null)
			return null;
		List<StoreTreePO> list = new ArrayList<StoreTreePO>();
		for (StoreTreeDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}	
	public static StoreTreeDTO toDTO(StoreTreeVO src) {
		if (src == null)
		return null;	
		StoreTreeDTO tar = new StoreTreeDTO();
		tar.setId(src.getId());
		tar.setName(src.getName());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setPlatformId(src.getPlatformId());	
		return tar;
	}

	public static StoreTreeVO toVO(StoreTreeDTO src) {
		if (src == null)
		return null;	
		StoreTreeVO tar = new StoreTreeVO();
		tar.setId(src.getId());
		tar.setName(src.getName());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setPlatformId(src.getPlatformId());	
		return tar;
	}

	public static List<StoreTreeDTO> toDTOs(List<StoreTreeVO> srcs) {
		if (srcs == null)
			return null;
		List<StoreTreeDTO> list = new ArrayList<StoreTreeDTO>();
		for (StoreTreeVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<StoreTreeVO> toVO(List<StoreTreeDTO> srcs) {
		if (srcs == null)
			return null;
		List<StoreTreeVO> list = new ArrayList<StoreTreeVO>();
		for (StoreTreeDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
}
	