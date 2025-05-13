package com.egeo.components.product.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.product.dto.SkuAttNameDTO;
import com.egeo.components.product.po.SkuAttNamePO;
import com.egeo.components.product.vo.SkuAttNameVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author min
 * @date 2018-01-06 09:08:12
 */
public class SkuAttNameConverter {
	
	public static SkuAttNameDTO toDTO(SkuAttNameVO src) {
		if (src == null)
		return null;	
		SkuAttNameDTO tar = new SkuAttNameDTO();
		tar.setId(src.getId());
		tar.setParentId(src.getParentId());	
		tar.setSkuId(src.getSkuId());	
		tar.setAttNameId(src.getAttNameId());	
		tar.setSortValue(src.getSortValue());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setPlatformId(src.getPlatformId());	
		return tar;
	}

	public static SkuAttNameVO toVO(SkuAttNameDTO src) {
		if (src == null)
		return null;	
		SkuAttNameVO tar = new SkuAttNameVO();
		tar.setId(src.getId());
		tar.setParentId(src.getParentId());	
		tar.setSkuId(src.getSkuId());	
		tar.setAttNameId(src.getAttNameId());	
		tar.setSortValue(src.getSortValue());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setPlatformId(src.getPlatformId());	
		return tar;
	}

	public static List<SkuAttNameDTO> toDTOs(List<SkuAttNameVO> srcs) {
		if (srcs == null)
			return null;
		List<SkuAttNameDTO> list = new ArrayList<SkuAttNameDTO>();
		for (SkuAttNameVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<SkuAttNameVO> toVO(List<SkuAttNameDTO> srcs) {
		if (srcs == null)
			return null;
		List<SkuAttNameVO> list = new ArrayList<SkuAttNameVO>();
		for (SkuAttNameDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static SkuAttNameDTO toDTO(SkuAttNamePO src) {
		if (src == null)
		return null;	
		SkuAttNameDTO tar = new SkuAttNameDTO();
		tar.setId(src.getId());
		tar.setParentId(src.getParentId());
		tar.setSkuId(src.getSkuId());
		tar.setAttNameId(src.getAttNameId());
		tar.setSortValue(src.getSortValue());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static SkuAttNamePO toPO(SkuAttNameDTO src) {
		if (src == null)
		return null;	
		SkuAttNamePO tar = new SkuAttNamePO();
		tar.setId(src.getId());
		tar.setParentId(src.getParentId());
		tar.setSkuId(src.getSkuId());
		tar.setAttNameId(src.getAttNameId());
		tar.setSortValue(src.getSortValue());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static List<SkuAttNameDTO> toDTO(List<SkuAttNamePO> srcs) {
		if (srcs == null)
			return null;
		List<SkuAttNameDTO> list = new ArrayList<SkuAttNameDTO>();
		for (SkuAttNamePO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<SkuAttNamePO> toPO(List<SkuAttNameDTO> srcs) {
		if (srcs == null)
			return null;
		List<SkuAttNamePO> list = new ArrayList<SkuAttNamePO>();
		for (SkuAttNameDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	