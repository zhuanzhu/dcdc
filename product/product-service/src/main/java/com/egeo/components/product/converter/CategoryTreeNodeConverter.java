package com.egeo.components.product.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.product.condition.CategoryTreeNodeCondition;
import com.egeo.components.product.dto.CategoryTreeNodeDTO;
import com.egeo.components.product.po.CategoryTreeNodePO;
import com.egeo.components.product.vo.CategoryTreeNodeVO;
import com.egeo.utils.EmptyUtil;

/**
 * DTO和PO相互转换工具类
 * 
 * @author xiaping
 * @date 2017-08-14 13:46:25
 */
public class CategoryTreeNodeConverter {

	public static CategoryTreeNodeDTO toDTO(CategoryTreeNodeVO src) {
		CategoryTreeNodeDTO tar = new CategoryTreeNodeDTO();
		tar.setId(src.getId());
		tar.setParentId(src.getParentId());	
		tar.setPids(src.getPids());	
		tar.setCategoryTreeId(src.getCategoryTreeId());	
		tar.setCategoryId(src.getCategoryId());	
		tar.setPlatformId(src.getPlatformId());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setName(src.getName());
		tar.setListSort(src.getListSort());
		tar.setCategoryLable(src.getCategoryLable());
		tar.setDescription(src.getDescription());
		tar.setSerialNumber(src.getSerialNumber());
		tar.setBannerImg(src.getBannerImg());;
		tar.setLinkType(src.getLinkType());
		tar.setLinkUrl(src.getLinkUrl());
		tar.setLinkParam(src.getLinkParam());
		tar.setExternalLinkId(src.getExternalLinkId());
		tar.setStandardUnitCombinationId(src.getStandardUnitCombinationId());
		tar.setStandardUnitId(src.getStandardUnitId());
		tar.setLocalParamId(src.getLocalParamId());
		tar.setAggregationCategoryIds(src.getAggregationCategoryIds());
		tar.setContent(src.getContent());
		
		if(EmptyUtil.isEmpty(src.getChildren())){
			tar.setLists(null);
		}else{
			List<CategoryTreeNodeDTO> list = new ArrayList<CategoryTreeNodeDTO>();
			for(CategoryTreeNodeVO dto:src.getChildren()){
				list.add(toDTO(dto));
			}
			tar.setLists(list);
		}			return tar;
	}

	public static CategoryTreeNodeVO toVO(CategoryTreeNodeDTO src) {
		CategoryTreeNodeVO tar = new CategoryTreeNodeVO();
		tar.setId(src.getId());
		tar.setParentId(src.getParentId());		
		tar.setPids(src.getPids());		
		tar.setCategoryTreeId(src.getCategoryTreeId());		
		tar.setCategoryId(src.getCategoryId());		
		tar.setPlatformId(src.getPlatformId());		
		tar.setCreateTime(src.getCreateTime());		
		tar.setUpdateTime(src.getUpdateTime());		
		tar.setName(src.getName());
		tar.setListSort(src.getListSort());
		tar.setCategoryLable(src.getCategoryLable());
		tar.setDescription(src.getDescription());
		tar.setSerialNumber(src.getSerialNumber());
		tar.setBannerImg(src.getBannerImg());;
		tar.setLinkType(src.getLinkType());
		tar.setLinkUrl(src.getLinkUrl());
		tar.setLinkParam(src.getLinkParam());
		tar.setExternalLinkId(src.getExternalLinkId());
		tar.setStandardUnitCombinationId(src.getStandardUnitCombinationId());
		tar.setStandardUnitId(src.getStandardUnitId());
		tar.setLocalParamId(src.getLocalParamId());
		tar.setAggregationCategoryIds(src.getAggregationCategoryIds());
		tar.setContent(src.getContent());
		if(EmptyUtil.isEmpty(src.getLists())){
			tar.setChildren(null);
		}else{
			List<CategoryTreeNodeVO> list = new ArrayList<CategoryTreeNodeVO>();
			for(CategoryTreeNodeDTO dto:src.getLists()){
				list.add(toVO(dto));
			}
			tar.setChildren(list);
		}		return tar;
	}

