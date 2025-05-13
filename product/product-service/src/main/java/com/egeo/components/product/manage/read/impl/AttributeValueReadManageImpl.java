package com.egeo.components.product.manage.read.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.read.AttributeValueReadManage;
import com.egeo.components.product.dao.read.AttributeValueReadDAO;
import com.egeo.components.product.po.AttributeValuePO;
import com.egeo.utils.EmptyUtil;

@Service
public class AttributeValueReadManageImpl implements AttributeValueReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private AttributeValueReadDAO attributeValueReadDAO;
	
        @Override
        public AttributeValuePO findById(AttributeValuePO po) {
            return attributeValueReadDAO.findById(po);
        }

        @Override
        public List<AttributeValuePO> findAll(AttributeValuePO po) {
            return attributeValueReadDAO.findAll(po,null);
        }
        /**
         * 根据属性id查询属性值排序最大的值
         * @param attributeNameId
         * @return
         */
		@Override
		public Integer maxSortValue(Long attributeNameId) {
			return attributeValueReadDAO.maxSortValue(attributeNameId);
		}
		/**
		 * 根据属性值集合和属性id查询属性值信息
		 * @param ids
		 * @param attNameId
		 * @return
		 */
		@Override
		public List<AttributeValuePO> findByProductIdAndAttNameId(List<Long> ids, Long attNameId) {
			if(EmptyUtil.isEmpty(ids)){
				ids = null;
			}
			return attributeValueReadDAO.findByProductIdAndAttNameId(ids, attNameId);
		}
		/**
		 * 根据spu草稿id和属性id查询属性值信息
		 * @param productId
		 * @param attNameId
		 * @return
		 */
		@Override
		public List<AttributeValuePO> attributeValueByProductIdAndAttNameId(Long productId, Long attNameId) {
			// TODO Auto-generated method stub
			return attributeValueReadDAO.attributeValueByProductIdAndAttNameId(productId, attNameId);
		}
		/**
		 * 根据属性id和sup草稿id查询spu属性值信息
		 * @param productId
		 * @param id
		 * @return
		 */
		@Override
		public List<AttributeValuePO> spuAttributeValueByProductIdAndAttNameId(Long productId, Long attNameId) {
			// TODO Auto-generated method stub
			return attributeValueReadDAO.spuAttributeValueByProductIdAndAttNameId(productId, attNameId);
		}
		/**
		 * 根据属性值id集合查询属性值信息
		 * @param puids
		 * @return
		 */
		@Override
		public List<AttributeValuePO> findByAttributeValueIds(List<Long> attributeValueIds) {
			// TODO Auto-generated method stub
			return attributeValueReadDAO.findByAttributeValueIds(attributeValueIds);
		}

	@Override
	public Long findLastId() {
		return attributeValueReadDAO.findLastId();
	}

}
	