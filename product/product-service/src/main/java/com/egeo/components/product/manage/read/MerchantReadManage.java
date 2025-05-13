package com.egeo.components.product.manage.read;

import java.util.List;
	
import com.egeo.components.product.po.MerchantPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface MerchantReadManage {

	public MerchantPO findMerchantById(MerchantPO po);

	public PageResult<MerchantPO> findMerchantOfPage(MerchantPO po,Pagination page);

	public List<MerchantPO> findMerchantAll(MerchantPO po);

    List<MerchantPO> findMerchantList();

    List<MerchantPO> findMerchantListByType(Integer type);
}
	