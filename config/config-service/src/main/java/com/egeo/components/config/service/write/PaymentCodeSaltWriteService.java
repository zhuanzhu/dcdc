package com.egeo.components.config.service.write;

import com.egeo.components.config.dto.PaymentCodeSaltDTO;


public interface PaymentCodeSaltWriteService {

	public Long insertPaymentCodeSaltWithTx(PaymentCodeSaltDTO dto);

	public int updatePaymentCodeSaltWithTx(PaymentCodeSaltDTO dto);

	public int deletePaymentCodeSaltWithTx(PaymentCodeSaltDTO dto);
}
	