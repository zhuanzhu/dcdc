package com.egeo.components.product.manage.read.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.read.CategoryReadManage;
import com.egeo.components.product.condition.CategoryCondition;
import com.egeo.components.product.dao.read.CategoryReadDAO;
import com.egeo.components.product.po.CategoryPO;

@Service
public class CategoryReadManageImpl implements CategoryReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CategoryReadDAO categoryReadDAO;
        @Override
        public List<CategoryCondition> findAll(CategoryPO po) {
            return categoryReadDAO.find(po);
        }
        @Override
        public CategoryPO findById(CategoryPO categoryPO) {
            return categoryReadDAO.findById(categoryPO);
        }
        @Override
        public List<CategoryPO> findAllList(CategoryPO po) {
            return categoryReadDAO.findAll(po,null);
        }
		@Override
		public CategoryPO findCategoryById(Long id) {
			CategoryPO po = new CategoryPO();
			po.setId(id);
			return categoryReadDAO.findById(po);
		}
		/**
		 * 根据上级节点信息查询类目信息
		 * @param parentId
		 * @return
		 */
		@Override
		public CategoryPO categoryByPIdNode(Long parentId) {
			return categoryReadDAO.categoryByPIdNode(parentId);
		}
		
		@Override
		public List<String> findCategoryNameBySuId(Long suId) {
			return categoryReadDAO.findCategoryNameBySuId(suId);
		}
	
}
	