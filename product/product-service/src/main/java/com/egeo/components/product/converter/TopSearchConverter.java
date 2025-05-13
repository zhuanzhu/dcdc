package com.egeo.components.product.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.product.dto.TopSearchDTO;
import com.egeo.components.product.po.TopSearchPO;
import com.egeo.components.product.vo.TopSearchVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author min
 * @date 2018-07-04 13:14:04
 */
public class TopSearchConverter {
	
	public static TopSearchDTO toDTO(TopSearchPO src) {
		if (src == null)
		return null;	
		TopSearchDTO tar = new TopSearchDTO();
		tar.setId(src.getId());
		tar.setName(src.getName());
		tar.setSortValue(src.getSortValue());
		tar.setIsStart(src.getIsStart());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static TopSearchPO toPO(TopSearchDTO src) {
		if (src == null)
		return null;	
		TopSearchPO tar = new TopSearchPO();
		tar.setId(src.getId());
		tar.setName(src.getName());
		tar.setSortValue(src.getSortValue());
		tar.setIsStart(src.getIsStart());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static List<TopSearchDTO> toDTO(List<TopSearchPO> srcs) {
		if (srcs == null)
			return null;
		List<TopSearchDTO> list = new ArrayList<TopSearchDTO>();
		for (TopSearchPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<TopSearchPO> toPO(List<TopSearchDTO> srcs) {
		if (srcs == null)
			return null;
		List<TopSearchPO> list = new ArrayList<TopSearchPO>();
		for (TopSearchDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
	public static TopSearchDTO toDTO(TopSearchVO src) {
		if (src == null)
		return null;	
		TopSearchDTO tar = new TopSearchDTO();
		tar.setId(src.getId());
		tar.setName(src.getName());	
		tar.setSortValue(src.getSortValue());	
		tar.setIsStart(src.getIsStart());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setPlatformId(src.getPlatformId());	
		return tar;
	}

	public static TopSearchVO toVO(TopSearchDTO src) {
		if (src == null)
		return null;	
		TopSearchVO tar = new TopSearchVO();
		tar.setId(src.getId());
		tar.setName(src.getName());	
		tar.setSortValue(src.getSortValue());	
		tar.setIsStart(src.getIsStart());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setPlatformId(src.getPlatformId());	
		return tar;
	}

	public static List<TopSearchDTO> toDTOs(List<TopSearchVO> srcs) {
		if (srcs == null)
			return null;
		List<TopSearchDTO> list = new ArrayList<TopSearchDTO>();
		for (TopSearchVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<TopSearchVO> toVO(List<TopSearchDTO> srcs) {
		if (srcs == null)
			return null;
		List<TopSearchVO> list = new ArrayList<TopSearchVO>();
		for (TopSearchDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
}
	