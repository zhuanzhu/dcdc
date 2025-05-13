package com.egeo.components.product.manage.read;

import java.util.List;
	
import com.egeo.components.product.po.SupplierPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface SupplierReadManage {

	public SupplierPO findSupplierById(SupplierPO po);

	public PageResult<SupplierPO> findSupplierOfPage(SupplierPO po,Pagination page);

	public List<SupplierPO> findSupplierAll(SupplierPO po);

	List<SupplierPO> findByIdList(List<Long> ids);
}
	