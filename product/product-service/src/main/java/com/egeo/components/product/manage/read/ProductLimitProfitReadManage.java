package com.egeo.components.product.manage.read;

import java.util.List;
	
import com.egeo.components.product.po.ProductLimitProfitPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface ProductLimitProfitReadManage {

	public ProductLimitProfitPO findProductLimitProfitById(ProductLimitProfitPO po);

	public PageResult<ProductLimitProfitPO> findProductLimitProfitOfPage(ProductLimitProfitPO po,Pagination page);

	public List<ProductLimitProfitPO> findProductLimitProfitAll(ProductLimitProfitPO po);
}
	