package com.egeo.components.promotion.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

 
import com.egeo.components.promotion.service.write.CardBatchWriteService;
import com.egeo.components.promotion.manage.write.CardBatchWriteManage;
import com.egeo.components.promotion.converter.CardBatchConverter;
import com.egeo.components.promotion.dto.CardBatchDTO;
import com.egeo.components.promotion.po.CardBatchPO;

@Service("cardBatchWriteService")
public class CardBatchWriteServiceImpl implements CardBatchWriteService {
	@Autowired
	private CardBatchWriteManage cardBatchWriteManage;

	@Override
	public Long insertCardBatchWithTx(CardBatchDTO dto) {
		CardBatchPO po = CardBatchConverter.toPO(dto);
		Long rt = cardBatchWriteManage.insertCardBatchWithTx(po);		
		return rt;
	}

	@Override
	public int updateCardBatchWithTx(CardBatchDTO dto) {
		CardBatchPO po = CardBatchConverter.toPO(dto);
		int rt = cardBatchWriteManage.updateCardBatchWithTx(po);		
		return rt;
	}

	@Override
	public int deleteCardBatchWithTx(CardBatchDTO dto) {
		CardBatchPO po = CardBatchConverter.toPO(dto);
		int rt = cardBatchWriteManage.deleteCardBatchWithTx(po);		
		return rt;
	}
}
	