package com.egeo.components.product.manage.read;

import java.util.List;
	
import com.egeo.components.product.po.MerchantProdAttNamePO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface MerchantProdAttNameReadManage {

	public MerchantProdAttNamePO findMerchantProdAttNameById(MerchantProdAttNamePO po);

	public PageResult<MerchantProdAttNamePO> findMerchantProdAttNameOfPage(MerchantProdAttNamePO po,Pagination page);

	public List<MerchantProdAttNamePO> findMerchantProdAttNameAll(MerchantProdAttNamePO po);
}
	