package com.egeo.components.product.manage.read;

import java.util.List;
	
import com.egeo.components.product.po.MerchantProductCompanyPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface MerchantProductCompanyReadManage {

	public MerchantProductCompanyPO findMerchantProductCompanyById(MerchantProductCompanyPO po);

	public PageResult<MerchantProductCompanyPO> findMerchantProductCompanyOfPage(MerchantProductCompanyPO po,Pagination page);

	public List<MerchantProductCompanyPO> findMerchantProductCompanyAll(MerchantProductCompanyPO po);
}
	