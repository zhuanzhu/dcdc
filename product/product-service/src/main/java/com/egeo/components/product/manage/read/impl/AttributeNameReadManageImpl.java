package com.egeo.components.product.manage.read.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.read.AttributeNameReadManage;
import com.egeo.components.product.dao.read.AttributeNameReadDAO;
import com.egeo.components.product.po.AttributeNamePO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service
public class AttributeNameReadManageImpl implements AttributeNameReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private AttributeNameReadDAO attributeNameReadDAO;
	
        @Override
        public AttributeNamePO findById(AttributeNamePO po) {
            return attributeNameReadDAO.findByAttributeNameId(po);
        }

        @Override
        public List<AttributeNamePO> findAll(AttributeNamePO po) {
            return attributeNameReadDAO.findAll(po,null);
        }

        @Override
        public PageResult<AttributeNamePO> findPage(Pagination page, AttributeNamePO po) {
            int cnt = attributeNameReadDAO.countOfPage(po);
            List<AttributeNamePO> list = new ArrayList<AttributeNamePO>();
            if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
                page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
                list = attributeNameReadDAO.findOfPage(po, page);
            }
            PageResult<AttributeNamePO> pageResult = new PageResult<AttributeNamePO>();
            pageResult.setList(list);
            pageResult.setTotalSize(cnt);
            pageResult.setPageNo(page.getPageNo());
            pageResult.setPageSize(page.getPageSize());
            return pageResult;
        }

		@Override
		public List<AttributeNamePO> findByIds(String ids,Integer specificationProperty,Integer parameterProperty,String name) {
			return attributeNameReadDAO.findByIds(ids,specificationProperty,parameterProperty,name);
		}

		@Override
		public List<AttributeNamePO> findAllByName(String name, String ids) {
			return attributeNameReadDAO.findAllByName(name, ids);
		}

		@Override
		public List<AttributeNamePO> queryAttributeNamesByIds(List<Long> nameIds) {
			return attributeNameReadDAO.queryAttributeNamesByIds(nameIds);
		}
		/**
		 * 根据类目id查询属性信息
		 * @param categoryId
		 * @return
		 */
		@Override
		public List<AttributeNamePO> findByCategoryId(Long categoryId) {
			// TODO Auto-generated method stub
			return attributeNameReadDAO.findByCategoryId(categoryId);
		}
		/**
		 * 根据spu草稿id查询属性和属性值信息
		 * @param productId
		 * @return
		 */
		@Override
		public List<AttributeNamePO> findByProductId(Long productId) {
			// TODO Auto-generated method stub
			return attributeNameReadDAO.findByProductId(productId);
		}
		/**
		 * 根据属性名称查询属性信息
		 * @param name
		 * @return
		 */
		@Override
		public AttributeNamePO findByAttName(String name) {
			// TODO Auto-generated method stub
			return attributeNameReadDAO.findByAttName(name);
		}
	
}
	