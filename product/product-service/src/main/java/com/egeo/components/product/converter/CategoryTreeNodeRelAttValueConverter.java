package com.egeo.components.product.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.product.dto.CategoryTreeNodeRelAttValueDTO;
import com.egeo.components.product.po.CategoryTreeNodeRelAttValuePO;
import com.egeo.components.product.vo.CategoryTreeNodeRelAttValueVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author xiaping
 * @date 2017-08-14 13:46:25
 */
public class CategoryTreeNodeRelAttValueConverter {
	
	public static CategoryTreeNodeRelAttValueDTO toDTO(CategoryTreeNodeRelAttValueVO src) {
		CategoryTreeNodeRelAttValueDTO tar = new CategoryTreeNodeRelAttValueDTO();
		tar.setId(src.getId());
		tar.setCateTreeNodeRelAttNameId(src.getCateTreeNodeRelAttNameId());	
		tar.setAttValueId(src.getAttValueId());	
		tar.setAttValueCustom(src.getAttValueCustom());	
		tar.setSortValue(src.getSortValue());	
		tar.setPlatformId(src.getPlatformId());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		return tar;
	}

	public static CategoryTreeNodeRelAttValueVO toVO(CategoryTreeNodeRelAttValueDTO src) {
		CategoryTreeNodeRelAttValueVO tar = new CategoryTreeNodeRelAttValueVO();
		tar.setId(src.getId());
		tar.setCateTreeNodeRelAttNameId(src.getCateTreeNodeRelAttNameId());		
		tar.setAttValueId(src.getAttValueId());		
		tar.setAttValueCustom(src.getAttValueCustom());		
		tar.setSortValue(src.getSortValue());		
		tar.setPlatformId(src.getPlatformId());		
		tar.setCreateTime(src.getCreateTime());		
		tar.setUpdateTime(src.getUpdateTime());		
		return tar;
	}

	public static List<CategoryTreeNodeRelAttValueDTO> toDTOs(List<CategoryTreeNodeRelAttValueVO> srcs) {
		if (srcs == null)
			return null;
		List<CategoryTreeNodeRelAttValueDTO> list = new ArrayList<CategoryTreeNodeRelAttValueDTO>();
		for (CategoryTreeNodeRelAttValueVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<CategoryTreeNodeRelAttValueVO> toVO(List<CategoryTreeNodeRelAttValueDTO> srcs) {
		if (srcs == null)
			return null;
		List<CategoryTreeNodeRelAttValueVO> list = new ArrayList<CategoryTreeNodeRelAttValueVO>();
		for (CategoryTreeNodeRelAttValueDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static CategoryTreeNodeRelAttValueDTO toDTO(CategoryTreeNodeRelAttValuePO src) {
		CategoryTreeNodeRelAttValueDTO tar = new CategoryTreeNodeRelAttValueDTO();
		tar.setId(src.getId());
		tar.setCateTreeNodeRelAttNameId(src.getCateTreeNodeRelAttNameId());
		tar.setAttValueId(src.getAttValueId());
		tar.setAttValueCustom(src.getAttValueCustom());
		tar.setSortValue(src.getSortValue());
		tar.setPlatformId(src.getPlatformId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static CategoryTreeNodeRelAttValuePO toPO(CategoryTreeNodeRelAttValueDTO src) {
		CategoryTreeNodeRelAttValuePO tar = new CategoryTreeNodeRelAttValuePO();
		tar.setId(src.getId());
		tar.setCateTreeNodeRelAttNameId(src.getCateTreeNodeRelAttNameId());
		tar.setAttValueId(src.getAttValueId());
		tar.setAttValueCustom(src.getAttValueCustom());
		tar.setSortValue(src.getSortValue());
		tar.setPlatformId(src.getPlatformId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static List<CategoryTreeNodeRelAttValueDTO> toDTO(List<CategoryTreeNodeRelAttValuePO> srcs) {
		if (srcs == null)
			return null;
		List<CategoryTreeNodeRelAttValueDTO> list = new ArrayList<CategoryTreeNodeRelAttValueDTO>();
		for (CategoryTreeNodeRelAttValuePO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<CategoryTreeNodeRelAttValuePO> toPO(List<CategoryTreeNodeRelAttValueDTO> srcs) {
		if (srcs == null)
			return null;
		List<CategoryTreeNodeRelAttValuePO> list = new ArrayList<CategoryTreeNodeRelAttValuePO>();
		for (CategoryTreeNodeRelAttValueDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	