package com.egeo.components.product.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.product.dto.CategoryDTO;
import com.egeo.components.product.po.CategoryPO;
import com.egeo.components.product.vo.CategoryVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author xiaping
 * @date 2017-08-14 13:46:25
 */
public class CategoryConverter {

	
	public static CategoryDTO toDTO(CategoryVO src) {
		CategoryDTO tar = new CategoryDTO();
		tar.setId(src.getId());
		tar.setName(src.getName());	
		tar.setCategoryLable(src.getCategoryLable());	
		tar.setDescription(src.getDescription());	
		tar.setPlatformId(src.getPlatformId());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setSerialNumber(src.getSerialNumber());
		return tar;
	}

	public static CategoryVO toVO(CategoryDTO src) {
		CategoryVO tar = new CategoryVO();
		tar.setId(src.getId());
		tar.setName(src.getName());		
		tar.setCategoryLable(src.getCategoryLable());		
		tar.setDescription(src.getDescription());		
		tar.setPlatformId(src.getPlatformId());		
		tar.setCreateTime(src.getCreateTime());		
		tar.setUpdateTime(src.getUpdateTime());		
		tar.setSerialNumber(src.getSerialNumber());
		return tar;
	}

	public static List<CategoryDTO> toDTOs(List<CategoryVO> srcs) {
		if (srcs == null)
			return null;
		List<CategoryDTO> list = new ArrayList<CategoryDTO>();
		for (CategoryVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<CategoryVO> toVO(List<CategoryDTO> srcs) {
		if (srcs == null)
			return null;
		List<CategoryVO> list = new ArrayList<CategoryVO>();
		for (CategoryDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static CategoryDTO toDTO(CategoryPO src) {
		if(src == null) {
			return null;
		}
		CategoryDTO tar = new CategoryDTO();
		tar.setId(src.getId());
		tar.setName(src.getName());
		tar.setCategoryLable(src.getCategoryLable());
		tar.setDescription(src.getDescription());
		tar.setPlatformId(src.getPlatformId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setSerialNumber(src.getSerialNumber());
		tar.setCategoryBannerId(src.getCategoryBannerId());
		return tar;
	}

	public static CategoryPO toPO(CategoryDTO src) {
		if(src == null) {
			return null;
		}
		CategoryPO tar = new CategoryPO();
		tar.setId(src.getId());
		tar.setName(src.getName());
		tar.setCategoryLable(src.getCategoryLable());
		tar.setDescription(src.getDescription());
		tar.setPlatformId(src.getPlatformId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setSerialNumber(src.getSerialNumber());
		tar.setCategoryBannerId(src.getCategoryBannerId());
		return tar;
	}

	public static List<CategoryDTO> toDTO(List<CategoryPO> srcs) {
		if (srcs == null)
			return null;
		List<CategoryDTO> list = new ArrayList<CategoryDTO>();
		for (CategoryPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<CategoryPO> toPO(List<CategoryDTO> srcs) {
		if (srcs == null)
			return null;
		List<CategoryPO> list = new ArrayList<CategoryPO>();
		for (CategoryDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	