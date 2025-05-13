package com.egeo.components.product.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.product.dto.StandardProductUnitAttNameDTO;
import com.egeo.components.product.po.StandardProductUnitAttNamePO;
import com.egeo.components.product.vo.StandardProductUnitAttNameVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author min
 * @date 2018-01-05 18:51:02
 */
public class StandardProductUnitAttNameConverter {
	
	public static StandardProductUnitAttNameDTO toDTO(StandardProductUnitAttNameVO src) {
		if (src == null)
		return null;	
		StandardProductUnitAttNameDTO tar = new StandardProductUnitAttNameDTO();
		tar.setId(src.getId());
		tar.setParentId(src.getParentId());	
		tar.setStandardProductUnitId(src.getStandardProductUnitId());	
		tar.setAttNameId(src.getAttNameId());	
		tar.setSortValue(src.getSortValue());	
		tar.setType(src.getType());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setPlatformId(src.getPlatformId());	
		return tar;
	}

	public static StandardProductUnitAttNameVO toVO(StandardProductUnitAttNameDTO src) {
		if (src == null)
		return null;	
		StandardProductUnitAttNameVO tar = new StandardProductUnitAttNameVO();
		tar.setId(src.getId());
		tar.setParentId(src.getParentId());	
		tar.setStandardProductUnitId(src.getStandardProductUnitId());	
		tar.setAttNameId(src.getAttNameId());	
		tar.setSortValue(src.getSortValue());	
		tar.setType(src.getType());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setPlatformId(src.getPlatformId());	
		return tar;
	}

	public static List<StandardProductUnitAttNameDTO> toDTOs(List<StandardProductUnitAttNameVO> srcs) {
		if (srcs == null)
			return null;
		List<StandardProductUnitAttNameDTO> list = new ArrayList<StandardProductUnitAttNameDTO>();
		for (StandardProductUnitAttNameVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<StandardProductUnitAttNameVO> toVO(List<StandardProductUnitAttNameDTO> srcs) {
		if (srcs == null)
			return null;
		List<StandardProductUnitAttNameVO> list = new ArrayList<StandardProductUnitAttNameVO>();
		for (StandardProductUnitAttNameDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static StandardProductUnitAttNameDTO toDTO(StandardProductUnitAttNamePO src) {
		if (src == null)
		return null;	
		StandardProductUnitAttNameDTO tar = new StandardProductUnitAttNameDTO();
		tar.setId(src.getId());
		tar.setParentId(src.getParentId());
		tar.setStandardProductUnitId(src.getStandardProductUnitId());
		tar.setAttNameId(src.getAttNameId());
		tar.setSortValue(src.getSortValue());
		tar.setType(src.getType());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		tar.setShowPicture(src.getShowPicture());
		return tar;
	}

	public static StandardProductUnitAttNamePO toPO(StandardProductUnitAttNameDTO src) {
		if (src == null)
		return null;	
		StandardProductUnitAttNamePO tar = new StandardProductUnitAttNamePO();
		tar.setId(src.getId());
		tar.setParentId(src.getParentId());
		tar.setStandardProductUnitId(src.getStandardProductUnitId());
		tar.setAttNameId(src.getAttNameId());
		tar.setSortValue(src.getSortValue());
		tar.setType(src.getType());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		tar.setShowPicture(src.getShowPicture());
		return tar;
	}

	public static List<StandardProductUnitAttNameDTO> toDTO(List<StandardProductUnitAttNamePO> srcs) {
		if (srcs == null)
			return null;
		List<StandardProductUnitAttNameDTO> list = new ArrayList<StandardProductUnitAttNameDTO>();
		for (StandardProductUnitAttNamePO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<StandardProductUnitAttNamePO> toPO(List<StandardProductUnitAttNameDTO> srcs) {
		if (srcs == null)
			return null;
		List<StandardProductUnitAttNamePO> list = new ArrayList<StandardProductUnitAttNamePO>();
		for (StandardProductUnitAttNameDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	