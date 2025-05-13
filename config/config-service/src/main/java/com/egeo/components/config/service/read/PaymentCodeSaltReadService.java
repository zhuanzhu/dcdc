package com.egeo.components.config.service.read;


import java.util.List;

import com.egeo.components.config.dto.PaymentCodeSaltDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface PaymentCodeSaltReadService {

	public PaymentCodeSaltDTO findPaymentCodeSaltById(PaymentCodeSaltDTO dto);

	public PageResult<PaymentCodeSaltDTO> findPaymentCodeSaltOfPage(PaymentCodeSaltDTO dto,Pagination page);

	public List<PaymentCodeSaltDTO> findPaymentCodeSaltAll(PaymentCodeSaltDTO dto);
	/**
	 * 根据uuId查询用户支付密钥
	 * @param paymentCodeUuid
	 * @return
	 */
	public String findSaltByUUID(String paymentCodeUuid);
}
	