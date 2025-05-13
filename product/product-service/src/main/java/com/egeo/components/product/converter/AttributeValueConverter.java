package com.egeo.components.product.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.product.dto.AttributeValueDTO;
import com.egeo.components.product.po.AttributeValuePO;
import com.egeo.components.product.vo.AttributeValueVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author xiaping
 * @date 2017-08-14 13:46:24
 */
public class AttributeValueConverter {

	
	public static AttributeValueDTO toDTO(AttributeValueVO src) {
		AttributeValueDTO tar = new AttributeValueDTO();
		tar.setId(src.getId());
		tar.setParentId(src.getParentId());	
		tar.setAttributeNameId(src.getAttributeNameId());	
		tar.setValue(src.getValue());	
		tar.setSortValue(src.getSortValue());	
		tar.setPlatformId(src.getPlatformId());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		return tar;
	}

	public static AttributeValueVO toVO(AttributeValueDTO src) {
		AttributeValueVO tar = new AttributeValueVO();
		tar.setId(src.getId());
		tar.setParentId(src.getParentId());		
		tar.setAttributeNameId(src.getAttributeNameId());		
		tar.setValue(src.getValue());		
		tar.setSortValue(src.getSortValue());		
		tar.setPlatformId(src.getPlatformId());		
		tar.setCreateTime(src.getCreateTime());		
		tar.setUpdateTime(src.getUpdateTime());		
		return tar;
	}

	public static List<AttributeValueDTO> toDTOs(List<AttributeValueVO> srcs) {
		if (srcs == null)
			return null;
		List<AttributeValueDTO> list = new ArrayList<AttributeValueDTO>();
		for (AttributeValueVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<AttributeValueVO> toVO(List<AttributeValueDTO> srcs) {
		if (srcs == null)
			return null;
		List<AttributeValueVO> list = new ArrayList<AttributeValueVO>();
		for (AttributeValueDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static AttributeValueDTO toDTO(AttributeValuePO src) {
		if (src == null) {
			return null;
		}
		AttributeValueDTO tar = new AttributeValueDTO();
		tar.setId(src.getId());
		tar.setParentId(src.getParentId());
		tar.setAttributeNameId(src.getAttributeNameId());
		tar.setValue(src.getValue());
		tar.setSortValue(src.getSortValue());
		tar.setPlatformId(src.getPlatformId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setProductAttValueId(src.getProductAttValueId());
		tar.setSpecificationCode(src.getSpecificationCode());
		tar.setPictureUrl(src.getPictureUrl());
		return tar;
	}

	public static AttributeValuePO toPO(AttributeValueDTO src) {
		if (src == null) {
			return null;
		}
		AttributeValuePO tar = new AttributeValuePO();
		tar.setId(src.getId());
		tar.setParentId(src.getParentId());
		tar.setAttributeNameId(src.getAttributeNameId());
		tar.setValue(src.getValue());
		tar.setSortValue(src.getSortValue());
		tar.setPlatformId(src.getPlatformId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setProductAttValueId(src.getProductAttValueId());
		tar.setSpecificationCode(src.getSpecificationCode());
		tar.setPictureUrl(src.getPictureUrl());
		return tar;
	}

	public static List<AttributeValueDTO> toDTO(List<AttributeValuePO> srcs) {
		if (srcs == null)
			return null;
		List<AttributeValueDTO> list = new ArrayList<AttributeValueDTO>();
		for (AttributeValuePO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<AttributeValuePO> toPO(List<AttributeValueDTO> srcs) {
		if (srcs == null)
			return null;
		List<AttributeValuePO> list = new ArrayList<AttributeValuePO>();
		for (AttributeValueDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	