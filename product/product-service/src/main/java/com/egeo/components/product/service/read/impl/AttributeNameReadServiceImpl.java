package com.egeo.components.product.service.read.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.converter.AttributeNameConverter;
import com.egeo.components.product.dto.AttributeNameDTO;
import com.egeo.components.product.manage.read.AttributeNameReadManage;
import com.egeo.components.product.po.AttributeNamePO;
import com.egeo.components.product.service.read.AttributeNameReadService;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.log.XLogger;

@Service("attributeNameReadService")
public class AttributeNameReadServiceImpl  implements AttributeNameReadService {
	private static final XLogger logger = XLogger.getLogger(AttributeNameReadServiceImpl.class);
	@Autowired
	private AttributeNameReadManage attributeNameReadManage;

        @Override
        public AttributeNameDTO findById(AttributeNameDTO dto) {
        	AttributeNamePO attributeNamePO = attributeNameReadManage.findById(AttributeNameConverter.toPO(dto));
        	return AttributeNameConverter.toDTO(attributeNamePO);
        }

        @Override
        public List<AttributeNameDTO> findAll(AttributeNameDTO dto) {
            List<AttributeNamePO> list = attributeNameReadManage.findAll(AttributeNameConverter.toPO(dto));
            return AttributeNameConverter.toDTO(list);
        }

        @Override
        public PageResult<AttributeNameDTO> findPageAttributeName(Pagination page, AttributeNameDTO dto) {
        	logger.info("分页查询属性：[findPageAttributeName] 参数 "+dto);
            AttributeNamePO po = AttributeNameConverter.toPO(dto);
            PageResult<AttributeNamePO> pageResult = attributeNameReadManage.findPage(page, po);
            
            List<AttributeNameDTO> list = new ArrayList<AttributeNameDTO>();
            for (AttributeNamePO tmp : pageResult.getList()) {
                AttributeNameDTO attributeNameDTO = AttributeNameConverter.toDTO(tmp);
                    list.add(attributeNameDTO);
            }
            PageResult<AttributeNameDTO> result = new PageResult<AttributeNameDTO>();
            result.setList(list);
            result.setPageNo(pageResult.getPageNo());
            result.setPageSize(pageResult.getPageSize());
            result.setTotalSize(pageResult.getTotalSize());
            return result;
        }

		@Override
		public List<AttributeNameDTO> findByIds(String ids,Integer specificationProperty,Integer parameterProperty,String name) {
			List<AttributeNamePO> list = attributeNameReadManage.findByIds(ids,specificationProperty,parameterProperty,name);
			return AttributeNameConverter.toDTO(list);
		}

		@Override
		public List<AttributeNameDTO> findAllByName(String name, String ids) {
			List<AttributeNamePO> list = attributeNameReadManage.findAllByName(name, ids);
			return AttributeNameConverter.toDTO(list);
		}
		/**
		 * 根据类目id查询属性信息
		 * @param categoryId
		 * @return
		 */
		@Override
		public List<AttributeNameDTO> findByCategoryId(Long categoryId) {
			List<AttributeNamePO> list = attributeNameReadManage.findByCategoryId(categoryId);
			return AttributeNameConverter.toDTO(list);
		}
		/**
		 * 根据spu草稿id查询属性和属性值信息
		 * @param productId
		 * @return
		 */
		@Override
		public List<AttributeNameDTO> findByProductId(Long productId) {
			List<AttributeNamePO> list = attributeNameReadManage.findByProductId(productId);
			return AttributeNameConverter.toDTO(list);
		}
		/**
		 * 根据属性名称查询属性信息
		 * @param name
		 * @return
		 */
		@Override
		public AttributeNameDTO findByAttName(String name) {
			AttributeNamePO attributeNamePO = attributeNameReadManage.findByAttName(name);
			return AttributeNameConverter.toDTO(attributeNamePO);
		}
}
	