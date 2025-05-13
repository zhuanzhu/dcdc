package com.egeo.components.product.business.impl;
	

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.egeo.components.product.business.AttributeValueManage;
import com.egeo.components.product.converter.AttributeValueConverter;
import com.egeo.components.product.dto.AttributeValueDTO;
import com.egeo.components.product.dto.ProductAttNameDTO;
import com.egeo.components.product.dto.ProductAttValueDTO;
import com.egeo.components.product.facade.AttributeValueFacade;
import com.egeo.components.product.facade.ProductAttNameFacade;
import com.egeo.components.product.facade.ProductAttValueFacade;
import com.egeo.components.product.vo.AttributeValueVO;
import com.egeo.utils.EmptyUtil;

@Service("attributeValue")
public class AttributeValueManageImpl implements AttributeValueManage{

	
	@Resource(name="attributeValueFacade")
	private AttributeValueFacade attributeValueFacade;
	
	@Resource(name="productAttNameFacade")
	private ProductAttNameFacade productAttNameFacade;
	
	@Resource(name="productAttValueFacade")
	private ProductAttValueFacade productAttValueFacade;

        @Override
        public Long saveAttributeValue(AttributeValueVO attributeValueVO) {
            
            return attributeValueFacade.saveAttributeValue(AttributeValueConverter.toDTO(attributeValueVO));
        }

        @Override
        public AttributeValueDTO findById(AttributeValueVO attributeValueVO) {
            return attributeValueFacade.findById(AttributeValueConverter.toDTO(attributeValueVO));
        }

		@Override
		public Integer deleteById(AttributeValueVO attributeValueVO) {
			return attributeValueFacade.deleteById(AttributeValueConverter.toDTO(attributeValueVO));
		}

		@Override
		public List<AttributeValueVO> findByAttributeNameId(AttributeValueVO attributeValueVO) {
			List<AttributeValueDTO> list = attributeValueFacade.findAll(AttributeValueConverter.toDTO(attributeValueVO));
			return AttributeValueConverter.toVO(list);
		}
		/**
		 * 根据sup草稿id和属性id查询属性值信息
		 */
		@Override
		public List<AttributeValueVO> findByProductIdAndAttNameId(Long productId, Long attNameId) {
			if(EmptyUtil.isEmpty(productId)){
				AttributeValueDTO attributeValueDTO = new AttributeValueDTO();
				attributeValueDTO.setAttributeNameId(attNameId);
				List<AttributeValueDTO> list = attributeValueFacade.findAll(attributeValueDTO);
				return AttributeValueConverter.toVO(list);
			}else{
				ProductAttNameDTO productAttNameDTO = new ProductAttNameDTO();
				productAttNameDTO.setProductId(productId);
				productAttNameDTO.setAttNameId(attNameId);
				List<ProductAttNameDTO> ProductAttNameList = productAttNameFacade.findAll(productAttNameDTO);
				List<Long> ids = new ArrayList<>();
				if(EmptyUtil.isNotEmpty(ProductAttNameList)){
					//根据sup草稿属性id查询属性值id
					ProductAttValueDTO productAttValueDTO = new ProductAttValueDTO();
					productAttValueDTO.setProductAttNameId(ProductAttNameList.get(0).getId());
					List<ProductAttValueDTO> productAttValueList = productAttValueFacade.findAll(productAttValueDTO);
					
					for (ProductAttValueDTO productAttValueDTO2 : productAttValueList) {
						ids.add(productAttValueDTO2.getAttValueId());
					}
				}
				List<AttributeValueDTO> list = null;
				list = attributeValueFacade.findByProductIdAndAttNameId(ids, attNameId);
				return AttributeValueConverter.toVO(list);
			}
		}

		@Override
		public List<AttributeValueVO> attributeValueByProductIdAndAttNameId(Long productId, Long attNameId) {
			List<AttributeValueDTO> attributeValueList = attributeValueFacade.attributeValueByProductIdAndAttNameId(productId,attNameId);
			return AttributeValueConverter.toVO(attributeValueList);
		}
	


}
	