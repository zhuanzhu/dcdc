package com.egeo.components.promotion.service.write.impl;

import com.egeo.components.promotion.converter.BuyCardItemRefundConverter;
import com.egeo.components.promotion.dto.BuyCardItemRefundDTO;
import com.egeo.components.promotion.manage.write.BuyCardItemRefundWriteManage;
import com.egeo.components.promotion.po.BuyCardItemRefundPO;
import com.egeo.components.promotion.service.write.BuyCardItemRefundWriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("buyCardItemRefundWriteService")
public class BuyCardItemRefundWriteServiceImpl implements BuyCardItemRefundWriteService {
	@Autowired
	private BuyCardItemRefundWriteManage buyCardItemRefundWriteManage;

	@Override
	public Long insertBuyCardItemRefundWithTx(BuyCardItemRefundDTO dto) {
		BuyCardItemRefundPO po = BuyCardItemRefundConverter.toPO(dto);
		Long rt = buyCardItemRefundWriteManage.insertBuyCardItemRefundWithTx(po);
		return rt;
	}

	@Override
	public int updateBuyCardItemRefundWithTx(BuyCardItemRefundDTO dto) {
		BuyCardItemRefundPO po = BuyCardItemRefundConverter.toPO(dto);
		int rt = buyCardItemRefundWriteManage.updateBuyCardItemRefundWithTx(po);
		return rt;
	}

	@Override
	public int deleteBuyCardItemRefundWithTx(BuyCardItemRefundDTO dto) {
		BuyCardItemRefundPO po = BuyCardItemRefundConverter.toPO(dto);
		int rt = buyCardItemRefundWriteManage.deleteBuyCardItemRefundWithTx(po);
		return rt;
	}
}
