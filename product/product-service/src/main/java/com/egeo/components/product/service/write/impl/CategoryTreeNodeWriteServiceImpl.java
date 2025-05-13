package com.egeo.components.product.service.write.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.common.BusinessConstant;
import com.egeo.components.product.condition.CategoryTreeNodeCondition;
import com.egeo.components.product.converter.CategoryTreeNodeConverter;
import com.egeo.components.product.dto.CategoryTreeNodeDTO;
import com.egeo.components.product.manage.write.CategoryAttNameWriteManage;
import com.egeo.components.product.manage.write.CategoryTreeNodeWriteManage;
import com.egeo.components.product.po.CategoryAttNamePO;
import com.egeo.components.product.service.write.CategoryTreeNodeWriteService;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.log.XLogger;

@Service("categoryTreeNodeWriteService")
public class CategoryTreeNodeWriteServiceImpl  implements CategoryTreeNodeWriteService {
	private static final XLogger logger = XLogger.getLogger(CategoryTreeNodeWriteServiceImpl.class);
	@Autowired
	private CategoryTreeNodeWriteManage categoryTreeNodeWriteManage;
	
	@Autowired
	private CategoryAttNameWriteManage categoryAttNameWriteManage;

	@Override
	public String addCategoryTreeNodeWithTx(CategoryTreeNodeDTO dto,List<Long> tagIdList) {

		CategoryTreeNodeCondition ctcc = CategoryTreeNodeConverter.categoryConditiontoPO(dto);
		Long categoryId = categoryTreeNodeWriteManage.addCategoryTreeNodeWithTx(ctcc,tagIdList);
		if(EmptyUtil.isNotEmpty(categoryId)){
			//所有类目都必须有是否为电子卡券、是否存在unit库存、是否在app内使用,因此在创建类目的时候默认给其加上
			CategoryAttNamePO po = new CategoryAttNamePO();
			po.setSortValue(1);
			po.setCategoryId(categoryId);
			//是否为电子卡券属性id
			po.setAttNameId(BusinessConstant.IS_E_CARD_ID);
			po.setType(1);
			categoryAttNameWriteManage.addCategoryAttNameWithTx(po);
			
			CategoryAttNamePO categoryAttNamePO = new CategoryAttNamePO();
			categoryAttNamePO.setSortValue(1);
			categoryAttNamePO.setCategoryId(categoryId);
			categoryAttNamePO.setType(1);
			//是否存在unit库存属性id
			categoryAttNamePO.setAttNameId(BusinessConstant.IS_UNIT_INVENTORY_ID);
			categoryAttNameWriteManage.addCategoryAttNameWithTx(categoryAttNamePO);
			
			CategoryAttNamePO categoryAttNamePO1 = new CategoryAttNamePO();
			categoryAttNamePO1.setSortValue(1);
			categoryAttNamePO1.setCategoryId(categoryId);
			categoryAttNamePO1.setType(1);
			//是否存在unit库存属性id
			categoryAttNamePO1.setAttNameId(BusinessConstant.IS_APP_USE_ID);
			categoryAttNameWriteManage.addCategoryAttNameWithTx(categoryAttNamePO1);
			
			CategoryAttNamePO categoryAttNamePO2 = new CategoryAttNamePO();
			categoryAttNamePO2.setSortValue(1);
			categoryAttNamePO2.setCategoryId(categoryId);
			categoryAttNamePO2.setType(1);
			//第三方对接参数
			categoryAttNamePO2.setAttNameId(BusinessConstant.THIRDPARTY_BUTT_JOINT_PARAMETER);
			categoryAttNameWriteManage.addCategoryAttNameWithTx(categoryAttNamePO2);
			
			CategoryAttNamePO categoryAttNamePO3 = new CategoryAttNamePO();
			categoryAttNamePO3.setSortValue(1);
			categoryAttNamePO3.setCategoryId(categoryId);
			categoryAttNamePO3.setType(1);
			//搜索关键词属性
			categoryAttNamePO3.setAttNameId(BusinessConstant.SEARCH_KEYWORD);
			categoryAttNameWriteManage.addCategoryAttNameWithTx(categoryAttNamePO3);
			return 1+"";
		}else{
			return 0+"";
		}
		
	}

	@Override
	public int updateCategoryTreeNodeWithTx(CategoryTreeNodeDTO categoryTreeNodeDTO) {
		
		return categoryTreeNodeWriteManage.updateCategoryTreeNodeWithTx(CategoryTreeNodeConverter.toPO(categoryTreeNodeDTO));
	}

	@Override
	public int deleteByIdWithTx(CategoryTreeNodeDTO categoryTreeNodeDTO) {
		
		return categoryTreeNodeWriteManage.deleteByIdWithTx(CategoryTreeNodeConverter.toPO(categoryTreeNodeDTO));
	}
	/**
	 * 添加前台类目节点信息
	 * @param categoryTreeNodeVO
	 * @return
	 */
	@Override
	public Long saveCategoryTreeNodeWithTx(CategoryTreeNodeDTO dto) {
		CategoryTreeNodeCondition ctcc = CategoryTreeNodeConverter.categoryConditiontoPO(dto);
		return categoryTreeNodeWriteManage.saveCategoryTreeNodeWithTx(ctcc);
	}
	/**
	 * 根据类目节点id修改前台类目节点信息
	 * @param categoryTreeNodeVO
	 * @param req
	 * @return
	 */
	@Override
	public boolean updateCategoryTreeNodeCategoryWithTx(CategoryTreeNodeDTO dto) {
		CategoryTreeNodeCondition ctcc = CategoryTreeNodeConverter.categoryConditiontoPO(dto);
		return categoryTreeNodeWriteManage.updateCategoryTreeNodeCategoryWithTx(ctcc);
	}
	/**
	 * 根据前台类目节点id删除前台类目节点及子节点信息
	 * @param req
	 * @return
	 */
	@Override
	public boolean delByCategoryTreeNodeIdWithTx(Long categoryTreeNodeId) {
		// TODO Auto-generated method stub
		return categoryTreeNodeWriteManage.delByCategoryTreeNodeIdWithTx(categoryTreeNodeId);
	}

	@Override
	public Long insertCategoryTreeNodeWithTx(CategoryTreeNodeDTO ctnDTO, String name, String catId) {
		return categoryTreeNodeWriteManage.insertCategoryTreeNodeWithTx(CategoryTreeNodeConverter.categoryConditiontoPO(ctnDTO), name,catId);
	}
}
	