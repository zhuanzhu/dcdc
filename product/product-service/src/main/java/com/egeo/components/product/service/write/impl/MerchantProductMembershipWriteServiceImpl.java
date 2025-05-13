package com.egeo.components.product.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.product.service.write.MerchantProductMembershipWriteService;
import com.egeo.components.product.manage.write.MerchantProductMembershipWriteManage;
import com.egeo.components.product.converter.MerchantProductMembershipConverter;
import com.egeo.components.product.dto.MerchantProductMembershipDTO;
import com.egeo.components.product.po.MerchantProductMembershipPO;

@Service("merchantProductMembershipWriteService")
public class MerchantProductMembershipWriteServiceImpl  implements MerchantProductMembershipWriteService {
	@Autowired
	private MerchantProductMembershipWriteManage merchantProductMembershipWriteManage;

	@Override
	public Long insertMerchantProductMembershipWithTx(MerchantProductMembershipDTO dto) {
		MerchantProductMembershipPO po = MerchantProductMembershipConverter.toPO(dto);
		Long rt = merchantProductMembershipWriteManage.insertMerchantProductMembershipWithTx(po);		
		return rt;
	}

	@Override
	public int updateMerchantProductMembershipWithTx(MerchantProductMembershipDTO dto) {
		MerchantProductMembershipPO po = MerchantProductMembershipConverter.toPO(dto);
		int rt = merchantProductMembershipWriteManage.updateMerchantProductMembershipWithTx(po);		
		return rt;
	}

	@Override
	public int deleteMerchantProductMembershipWithTx(MerchantProductMembershipDTO dto) {
		MerchantProductMembershipPO po = MerchantProductMembershipConverter.toPO(dto);
		int rt = merchantProductMembershipWriteManage.deleteMerchantProductMembershipWithTx(po);		
		return rt;
	}
}
	