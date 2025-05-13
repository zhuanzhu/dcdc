package com.egeo.components.product.business.impl;
	

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.egeo.common.PlatformKeyConstant;
import com.egeo.components.product.business.CategoryManage;
import com.egeo.components.product.business.CategoryTreeNodeManage;
import com.egeo.components.product.converter.AttributeNameConverter;
import com.egeo.components.product.converter.CategoryAttNameConverter;
import com.egeo.components.product.converter.CategoryConverter;
import com.egeo.components.product.dto.AttributeNameDTO;
import com.egeo.components.product.dto.CategoryAttNameDTO;
import com.egeo.components.product.dto.CategoryAttNameValuse;
import com.egeo.components.product.dto.CategoryDTO;
import com.egeo.components.product.dto.CategoryTagDTO;
import com.egeo.components.product.dto.CategoryTreeNodeDTO;
import com.egeo.components.product.dto.ProductDTO;
import com.egeo.components.product.facade.AttributeNameFacade;
import com.egeo.components.product.facade.CategoryAttNameFacade;
import com.egeo.components.product.facade.CategoryFacade;
import com.egeo.components.product.vo.AttributeNameVO;
import com.egeo.components.product.vo.CategoryAttNameVO;
import com.egeo.components.product.vo.CategoryTreeNodeVO;
import com.egeo.components.product.vo.CategoryVO;
import com.egeo.exception.BusinessException;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.str.StringUtils;

@Service("category")
public class CategoryManageImpl implements CategoryManage{

	
	@Resource(name="categoryFacade")
	private CategoryFacade categoryFacade;
	
	@Resource(name="categoryAttNameFacade")
	private CategoryAttNameFacade categoryAttNameFacade;
	
	@Resource(name="categoryTreeNode")
    private CategoryTreeNodeManage categoryTreeNodeManage;
	
	@Resource(name="attributeNameFacade")
	private AttributeNameFacade attributeNameFacade;

    @Override
    public List<Map<String, Object>> findAll(CategoryVO categoryVO) {
        return categoryFacade.findAll(CategoryConverter.toDTO(categoryVO));
    }

    @Override
    public List<String> idToName(String ids) {
        return categoryFacade.idToName(ids);
    }

    @Override
    public List<CategoryDTO> findAllList(CategoryVO categoryVO) {
        return categoryFacade.findAllList(CategoryConverter.toDTO(categoryVO));
    }

	@Override
	public String modifyCategory(CategoryVO categoryVO,String listSort,List<Long> tagIdList,HttpServletRequest req) {
		
		CategoryDTO dto =  categoryFacade.findCategoryById(categoryVO.getId());
		if(EmptyUtil.isEmpty(dto)){
			throw new BusinessException("类目id" + categoryVO.getId() +"不存在");
		}
		//根据类目id查询对应的节点信息
		CategoryTreeNodeVO categoryTreeNodeVO = new CategoryTreeNodeVO();
		categoryTreeNodeVO.setCategoryId(categoryVO.getId());
		List<CategoryTreeNodeDTO> list = categoryTreeNodeManage.findAll(categoryTreeNodeVO);
		if(EmptyUtil.isEmpty(list)){
			throw new BusinessException("类目id" + categoryVO.getId() +"对应的节点信息不存在");
		}
		CategoryTreeNodeDTO categoryTreeNodeDTO = list.get(0);
		if(EmptyUtil.isNotEmpty(listSort)){
			categoryTreeNodeDTO.setListSort(Integer.valueOf(listSort));
			//修改节点排序信息
			categoryTreeNodeManage.updateCategoryTreeNodeWithTx(categoryTreeNodeDTO);
		}

		if(EmptyUtil.isNotEmpty(categoryVO.getDescription())){
			dto.setDescription(categoryVO.getDescription());
		}
		if(EmptyUtil.isNotEmpty(categoryVO.getName())){
			dto.setName(categoryVO.getName());
		}
		if(EmptyUtil.isNotEmpty(categoryVO.getCategoryLable())){
			dto.setCategoryLable(categoryVO.getCategoryLable());
		}

		return categoryFacade.modifyCategory(dto,tagIdList);
	}

