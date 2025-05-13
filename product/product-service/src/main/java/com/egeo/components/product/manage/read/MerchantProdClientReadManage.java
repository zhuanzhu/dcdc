package com.egeo.components.product.manage.read;

import java.util.List;
	
import com.egeo.components.product.po.MerchantProdClientPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface MerchantProdClientReadManage {

	public MerchantProdClientPO findMerchantProdClientById(MerchantProdClientPO po);

	public PageResult<MerchantProdClientPO> findMerchantProdClientOfPage(MerchantProdClientPO po,Pagination page);

	public List<MerchantProdClientPO> findMerchantProdClientAll(MerchantProdClientPO po);
}
	