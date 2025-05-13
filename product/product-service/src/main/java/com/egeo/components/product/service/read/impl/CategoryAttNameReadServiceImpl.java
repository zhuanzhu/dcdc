package com.egeo.components.product.service.read.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.common.PlatformKeyConstant;
import com.egeo.components.product.condition.CategoryAttNameCondition;
import com.egeo.components.product.converter.AttributeNameConverter;
import com.egeo.components.product.converter.AttributeValueConverter;
import com.egeo.components.product.converter.CategoryAttNameConverter;
import com.egeo.components.product.dto.AttributeNameDTO;
import com.egeo.components.product.dto.AttributeValueDTO;
import com.egeo.components.product.dto.CategoryAttNameDTO;
import com.egeo.components.product.dto.CategoryAttNameValuse;
import com.egeo.components.product.manage.read.AttributeNameReadManage;
import com.egeo.components.product.manage.read.AttributeValueReadManage;
import com.egeo.components.product.manage.read.CategoryAttNameReadManage;
import com.egeo.components.product.po.AttributeNamePO;
import com.egeo.components.product.po.AttributeValuePO;
import com.egeo.components.product.po.CategoryAttNamePO;
import com.egeo.components.product.service.read.CategoryAttNameReadService;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.log.XLogger;


@Service("categoryAttNameReadService")
public class CategoryAttNameReadServiceImpl  implements CategoryAttNameReadService {
	private static final XLogger logger = XLogger.getLogger(CategoryAttNameReadServiceImpl.class);
	@Autowired
	private CategoryAttNameReadManage categoryAttNameReadManage;
	
	@Autowired
        private AttributeNameReadManage attributeNameReadManage;
	
	@Autowired
        private AttributeValueReadManage attributeValueReadManage;

        @Override
        public List<CategoryAttNameDTO> categoryAttNameByCategoryId(CategoryAttNameDTO dto) {
            logger.info("开始进行根据节点id查询节点属性的逻辑处理！");
            List<CategoryAttNameCondition> list = categoryAttNameReadManage.categoryAttNameByCategoryId(CategoryAttNameConverter.toPO(dto));
            List<CategoryAttNameDTO> lists = new ArrayList<CategoryAttNameDTO>();
            for (CategoryAttNameCondition categoryAttNameCondition : list) {
                CategoryAttNameDTO categoryAttNameDTO = CategoryAttNameConverter.toDTO(categoryAttNameCondition);
                categoryAttNameDTO.setName(categoryAttNameCondition.getName());
                lists.add(categoryAttNameDTO);
            }
            //递归成树结构
            List<CategoryAttNameDTO> sortList = new ArrayList<CategoryAttNameDTO>();
            for (CategoryAttNameDTO tree : lists) {
                for (CategoryAttNameDTO t : lists) {
                    if (t.getParentId().equals(tree.getId())) {
                        if (tree.getLists() == null) {
                            List<CategoryAttNameDTO> mylistss = new ArrayList<CategoryAttNameDTO>();
                            mylistss.add(t);
                            tree.setLists(mylistss);
                        } else {
                            tree.getLists().add(t);
                        }
                    }
                }
                if (tree.getParentId().equals(PlatformKeyConstant.PRODUCT_PLATFORMID)) {
                    sortList.add(tree);
                }
            }
            
            return sortList;
        }

        @Override
        public CategoryAttNameDTO categoryAttNameByAttNameId(CategoryAttNameDTO dto) {
            CategoryAttNamePO categoryAttNamePO = categoryAttNameReadManage.categoryAttNameByAttNameId(CategoryAttNameConverter.toPO(dto));
            return CategoryAttNameConverter.toDTO(categoryAttNamePO);
        }

        @Override
        public CategoryAttNameDTO findById(CategoryAttNameDTO dto) {
            CategoryAttNamePO categoryAttNamePO = categoryAttNameReadManage.findById(CategoryAttNameConverter.toPO(dto));
            return CategoryAttNameConverter.toDTO(categoryAttNamePO);
        }