	@Override
	public String deleteCategory(CategoryVO categoryVO, HttpServletRequest req) {
		Long categoryId = categoryVO.getId();
		CategoryAttNameVO  categoryAttNameVO = new  CategoryAttNameVO();
		categoryAttNameVO.setCategoryId(categoryId);
		List<CategoryAttNameValuse> list = categoryAttNameFacade.categoryAttNameByCId(CategoryAttNameConverter.toDTO(categoryAttNameVO));
		if(EmptyUtil.isNotEmpty(list)){
			throw new BusinessException("该类目下有属性，不能删除");
		}
		//根据类目id查询类目节点信息
		CategoryTreeNodeVO categoryTreeNodeVO = new CategoryTreeNodeVO();
		categoryTreeNodeVO.setCategoryId(categoryId);
		List<CategoryTreeNodeDTO> categoryTreeNodeList = categoryTreeNodeManage.findAll(categoryTreeNodeVO);
		if(EmptyUtil.isNotEmpty(categoryTreeNodeList)){
			categoryTreeNodeManage.deleteByIdWithTx(categoryTreeNodeList.get(0));
		}
		//根据类目id删除类目属性
		CategoryAttNameDTO categoryAttNameDTO = new CategoryAttNameDTO();
		categoryAttNameDTO.setCategoryId(categoryId);
		//根据条件删除类目属性
		categoryAttNameFacade.deleteByConditionWithTx(categoryAttNameDTO);
		
		return categoryFacade.deleteCategoryByIdWithTx(categoryId);
	}

	@Override
	public Map<String, Object> findCategorybyId(Long id) {
		
		//根据类目id查询产品
		List<ProductDTO> productList = categoryFacade.productListByCategoryId(id);
		if(productList.size() > 0){
			throw new BusinessException("该类目以被产品引用，无法编辑");
		}
		
		//根据类目id查询类目属性
		CategoryAttNameDTO categoryAttNameDTO = new CategoryAttNameDTO();
		categoryAttNameDTO.setCategoryId(id);
		List<CategoryAttNameDTO> list = categoryAttNameFacade.findAll(categoryAttNameDTO);
		//产品属性集合
		List<AttributeNameVO> productAttName = new ArrayList<AttributeNameVO>();
		//商品规格属性集合
		List<AttributeNameVO> merchantProductAttName = new ArrayList<AttributeNameVO>();
		//参数属性集合
		List<AttributeNameVO> parameterAttNameList = new ArrayList<AttributeNameVO>();
		for (CategoryAttNameDTO categoryAttNameDTO2 : list) {
			//类型为1加入产品属性集合
			if(categoryAttNameDTO2.getType() == 1){
				//根据类目属性id查询属性信息
				AttributeNameDTO attributeNameDTO = new AttributeNameDTO();
				attributeNameDTO.setId(categoryAttNameDTO2.getAttNameId());
				AttributeNameDTO attributeName = attributeNameFacade.findByAttributeNameId(attributeNameDTO);
				attributeName.setId(categoryAttNameDTO2.getId());
				productAttName.add(AttributeNameConverter.toVO(attributeName));
			}
			//类型为2加入商品规格属性集合
			if(categoryAttNameDTO2.getType() == 2){
				//根据类目属性id查询属性信息
				AttributeNameDTO attributeNameDTO = new AttributeNameDTO();
				attributeNameDTO.setId(categoryAttNameDTO2.getAttNameId());
				AttributeNameDTO attributeName = attributeNameFacade.findByAttributeNameId(attributeNameDTO);
				attributeName.setId(categoryAttNameDTO2.getId());
				merchantProductAttName.add(AttributeNameConverter.toVO(attributeName));
			}
			
			if(categoryAttNameDTO2.getType() == 3){
				//根据类目属性id查询属性信息
				AttributeNameDTO attributeNameDTO = new AttributeNameDTO();
				attributeNameDTO.setId(categoryAttNameDTO2.getAttNameId());
				AttributeNameDTO attributeName = attributeNameFacade.findByAttributeNameId(attributeNameDTO);
				attributeName.setId(categoryAttNameDTO2.getId());
				parameterAttNameList.add(AttributeNameConverter.toVO(attributeName));
			}
			
		}
		
		CategoryDTO categoryDTO = categoryFacade.findCategoryById(id);
		Map<String, Object> map = new HashMap<>();
		map.put("id", categoryDTO.getId());
		map.put("name", categoryDTO.getName());
		map.put("description", categoryDTO.getDescription());
		map.put("parentId", categoryDTO.getParentId());
		
		map.put("productAttName", productAttName);
		map.put("merchantProductAttName", merchantProductAttName);
		map.put("parameterAttNameList", parameterAttNameList);
		map.put("pids", null);
		map.put("listSort", null);
		map.put("serialNumber", null);
		
		//根据类目id查询类目节点信息
		CategoryTreeNodeVO categoryTreeNodeVO = new CategoryTreeNodeVO();
		categoryTreeNodeVO.setCategoryId(id);
		List<CategoryTreeNodeDTO> list2 = categoryTreeNodeManage.findAll(categoryTreeNodeVO);
		//如果类目节点信息不为空，添加类目排序信息
		if(list2.size() > 0){
			map.put("listSort", list2.get(0).getListSort());
			map.put("serialNumber", StringUtils.protectCategoryProductSerialNumber(categoryDTO.getSerialNumber()));
		}
		StringBuffer pid = new StringBuffer();
		
		if(list2.size() > 0){
			pid.append(",");//为了迎合之前的逻辑
			pid = getPId(list2.get(0),pid);
		}
		if(pid.length() > 1){
			List<String> list3 = categoryFacade.idToName(pid.toString());
			StringBuffer parentId = new StringBuffer();
			for (int i = list3.size()-1; i >= 0; i--) {
				parentId.append(list3.get(i));
				parentId.append(",");
			}
			if(parentId.length() > 0){
				map.put("pids", parentId.toString().substring(0,parentId.toString().length()-1));
			}
		}
		
		List<Long> tagIds = new ArrayList<>();
		List<CategoryTagDTO> categoryTagList = categoryFacade.categoryTagAllByCategoryId(id);
		for (CategoryTagDTO categoryTagDTO : categoryTagList) {
			tagIds.add(categoryTagDTO.getTagId());
		}
		map.put("tagIds", tagIds);
		return map;
	}
	
