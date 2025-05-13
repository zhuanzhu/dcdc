package com.egeo.components.promotion.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

 
import com.egeo.components.promotion.service.write.ExchangeActivityWriteService;
import com.egeo.components.promotion.manage.write.ExchangeActivityWriteManage;
import com.egeo.components.promotion.converter.ExchangeActivityConverter;
import com.egeo.components.promotion.dto.ExchangeActivityDTO;
import com.egeo.components.promotion.po.ExchangeActivityPO;

@Service("exchangeActivityWriteService")
public class ExchangeActivityWriteServiceImpl implements ExchangeActivityWriteService {
	@Autowired
	private ExchangeActivityWriteManage exchangeActivityWriteManage;

	@Override
	public Long insertExchangeActivityWithTx(ExchangeActivityDTO dto) {
		ExchangeActivityPO po = ExchangeActivityConverter.toPO(dto);
		Long rt = exchangeActivityWriteManage.insertExchangeActivityWithTx(po);		
		return rt;
	}

	@Override
	public int updateExchangeActivityWithTx(ExchangeActivityDTO dto) {
		ExchangeActivityPO po = ExchangeActivityConverter.toPO(dto);
		int rt = exchangeActivityWriteManage.updateExchangeActivityWithTx(po);		
		return rt;
	}

	@Override
	public int deleteExchangeActivityWithTx(ExchangeActivityDTO dto) {
		ExchangeActivityPO po = ExchangeActivityConverter.toPO(dto);
		int rt = exchangeActivityWriteManage.deleteExchangeActivityWithTx(po);		
		return rt;
	}

	@Override
	public int insertOrUpdateExchangeActivityWithTx(ExchangeActivityDTO exchangeActivityDTO) {
		int rt=exchangeActivityWriteManage.insertOrUpdateExchangeActivityWithTx(ExchangeActivityConverter.toCondition(exchangeActivityDTO));
		return rt;
	}
}
	