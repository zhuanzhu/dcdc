package com.egeo.components.product.manage.read;

import java.util.List;
	
import com.egeo.components.product.po.MerchantProductMembershipPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface MerchantProductMembershipReadManage {

	public MerchantProductMembershipPO findMerchantProductMembershipById(MerchantProductMembershipPO po);

	public PageResult<MerchantProductMembershipPO> findMerchantProductMembershipOfPage(MerchantProductMembershipPO po,Pagination page);

	public List<MerchantProductMembershipPO> findMerchantProductMembershipAll(MerchantProductMembershipPO po);
}
	