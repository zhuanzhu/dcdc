package com.egeo.components.product.facade;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.egeo.common.BusinessExceptionConstant;
import com.egeo.components.product.dto.AttributeValueDTO;
import com.egeo.components.product.dto.ProductAttValueDTO;
import com.egeo.components.product.service.read.AttributeValueReadService;
import com.egeo.components.product.service.read.ProductAttValueReadService;
import com.egeo.components.product.service.write.AttributeValueWriteService;
import com.egeo.exception.BusinessException;


@Component
public class AttributeValueFacade {
	
	@Resource
	private AttributeValueReadService attributeValueReadService;
	
	@Resource
    private AttributeValueWriteService attributeValueWriteService;
	
	@Resource
	private ProductAttValueReadService productAttValueReadService;

        public Long saveAttributeValue(AttributeValueDTO dto) {
            return attributeValueWriteService.saveAttributeValueWithTx(dto);
        }

        public AttributeValueDTO findById(AttributeValueDTO dto) {
            return attributeValueReadService.findById(dto);
        }

		public Integer deleteById(AttributeValueDTO dto) {
			//根据id查询是否被产品引用
			ProductAttValueDTO productAttValueDTO = new ProductAttValueDTO();
			productAttValueDTO.setAttValueId(dto.getId());
			List<ProductAttValueDTO> list = productAttValueReadService.findAll(productAttValueDTO);
			if(list.size() > 0){
				throw new BusinessException(BusinessExceptionConstant.NO_DEL_ATTVALUE, "该属性值被产品引用，不能删除！");
			}
			return attributeValueWriteService.deleteByIdWithTx(dto);
		}

		public List<AttributeValueDTO> findAll(AttributeValueDTO attributeValueDTO) {
			return attributeValueReadService.findAll(attributeValueDTO);
		}
		/**
		 * 根据属性值集合和属性id查询属性值信息
		 * @param ids
		 * @param attNameId
		 * @return
		 */
		public List<AttributeValueDTO> findByProductIdAndAttNameId(List<Long> ids, Long attNameId) {
			// TODO Auto-generated method stub
			return attributeValueReadService.findByProductIdAndAttNameId(ids, attNameId);
		}
		/**
		 * 根据spu草稿id和属性id查询属性值信息
		 * @param productId
		 * @param attNameId
		 * @return
		 */
		public List<AttributeValueDTO> attributeValueByProductIdAndAttNameId(Long productId, Long attNameId) {
			// TODO Auto-generated method stub
			return attributeValueReadService.attributeValueByProductIdAndAttNameId(productId, attNameId);
		}
		/**
		 * 根据属性id和sup草稿id查询spu属性值信息
		 * @param productId
		 * @param id
		 * @return
		 */
		public List<AttributeValueDTO> spuAttributeValueByProductIdAndAttNameId(Long productId, Long attNameId) {
			// TODO Auto-generated method stub
			return attributeValueReadService.spuAttributeValueByProductIdAndAttNameId(productId, attNameId);
		}
}
	