package com.egeo.components.product.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.product.dto.CategoryTreeNodeRelationDTO;
import com.egeo.components.product.po.CategoryTreeNodeRelationPO;
import com.egeo.components.product.vo.CategoryTreeNodeRelationVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author xiaping
 * @date 2017-08-14 13:46:25
 */
public class CategoryTreeNodeRelationConverter {

	public static CategoryTreeNodeRelationDTO toDTO(CategoryTreeNodeRelationVO src) {
		CategoryTreeNodeRelationDTO tar = new CategoryTreeNodeRelationDTO();
		tar.setId(src.getId());
		tar.setBackTreeNodeId(src.getBackTreeNodeId());	
		tar.setFrontTreeNodeId(src.getFrontTreeNodeId());	
		tar.setSortValue(src.getSortValue());	
		tar.setPlatformId(src.getPlatformId());	
		tar.setType(src.getType());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		return tar;
	}

	public static CategoryTreeNodeRelationVO toVO(CategoryTreeNodeRelationDTO src) {
		CategoryTreeNodeRelationVO tar = new CategoryTreeNodeRelationVO();
		tar.setId(src.getId());
		tar.setBackTreeNodeId(src.getBackTreeNodeId());		
		tar.setFrontTreeNodeId(src.getFrontTreeNodeId());		
		tar.setSortValue(src.getSortValue());		
		tar.setPlatformId(src.getPlatformId());		
		tar.setType(src.getType());		
		tar.setCreateTime(src.getCreateTime());		
		tar.setUpdateTime(src.getUpdateTime());		
		return tar;
	}

	public static List<CategoryTreeNodeRelationDTO> toDTOs(List<CategoryTreeNodeRelationVO> srcs) {
		if (srcs == null)
			return null;
		List<CategoryTreeNodeRelationDTO> list = new ArrayList<CategoryTreeNodeRelationDTO>();
		for (CategoryTreeNodeRelationVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<CategoryTreeNodeRelationVO> toVO(List<CategoryTreeNodeRelationDTO> srcs) {
		if (srcs == null)
			return null;
		List<CategoryTreeNodeRelationVO> list = new ArrayList<CategoryTreeNodeRelationVO>();
		for (CategoryTreeNodeRelationDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static CategoryTreeNodeRelationDTO toDTO(CategoryTreeNodeRelationPO src) {
		CategoryTreeNodeRelationDTO tar = new CategoryTreeNodeRelationDTO();
		tar.setId(src.getId());
		tar.setBackTreeNodeId(src.getBackTreeNodeId());
		tar.setFrontTreeNodeId(src.getFrontTreeNodeId());
		tar.setSortValue(src.getSortValue());
		tar.setPlatformId(src.getPlatformId());
		tar.setType(src.getType());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static CategoryTreeNodeRelationPO toPO(CategoryTreeNodeRelationDTO src) {
		CategoryTreeNodeRelationPO tar = new CategoryTreeNodeRelationPO();
		tar.setId(src.getId());
		tar.setBackTreeNodeId(src.getBackTreeNodeId());
		tar.setFrontTreeNodeId(src.getFrontTreeNodeId());
		tar.setSortValue(src.getSortValue());
		tar.setPlatformId(src.getPlatformId());
		tar.setType(src.getType());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static List<CategoryTreeNodeRelationDTO> toDTO(List<CategoryTreeNodeRelationPO> srcs) {
		if (srcs == null)
			return null;
		List<CategoryTreeNodeRelationDTO> list = new ArrayList<CategoryTreeNodeRelationDTO>();
		for (CategoryTreeNodeRelationPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<CategoryTreeNodeRelationPO> toPO(List<CategoryTreeNodeRelationDTO> srcs) {
		if (srcs == null)
			return null;
		List<CategoryTreeNodeRelationPO> list = new ArrayList<CategoryTreeNodeRelationPO>();
		for (CategoryTreeNodeRelationDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	