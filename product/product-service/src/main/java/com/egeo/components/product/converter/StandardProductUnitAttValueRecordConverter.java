package com.egeo.components.product.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.product.dto.StandardProductUnitAttValueRecordDTO;
import com.egeo.components.product.po.StandardProductUnitAttValueRecordPO;
import com.egeo.components.product.vo.StandardProductUnitAttValueRecordVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author min
 * @date 2018-01-05 19:55:46
 */
public class StandardProductUnitAttValueRecordConverter {
	
	public static StandardProductUnitAttValueRecordDTO toDTO(StandardProductUnitAttValueRecordVO src) {
		if (src == null)
		return null;	
		StandardProductUnitAttValueRecordDTO tar = new StandardProductUnitAttValueRecordDTO();
		tar.setId(src.getId());
		tar.setStandardProductUnitAttNameRecordId(src.getStandardProductUnitAttNameRecordId());	
		tar.setAttValueId(src.getAttValueId());	
		tar.setAttValueCustom(src.getAttValueCustom());	
		tar.setSortValue(src.getSortValue());	
		tar.setPictureUrl(src.getPictureUrl());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setPlatformId(src.getPlatformId());	
		return tar;
	}

	public static StandardProductUnitAttValueRecordVO toVO(StandardProductUnitAttValueRecordDTO src) {
		if (src == null)
		return null;	
		StandardProductUnitAttValueRecordVO tar = new StandardProductUnitAttValueRecordVO();
		tar.setId(src.getId());
		tar.setStandardProductUnitAttNameRecordId(src.getStandardProductUnitAttNameRecordId());	
		tar.setAttValueId(src.getAttValueId());	
		tar.setAttValueCustom(src.getAttValueCustom());	
		tar.setSortValue(src.getSortValue());	
		tar.setPictureUrl(src.getPictureUrl());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setPlatformId(src.getPlatformId());	
		return tar;
	}

	public static List<StandardProductUnitAttValueRecordDTO> toDTOs(List<StandardProductUnitAttValueRecordVO> srcs) {
		if (srcs == null)
			return null;
		List<StandardProductUnitAttValueRecordDTO> list = new ArrayList<StandardProductUnitAttValueRecordDTO>();
		for (StandardProductUnitAttValueRecordVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<StandardProductUnitAttValueRecordVO> toVO(List<StandardProductUnitAttValueRecordDTO> srcs) {
		if (srcs == null)
			return null;
		List<StandardProductUnitAttValueRecordVO> list = new ArrayList<StandardProductUnitAttValueRecordVO>();
		for (StandardProductUnitAttValueRecordDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static StandardProductUnitAttValueRecordDTO toDTO(StandardProductUnitAttValueRecordPO src) {
		if (src == null)
		return null;	
		StandardProductUnitAttValueRecordDTO tar = new StandardProductUnitAttValueRecordDTO();
		tar.setId(src.getId());
		tar.setStandardProductUnitAttNameRecordId(src.getStandardProductUnitAttNameRecordId());
		tar.setAttValueId(src.getAttValueId());
		tar.setAttValueCustom(src.getAttValueCustom());
		tar.setSortValue(src.getSortValue());
		tar.setPictureUrl(src.getPictureUrl());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static StandardProductUnitAttValueRecordPO toPO(StandardProductUnitAttValueRecordDTO src) {
		if (src == null)
		return null;	
		StandardProductUnitAttValueRecordPO tar = new StandardProductUnitAttValueRecordPO();
		tar.setId(src.getId());
		tar.setStandardProductUnitAttNameRecordId(src.getStandardProductUnitAttNameRecordId());
		tar.setAttValueId(src.getAttValueId());
		tar.setAttValueCustom(src.getAttValueCustom());
		tar.setSortValue(src.getSortValue());
		tar.setPictureUrl(src.getPictureUrl());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static List<StandardProductUnitAttValueRecordDTO> toDTO(List<StandardProductUnitAttValueRecordPO> srcs) {
		if (srcs == null)
			return null;
		List<StandardProductUnitAttValueRecordDTO> list = new ArrayList<StandardProductUnitAttValueRecordDTO>();
		for (StandardProductUnitAttValueRecordPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<StandardProductUnitAttValueRecordPO> toPO(List<StandardProductUnitAttValueRecordDTO> srcs) {
		if (srcs == null)
			return null;
		List<StandardProductUnitAttValueRecordPO> list = new ArrayList<StandardProductUnitAttValueRecordPO>();
		for (StandardProductUnitAttValueRecordDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	