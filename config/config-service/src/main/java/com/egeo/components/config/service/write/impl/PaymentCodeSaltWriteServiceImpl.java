package com.egeo.components.config.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.config.converter.PaymentCodeSaltConverter;
import com.egeo.components.config.dto.PaymentCodeSaltDTO;
import com.egeo.components.config.manage.write.PaymentCodeSaltWriteManage;
import com.egeo.components.config.po.PaymentCodeSaltPO;
import com.egeo.components.config.service.write.PaymentCodeSaltWriteService;

@Service("paymentCodeSaltWriteService")
public class PaymentCodeSaltWriteServiceImpl  implements PaymentCodeSaltWriteService {
	@Autowired
	private PaymentCodeSaltWriteManage paymentCodeSaltWriteManage;

	@Override
	public Long insertPaymentCodeSaltWithTx(PaymentCodeSaltDTO dto) {
		PaymentCodeSaltPO po = PaymentCodeSaltConverter.toPO(dto);
		Long rt = paymentCodeSaltWriteManage.insertPaymentCodeSaltWithTx(po);		
		return rt;
	}

	@Override
	public int updatePaymentCodeSaltWithTx(PaymentCodeSaltDTO dto) {
		PaymentCodeSaltPO po = PaymentCodeSaltConverter.toPO(dto);
		int rt = paymentCodeSaltWriteManage.updatePaymentCodeSaltWithTx(po);		
		return rt;
	}

	@Override
	public int deletePaymentCodeSaltWithTx(PaymentCodeSaltDTO dto) {
		PaymentCodeSaltPO po = PaymentCodeSaltConverter.toPO(dto);
		int rt = paymentCodeSaltWriteManage.deletePaymentCodeSaltWithTx(po);		
		return rt;
	}
}
	