package com.egeo.components.product.facade;

import java.util.List;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.product.service.read.ProductAttNameReadService;
import com.egeo.components.product.service.write.ProductAttNameWriteService;
import com.egeo.components.product.dto.ProductAttNameDTO;


@Component
public class ProductAttNameFacade {
	
	@Resource
	private ProductAttNameReadService productAttNameReadService;
	
	@Resource
    private ProductAttNameWriteService productAttNameWriteService;

        public Long saveProductAttName(ProductAttNameDTO dto) {
            
            return productAttNameWriteService.saveProductAttNameWithTx(dto);
        }

        public List<ProductAttNameDTO> findAll(ProductAttNameDTO dto) {
            
            return productAttNameReadService.findAll(dto);
        }

        public String deleteByProductId(Long productId) {
            
            return productAttNameWriteService.deleteByProductIdWithTx(productId);
        }
	


}
	