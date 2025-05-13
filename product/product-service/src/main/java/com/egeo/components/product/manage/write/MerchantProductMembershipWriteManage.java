package com.egeo.components.product.manage.write;

import com.egeo.components.product.po.MerchantProductMembershipPO;


public interface MerchantProductMembershipWriteManage {

	Long insertMerchantProductMembershipWithTx(MerchantProductMembershipPO po);

	int updateMerchantProductMembershipWithTx(MerchantProductMembershipPO po);

	int deleteMerchantProductMembershipWithTx(MerchantProductMembershipPO po);
}
	