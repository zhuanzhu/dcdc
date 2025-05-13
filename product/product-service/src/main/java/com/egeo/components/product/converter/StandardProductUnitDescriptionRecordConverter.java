package com.egeo.components.product.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.product.dto.StandardProductUnitDescriptionRecordDTO;
import com.egeo.components.product.po.StandardProductUnitDescriptionRecordPO;
import com.egeo.components.product.vo.StandardProductUnitDescriptionRecordVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author min
 * @date 2018-01-05 19:55:46
 */
public class StandardProductUnitDescriptionRecordConverter {
	
	public static StandardProductUnitDescriptionRecordDTO toDTO(StandardProductUnitDescriptionRecordVO src) {
		if (src == null)
		return null;	
		StandardProductUnitDescriptionRecordDTO tar = new StandardProductUnitDescriptionRecordDTO();
		tar.setId(src.getId());
		tar.setStandardProductUnitRecordId(src.getStandardProductUnitRecordId());	
		tar.setContent(src.getContent());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setPlatformId(src.getPlatformId());	
		return tar;
	}

	public static StandardProductUnitDescriptionRecordVO toVO(StandardProductUnitDescriptionRecordDTO src) {
		if (src == null)
		return null;	
		StandardProductUnitDescriptionRecordVO tar = new StandardProductUnitDescriptionRecordVO();
		tar.setId(src.getId());
		tar.setStandardProductUnitRecordId(src.getStandardProductUnitRecordId());	
		tar.setContent(src.getContent());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setPlatformId(src.getPlatformId());	
		return tar;
	}

	public static List<StandardProductUnitDescriptionRecordDTO> toDTOs(List<StandardProductUnitDescriptionRecordVO> srcs) {
		if (srcs == null)
			return null;
		List<StandardProductUnitDescriptionRecordDTO> list = new ArrayList<StandardProductUnitDescriptionRecordDTO>();
		for (StandardProductUnitDescriptionRecordVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<StandardProductUnitDescriptionRecordVO> toVO(List<StandardProductUnitDescriptionRecordDTO> srcs) {
		if (srcs == null)
			return null;
		List<StandardProductUnitDescriptionRecordVO> list = new ArrayList<StandardProductUnitDescriptionRecordVO>();
		for (StandardProductUnitDescriptionRecordDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static StandardProductUnitDescriptionRecordDTO toDTO(StandardProductUnitDescriptionRecordPO src) {
		if (src == null)
		return null;	
		StandardProductUnitDescriptionRecordDTO tar = new StandardProductUnitDescriptionRecordDTO();
		tar.setId(src.getId());
		tar.setStandardProductUnitRecordId(src.getStandardProductUnitRecordId());
		tar.setContent(src.getContent());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static StandardProductUnitDescriptionRecordPO toPO(StandardProductUnitDescriptionRecordDTO src) {
		if (src == null)
		return null;	
		StandardProductUnitDescriptionRecordPO tar = new StandardProductUnitDescriptionRecordPO();
		tar.setId(src.getId());
		tar.setStandardProductUnitRecordId(src.getStandardProductUnitRecordId());
		tar.setContent(src.getContent());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static List<StandardProductUnitDescriptionRecordDTO> toDTO(List<StandardProductUnitDescriptionRecordPO> srcs) {
		if (srcs == null)
			return null;
		List<StandardProductUnitDescriptionRecordDTO> list = new ArrayList<StandardProductUnitDescriptionRecordDTO>();
		for (StandardProductUnitDescriptionRecordPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<StandardProductUnitDescriptionRecordPO> toPO(List<StandardProductUnitDescriptionRecordDTO> srcs) {
		if (srcs == null)
			return null;
		List<StandardProductUnitDescriptionRecordPO> list = new ArrayList<StandardProductUnitDescriptionRecordPO>();
		for (StandardProductUnitDescriptionRecordDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	