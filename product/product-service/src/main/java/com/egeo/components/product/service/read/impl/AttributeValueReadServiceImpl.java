package com.egeo.components.product.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.service.read.AttributeValueReadService;
import com.egeo.components.product.manage.read.AttributeValueReadManage;
import com.egeo.components.product.converter.AttributeValueConverter;
import com.egeo.components.product.dto.AttributeValueDTO;
import com.egeo.components.product.po.AttributeValuePO;


@Service("attributeValueReadService")
public class AttributeValueReadServiceImpl  implements AttributeValueReadService {
	@Autowired
	private AttributeValueReadManage attributeValueReadManage;

        @Override
        public AttributeValueDTO findById(AttributeValueDTO dto) {
			AttributeValuePO attributeValuePO = attributeValueReadManage.findById(AttributeValueConverter.toPO(dto));
            return AttributeValueConverter.toDTO(attributeValuePO);
        }

        @Override
        public List<AttributeValueDTO> findAll(AttributeValueDTO dto) {
            List<AttributeValuePO> list = attributeValueReadManage.findAll(AttributeValueConverter.toPO(dto));
            return AttributeValueConverter.toDTO(list);
        }
        /**
         * 根据属性id查询属性值排序最大的值
         * @param attributeNameId
         * @return
         */
		@Override
		public Integer maxSortValue(Long attributeNameId) {
			return attributeValueReadManage.maxSortValue(attributeNameId);
		}
		/**
		 * 根据属性值集合和属性id查询属性值信息
		 * @param ids
		 * @param attNameId
		 * @return
		 */
		@Override
		public List<AttributeValueDTO> findByProductIdAndAttNameId(List<Long> ids, Long attNameId) {
			List<AttributeValuePO> list = attributeValueReadManage.findByProductIdAndAttNameId(ids, attNameId);
			return AttributeValueConverter.toDTO(list);
		}
		/**
		 * 根据spu草稿id和属性id查询属性值信息
		 * @param productId
		 * @param attNameId
		 * @return
		 */
		@Override
		public List<AttributeValueDTO> attributeValueByProductIdAndAttNameId(Long productId, Long attNameId) {
			List<AttributeValuePO> list = attributeValueReadManage.attributeValueByProductIdAndAttNameId(productId, attNameId) ;
			return AttributeValueConverter.toDTO(list);
		}
		/**
		 * 根据属性id和sup草稿id查询spu属性值信息
		 * @param productId
		 * @param id
		 * @return
		 */
		@Override
		public List<AttributeValueDTO> spuAttributeValueByProductIdAndAttNameId(Long productId, Long attNameId) {
			List<AttributeValuePO> list = attributeValueReadManage.spuAttributeValueByProductIdAndAttNameId(productId, attNameId);
			return AttributeValueConverter.toDTO(list);
		}
		/**
		 * 根据属性值id集合查询属性值信息
		 * @param puids
		 * @return
		 */
		@Override
		public List<AttributeValueDTO> findByAttributeValueIds(List<Long> attributeValueIds) {
			List<AttributeValuePO> list = attributeValueReadManage.findByAttributeValueIds(attributeValueIds);
			return AttributeValueConverter.toDTO(list);
		}

	@Override
	public Long findLastId() {
		return attributeValueReadManage.findLastId();
	}
}
	