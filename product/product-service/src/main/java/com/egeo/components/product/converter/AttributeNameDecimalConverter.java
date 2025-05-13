package com.egeo.components.product.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.product.dto.AttributeNameDecimalDTO;
import com.egeo.components.product.po.AttributeNameDecimalPO;
import com.egeo.components.product.vo.AttributeNameDecimalVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author min
 * @date 2018-01-03 20:15:50
 */
public class AttributeNameDecimalConverter {
	
	
	public static AttributeNameDecimalDTO toDTO(AttributeNameDecimalVO src) {
		if (src == null)
		return null;	
		AttributeNameDecimalDTO tar = new AttributeNameDecimalDTO();
		tar.setId(src.getId());
		tar.setAttributeNameId(src.getAttributeNameId());	
		tar.setBeginDecimal(src.getBeginDecimal());	
		tar.setFinishDecimal(src.getFinishDecimal());	
		tar.setUnit(src.getUnit());	
		return tar;
	}

	public static AttributeNameDecimalVO toVO(AttributeNameDecimalDTO src) {
		if (src == null)
		return null;	
		AttributeNameDecimalVO tar = new AttributeNameDecimalVO();
		tar.setId(src.getId());
		tar.setAttributeNameId(src.getAttributeNameId());	
		tar.setBeginDecimal(src.getBeginDecimal());	
		tar.setFinishDecimal(src.getFinishDecimal());	
		tar.setUnit(src.getUnit());	
		return tar;
	}

	public static List<AttributeNameDecimalDTO> toDTOs(List<AttributeNameDecimalVO> srcs) {
		if (srcs == null)
			return null;
		List<AttributeNameDecimalDTO> list = new ArrayList<AttributeNameDecimalDTO>();
		for (AttributeNameDecimalVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<AttributeNameDecimalVO> toVO(List<AttributeNameDecimalDTO> srcs) {
		if (srcs == null)
			return null;
		List<AttributeNameDecimalVO> list = new ArrayList<AttributeNameDecimalVO>();
		for (AttributeNameDecimalDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static AttributeNameDecimalDTO toDTO(AttributeNameDecimalPO src) {
		if (src == null)
		return null;	
		AttributeNameDecimalDTO tar = new AttributeNameDecimalDTO();
		tar.setId(src.getId());
		tar.setAttributeNameId(src.getAttributeNameId());
		tar.setBeginDecimal(src.getBeginDecimal());
		tar.setFinishDecimal(src.getFinishDecimal());
		tar.setUnit(src.getUnit());
		tar.setImportHint(src.getImportHint());
		return tar;
	}

	public static AttributeNameDecimalPO toPO(AttributeNameDecimalDTO src) {
		if (src == null)
		return null;	
		AttributeNameDecimalPO tar = new AttributeNameDecimalPO();
		tar.setId(src.getId());
		tar.setAttributeNameId(src.getAttributeNameId());
		tar.setBeginDecimal(src.getBeginDecimal());
		tar.setFinishDecimal(src.getFinishDecimal());
		tar.setUnit(src.getUnit());
		tar.setImportHint(src.getImportHint());
		return tar;
	}

	public static List<AttributeNameDecimalDTO> toDTO(List<AttributeNameDecimalPO> srcs) {
		if (srcs == null)
			return null;
		List<AttributeNameDecimalDTO> list = new ArrayList<AttributeNameDecimalDTO>();
		for (AttributeNameDecimalPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<AttributeNameDecimalPO> toPO(List<AttributeNameDecimalDTO> srcs) {
		if (srcs == null)
			return null;
		List<AttributeNameDecimalPO> list = new ArrayList<AttributeNameDecimalPO>();
		for (AttributeNameDecimalDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	