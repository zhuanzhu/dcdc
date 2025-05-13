package com.egeo.components.product.business;

import java.util.List;
	
import com.egeo.components.product.dto.ProductLimitProfitDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface ProductLimitProfitManage {

	public ProductLimitProfitDTO findProductLimitProfitById(ProductLimitProfitDTO dto);	

	public PageResult<ProductLimitProfitDTO> findProductLimitProfitOfPage(ProductLimitProfitDTO dto,Pagination page);

	public List<ProductLimitProfitDTO> findProductLimitProfitAll(ProductLimitProfitDTO dto);

	Long insertProductLimitProfitWithTx(ProductLimitProfitDTO dto);

	int updateProductLimitProfitWithTx(ProductLimitProfitDTO dto);

	int deleteProductLimitProfitWithTx(ProductLimitProfitDTO dto);
}
	