	// 循环获取所有父类名称
    public StringBuffer getPId(CategoryTreeNodeDTO dto,StringBuffer pid) {
        if(!dto.getParentId().equals(PlatformKeyConstant.PRODUCT_PLATFORMID)){
        	
            CategoryTreeNodeDTO categoryTreeNodeDTO = categoryTreeNodeManage.CategoryTreeNodeByCategoryId(dto.getParentId());
            pid.append(categoryTreeNodeDTO.getCategoryId());
        	pid.append(",");
            getPId(categoryTreeNodeDTO,pid);
        }
        return pid;
    }

	@Override
	public List<Long> idsByCategoryId(Long id) {
		List<Long> list = new ArrayList<Long>();
		//根据类目id获取类目节点信息
		CategoryTreeNodeVO categoryTreeNodeVO = new CategoryTreeNodeVO();
		categoryTreeNodeVO.setCategoryId(id);
		//每个类目对应一个节点
		List<CategoryTreeNodeDTO> list2 = categoryTreeNodeManage.findAll(categoryTreeNodeVO);
		if(list2.size() > 0){
			list.add(list2.get(0).getId());
			list = PId(list2.get(0),list);
		}
		return list;
	}
	
	// 循环获取所有父类id
    public List<Long> PId(CategoryTreeNodeDTO dto,List<Long> pid) {
        if(!dto.getParentId().equals(PlatformKeyConstant.PRODUCT_PLATFORMID)){
        	pid.add(0,dto.getParentId());
            CategoryTreeNodeDTO categoryTreeNodeDTO = categoryTreeNodeManage.CategoryTreeNodeByCategoryId(dto.getParentId());
            PId(categoryTreeNodeDTO,pid);
        }
        return pid;
    }

}
	