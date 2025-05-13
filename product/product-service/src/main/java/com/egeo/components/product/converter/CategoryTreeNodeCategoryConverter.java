package com.egeo.components.product.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.product.dto.CategoryTreeNodeCategoryDTO;
import com.egeo.components.product.po.CategoryTreeNodeCategoryPO;
import com.egeo.components.product.vo.CategoryTreeNodeCategoryVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author min
 * @date 2018-04-08 14:34:40
 */
public class CategoryTreeNodeCategoryConverter {
	
	
	public static CategoryTreeNodeCategoryDTO toDTO(CategoryTreeNodeCategoryVO src) {
		if (src == null)
		return null;	
		CategoryTreeNodeCategoryDTO tar = new CategoryTreeNodeCategoryDTO();
		tar.setId(src.getId());
		tar.setFrontCategoryTreeNodeId(src.getFrontCategoryTreeNodeId());	
		tar.setQueenCategoryTreeNodeId(src.getQueenCategoryTreeNodeId());	
		return tar;
	}

	public static CategoryTreeNodeCategoryVO toVO(CategoryTreeNodeCategoryDTO src) {
		if (src == null)
		return null;	
		CategoryTreeNodeCategoryVO tar = new CategoryTreeNodeCategoryVO();
		tar.setId(src.getId());
		tar.setFrontCategoryTreeNodeId(src.getFrontCategoryTreeNodeId());	
		tar.setQueenCategoryTreeNodeId(src.getQueenCategoryTreeNodeId());	
		return tar;
	}

	public static List<CategoryTreeNodeCategoryDTO> toDTOs(List<CategoryTreeNodeCategoryVO> srcs) {
		if (srcs == null)
			return null;
		List<CategoryTreeNodeCategoryDTO> list = new ArrayList<CategoryTreeNodeCategoryDTO>();
		for (CategoryTreeNodeCategoryVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<CategoryTreeNodeCategoryVO> toVO(List<CategoryTreeNodeCategoryDTO> srcs) {
		if (srcs == null)
			return null;
		List<CategoryTreeNodeCategoryVO> list = new ArrayList<CategoryTreeNodeCategoryVO>();
		for (CategoryTreeNodeCategoryDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static CategoryTreeNodeCategoryDTO toDTO(CategoryTreeNodeCategoryPO src) {
		if (src == null)
		return null;	
		CategoryTreeNodeCategoryDTO tar = new CategoryTreeNodeCategoryDTO();
		tar.setId(src.getId());
		tar.setFrontCategoryTreeNodeId(src.getFrontCategoryTreeNodeId());
		tar.setQueenCategoryTreeNodeId(src.getQueenCategoryTreeNodeId());
		return tar;
	}

	public static CategoryTreeNodeCategoryPO toPO(CategoryTreeNodeCategoryDTO src) {
		if (src == null)
		return null;	
		CategoryTreeNodeCategoryPO tar = new CategoryTreeNodeCategoryPO();
		tar.setId(src.getId());
		tar.setFrontCategoryTreeNodeId(src.getFrontCategoryTreeNodeId());
		tar.setQueenCategoryTreeNodeId(src.getQueenCategoryTreeNodeId());
		return tar;
	}

	public static List<CategoryTreeNodeCategoryDTO> toDTO(List<CategoryTreeNodeCategoryPO> srcs) {
		if (srcs == null)
			return null;
		List<CategoryTreeNodeCategoryDTO> list = new ArrayList<CategoryTreeNodeCategoryDTO>();
		for (CategoryTreeNodeCategoryPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<CategoryTreeNodeCategoryPO> toPO(List<CategoryTreeNodeCategoryDTO> srcs) {
		if (srcs == null)
			return null;
		List<CategoryTreeNodeCategoryPO> list = new ArrayList<CategoryTreeNodeCategoryPO>();
		for (CategoryTreeNodeCategoryDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	