        @Override
        public List<CategoryAttNameValuse> categoryAttNameByCId(CategoryAttNameDTO dto) {
        	SimpleDateFormat format =   new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );  
            List<CategoryAttNameValuse> list = new ArrayList<CategoryAttNameValuse>();
            List<CategoryAttNamePO> categoryAttNameList = categoryAttNameReadManage.categoryAttNameByCId(CategoryAttNameConverter.toPO(dto));
            for (CategoryAttNamePO categoryAttNamePO : categoryAttNameList) {
                CategoryAttNameValuse categoryAttNameValuse = new CategoryAttNameValuse();
                //根据属性id查询其范围值
                //根据节点属性id查询属性信息
                AttributeNamePO attributeNamePO = new AttributeNamePO();
                attributeNamePO.setId(categoryAttNamePO.getAttNameId());
                AttributeNamePO attributeNamePO1 = attributeNameReadManage.findById(attributeNamePO);
                AttributeNameDTO attributeNameDTO = AttributeNameConverter.toDTO(attributeNamePO1);
                
                //根据类目id和属性id查询类目属性是否必填写 0否、1是
                int isRequired = categoryAttNameReadManage.isRequiredByCategoryIdAttNameId(dto.getCategoryId(),attributeNameDTO.getId());
                attributeNameDTO.setIsRequired(isRequired);
                
                if(attributeNameDTO.getMode() == 2 ){
                    Integer begin = null ;
                    Integer finish = null;
                    if ( EmptyUtil.isNotEmpty(attributeNameDTO.getBeginDecimal()) ) {
                        //String beginDecimal = attributeNameDTO.getBeginDecimal().toString().substring(0, attributeNameDTO.getBeginDecimal().toString().length()-3);
                         begin = attributeNameDTO.getBeginDecimal().intValue();
                    }
                    if ( EmptyUtil.isNotEmpty(attributeNameDTO.getFinishDecimal()) ) {
                        //String finishDecim = attributeNameDTO.getFinishDecimal().toString().substring(0, attributeNameDTO.getFinishDecimal().toString().length()-3);
                         finish = attributeNameDTO.getFinishDecimal().intValue();
                    }
                    if ( EmptyUtil.isNotEmpty(begin) && EmptyUtil.isNotEmpty(finish) ) {
                        attributeNameDTO.setReminder((isRequired == 1)?"请输入"+ begin + "~" +finish +"位字符"+"(必填)":"请输入"+ begin + "~" +finish +"位字符");
                    }

    			}else if(attributeNameDTO.getMode() == 4){
    				String beginDecimal = attributeNameDTO.getBeginDecimal().toString().substring(0, attributeNameDTO.getBeginDecimal().toString().length()-3);
    				Long begin = Long.valueOf(beginDecimal);
    				String finishDecim = attributeNameDTO.getFinishDecimal().toString().substring(0, attributeNameDTO.getFinishDecimal().toString().length()-3);
    				Long finish = Long.valueOf(finishDecim);
                    attributeNameDTO.setReminder((isRequired == 1)?"请输入"+ format.format(begin) + "~" +format.format(finish) +"时间"+"(必填)":"请输入"+ format.format(begin) + "~" +format.format(finish) +"时间");
    			}else if(attributeNameDTO.getMode() == 5){
    				String beginDecimal = attributeNameDTO.getBeginDecimal().toString().substring(0, attributeNameDTO.getBeginDecimal().toString().length()-3);
    				Integer begin = Integer.valueOf(beginDecimal);
    				String finishDecim = attributeNameDTO.getFinishDecimal().toString().substring(0, attributeNameDTO.getFinishDecimal().toString().length()-3);
    				Integer finish = Integer.valueOf(finishDecim);
                    attributeNameDTO.setReminder((isRequired == 1)?"请输入"+ begin + "~" +finish +"位整数数字"+"(必填)":"请输入"+ begin + "~" +finish +"位整数数字");
    			}else if(attributeNameDTO.getMode() == 6){
    				String beginDecimal = attributeNameDTO.getBeginDecimal().toString().substring(0, attributeNameDTO.getBeginDecimal().toString().length()-3);
    				Integer begin = Integer.valueOf(beginDecimal);
    				String finishDecim = attributeNameDTO.getFinishDecimal().toString().substring(0, attributeNameDTO.getFinishDecimal().toString().length()-3);
    				Integer finish = Integer.valueOf(finishDecim);
                    attributeNameDTO.setReminder((isRequired == 1)?"请输入"+ begin + "~" +finish +"位数字"+"(必填)":"请输入"+ begin + "~" +finish +"位数字");
    			}
                
                
                
                attributeNameDTO.setType(categoryAttNamePO.getType());
                categoryAttNameValuse.setAttributeNameDTO(attributeNameDTO);
                //根据属性id查询属性值信息
                AttributeValuePO attributeValuePO = new AttributeValuePO();
                attributeValuePO.setAttributeNameId(categoryAttNamePO.getAttNameId());
                List<AttributeValuePO> attributeValueList = attributeValueReadManage.findAll(attributeValuePO);
                List<AttributeValueDTO> valueList = AttributeValueConverter.toDTO(attributeValueList);
                for (AttributeValueDTO attributeValueDTO : valueList) {
                    attributeValueDTO.setName(attributeValueDTO.getValue());
                }
                //递归成树结构
                List<AttributeValueDTO> sortList = new ArrayList<AttributeValueDTO>();
                if(valueList.size() > 0){
                    for (AttributeValueDTO tree : valueList) {
                        for (AttributeValueDTO t : valueList) {
                            if (t.getParentId().equals(tree.getId())) {
                                if (tree.getLists() == null) {
                                    List<AttributeValueDTO> mylistss = new ArrayList<AttributeValueDTO>();
                                    mylistss.add(t);
                                    tree.setLists(mylistss);
                                } else {
                                    tree.getLists().add(t);
                                }
                            }
                        }
                        if (tree.getParentId().equals(PlatformKeyConstant.PRODUCT_PLATFORMID)) {
                            sortList.add(tree);
                        }
                    }
                }
                categoryAttNameValuse.setLists(sortList);
                list.add(categoryAttNameValuse);
            }
            return list;
        }

        @Override
        public List<CategoryAttNameDTO> findAll(CategoryAttNameDTO dto) {
            List<CategoryAttNamePO> list = categoryAttNameReadManage.findAll(CategoryAttNameConverter.toPO(dto));
            return CategoryAttNameConverter.toDTO(list);
        }

        @Override
        public List<AttributeNameDTO> merchantProductAttNameByCId(CategoryAttNameDTO dto) {
            List<AttributeNameDTO> list = new ArrayList<AttributeNameDTO>();
            List<CategoryAttNamePO> categoryAttNameList = categoryAttNameReadManage.merchantProductAttNameByCId(CategoryAttNameConverter.toPO(dto));
            for (CategoryAttNamePO categoryAttNamePO : categoryAttNameList) {
                //根据节点属性id查询属性信息
                AttributeNamePO attributeNamePO = new AttributeNamePO();
                attributeNamePO.setId(categoryAttNamePO.getAttNameId());
                AttributeNamePO attributeNamePO2 = attributeNameReadManage.findById(attributeNamePO);
                AttributeNameDTO attributeNameDTO = AttributeNameConverter.toDTO(attributeNamePO2);
                list.add(attributeNameDTO);
            }
            return list;
        }

		@Override
		public int isRequiredByCategoryIdAttNameId(Long categoryId, Long id) {
			// TODO Auto-generated method stub
			return categoryAttNameReadManage.isRequiredByCategoryIdAttNameId(categoryId, id);
		}
}
	