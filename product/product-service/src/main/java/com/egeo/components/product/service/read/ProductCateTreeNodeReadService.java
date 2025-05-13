package com.egeo.components.product.service.read;
import java.util.List;

import com.egeo.components.product.dto.ProductCateTreeNodeDTO;

public interface ProductCateTreeNodeReadService {

	public List<ProductCateTreeNodeDTO> findAll(ProductCateTreeNodeDTO productCateTreeNodeDTO); 
	
}
	