package com.egeo.components.product.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.product.dto.CategoryAttNameDTO;
import com.egeo.components.product.po.CategoryAttNamePO;
import com.egeo.components.product.vo.CategoryAttNameVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author xiaping
 * @date 2017-08-14 13:46:25
 */
public class CategoryAttNameConverter {

	
	public static CategoryAttNameDTO toDTO(CategoryAttNameVO src) {
		CategoryAttNameDTO tar = new CategoryAttNameDTO();
		tar.setId(src.getId());
		tar.setParentId(src.getParentId());	
		tar.setCategoryId(src.getCategoryId());	
		tar.setAttNameId(src.getAttNameId());	
		tar.setSortValue(src.getSortValue());	
		tar.setPlatformId(src.getPlatformId());	
		tar.setType(src.getType());	
		tar.setIsRequired(src.getIsRequired());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		return tar;
	}

	public static CategoryAttNameVO toVO(CategoryAttNameDTO src) {
		CategoryAttNameVO tar = new CategoryAttNameVO();
		tar.setId(src.getId());
		tar.setParentId(src.getParentId());		
		tar.setCategoryId(src.getCategoryId());		
		tar.setAttNameId(src.getAttNameId());		
		tar.setSortValue(src.getSortValue());		
		tar.setPlatformId(src.getPlatformId());		
		tar.setType(src.getType());		
		tar.setIsRequired(src.getIsRequired());		
		tar.setCreateTime(src.getCreateTime());		
		tar.setUpdateTime(src.getUpdateTime());	
		return tar;
	}

	public static List<CategoryAttNameDTO> toDTOs(List<CategoryAttNameVO> srcs) {
		if (srcs == null)
			return null;
		List<CategoryAttNameDTO> list = new ArrayList<CategoryAttNameDTO>();
		for (CategoryAttNameVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<CategoryAttNameVO> toVO(List<CategoryAttNameDTO> srcs) {
		if (srcs == null)
			return null;
		List<CategoryAttNameVO> list = new ArrayList<CategoryAttNameVO>();
		for (CategoryAttNameDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static CategoryAttNameDTO toDTO(CategoryAttNamePO src) {
		CategoryAttNameDTO tar = new CategoryAttNameDTO();
		tar.setId(src.getId());
		tar.setParentId(src.getParentId());
		tar.setCategoryId(src.getCategoryId());
		tar.setAttNameId(src.getAttNameId());
		tar.setSortValue(src.getSortValue());
		tar.setPlatformId(src.getPlatformId());
		tar.setType(src.getType());
		tar.setIsRequired(src.getIsRequired());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static CategoryAttNamePO toPO(CategoryAttNameDTO src) {
		CategoryAttNamePO tar = new CategoryAttNamePO();
		tar.setId(src.getId());
		tar.setParentId(src.getParentId());
		tar.setCategoryId(src.getCategoryId());
		tar.setAttNameId(src.getAttNameId());
		tar.setSortValue(src.getSortValue());
		tar.setPlatformId(src.getPlatformId());
		tar.setType(src.getType());
		tar.setIsRequired(src.getIsRequired());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static List<CategoryAttNameDTO> toDTO(List<CategoryAttNamePO> srcs) {
		if (srcs == null)
			return null;
		List<CategoryAttNameDTO> list = new ArrayList<CategoryAttNameDTO>();
		for (CategoryAttNamePO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<CategoryAttNamePO> toPO(List<CategoryAttNameDTO> srcs) {
		if (srcs == null)
			return null;
		List<CategoryAttNamePO> list = new ArrayList<CategoryAttNamePO>();
		for (CategoryAttNameDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	