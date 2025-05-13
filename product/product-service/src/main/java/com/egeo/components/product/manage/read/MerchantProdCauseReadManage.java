package com.egeo.components.product.manage.read;

import java.util.List;
	
import com.egeo.components.product.po.MerchantProdCausePO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface MerchantProdCauseReadManage {

	public MerchantProdCausePO findMerchantProdCauseById(MerchantProdCausePO po);

	public PageResult<MerchantProdCausePO> findMerchantProdCauseOfPage(MerchantProdCausePO po,Pagination page);

	public List<MerchantProdCausePO> findMerchantProdCauseAll(MerchantProdCausePO po);
}
	