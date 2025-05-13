package com.egeo.components.product.manage.read;

import java.util.List;
	
import com.egeo.components.product.po.MerchantProdAttValuePO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface MerchantProdAttValueReadManage {

	public MerchantProdAttValuePO findMerchantProdAttValueById(MerchantProdAttValuePO po);

	public PageResult<MerchantProdAttValuePO> findMerchantProdAttValueOfPage(MerchantProdAttValuePO po,Pagination page);

	public List<MerchantProdAttValuePO> findMerchantProdAttValueAll(MerchantProdAttValuePO po);
}
	