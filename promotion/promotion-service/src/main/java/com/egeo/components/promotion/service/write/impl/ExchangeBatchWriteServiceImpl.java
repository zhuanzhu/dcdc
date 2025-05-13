package com.egeo.components.promotion.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

 
import com.egeo.components.promotion.service.write.ExchangeBatchWriteService;
import com.egeo.components.promotion.manage.write.ExchangeBatchWriteManage;
import com.egeo.components.promotion.converter.ExchangeBatchConverter;
import com.egeo.components.promotion.dto.ExchangeBatchDTO;
import com.egeo.components.promotion.po.ExchangeBatchPO;

@Service("exchangeBatchWriteService")
public class ExchangeBatchWriteServiceImpl implements ExchangeBatchWriteService {
	@Autowired
	private ExchangeBatchWriteManage exchangeBatchWriteManage;

	@Override
	public Long insertExchangeBatchWithTx(ExchangeBatchDTO dto) {
		ExchangeBatchPO po = ExchangeBatchConverter.toPO(dto);
		Long rt = exchangeBatchWriteManage.insertExchangeBatchWithTx(po);		
		return rt;
	}

	@Override
	public int updateExchangeBatchWithTx(ExchangeBatchDTO dto) {
		ExchangeBatchPO po = ExchangeBatchConverter.toPO(dto);
		int rt = exchangeBatchWriteManage.updateExchangeBatchWithTx(po);		
		return rt;
	}

	@Override
	public int deleteExchangeBatchWithTx(ExchangeBatchDTO dto) {
		ExchangeBatchPO po = ExchangeBatchConverter.toPO(dto);
		int rt = exchangeBatchWriteManage.deleteExchangeBatchWithTx(po);		
		return rt;
	}
}
	