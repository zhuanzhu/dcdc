package com.egeo.components.product.business.impl;
	

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.egeo.components.product.business.ProductAttNameManage;
import com.egeo.components.product.converter.ProductAttNameConverter;
import com.egeo.components.product.dto.ProductAttNameDTO;
import com.egeo.components.product.facade.ProductAttNameFacade;
import com.egeo.components.product.vo.ProductAttNameVO;

@Service("productAttName")
public class ProductAttNameManageImpl implements ProductAttNameManage{

	
	@Resource(name="productAttNameFacade")
	private ProductAttNameFacade productAttNameFacade;

        @Override
        public Long saveProductAttName(ProductAttNameVO productAttNameVO) {
            
            return productAttNameFacade.saveProductAttName(ProductAttNameConverter.toDTO(productAttNameVO));
        }

        @Override
        public List<ProductAttNameDTO> findAll(ProductAttNameVO productAttNameVO) {
            
            return productAttNameFacade.findAll(ProductAttNameConverter.toDTO(productAttNameVO));
        }

        @Override
        public String deleteByProductId(Long productId) {
            
            return productAttNameFacade.deleteByProductId(productId);
        }
	


}
	