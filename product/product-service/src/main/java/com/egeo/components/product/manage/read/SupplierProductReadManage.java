package com.egeo.components.product.manage.read;

import java.util.List;
	
import com.egeo.components.product.po.SupplierProductPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface SupplierProductReadManage {

	public SupplierProductPO findSupplierProductById(SupplierProductPO po);

	public PageResult<SupplierProductPO> findSupplierProductOfPage(SupplierProductPO po,Pagination page);

	public List<SupplierProductPO> findSupplierProductAll(SupplierProductPO po);
}
	