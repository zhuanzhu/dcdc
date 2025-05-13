package com.egeo.components.product.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.product.dto.StandardUnitCombinationCategoryDTO;
import com.egeo.components.product.po.StandardUnitCombinationCategoryPO;
import com.egeo.components.product.vo.StandardUnitCombinationCategoryVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author min
 * @date 2018-04-06 16:03:34
 */
public class StandardUnitCombinationCategoryConverter {
	
	public static StandardUnitCombinationCategoryDTO toDTO(StandardUnitCombinationCategoryPO src) {
		if (src == null)
		return null;	
		StandardUnitCombinationCategoryDTO tar = new StandardUnitCombinationCategoryDTO();
		tar.setId(src.getId());
		tar.setStandardUnitCombinationId(src.getStandardUnitCombinationId());
		tar.setCategoryTreeNodeId(src.getCategoryTreeNodeId());
		tar.setSortValue(src.getSortValue());
		tar.setType(src.getType());
		return tar;
	}

	public static StandardUnitCombinationCategoryPO toPO(StandardUnitCombinationCategoryDTO src) {
		if (src == null)
		return null;	
		StandardUnitCombinationCategoryPO tar = new StandardUnitCombinationCategoryPO();
		tar.setId(src.getId());
		tar.setStandardUnitCombinationId(src.getStandardUnitCombinationId());
		tar.setCategoryTreeNodeId(src.getCategoryTreeNodeId());
		tar.setSortValue(src.getSortValue());
		tar.setType(src.getType());
		return tar;
	}

	public static List<StandardUnitCombinationCategoryDTO> toDTO(List<StandardUnitCombinationCategoryPO> srcs) {
		if (srcs == null)
			return null;
		List<StandardUnitCombinationCategoryDTO> list = new ArrayList<StandardUnitCombinationCategoryDTO>();
		for (StandardUnitCombinationCategoryPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<StandardUnitCombinationCategoryPO> toPO(List<StandardUnitCombinationCategoryDTO> srcs) {
		if (srcs == null)
			return null;
		List<StandardUnitCombinationCategoryPO> list = new ArrayList<StandardUnitCombinationCategoryPO>();
		for (StandardUnitCombinationCategoryDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
	public static StandardUnitCombinationCategoryDTO toDTO(StandardUnitCombinationCategoryVO src) {
		if (src == null)
		return null;	
		StandardUnitCombinationCategoryDTO tar = new StandardUnitCombinationCategoryDTO();
		tar.setId(src.getId());
		tar.setStandardUnitCombinationId(src.getStandardUnitCombinationId());	
		tar.setCategoryTreeNodeId(src.getCategoryTreeNodeId());	
		tar.setSortValue(src.getSortValue());	
		return tar;
	}

	public static StandardUnitCombinationCategoryVO toVO(StandardUnitCombinationCategoryDTO src) {
		if (src == null)
		return null;	
		StandardUnitCombinationCategoryVO tar = new StandardUnitCombinationCategoryVO();
		tar.setId(src.getId());
		tar.setStandardUnitCombinationId(src.getStandardUnitCombinationId());	
		tar.setCategoryTreeNodeId(src.getCategoryTreeNodeId());	
		tar.setSortValue(src.getSortValue());	
		return tar;
	}

	public static List<StandardUnitCombinationCategoryDTO> toDTOs(List<StandardUnitCombinationCategoryVO> srcs) {
		if (srcs == null)
			return null;
		List<StandardUnitCombinationCategoryDTO> list = new ArrayList<StandardUnitCombinationCategoryDTO>();
		for (StandardUnitCombinationCategoryVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<StandardUnitCombinationCategoryVO> toVO(List<StandardUnitCombinationCategoryDTO> srcs) {
		if (srcs == null)
			return null;
		List<StandardUnitCombinationCategoryVO> list = new ArrayList<StandardUnitCombinationCategoryVO>();
		for (StandardUnitCombinationCategoryDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
}
	