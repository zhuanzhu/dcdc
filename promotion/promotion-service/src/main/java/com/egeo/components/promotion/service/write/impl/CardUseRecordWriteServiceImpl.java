package com.egeo.components.promotion.service.write.impl;

import com.egeo.components.promotion.converter.CardUseRecordConverter;
import com.egeo.components.promotion.dto.CardUseRecordDTO;
import com.egeo.components.promotion.manage.write.CardUseRecordWriteManage;
import com.egeo.components.promotion.po.CardUseRecordPO;
import com.egeo.components.promotion.service.write.CardUseRecordWriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service("cardUseRecordWriteService")
public class CardUseRecordWriteServiceImpl implements CardUseRecordWriteService {
	@Autowired
	private CardUseRecordWriteManage cardUseRecordWriteManage;

	@Override
	public Long insertCardUseRecordWithTx(CardUseRecordDTO dto) {
		CardUseRecordPO po = CardUseRecordConverter.toPO(dto);
		Long rt = cardUseRecordWriteManage.insertCardUseRecordWithTx(po);
		return rt;
	}

	@Override
	public int updateCardUseRecordWithTx(CardUseRecordDTO dto) {
		CardUseRecordPO po = CardUseRecordConverter.toPO(dto);
		int rt = cardUseRecordWriteManage.updateCardUseRecordWithTx(po);
		return rt;
	}

	@Override
	public int deleteCardUseRecordWithTx(CardUseRecordDTO dto) {
		CardUseRecordPO po = CardUseRecordConverter.toPO(dto);
		int rt = cardUseRecordWriteManage.deleteCardUseRecordWithTx(po);
		return rt;
	}

	@Override
	public Boolean cancelUserCard(){
		cardUseRecordWriteManage.cancelUserCard();
		return true;
	}

}
