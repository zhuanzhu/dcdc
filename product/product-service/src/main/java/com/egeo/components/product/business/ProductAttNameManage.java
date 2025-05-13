package com.egeo.components.product.business;

import java.util.List;

import com.egeo.components.product.vo.ProductAttNameVO;
import com.egeo.components.product.dto.ProductAttNameDTO;

public interface ProductAttNameManage {

    Long saveProductAttName(ProductAttNameVO productAttNameVO);

    List<ProductAttNameDTO> findAll(ProductAttNameVO productAttNameVO);

    String deleteByProductId(Long productId);
	

}
	