package com.egeo.components.product.facade;

import java.util.List;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.product.service.read.ProductCauseReadService;
import com.egeo.components.product.service.write.ProductCauseWriteService;
import com.egeo.components.product.dto.ProductCauseDTO;


@Component
public class ProductCauseFacade {
	
	@Resource
	private ProductCauseReadService productCauseReadService;
	
	@Resource
        private ProductCauseWriteService productCauseWriteService;
    
        public List<ProductCauseDTO> findByProductId(Long productId) {
            
            return productCauseReadService.findByProductId(productId);
        }

        public Long saveProductCause(ProductCauseDTO dto) {
            
            return productCauseWriteService.saveProductCauseWithTx(dto);
        }
	


}
	