package com.egeo.components.product.manage.read.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.read.CategoryAttNameReadManage;
import com.egeo.components.product.condition.CategoryAttNameCondition;
import com.egeo.components.product.dao.read.CategoryAttNameReadDAO;
import com.egeo.components.product.po.CategoryAttNamePO;
import com.egeo.exception.BusinessException;
import com.egeo.utils.EmptyUtil;

@Service
public class CategoryAttNameReadManageImpl implements CategoryAttNameReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CategoryAttNameReadDAO categoryAttNameReadDAO;
	
        @Override
        public List<CategoryAttNameCondition> categoryAttNameByCategoryId(CategoryAttNamePO po) {
            return categoryAttNameReadDAO.categoryAttNameByCategoryId(po);
        }

        @Override
        public CategoryAttNamePO categoryAttNameByAttNameId(CategoryAttNamePO po) {
            return categoryAttNameReadDAO.categoryAttNameByAttNameId(po);
        }

        @Override
        public CategoryAttNamePO findById(CategoryAttNamePO po) {
            return categoryAttNameReadDAO.findById(po);
        }

        @Override
        public List<CategoryAttNamePO> findAll(CategoryAttNamePO po) {
            return categoryAttNameReadDAO.findAll(po,null);
        }

        @Override
        public List<CategoryAttNamePO> categoryAttNameByCId(CategoryAttNamePO po) {
            return categoryAttNameReadDAO.categoryAttNameByCId(po);
        }

        @Override
        public List<CategoryAttNamePO> merchantProductAttNameByCId(CategoryAttNamePO po) {
            return categoryAttNameReadDAO.merchantProductAttNameByCId(po);
        }
        /**
         * 根据类目id和属性id查询类目属性是否必填写 0否、1是
         * @param categoryId
         * @param attNameId
         * @return
         */
		@Override
		public int isRequiredByCategoryIdAttNameId(Long categoryId, Long attNameId) {
			if(EmptyUtil.isEmpty(categoryId)){
				throw new BusinessException("根据类目id和属性id查询类目属性是否必填写,类目id不能为空");
			}
			if(EmptyUtil.isEmpty(attNameId)){
				throw new BusinessException("根据类目id和属性id查询类目属性是否必填写,属性id不能为空");
			}
			return categoryAttNameReadDAO.isRequiredByCategoryIdAttNameId(categoryId, attNameId);
		}
	
}
	