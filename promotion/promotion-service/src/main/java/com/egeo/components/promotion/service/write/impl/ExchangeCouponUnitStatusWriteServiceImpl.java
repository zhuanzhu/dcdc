package com.egeo.components.promotion.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

 
import com.egeo.components.promotion.service.write.ExchangeCouponUnitStatusWriteService;
import com.egeo.components.promotion.manage.write.ExchangeCouponUnitStatusWriteManage;
import com.egeo.components.promotion.converter.ExchangeCouponUnitStatusConverter;
import com.egeo.components.promotion.dto.ExchangeCouponUnitStatusDTO;
import com.egeo.components.promotion.po.ExchangeCouponUnitStatusPO;

@Service("exchangeCouponUnitStatusWriteService")
public class ExchangeCouponUnitStatusWriteServiceImpl implements ExchangeCouponUnitStatusWriteService {
	@Autowired
	private ExchangeCouponUnitStatusWriteManage exchangeCouponUnitStatusWriteManage;

	@Override
	public Long insertExchangeCouponUnitStatusWithTx(ExchangeCouponUnitStatusDTO dto) {
		ExchangeCouponUnitStatusPO po = ExchangeCouponUnitStatusConverter.toPO(dto);
		Long rt = exchangeCouponUnitStatusWriteManage.insertExchangeCouponUnitStatusWithTx(po);		
		return rt;
	}

	@Override
	public int updateExchangeCouponUnitStatusWithTx(ExchangeCouponUnitStatusDTO dto) {
		ExchangeCouponUnitStatusPO po = ExchangeCouponUnitStatusConverter.toPO(dto);
		int rt = exchangeCouponUnitStatusWriteManage.updateExchangeCouponUnitStatusWithTx(po);		
		return rt;
	}

	@Override
	public int deleteExchangeCouponUnitStatusWithTx(ExchangeCouponUnitStatusDTO dto) {
		ExchangeCouponUnitStatusPO po = ExchangeCouponUnitStatusConverter.toPO(dto);
		int rt = exchangeCouponUnitStatusWriteManage.deleteExchangeCouponUnitStatusWithTx(po);		
		return rt;
	}
}
	