package com.egeo.components.product.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.product.dto.CategoryTreeDTO;
import com.egeo.components.product.po.CategoryTreePO;
import com.egeo.components.product.vo.CategoryTreeVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author xiaping
 * @date 2017-08-14 13:46:25
 */
public class CategoryTreeConverter {

	
	public static CategoryTreeDTO toDTO(CategoryTreeVO src) {
		if (src == null)
			return null;
		CategoryTreeDTO tar = new CategoryTreeDTO();
		tar.setId(src.getId());
		tar.setType(src.getType());	
		tar.setName(src.getName());
		tar.setCompanyId(src.getCompanyId());
		tar.setEnterpriseId(src.getEnterpriseId());
		tar.setPlatformId(src.getPlatformId());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setSeriesType(src.getSeriesType());
		tar.setStartUsing(src.getStartUsing());
		tar.setContent(src.getContent());
		tar.setCategoryTreeTemplateId(src.getCategoryTreeTemplateId());
		tar.setCompanyType(src.getCompanyType());
		tar.setWebStart(src.getWebStart());
		tar.setIsDefault(src.getIsDefault());
		return tar;
	}

	public static CategoryTreeVO toVO(CategoryTreeDTO src) {
		if (src == null)
			return null;
		CategoryTreeVO tar = new CategoryTreeVO();
		tar.setId(src.getId());
		tar.setType(src.getType());		
		tar.setName(src.getName());		
		tar.setPlatformId(src.getPlatformId());		
		tar.setCreateTime(src.getCreateTime());		
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setSeriesType(src.getSeriesType());
		tar.setStartUsing(src.getStartUsing());
		tar.setContent(src.getContent());
		tar.setCategoryTreeTemplateId(src.getCategoryTreeTemplateId());
		tar.setCompanyType(src.getCompanyType());
		tar.setWebStart(src.getWebStart());
		return tar;
	}

	public static List<CategoryTreeDTO> toDTOs(List<CategoryTreeVO> srcs) {
		if (srcs == null)
			return null;
		List<CategoryTreeDTO> list = new ArrayList<CategoryTreeDTO>();
		for (CategoryTreeVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<CategoryTreeVO> toVO(List<CategoryTreeDTO> srcs) {
		if (srcs == null)
			return null;
		List<CategoryTreeVO> list = new ArrayList<CategoryTreeVO>();
		for (CategoryTreeDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static CategoryTreeDTO toDTO(CategoryTreePO src) {
		CategoryTreeDTO tar = new CategoryTreeDTO();
		if (src == null)
			return null;
		tar.setId(src.getId());
		tar.setType(src.getType());
		tar.setName(src.getName());
		tar.setCompanyId(src.getCompanyId());
		tar.setEnterpriseId(src.getEnterpriseId());
		tar.setPlatformId(src.getPlatformId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setSeriesType(src.getSeriesType());
		tar.setStartUsing(src.getStartUsing());
		tar.setContent(src.getContent());
		tar.setCategoryTreeTemplateId(src.getCategoryTreeTemplateId());
		tar.setCompanyType(src.getCompanyType());
		tar.setWebStart(src.getWebStart());
		tar.setIsDefault(src.getIsDefault());
		return tar;
	}

	public static CategoryTreePO toPO(CategoryTreeDTO src) {
		CategoryTreePO tar = new CategoryTreePO();
		if (src == null)
			return null;
		tar.setId(src.getId());
		tar.setType(src.getType());
		tar.setName(src.getName());
		tar.setCompanyId(src.getCompanyId());
		tar.setEnterpriseId(src.getEnterpriseId());
		tar.setPlatformId(src.getPlatformId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setSeriesType(src.getSeriesType());
		tar.setStartUsing(src.getStartUsing());
		tar.setContent(src.getContent());
		tar.setCategoryTreeTemplateId(src.getCategoryTreeTemplateId());
		tar.setCompanyType(src.getCompanyType());
		tar.setWebStart(src.getWebStart());
		tar.setIsDefault(src.getIsDefault());
		return tar;
	}

	public static List<CategoryTreeDTO> toDTO(List<CategoryTreePO> srcs) {
		if (srcs == null)
			return null;
		List<CategoryTreeDTO> list = new ArrayList<CategoryTreeDTO>();
		for (CategoryTreePO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<CategoryTreePO> toPO(List<CategoryTreeDTO> srcs) {
		if (srcs == null)
			return null;
		List<CategoryTreePO> list = new ArrayList<CategoryTreePO>();
		for (CategoryTreeDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	