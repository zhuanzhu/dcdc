package com.egeo.components.promotion.service.write.impl;

import com.egeo.components.promotion.converter.BuyCardItemConverter;
import com.egeo.components.promotion.dto.BuyCardItemDTO;
import com.egeo.components.promotion.manage.write.BuyCardItemWriteManage;
import com.egeo.components.promotion.po.BuyCardItemPO;
import com.egeo.components.promotion.service.write.BuyCardItemWriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("buyCardItemWriteService")
public class BuyCardItemWriteServiceImpl implements BuyCardItemWriteService {
	@Autowired
	private BuyCardItemWriteManage buyCardItemWriteManage;

	@Override
	public Long insertBuyCardItemWithTx(BuyCardItemDTO dto) {
		BuyCardItemPO po = BuyCardItemConverter.toPO(dto);
		Long rt = buyCardItemWriteManage.insertBuyCardItemWithTx(po);
		return rt;
	}

	@Override
	public int updateBuyCardItemWithTx(BuyCardItemDTO dto) {
		BuyCardItemPO po = BuyCardItemConverter.toPO(dto);
		int rt = buyCardItemWriteManage.updateBuyCardItemWithTx(po);
		return rt;
	}

	@Override
	public int deleteBuyCardItemWithTx(BuyCardItemDTO dto) {
		BuyCardItemPO po = BuyCardItemConverter.toPO(dto);
		int rt = buyCardItemWriteManage.deleteBuyCardItemWithTx(po);
		return rt;
	}
}
