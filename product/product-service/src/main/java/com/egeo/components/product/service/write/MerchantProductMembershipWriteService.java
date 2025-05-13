package com.egeo.components.product.service.write;

import com.egeo.components.product.dto.MerchantProductMembershipDTO;


public interface MerchantProductMembershipWriteService {

	public Long insertMerchantProductMembershipWithTx(MerchantProductMembershipDTO dto);

	public int updateMerchantProductMembershipWithTx(MerchantProductMembershipDTO dto);

	public int deleteMerchantProductMembershipWithTx(MerchantProductMembershipDTO dto);
}
	