	public static List<CategoryTreeNodeDTO> toDTOs(List<CategoryTreeNodeVO> srcs) {
		if (srcs == null)
			return null;
		List<CategoryTreeNodeDTO> list = new ArrayList<CategoryTreeNodeDTO>();
		for (CategoryTreeNodeVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<CategoryTreeNodeVO> toVO(List<CategoryTreeNodeDTO> srcs) {
		if (srcs == null)
			return null;
		List<CategoryTreeNodeVO> list = new ArrayList<CategoryTreeNodeVO>();
		for (CategoryTreeNodeDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static CategoryTreeNodeDTO toDTO(CategoryTreeNodePO src) {
		if (src == null)
			return null;	
		CategoryTreeNodeDTO tar = new CategoryTreeNodeDTO();
		tar.setId(src.getId());
		tar.setParentId(src.getParentId());
		tar.setPids(src.getPids());
		tar.setCategoryTreeId(src.getCategoryTreeId());
		tar.setCategoryId(src.getCategoryId());
		tar.setListSort(src.getListSort());
		tar.setPlatformId(src.getPlatformId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static CategoryTreeNodeDTO conditionToDTO(CategoryTreeNodeCondition src) {
		if (src == null)
			return null;	
		CategoryTreeNodeDTO tar = new CategoryTreeNodeDTO();
		tar.setId(src.getId());
		tar.setParentId(src.getParentId());
		tar.setCategoryTreeId(src.getCategoryTreeId());
		tar.setCategoryId(src.getCategoryId());
		tar.setListSort(src.getListSort());
		tar.setPlatformId(src.getPlatformId());
		tar.setLinkableId(src.getLinkableId());
		return tar;
	}

	public static CategoryTreeNodePO toPO(CategoryTreeNodeDTO src) {
		CategoryTreeNodePO tar = new CategoryTreeNodePO();
		tar.setId(src.getId());
		tar.setParentId(src.getParentId());
		tar.setPids(src.getPids());
		tar.setCategoryTreeId(src.getCategoryTreeId());
		tar.setCategoryId(src.getCategoryId());
		tar.setListSort(src.getListSort());
		tar.setPlatformId(src.getPlatformId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static List<CategoryTreeNodeDTO> conditionToDTO(List<CategoryTreeNodeCondition> srcs) {
		if (srcs == null)
			return null;
		List<CategoryTreeNodeDTO> list = new ArrayList<CategoryTreeNodeDTO>();
		for (CategoryTreeNodeCondition src : srcs) {
			list.add(conditionToDTO(src));
		}
		return list;
	}
	
	public static List<CategoryTreeNodeDTO> toDTO(List<CategoryTreeNodePO> srcs) {
		if (srcs == null)
			return null;
		List<CategoryTreeNodeDTO> list = new ArrayList<CategoryTreeNodeDTO>();
		for (CategoryTreeNodePO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<CategoryTreeNodePO> toPO(List<CategoryTreeNodeDTO> srcs) {
		if (srcs == null)
			return null;
		List<CategoryTreeNodePO> list = new ArrayList<CategoryTreeNodePO>();
		for (CategoryTreeNodeDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
	
	
	
	
	

	
	public static CategoryTreeNodeDTO categoryConditiontoDTO(CategoryTreeNodeCondition src) {
		CategoryTreeNodeDTO tar = new CategoryTreeNodeDTO();
		tar.setId(src.getId());
		tar.setParentId(src.getParentId());
		tar.setCategoryTreeId(src.getCategoryTreeId());
		tar.setCategoryId(src.getCategoryId());
		tar.setListSort(src.getListSort());
		tar.setSerialNumber(src.getSerialNumber());
		tar.setPlatformId(src.getPlatformId());
		tar.setName(src.getName());
		tar.setCategoryLable(src.getCategoryLable());
		tar.setDescription(src.getDescription());
		tar.setBannerImg(src.getBannerImg());;
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
	


	public static CategoryTreeNodeCondition categoryConditiontoPO(CategoryTreeNodeDTO src) {
		CategoryTreeNodeCondition tar = new CategoryTreeNodeCondition();
		tar.setId(src.getId());
		tar.setParentId(src.getParentId());
		tar.setCategoryTreeId(src.getCategoryTreeId());
		tar.setCategoryId(src.getCategoryId());
		tar.setListSort(src.getListSort());
		tar.setSerialNumber(src.getSerialNumber());
		tar.setPlatformId(src.getPlatformId());
		tar.setName(src.getName());
		tar.setCategoryLable(src.getCategoryLable());
		tar.setDescription(src.getDescription());
		tar.setBannerImg(src.getBannerImg());;
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


	
	public static List<CategoryTreeNodeDTO> categoryConditiontoDTO(List<CategoryTreeNodeCondition> srcs) {
		if (srcs == null)
			return null;
		List<CategoryTreeNodeDTO> list = new ArrayList<CategoryTreeNodeDTO>();
		for (CategoryTreeNodeCondition src : srcs) {
			list.add(categoryConditiontoDTO(src));
		}
		return list;
	}

	public static List<CategoryTreeNodeCondition> categoryConditiontoPO(List<CategoryTreeNodeDTO> srcs) {
		if (srcs == null)
			return null;
		List<CategoryTreeNodeCondition> list = new ArrayList<CategoryTreeNodeCondition>();
		for (CategoryTreeNodeDTO src : srcs) {
			list.add(categoryConditiontoPO(src));
		}
		return list;
	}

	
	
	
	
	
	
	
	
	
	
	
}
	