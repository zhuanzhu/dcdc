package com.egeo.components.pay.service.write;

import com.egeo.components.pay.dto.PayWeixinLogDTO;


public interface PayWeixinLogWriteService {

	public Long insertPayWeixinLogWithTx(PayWeixinLogDTO dto);

	public int updatePayWeixinLogWithTx(PayWeixinLogDTO dto);

	public int deletePayWeixinLogWithTx(PayWeixinLogDTO dto);
}
	