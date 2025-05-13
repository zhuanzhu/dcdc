package com.egeo.components.product.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.product.dto.CategoryAttValueDTO;
import com.egeo.components.product.po.CategoryAttValuePO;
import com.egeo.components.product.vo.CategoryAttValueVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author xiaping
 * @date 2017-08-14 13:46:25
 */
public class CategoryAttValueConverter {

	public static CategoryAttValueDTO toDTO(CategoryAttValueVO src) {
		CategoryAttValueDTO tar = new CategoryAttValueDTO();
		tar.setId(src.getId());
		tar.setCategoryAttNameId(src.getCategoryAttNameId());	
		tar.setAttValueId(src.getAttValueId());	
		tar.setAttValueCustom(src.getAttValueCustom());	
		tar.setSortValue(src.getSortValue());	
		tar.setPlatformId(src.getPlatformId());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		return tar;
	}

	public static CategoryAttValueVO toVO(CategoryAttValueDTO src) {
		CategoryAttValueVO tar = new CategoryAttValueVO();
		tar.setId(src.getId());
		tar.setCategoryAttNameId(src.getCategoryAttNameId());		
		tar.setAttValueId(src.getAttValueId());		
		tar.setAttValueCustom(src.getAttValueCustom());		
		tar.setSortValue(src.getSortValue());		
		tar.setPlatformId(src.getPlatformId());		
		tar.setCreateTime(src.getCreateTime());		
		tar.setUpdateTime(src.getUpdateTime());		
		return tar;
	}

	public static List<CategoryAttValueDTO> toDTOs(List<CategoryAttValueVO> srcs) {
		if (srcs == null)
			return null;
		List<CategoryAttValueDTO> list = new ArrayList<CategoryAttValueDTO>();
		for (CategoryAttValueVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<CategoryAttValueVO> toVO(List<CategoryAttValueDTO> srcs) {
		if (srcs == null)
			return null;
		List<CategoryAttValueVO> list = new ArrayList<CategoryAttValueVO>();
		for (CategoryAttValueDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static CategoryAttValueDTO toDTO(CategoryAttValuePO src) {
		CategoryAttValueDTO tar = new CategoryAttValueDTO();
		tar.setId(src.getId());
		tar.setCategoryAttNameId(src.getCategoryAttNameId());
		tar.setAttValueId(src.getAttValueId());
		tar.setAttValueCustom(src.getAttValueCustom());
		tar.setSortValue(src.getSortValue());
		tar.setPlatformId(src.getPlatformId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setProductId(src.getProductId());
		return tar;
	}

	public static CategoryAttValuePO toPO(CategoryAttValueDTO src) {
		CategoryAttValuePO tar = new CategoryAttValuePO();
		tar.setId(src.getId());
		tar.setCategoryAttNameId(src.getCategoryAttNameId());
		tar.setAttValueId(src.getAttValueId());
		tar.setAttValueCustom(src.getAttValueCustom());
		tar.setSortValue(src.getSortValue());
		tar.setPlatformId(src.getPlatformId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setProductId(src.getProductId());
		return tar;
	}

	public static List<CategoryAttValueDTO> toDTO(List<CategoryAttValuePO> srcs) {
		if (srcs == null)
			return null;
		List<CategoryAttValueDTO> list = new ArrayList<CategoryAttValueDTO>();
		for (CategoryAttValuePO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<CategoryAttValuePO> toPO(List<CategoryAttValueDTO> srcs) {
		if (srcs == null)
			return null;
		List<CategoryAttValuePO> list = new ArrayList<CategoryAttValuePO>();
		for (CategoryAttValueDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	