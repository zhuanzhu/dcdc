package com.egeo.components.product.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.product.service.write.CardStampsAdministrationWriteService;
import com.egeo.components.product.manage.write.CardStampsAdministrationWriteManage;
import com.egeo.components.product.converter.CardStampsAdministrationConverter;
import com.egeo.components.product.dto.CardStampsAdministrationDTO;
import com.egeo.components.product.po.CardStampsAdministrationPO;

@Service("cardStampsAdministrationWriteService")
public class CardStampsAdministrationWriteServiceImpl  implements CardStampsAdministrationWriteService {
	@Autowired
	private CardStampsAdministrationWriteManage cardStampsAdministrationWriteManage;

	@Override
	public Long insertCardStampsAdministrationWithTx(CardStampsAdministrationDTO dto) {
		CardStampsAdministrationPO po = CardStampsAdministrationConverter.toPO(dto);
		Long rt = cardStampsAdministrationWriteManage.insertCardStampsAdministrationWithTx(po);		
		return rt;
	}

	@Override
	public int updateCardStampsAdministrationWithTx(CardStampsAdministrationDTO dto) {
		CardStampsAdministrationPO po = CardStampsAdministrationConverter.toPO(dto);
		int rt = cardStampsAdministrationWriteManage.updateCardStampsAdministrationWithTx(po);		
		return rt;
	}

	@Override
	public int deleteCardStampsAdministrationWithTx(CardStampsAdministrationDTO dto) {
		CardStampsAdministrationPO po = CardStampsAdministrationConverter.toPO(dto);
		int rt = cardStampsAdministrationWriteManage.deleteCardStampsAdministrationWithTx(po);		
		return rt;
	}
}
	