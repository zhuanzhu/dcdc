package com.egeo.components.config.manage.read;

import java.util.List;

import com.egeo.components.config.po.PaymentCodeSaltPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface PaymentCodeSaltReadManage {

	public PaymentCodeSaltPO findPaymentCodeSaltById(PaymentCodeSaltPO po);

	public PageResult<PaymentCodeSaltPO> findPaymentCodeSaltOfPage(PaymentCodeSaltPO po,Pagination page);

	public List<PaymentCodeSaltPO> findPaymentCodeSaltAll(PaymentCodeSaltPO po);
	/**
	 * 根据uuId查询用户支付密钥
	 * @param paymentCodeUuid
	 * @return
	 */
	public String findSaltByUUID(String paymentCodeUuid);
}
	