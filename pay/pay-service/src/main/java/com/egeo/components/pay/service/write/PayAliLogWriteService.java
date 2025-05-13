package com.egeo.components.pay.service.write;

import com.egeo.components.pay.dto.PayAliLogDTO;


public interface PayAliLogWriteService {

	public Long insertPayAliLogWithTx(PayAliLogDTO dto);

	public int updatePayAliLogWithTx(PayAliLogDTO dto);

	public int deletePayAliLogWithTx(PayAliLogDTO dto);

	/**
	 * 插入支付宝回调记录
	 * @param log
	 */
	public Long insertPayWeixinLogWithTx(PayAliLogDTO log);
}
	