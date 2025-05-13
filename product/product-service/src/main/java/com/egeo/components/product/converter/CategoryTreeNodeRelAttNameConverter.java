package com.egeo.components.product.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.product.dto.CategoryTreeNodeRelAttNameDTO;
import com.egeo.components.product.po.CategoryTreeNodeRelAttNamePO;
import com.egeo.components.product.vo.CategoryTreeNodeRelAttNameVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author xiaping
 * @date 2017-08-14 13:46:25
 */
public class CategoryTreeNodeRelAttNameConverter {
	
	public static CategoryTreeNodeRelAttNameDTO toDTO(CategoryTreeNodeRelAttNameVO src) {
		CategoryTreeNodeRelAttNameDTO tar = new CategoryTreeNodeRelAttNameDTO();
		tar.setId(src.getId());
		tar.setParentId(src.getParentId());	
		tar.setCateTreeNodeRelId(src.getCateTreeNodeRelId());	
		tar.setAttNameId(src.getAttNameId());	
		tar.setSortValue(src.getSortValue());	
		tar.setPlatformId(src.getPlatformId());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		return tar;
	}

	public static CategoryTreeNodeRelAttNameVO toVO(CategoryTreeNodeRelAttNameDTO src) {
		CategoryTreeNodeRelAttNameVO tar = new CategoryTreeNodeRelAttNameVO();
		tar.setId(src.getId());
		tar.setParentId(src.getParentId());		
		tar.setCateTreeNodeRelId(src.getCateTreeNodeRelId());		
		tar.setAttNameId(src.getAttNameId());		
		tar.setSortValue(src.getSortValue());		
		tar.setPlatformId(src.getPlatformId());		
		tar.setCreateTime(src.getCreateTime());		
		tar.setUpdateTime(src.getUpdateTime());		
		return tar;
	}

	public static List<CategoryTreeNodeRelAttNameDTO> toDTOs(List<CategoryTreeNodeRelAttNameVO> srcs) {
		if (srcs == null)
			return null;
		List<CategoryTreeNodeRelAttNameDTO> list = new ArrayList<CategoryTreeNodeRelAttNameDTO>();
		for (CategoryTreeNodeRelAttNameVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<CategoryTreeNodeRelAttNameVO> toVO(List<CategoryTreeNodeRelAttNameDTO> srcs) {
		if (srcs == null)
			return null;
		List<CategoryTreeNodeRelAttNameVO> list = new ArrayList<CategoryTreeNodeRelAttNameVO>();
		for (CategoryTreeNodeRelAttNameDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static CategoryTreeNodeRelAttNameDTO toDTO(CategoryTreeNodeRelAttNamePO src) {
		CategoryTreeNodeRelAttNameDTO tar = new CategoryTreeNodeRelAttNameDTO();
		tar.setId(src.getId());
		tar.setParentId(src.getParentId());
		tar.setCateTreeNodeRelId(src.getCateTreeNodeRelId());
		tar.setAttNameId(src.getAttNameId());
		tar.setSortValue(src.getSortValue());
		tar.setPlatformId(src.getPlatformId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static CategoryTreeNodeRelAttNamePO toPO(CategoryTreeNodeRelAttNameDTO src) {
		CategoryTreeNodeRelAttNamePO tar = new CategoryTreeNodeRelAttNamePO();
		tar.setId(src.getId());
		tar.setParentId(src.getParentId());
		tar.setCateTreeNodeRelId(src.getCateTreeNodeRelId());
		tar.setAttNameId(src.getAttNameId());
		tar.setSortValue(src.getSortValue());
		tar.setPlatformId(src.getPlatformId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static List<CategoryTreeNodeRelAttNameDTO> toDTO(List<CategoryTreeNodeRelAttNamePO> srcs) {
		if (srcs == null)
			return null;
		List<CategoryTreeNodeRelAttNameDTO> list = new ArrayList<CategoryTreeNodeRelAttNameDTO>();
		for (CategoryTreeNodeRelAttNamePO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<CategoryTreeNodeRelAttNamePO> toPO(List<CategoryTreeNodeRelAttNameDTO> srcs) {
		if (srcs == null)
			return null;
		List<CategoryTreeNodeRelAttNamePO> list = new ArrayList<CategoryTreeNodeRelAttNamePO>();
		for (CategoryTreeNodeRelAttNameDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	