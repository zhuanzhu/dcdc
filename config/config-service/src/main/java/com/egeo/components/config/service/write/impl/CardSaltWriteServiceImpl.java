package com.egeo.components.config.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.config.converter.CardSaltConverter;
import com.egeo.components.config.dto.CardSaltDTO;
import com.egeo.components.config.manage.write.CardSaltWriteManage;
import com.egeo.components.config.po.CardSaltPO;
import com.egeo.components.config.service.write.CardSaltWriteService;

@Service("cardSaltWriteService")
public class CardSaltWriteServiceImpl  implements CardSaltWriteService {
	@Autowired
	private CardSaltWriteManage cardSaltWriteManage;

	@Override
	public Long insertCardSaltWithTx(CardSaltDTO dto) {
		CardSaltPO po = CardSaltConverter.toPO(dto);
		Long rt = cardSaltWriteManage.insertCardSaltWithTx(po);		
		return rt;
	}

	@Override
	public int updateCardSaltWithTx(CardSaltDTO dto) {
		CardSaltPO po = CardSaltConverter.toPO(dto);
		int rt = cardSaltWriteManage.updateCardSaltWithTx(po);		
		return rt;
	}

	@Override
	public int deleteCardSaltWithTx(CardSaltDTO dto) {
		CardSaltPO po = CardSaltConverter.toPO(dto);
		int rt = cardSaltWriteManage.deleteCardSaltWithTx(po);		
		return rt;
	}
}
	