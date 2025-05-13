package com.egeo.components.product.business.impl;
	

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.egeo.components.product.business.ProductCauseManage;
import com.egeo.components.product.converter.ProductCauseConverter;
import com.egeo.components.product.dto.ProductCauseDTO;
import com.egeo.components.product.facade.ProductCauseFacade;
import com.egeo.components.product.vo.ProductCauseVO;

@Service("productCause")
public class ProductCauseManageImpl implements ProductCauseManage{

	
	@Resource(name="productCauseFacade")
	private ProductCauseFacade productCauseFacade;

        @Override
        public List<ProductCauseVO> findByProductId(Long productId) {
            List<ProductCauseDTO> list = productCauseFacade.findByProductId(productId);
            return ProductCauseConverter.toVO(list);
        }

        @Override
        public Long saveProductCause(ProductCauseVO productCauseVO, HttpServletRequest req) {
        	ProductCauseDTO dto = ProductCauseConverter.toDTO(productCauseVO);

            return productCauseFacade.saveProductCause(dto);
        }
    	
}
	