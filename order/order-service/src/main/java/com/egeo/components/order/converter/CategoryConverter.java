package com.egeo.components.order.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.order.dto.CategoryDTO;
import com.egeo.components.order.po.CategoryPO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author xiaping
 * @date 2017-08-12 12:57:52
 */
public class CategoryConverter {
	
	public static CategoryDTO toDTO(CategoryPO src) {
		CategoryDTO tar = new CategoryDTO();
		tar.setId(src.getId());
		tar.setParentId(src.getParentId());
		tar.setName(src.getName());
		tar.setPlatformId(src.getPlatformId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static CategoryPO toPO(CategoryDTO src) {
		CategoryPO tar = new CategoryPO();
		tar.setId(src.getId());
		tar.setParentId(src.getParentId());
		tar.setName(src.getName());
		tar.setPlatformId(src.getPlatformId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
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
	