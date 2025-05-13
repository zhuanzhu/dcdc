package com.egeo.components.product.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.product.dto.CategoryBannerDTO;
import com.egeo.components.product.po.CategoryBannerPO;
import com.egeo.components.product.vo.CategoryBannerVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author min
 * @date 2018-04-20 10:38:52
 */
public class CategoryBannerConverter {
	
	public static CategoryBannerDTO toDTO(CategoryBannerVO src) {
		if (src == null)
		return null;	
		CategoryBannerDTO tar = new CategoryBannerDTO();
		tar.setId(src.getId());
		tar.setBannerName(src.getBannerName());	
		tar.setBannerImg(src.getBannerImg());	
		tar.setLinkType(src.getLinkType());	
		tar.setLinkUrl(src.getLinkUrl());	
		tar.setLinkParam(src.getLinkParam());	
		tar.setExternalLinkId(src.getExternalLinkId());	
		tar.setStandardUnitCombinationId(src.getStandardUnitCombinationId());	
		tar.setStandardUnitId(src.getStandardUnitId());	
		tar.setLocalParamId(src.getLocalParamId());	
		return tar;
	}

	public static CategoryBannerVO toVO(CategoryBannerDTO src) {
		if (src == null)
		return null;	
		CategoryBannerVO tar = new CategoryBannerVO();
		tar.setId(src.getId());
		tar.setBannerName(src.getBannerName());	
		tar.setBannerImg(src.getBannerImg());	
		tar.setLinkType(src.getLinkType());	
		tar.setLinkUrl(src.getLinkUrl());	
		tar.setLinkParam(src.getLinkParam());	
		tar.setExternalLinkId(src.getExternalLinkId());	
		tar.setStandardUnitCombinationId(src.getStandardUnitCombinationId());	
		tar.setStandardUnitId(src.getStandardUnitId());	
		tar.setLocalParamId(src.getLocalParamId());	
		return tar;
	}

	public static List<CategoryBannerDTO> toDTOs(List<CategoryBannerVO> srcs) {
		if (srcs == null)
			return null;
		List<CategoryBannerDTO> list = new ArrayList<CategoryBannerDTO>();
		for (CategoryBannerVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<CategoryBannerVO> toVO(List<CategoryBannerDTO> srcs) {
		if (srcs == null)
			return null;
		List<CategoryBannerVO> list = new ArrayList<CategoryBannerVO>();
		for (CategoryBannerDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static CategoryBannerDTO toDTO(CategoryBannerPO src) {
		if (src == null)
		return null;	
		CategoryBannerDTO tar = new CategoryBannerDTO();
		tar.setId(src.getId());
		tar.setBannerName(src.getBannerName());
		tar.setBannerImg(src.getBannerImg());
		tar.setLinkType(src.getLinkType());
		tar.setLinkUrl(src.getLinkUrl());
		tar.setLinkParam(src.getLinkParam());
		tar.setExternalLinkId(src.getExternalLinkId());
		tar.setStandardUnitCombinationId(src.getStandardUnitCombinationId());
		tar.setStandardUnitId(src.getStandardUnitId());
		tar.setLocalParamId(src.getLocalParamId());
		tar.setLinkableId(src.getLinkableId());
		return tar;
	}

	public static CategoryBannerPO toPO(CategoryBannerDTO src) {
		if (src == null)
		return null;	
		CategoryBannerPO tar = new CategoryBannerPO();
		tar.setId(src.getId());
		tar.setBannerName(src.getBannerName());
		tar.setBannerImg(src.getBannerImg());
		tar.setLinkType(src.getLinkType());
		tar.setLinkUrl(src.getLinkUrl());
		tar.setLinkParam(src.getLinkParam());
		tar.setExternalLinkId(src.getExternalLinkId());
		tar.setStandardUnitCombinationId(src.getStandardUnitCombinationId());
		tar.setStandardUnitId(src.getStandardUnitId());
		tar.setLocalParamId(src.getLocalParamId());
		tar.setLinkableId(src.getLinkableId());
		return tar;
	}

	public static List<CategoryBannerDTO> toDTO(List<CategoryBannerPO> srcs) {
		if (srcs == null)
			return null;
		List<CategoryBannerDTO> list = new ArrayList<CategoryBannerDTO>();
		for (CategoryBannerPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<CategoryBannerPO> toPO(List<CategoryBannerDTO> srcs) {
		if (srcs == null)
			return null;
		List<CategoryBannerPO> list = new ArrayList<CategoryBannerPO>();
		for (CategoryBannerDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	