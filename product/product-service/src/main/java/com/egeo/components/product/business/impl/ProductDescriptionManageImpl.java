package com.egeo.components.product.business.impl;
	

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.egeo.components.product.business.ProductDescriptionManage;
import com.egeo.components.product.converter.ProductDescriptionConverter;
import com.egeo.components.product.dto.ProductDescriptionDTO;
import com.egeo.components.product.facade.ProductDescriptionFacade;
import com.egeo.components.product.vo.ProductDescriptionVO;

@Service("productDescription")
public class ProductDescriptionManageImpl implements ProductDescriptionManage{

	
	@Resource(name="productDescriptionFacade")
	private ProductDescriptionFacade productDescriptionFacade;

        @Override
        public Long saveProductDescription(ProductDescriptionVO productDescriptionVO) {
            
            return productDescriptionFacade.saveProductDescription(ProductDescriptionConverter.toDTO(productDescriptionVO));
        }

        @Override
        public List<ProductDescriptionDTO> findAll(ProductDescriptionVO productDescriptionVO) {
            
            return productDescriptionFacade.findAll(ProductDescriptionConverter.toDTO(productDescriptionVO));
        }

        @Override
        public String updateProductDescription(ProductDescriptionVO productDescriptionVO) {
            
            return productDescriptionFacade.updateProductDescription(ProductDescriptionConverter.toDTO(productDescriptionVO));
        }

        @Override
        public ProductDescriptionDTO findById(ProductDescriptionVO productDescriptionVO) {
            
            return productDescriptionFacade.findById(ProductDescriptionConverter.toDTO(productDescriptionVO));
        }

        @Override
        public String deleteByProductId(Long productId) {
            
            return productDescriptionFacade.deleteByProductId(productId);
        }
	


}
	