package com.egeo.components.product.business;

import java.util.List;

import com.egeo.components.product.vo.ProductDescriptionVO;
import com.egeo.components.product.dto.ProductDescriptionDTO;

public interface ProductDescriptionManage {

    Long saveProductDescription(ProductDescriptionVO productDescriptionVO);

    List<ProductDescriptionDTO> findAll(ProductDescriptionVO productDescriptionVO);

    String updateProductDescription(ProductDescriptionVO productDescriptionVO);

    ProductDescriptionDTO findById(ProductDescriptionVO productDescriptionVO);

    String deleteByProductId(Long productId);
	

}
	