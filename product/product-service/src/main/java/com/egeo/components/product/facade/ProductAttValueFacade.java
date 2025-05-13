package com.egeo.components.product.facade;

import java.util.List;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.product.service.read.ProductAttValueReadService;
import com.egeo.components.product.service.write.ProductAttValueWriteService;
import com.egeo.components.product.dto.ProductAttValueDTO;


@Component
public class ProductAttValueFacade {
	
	@Resource
	private ProductAttValueReadService productAttValueReadService;
	
	@Resource
    private ProductAttValueWriteService productAttValueWriteService;

        public Long saveProductAttValue(ProductAttValueDTO dto) {
            
            return productAttValueWriteService.saveProductAttValueWithTx(dto);
        }

        public List<ProductAttValueDTO> findAll(ProductAttValueDTO dto) {
            
            return productAttValueReadService.findAll(dto);
        }

        public String deleteByProductAttNameId(ProductAttValueDTO dto) {
            
            return productAttValueWriteService.deleteByProductAttNameIdWithTx(dto);
        }

        public String deleteByMuchProductAttNameId(String productAttNameIds) {
            
            return productAttValueWriteService.deleteByMuchProductAttNameIdWithTx(productAttNameIds);
        }
        /**
		 * 根据产品属性值id删除产品属性值的关系
		 * @param productAttValueVO
		 * @return
		 */
		public int deleteById(ProductAttValueDTO dto, Long productId) {
			// TODO Auto-generated method stub
			return productAttValueWriteService.deleteByIdWithTx(dto,productId);
		}
		/**
         * 根据产品id和属性id保存产品属性值信息
         * @param productId
         * @param attnameId
         * @return
         */
		public Long saveProductAttValueByProductIdAndAttnameId(Long productId, List<ProductAttValueDTO> dto,
				Long platformId) {
			// TODO Auto-generated method stub
			return productAttValueWriteService.saveProductAttValueByProductIdAndAttnameIdWithTx(productId, dto,platformId);
		}
		/**
		 * 根据spu规格属性值草稿id保存spu规格图片
		 * @param productAttValueId
		 * @param pictureUrl
		 * @return
		 */
		public boolean saveImgByProductAttValueId(Long productAttValueId, String pictureUrl) {
			// TODO Auto-generated method stub
			return productAttValueWriteService.saveImgByProductAttValueId(productAttValueId, pictureUrl);
		}
		/**
		 * 根据spu规格属性草稿id删除spu规格图片
		 * @param productAttValueId
		 * @param pictureUrl
		 * @return
		 */
		public boolean delImgByProductAttNameId(Long productAttNameId) {
			// TODO Auto-generated method stub
			return productAttValueWriteService.delImgByProductAttNameId(productAttNameId);
		}
		/**
		 * 根据属性属性值和spuid删除规格信息
		 * @param productId
		 * @param attributeNameId
		 * @param attributeValueId
		 * @return
		 */
		public boolean delByAttIdAndSpuIdWithTx(Long productId, Long attributeNameId, Long attributeValueId,Long productAttValueId) {
			// TODO Auto-generated method stub
			return productAttValueWriteService.delByAttIdAndSpuIdWithTx(productId, attributeNameId, attributeValueId,productAttValueId);
		}
	


}
	