package com.egeo.components.product.manage.read;

import java.util.List;
	
import com.egeo.components.product.po.MerchantProductStorePO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface MerchantProductStoreReadManage {

	public MerchantProductStorePO findMerchantProductStoreById(MerchantProductStorePO po);

	public PageResult<MerchantProductStorePO> findMerchantProductStoreOfPage(MerchantProductStorePO po,Pagination page);

	public List<MerchantProductStorePO> findMerchantProductStoreAll(MerchantProductStorePO po);
}
	