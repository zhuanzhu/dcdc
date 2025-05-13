package com.egeo.components.product.facade;

import java.util.List;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.product.service.read.ProductDescriptionReadService;
import com.egeo.components.product.service.write.ProductDescriptionWriteService;
import com.egeo.components.product.dto.ProductDescriptionDTO;


@Component
public class ProductDescriptionFacade {
	
	@Resource
	private ProductDescriptionReadService productDescriptionReadService;
	
	@Resource
        private ProductDescriptionWriteService productDescriptionWriteService;

        public Long saveProductDescription(ProductDescriptionDTO dto) {
            
            return productDescriptionWriteService.saveProductDescriptionWithTx(dto);
        }

        public List<ProductDescriptionDTO> findAll(ProductDescriptionDTO dto) {
            
            return productDescriptionReadService.findAll(dto);
        }

        public String updateProductDescription(ProductDescriptionDTO dto) {
            
            return productDescriptionWriteService.updateProductDescriptionWithTx(dto);
        }

        public ProductDescriptionDTO findById(ProductDescriptionDTO dto) {
            
            return productDescriptionReadService.findById(dto);
        }

        public String deleteByProductId(Long productId) {
            
            return productDescriptionWriteService.deleteByProductIdWithTx(productId);
        }
	


}
	