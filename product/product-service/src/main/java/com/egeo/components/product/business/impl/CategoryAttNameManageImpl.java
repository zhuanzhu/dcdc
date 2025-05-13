package com.egeo.components.product.business.impl;
	

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.egeo.common.BusinessConstant;
import com.egeo.common.PlatformKeyConstant;
import com.egeo.components.product.business.CategoryAttNameManage;
import com.egeo.components.product.business.CategoryTreeNodeManage;
import com.egeo.components.product.converter.AttributeNameConverter;
import com.egeo.components.product.converter.CategoryAttNameConverter;
import com.egeo.components.product.dto.AttributeNameDTO;
import com.egeo.components.product.dto.AttributeValueDTO;
import com.egeo.components.product.dto.CategoryAttNameDTO;
import com.egeo.components.product.dto.CategoryAttNameValuse;
import com.egeo.components.product.dto.CategoryTreeNodeDTO;
import com.egeo.components.product.facade.AttributeNameFacade;
import com.egeo.components.product.facade.AttributeValueFacade;
import com.egeo.components.product.facade.CategoryAttNameFacade;
import com.egeo.components.product.vo.ApecificationAndAtt;
import com.egeo.components.product.vo.AttributeNameVO;
import com.egeo.components.product.vo.CategoryAttName;
import com.egeo.components.product.vo.CategoryAttNameVO;
import com.egeo.exception.BusinessException;
import com.egeo.utils.EmptyUtil;

@Service("categoryAttName")
public class CategoryAttNameManageImpl implements CategoryAttNameManage{

	
	@Resource(name="categoryAttNameFacade")
	private CategoryAttNameFacade categoryAttNameFacade;
	
	@Resource(name="categoryTreeNode")
    private CategoryTreeNodeManage categoryTreeNodeManage;
	
	@Resource(name="attributeNameFacade")
	private AttributeNameFacade attributeNameFacade;
	
	@Resource(name="attributeValueFacade")
	private AttributeValueFacade attributeValueFacade;

        // 循环获取pid
        public List<Long> getPId(CategoryTreeNodeDTO dto,List<Long> list) {
            if(!dto.getParentId().equals(PlatformKeyConstant.PRODUCT_PLATFORMID)){
                list.add(0,dto.getParentId());
                CategoryTreeNodeDTO categoryTreeNodeDTO = categoryTreeNodeManage.CategoryTreeNodeByCategoryId(dto.getParentId());
                getPId(categoryTreeNodeDTO,list);
            }
            return list;
        }



        @Override
        public CategoryAttNameDTO categoryAttNameByAttNameId(CategoryAttNameVO categoryAttNameVO) {
            return categoryAttNameFacade.categoryAttNameByAttNameId(CategoryAttNameConverter.toDTO(categoryAttNameVO));
        }



        @Override
        public CategoryAttNameDTO findById(CategoryAttNameVO categoryAttNameVO) {
            return categoryAttNameFacade.findById(CategoryAttNameConverter.toDTO(categoryAttNameVO));
        }



		@Override
		public String addCategoryAttName(CategoryAttNameDTO dto, HttpServletRequest req) {
			if(EmptyUtil.isEmpty(dto.getParentId())){
				dto.setParentId((long) 0);
			}

		
			return categoryAttNameFacade.addCategoryAttNameWithTx(dto);
		}



        @Override
        public List<CategoryAttNameDTO> findAll(CategoryAttNameVO categoryAttNameVO) {
            return categoryAttNameFacade.findAll(CategoryAttNameConverter.toDTO(categoryAttNameVO));
        }



        @Override
        public ApecificationAndAtt categoryAttNameByCId(CategoryAttNameVO categoryAttNameVO) {
        	ApecificationAndAtt apecificationAndAtt = new ApecificationAndAtt();
        	List<CategoryAttNameValuse> categoryAttNameValuseList = categoryAttNameFacade.categoryAttNameByCId(CategoryAttNameConverter.toDTO(categoryAttNameVO));
        	List<CategoryAttNameValuse> attList = new ArrayList<>();
        	List<CategoryAttNameValuse> parameterAttList = new ArrayList<>();
        	for (CategoryAttNameValuse categoryAttNameValuse : categoryAttNameValuseList) {
        		//设置默认值、默认为否
        		switch (Integer.valueOf(categoryAttNameValuse.getAttributeNameDTO().getId().toString())) {
				case 1:
					categoryAttNameValuse.setValue(2);
					break;
				case 2:
					categoryAttNameValuse.setValue(4);
					break;
				case 3:
					categoryAttNameValuse.setValue(6);
					break;
				case 4:
					categoryAttNameValuse.setValue(7);
					break;

				default:
					break;
				}
				if(categoryAttNameValuse.getAttributeNameDTO().getType() == 1){
					if(categoryAttNameValuse.getAttributeNameDTO().getId().equals(BusinessConstant.THIRDPARTY_BUTT_JOINT_PARAMETER)){
						List<AttributeValueDTO> attributeValueList = categoryAttNameValuse.getLists();
						if(EmptyUtil.isNotEmpty(attributeValueList)){
							for (AttributeValueDTO attributeValueDTO : attributeValueList) {
								if(attributeValueDTO.getId().equals(7L) || attributeValueDTO.getId().equals(12L)){
									attributeValueDTO.setAttributeNameId(3L);
								}else{
									attributeValueDTO.setAttributeNameId(1L);
								}
								
							}
						}
					}
					attList.add(categoryAttNameValuse);
				}else if(categoryAttNameValuse.getAttributeNameDTO().getType() == 3){
					parameterAttList.add(categoryAttNameValuse);
				}
			}
        	List<AttributeNameDTO> merchantProductAttNameByCId = categoryAttNameFacade.merchantProductAttNameByCId(CategoryAttNameConverter.toDTO(categoryAttNameVO));
        	apecificationAndAtt.setAttList(attList);
        	apecificationAndAtt.setApecificationList(AttributeNameConverter.toVO(merchantProductAttNameByCId));
        	apecificationAndAtt.setParameterAttList(parameterAttList);
            return apecificationAndAtt;
        }



