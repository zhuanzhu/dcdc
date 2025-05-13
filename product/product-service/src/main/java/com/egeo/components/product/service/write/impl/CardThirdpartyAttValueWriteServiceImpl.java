package com.egeo.components.product.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.product.service.write.CardThirdpartyAttValueWriteService;
import com.egeo.components.product.manage.write.CardThirdpartyAttValueWriteManage;
import com.egeo.components.product.converter.CardThirdpartyAttValueConverter;
import com.egeo.components.product.dto.CardThirdpartyAttValueDTO;
import com.egeo.components.product.po.CardThirdpartyAttValuePO;

@Service("cardThirdpartyAttValueWriteService")
public class CardThirdpartyAttValueWriteServiceImpl  implements CardThirdpartyAttValueWriteService {
	@Autowired
	private CardThirdpartyAttValueWriteManage cardThirdpartyAttValueWriteManage;

	@Override
	public Long insertCardThirdpartyAttValueWithTx(CardThirdpartyAttValueDTO dto) {
		CardThirdpartyAttValuePO po = CardThirdpartyAttValueConverter.toPO(dto);
		Long rt = cardThirdpartyAttValueWriteManage.insertCardThirdpartyAttValueWithTx(po);		
		return rt;
	}

	@Override
	public int updateCardThirdpartyAttValueWithTx(CardThirdpartyAttValueDTO dto) {
		CardThirdpartyAttValuePO po = CardThirdpartyAttValueConverter.toPO(dto);
		int rt = cardThirdpartyAttValueWriteManage.updateCardThirdpartyAttValueWithTx(po);		
		return rt;
	}

	@Override
	public int deleteCardThirdpartyAttValueWithTx(CardThirdpartyAttValueDTO dto) {
		CardThirdpartyAttValuePO po = CardThirdpartyAttValueConverter.toPO(dto);
		int rt = cardThirdpartyAttValueWriteManage.deleteCardThirdpartyAttValueWithTx(po);		
		return rt;
	}
}
	