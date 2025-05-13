package com.egeo.components.product.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.product.dto.StandardProductUnitAttValueDTO;
import com.egeo.components.product.po.StandardProductUnitAttValuePO;
import com.egeo.components.product.vo.StandardProductUnitAttValueVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author min
 * @date 2018-01-06 13:00:30
 */
public class StandardProductUnitAttValueConverter {
	
	public static StandardProductUnitAttValueDTO toDTO(StandardProductUnitAttValueVO src) {
		if (src == null)
		return null;	
		StandardProductUnitAttValueDTO tar = new StandardProductUnitAttValueDTO();
		tar.setId(src.getId());
		tar.setStandardProductUnitAttNameId(src.getStandardProductUnitAttNameId());	
		tar.setAttValueId(src.getAttValueId());	
		tar.setSpecificationCode(src.getSpecificationCode());	
		tar.setAttValueCustom(src.getAttValueCustom());	
		tar.setSortValue(src.getSortValue());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setPlatformId(src.getPlatformId());	
		return tar;
	}

	public static StandardProductUnitAttValueVO toVO(StandardProductUnitAttValueDTO src) {
		if (src == null)
		return null;	
		StandardProductUnitAttValueVO tar = new StandardProductUnitAttValueVO();
		tar.setId(src.getId());
		tar.setStandardProductUnitAttNameId(src.getStandardProductUnitAttNameId());	
		tar.setAttValueId(src.getAttValueId());	
		tar.setSpecificationCode(src.getSpecificationCode());	
		tar.setAttValueCustom(src.getAttValueCustom());	
		tar.setSortValue(src.getSortValue());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setPlatformId(src.getPlatformId());	
		return tar;
	}

	public static List<StandardProductUnitAttValueDTO> toDTOs(List<StandardProductUnitAttValueVO> srcs) {
		if (srcs == null)
			return null;
		List<StandardProductUnitAttValueDTO> list = new ArrayList<StandardProductUnitAttValueDTO>();
		for (StandardProductUnitAttValueVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<StandardProductUnitAttValueVO> toVO(List<StandardProductUnitAttValueDTO> srcs) {
		if (srcs == null)
			return null;
		List<StandardProductUnitAttValueVO> list = new ArrayList<StandardProductUnitAttValueVO>();
		for (StandardProductUnitAttValueDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static StandardProductUnitAttValueDTO toDTO(StandardProductUnitAttValuePO src) {
		if (src == null)
		return null;	
		StandardProductUnitAttValueDTO tar = new StandardProductUnitAttValueDTO();
		tar.setId(src.getId());
		tar.setStandardProductUnitAttNameId(src.getStandardProductUnitAttNameId());
		tar.setAttValueId(src.getAttValueId());
		tar.setSpecificationCode(src.getSpecificationCode());
		tar.setAttValueCustom(src.getAttValueCustom());
		tar.setSortValue(src.getSortValue());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		tar.setPictureUrl(src.getPictureUrl());
		return tar;
	}

	public static StandardProductUnitAttValuePO toPO(StandardProductUnitAttValueDTO src) {
		if (src == null)
		return null;	
		StandardProductUnitAttValuePO tar = new StandardProductUnitAttValuePO();
		tar.setId(src.getId());
		tar.setStandardProductUnitAttNameId(src.getStandardProductUnitAttNameId());
		tar.setAttValueId(src.getAttValueId());
		tar.setSpecificationCode(src.getSpecificationCode());
		tar.setAttValueCustom(src.getAttValueCustom());
		tar.setSortValue(src.getSortValue());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		tar.setPictureUrl(src.getPictureUrl());
		return tar;
	}

	public static List<StandardProductUnitAttValueDTO> toDTO(List<StandardProductUnitAttValuePO> srcs) {
		if (srcs == null)
			return null;
		List<StandardProductUnitAttValueDTO> list = new ArrayList<StandardProductUnitAttValueDTO>();
		for (StandardProductUnitAttValuePO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<StandardProductUnitAttValuePO> toPO(List<StandardProductUnitAttValueDTO> srcs) {
		if (srcs == null)
			return null;
		List<StandardProductUnitAttValuePO> list = new ArrayList<StandardProductUnitAttValuePO>();
		for (StandardProductUnitAttValueDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	