package com.egeo.components.product.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.product.dto.SerachSortDTO;
import com.egeo.components.product.po.SerachSortPO;
import com.egeo.components.product.vo.SerachSortVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author min
 * @date 2018-07-06 00:09:30
 */
public class SerachSortConverter {
	
	public static SerachSortDTO toDTO(SerachSortVO src) {
		if (src == null)
		return null;	
		SerachSortDTO tar = new SerachSortDTO();
		tar.setId(src.getId());
		tar.setSortValue(src.getSortValue());	
		tar.setRegulation(src.getRegulation());	
		tar.setDescription(src.getDescription());	
		tar.setPlatformId(src.getPlatformId());	
		return tar;
	}

	public static SerachSortVO toVO(SerachSortDTO src) {
		if (src == null)
		return null;	
		SerachSortVO tar = new SerachSortVO();
		tar.setId(src.getId());
		tar.setSortValue(src.getSortValue());	
		tar.setRegulation(src.getRegulation());	
		tar.setDescription(src.getDescription());	
		tar.setPlatformId(src.getPlatformId());	
		return tar;
	}

	public static List<SerachSortDTO> toDTOs(List<SerachSortVO> srcs) {
		if (srcs == null)
			return null;
		List<SerachSortDTO> list = new ArrayList<SerachSortDTO>();
		for (SerachSortVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<SerachSortVO> toVO(List<SerachSortDTO> srcs) {
		if (srcs == null)
			return null;
		List<SerachSortVO> list = new ArrayList<SerachSortVO>();
		for (SerachSortDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static SerachSortDTO toDTO(SerachSortPO src) {
		if (src == null)
		return null;	
		SerachSortDTO tar = new SerachSortDTO();
		tar.setId(src.getId());
		tar.setSortValue(src.getSortValue());
		tar.setRegulation(src.getRegulation());
		tar.setDescription(src.getDescription());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static SerachSortPO toPO(SerachSortDTO src) {
		if (src == null)
		return null;	
		SerachSortPO tar = new SerachSortPO();
		tar.setId(src.getId());
		tar.setSortValue(src.getSortValue());
		tar.setRegulation(src.getRegulation());
		tar.setDescription(src.getDescription());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static List<SerachSortDTO> toDTO(List<SerachSortPO> srcs) {
		if (srcs == null)
			return null;
		List<SerachSortDTO> list = new ArrayList<SerachSortDTO>();
		for (SerachSortPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<SerachSortPO> toPO(List<SerachSortDTO> srcs) {
		if (srcs == null)
			return null;
		List<SerachSortPO> list = new ArrayList<SerachSortPO>();
		for (SerachSortDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	