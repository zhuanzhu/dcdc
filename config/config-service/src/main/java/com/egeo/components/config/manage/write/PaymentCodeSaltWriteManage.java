package com.egeo.components.config.manage.write;

import com.egeo.components.config.po.PaymentCodeSaltPO;


public interface PaymentCodeSaltWriteManage {

	Long insertPaymentCodeSaltWithTx(PaymentCodeSaltPO po);

	int updatePaymentCodeSaltWithTx(PaymentCodeSaltPO po);

	int deletePaymentCodeSaltWithTx(PaymentCodeSaltPO po);
}
	