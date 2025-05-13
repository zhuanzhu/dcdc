package com.egeo.components.product.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.product.dto.CategoryTagDTO;
import com.egeo.components.product.po.CategoryTagPO;
import com.egeo.components.product.vo.CategoryTagVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author min
 * @date 2018-05-11 15:04:03
 */
public class CategoryTagConverter {
	
	
	public static CategoryTagDTO toDTO(CategoryTagVO src) {
		if (src == null)
		return null;	
		CategoryTagDTO tar = new CategoryTagDTO();
		tar.setId(src.getId());
		tar.setCategoryId(src.getCategoryId());	
		tar.setTagId(src.getTagId());	
		return tar;
	}

	public static CategoryTagVO toVO(CategoryTagDTO src) {
		if (src == null)
		return null;	
		CategoryTagVO tar = new CategoryTagVO();
		tar.setId(src.getId());
		tar.setCategoryId(src.getCategoryId());	
		tar.setTagId(src.getTagId());	
		return tar;
	}

	public static List<CategoryTagDTO> toDTOs(List<CategoryTagVO> srcs) {
		if (srcs == null)
			return null;
		List<CategoryTagDTO> list = new ArrayList<CategoryTagDTO>();
		for (CategoryTagVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<CategoryTagVO> toVO(List<CategoryTagDTO> srcs) {
		if (srcs == null)
			return null;
		List<CategoryTagVO> list = new ArrayList<CategoryTagVO>();
		for (CategoryTagDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static CategoryTagDTO toDTO(CategoryTagPO src) {
		if (src == null)
		return null;	
		CategoryTagDTO tar = new CategoryTagDTO();
		tar.setId(src.getId());
		tar.setCategoryId(src.getCategoryId());
		tar.setTagId(src.getTagId());
		return tar;
	}

	public static CategoryTagPO toPO(CategoryTagDTO src) {
		if (src == null)
		return null;	
		CategoryTagPO tar = new CategoryTagPO();
		tar.setId(src.getId());
		tar.setCategoryId(src.getCategoryId());
		tar.setTagId(src.getTagId());
		return tar;
	}

	public static List<CategoryTagDTO> toDTO(List<CategoryTagPO> srcs) {
		if (srcs == null)
			return null;
		List<CategoryTagDTO> list = new ArrayList<CategoryTagDTO>();
		for (CategoryTagPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<CategoryTagPO> toPO(List<CategoryTagDTO> srcs) {
		if (srcs == null)
			return null;
		List<CategoryTagPO> list = new ArrayList<CategoryTagPO>();
		for (CategoryTagDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	