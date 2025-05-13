package com.egeo.components.product.service.read;


import java.util.List;
	
import com.egeo.components.product.dto.ProductLimitProfitDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface ProductLimitProfitReadService {

	public ProductLimitProfitDTO findProductLimitProfitById(ProductLimitProfitDTO dto);

	public PageResult<ProductLimitProfitDTO> findProductLimitProfitOfPage(ProductLimitProfitDTO dto,Pagination page);

	public List<ProductLimitProfitDTO> findProductLimitProfitAll(ProductLimitProfitDTO dto);
}
	