package com.egeo.components.product.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.product.dto.AttributeNameDTO;
import com.egeo.components.product.po.AttributeNamePO;
import com.egeo.components.product.vo.AttributeNameVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author xiaping
 * @date 2017-08-14 13:46:24
 */
public class AttributeNameConverter {

	public static AttributeNameDTO toDTO(AttributeNameVO src) {
		AttributeNameDTO tar = new AttributeNameDTO();
		tar.setId(src.getId());
		tar.setName(src.getName());	
		tar.setSpecificationProperty(src.getSpecificationProperty());	
		tar.setParameterProperty(src.getParameterProperty());
		tar.setPlatformId(src.getPlatformId());	
		tar.setMode(src.getMode());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setUnit(src.getUnit());
		tar.setImportHint(src.getImportHint());
		tar.setIsRequired(src.getIsRequired());
		tar.setReminder(src.getReminder());
		tar.setCategoryId(src.getCategoryId());
		return tar;
	}

	public static AttributeNameVO toVO(AttributeNameDTO src) {
		AttributeNameVO tar = new AttributeNameVO();
		tar.setId(src.getId());
		tar.setName(src.getName());		
		tar.setSpecificationProperty(src.getSpecificationProperty());	
		tar.setParameterProperty(src.getParameterProperty());	
		tar.setPlatformId(src.getPlatformId());		
		tar.setMode(src.getMode());		
		tar.setCreateTime(src.getCreateTime());		
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setUnit(src.getUnit());
		tar.setImportHint(src.getImportHint());
		tar.setCategoryId(src.getCategoryId());
		tar.setIsRequired(src.getIsRequired());
		tar.setReminder(src.getReminder());
		return tar;
	}

	public static List<AttributeNameDTO> toDTOs(List<AttributeNameVO> srcs) {
		if (srcs == null)
			return null;
		List<AttributeNameDTO> list = new ArrayList<AttributeNameDTO>();
		for (AttributeNameVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<AttributeNameVO> toVO(List<AttributeNameDTO> srcs) {
		if (srcs == null)
			return null;
		List<AttributeNameVO> list = new ArrayList<AttributeNameVO>();
		for (AttributeNameDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static AttributeNameDTO toDTO(AttributeNamePO src) {
		if(src == null){
			return null;
		}
		AttributeNameDTO tar = new AttributeNameDTO();
		tar.setId(src.getId());
		tar.setName(src.getName());
		tar.setSpecificationProperty(src.getSpecificationProperty());
		tar.setParameterProperty(src.getParameterProperty());
		tar.setPlatformId(src.getPlatformId());
		tar.setMode(src.getMode());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setBeginDecimal(src.getBeginDecimal());
		tar.setFinishDecimal(src.getFinishDecimal());
		tar.setUnit(src.getUnit());
		tar.setImportHint(src.getImportHint());
		return tar;
	}

	public static AttributeNamePO toPO(AttributeNameDTO src) {
		if(src == null){
			return null;
		}
		AttributeNamePO tar = new AttributeNamePO();
		tar.setId(src.getId());
		tar.setName(src.getName());
		tar.setSpecificationProperty(src.getSpecificationProperty());
		tar.setParameterProperty(src.getParameterProperty());
		tar.setPlatformId(src.getPlatformId());
		tar.setMode(src.getMode());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setBeginDecimal(src.getBeginDecimal());
		tar.setFinishDecimal(src.getFinishDecimal());
		tar.setUnit(src.getUnit());
		tar.setImportHint(src.getImportHint());
		return tar;
	}

	public static List<AttributeNameDTO> toDTO(List<AttributeNamePO> srcs) {
		if (srcs == null)
			return null;
		List<AttributeNameDTO> list = new ArrayList<AttributeNameDTO>();
		for (AttributeNamePO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<AttributeNamePO> toPO(List<AttributeNameDTO> srcs) {
		if (srcs == null)
			return null;
		List<AttributeNamePO> list = new ArrayList<AttributeNamePO>();
		for (AttributeNameDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	