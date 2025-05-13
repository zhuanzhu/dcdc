package com.egeo.components.product.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.product.service.write.MerchantProductFeedbackWriteService;
import com.egeo.components.product.manage.write.MerchantProductFeedbackWriteManage;
import com.egeo.components.product.converter.MerchantProductFeedbackConverter;
import com.egeo.components.product.dto.MerchantProductFeedbackDTO;
import com.egeo.components.product.po.MerchantProductFeedbackPO;

@Service("merchantProductFeedbackWriteService")
public class MerchantProductFeedbackWriteServiceImpl  implements MerchantProductFeedbackWriteService {
	@Autowired
	private MerchantProductFeedbackWriteManage merchantProductFeedbackWriteManage;

	@Override
	public Long insertMerchantProductFeedbackWithTx(MerchantProductFeedbackDTO dto) {
		MerchantProductFeedbackPO po = MerchantProductFeedbackConverter.toPO(dto);
		Long rt = merchantProductFeedbackWriteManage.insertMerchantProductFeedbackWithTx(po);		
		return rt;
	}

	@Override
	public int updateMerchantProductFeedbackWithTx(MerchantProductFeedbackDTO dto) {
		MerchantProductFeedbackPO po = MerchantProductFeedbackConverter.toPO(dto);
		int rt = merchantProductFeedbackWriteManage.updateMerchantProductFeedbackWithTx(po);		
		return rt;
	}

	@Override
	public int deleteMerchantProductFeedbackWithTx(MerchantProductFeedbackDTO dto) {
		MerchantProductFeedbackPO po = MerchantProductFeedbackConverter.toPO(dto);
		int rt = merchantProductFeedbackWriteManage.deleteMerchantProductFeedbackWithTx(po);		
		return rt;
	}
}
	