		@Override
		public String deleteCategoryAttName(CategoryAttNameDTO categoryAttNameDTO) {
			//根据类目属性Id查询类目属性信息
			CategoryAttNameDTO categoryAttNameDTO2 = categoryAttNameFacade.findById(categoryAttNameDTO);
			if(categoryAttNameDTO2.getAttNameId().equals(BusinessConstant.IS_E_CARD_ID)){
				throw new BusinessException("是否为电子卡券属性不能删除");
			}
			if(categoryAttNameDTO2.getAttNameId().equals(BusinessConstant.IS_UNIT_INVENTORY_ID)){
				throw new BusinessException("是否存在unit库存属性不能删除");
			}
			if(categoryAttNameDTO2.getAttNameId().equals(BusinessConstant.IS_APP_USE_ID)){
				throw new BusinessException("是否app使用属性不能删除");
			}
			if(categoryAttNameDTO2.getAttNameId().equals(BusinessConstant.THIRDPARTY_BUTT_JOINT_PARAMETER)){
				throw new BusinessException("第三方对接参数属性不能删除");
			}
			if(categoryAttNameDTO2.getAttNameId().equals(BusinessConstant.SEARCH_KEYWORD)){
				throw new BusinessException("搜索关键词属性不能删除");
			}
			return categoryAttNameFacade.deleteCategoryAttNameWithTx(categoryAttNameDTO);
		}



        @Override
        public List<AttributeNameVO> merchantProductAttNameByCId(CategoryAttNameVO categoryAttNameVO) {
        	List<AttributeNameDTO> attributeNameList = categoryAttNameFacade.merchantProductAttNameByCId(CategoryAttNameConverter.toDTO(categoryAttNameVO));
            return AttributeNameConverter.toVO(attributeNameList);
        }



		@Override
		public String saveCategoryAttNameByCategoryId(Long categoryId,Integer type, List<CategoryAttNameDTO> lists,Long platformId,HttpServletRequest req) {
			//批量添加类目属性
			for (CategoryAttNameDTO categoryAttNameDTO : lists) {
				categoryAttNameDTO.setCategoryId(categoryId);
				categoryAttNameDTO.setType(type);
				categoryAttNameDTO.setPlatformId(platformId);
				this.addCategoryAttName(categoryAttNameDTO, req);
			}
			return "批量添加类目属性成功！";
		}



		@Override
		public String updateCategoryAttNameByCategoryId(List<CategoryAttName> lists) {
			//批量修改是否必填及排序
			for (CategoryAttName categoryAttName : lists) {
				//根据类目属性id查询类目属性
				CategoryAttNameDTO categoryAttNameDTO = new CategoryAttNameDTO();
				categoryAttNameDTO.setId(categoryAttName.getId());
				CategoryAttNameDTO findById = categoryAttNameFacade.findById(categoryAttNameDTO);
				findById.setSortValue(categoryAttName.getSortValue());
				findById.setIsRequired((categoryAttName.isChecked())?1:0);
				Long id = categoryAttNameFacade.updaCategoryAttName(findById);
			}
			return "批量修改是否必填成功！";
		}



		@Override
		public List<CategoryAttName> showCategoryAttName(Long categoryId, Integer type) {
			List<CategoryAttName> lists = new ArrayList<CategoryAttName>();
			//根据类目属性id及类目类型查询类目属性
			CategoryAttNameDTO categoryAttNameDTO = new CategoryAttNameDTO();
			categoryAttNameDTO.setCategoryId(categoryId);
			categoryAttNameDTO.setType(type);
			List<CategoryAttNameDTO> list = categoryAttNameFacade.findAll(categoryAttNameDTO);
			
			for (CategoryAttNameDTO categoryAttNameDTO2 : list) {
				CategoryAttName categoryAttName = new CategoryAttName();
				
				//根据属性id查询属性信息
				AttributeNameDTO attributeNameDTO = new AttributeNameDTO();
				attributeNameDTO.setId(categoryAttNameDTO2.getAttNameId());
				AttributeNameVO attributeNameVO = attributeNameFacade.findById(attributeNameDTO);
				
				categoryAttName.setId(categoryAttNameDTO2.getId());
				categoryAttName.setName(attributeNameVO.getName());
				categoryAttName.setSortValue(categoryAttNameDTO2.getSortValue());
				if(categoryAttNameDTO2.getIsRequired() == 0){
					categoryAttName.setChecked(false);
				}else if(categoryAttNameDTO2.getIsRequired() == 1){
					categoryAttName.setChecked(true);
				}
				
				lists.add(categoryAttName);
			}
			return lists;
		}
	


}
	