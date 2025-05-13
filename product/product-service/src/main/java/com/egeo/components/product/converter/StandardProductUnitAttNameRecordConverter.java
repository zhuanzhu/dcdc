package com.egeo.components.product.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.product.dto.StandardProductUnitAttNameRecordDTO;
import com.egeo.components.product.po.StandardProductUnitAttNameRecordPO;
import com.egeo.components.product.vo.StandardProductUnitAttNameRecordVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author min
 * @date 2018-01-05 19:55:45
 */
public class StandardProductUnitAttNameRecordConverter {
	
	public static StandardProductUnitAttNameRecordDTO toDTO(StandardProductUnitAttNameRecordVO src) {
		if (src == null)
		return null;	
		StandardProductUnitAttNameRecordDTO tar = new StandardProductUnitAttNameRecordDTO();
		tar.setId(src.getId());
		tar.setParentId(src.getParentId());	
		tar.setStandardProductUnitRecordId(src.getStandardProductUnitRecordId());	
		tar.setAttNameId(src.getAttNameId());	
		tar.setSortValue(src.getSortValue());	
		tar.setType(src.getType());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setPlatformId(src.getPlatformId());	
		return tar;
	}

	public static StandardProductUnitAttNameRecordVO toVO(StandardProductUnitAttNameRecordDTO src) {
		if (src == null)
		return null;	
		StandardProductUnitAttNameRecordVO tar = new StandardProductUnitAttNameRecordVO();
		tar.setId(src.getId());
		tar.setParentId(src.getParentId());	
		tar.setStandardProductUnitRecordId(src.getStandardProductUnitRecordId());	
		tar.setAttNameId(src.getAttNameId());	
		tar.setSortValue(src.getSortValue());	
		tar.setType(src.getType());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setPlatformId(src.getPlatformId());	
		return tar;
	}

	public static List<StandardProductUnitAttNameRecordDTO> toDTOs(List<StandardProductUnitAttNameRecordVO> srcs) {
		if (srcs == null)
			return null;
		List<StandardProductUnitAttNameRecordDTO> list = new ArrayList<StandardProductUnitAttNameRecordDTO>();
		for (StandardProductUnitAttNameRecordVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<StandardProductUnitAttNameRecordVO> toVO(List<StandardProductUnitAttNameRecordDTO> srcs) {
		if (srcs == null)
			return null;
		List<StandardProductUnitAttNameRecordVO> list = new ArrayList<StandardProductUnitAttNameRecordVO>();
		for (StandardProductUnitAttNameRecordDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static StandardProductUnitAttNameRecordDTO toDTO(StandardProductUnitAttNameRecordPO src) {
		if (src == null)
		return null;	
		StandardProductUnitAttNameRecordDTO tar = new StandardProductUnitAttNameRecordDTO();
		tar.setId(src.getId());
		tar.setParentId(src.getParentId());
		tar.setStandardProductUnitRecordId(src.getStandardProductUnitRecordId());
		tar.setAttNameId(src.getAttNameId());
		tar.setSortValue(src.getSortValue());
		tar.setType(src.getType());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static StandardProductUnitAttNameRecordPO toPO(StandardProductUnitAttNameRecordDTO src) {
		if (src == null)
		return null;	
		StandardProductUnitAttNameRecordPO tar = new StandardProductUnitAttNameRecordPO();
		tar.setId(src.getId());
		tar.setParentId(src.getParentId());
		tar.setStandardProductUnitRecordId(src.getStandardProductUnitRecordId());
		tar.setAttNameId(src.getAttNameId());
		tar.setSortValue(src.getSortValue());
		tar.setType(src.getType());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static List<StandardProductUnitAttNameRecordDTO> toDTO(List<StandardProductUnitAttNameRecordPO> srcs) {
		if (srcs == null)
			return null;
		List<StandardProductUnitAttNameRecordDTO> list = new ArrayList<StandardProductUnitAttNameRecordDTO>();
		for (StandardProductUnitAttNameRecordPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<StandardProductUnitAttNameRecordPO> toPO(List<StandardProductUnitAttNameRecordDTO> srcs) {
		if (srcs == null)
			return null;
		List<StandardProductUnitAttNameRecordPO> list = new ArrayList<StandardProductUnitAttNameRecordPO>();
		for (StandardProductUnitAttNameRecordDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	