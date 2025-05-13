package com.egeo.components.pay.service.write;

import com.egeo.components.pay.dto.AlipayNativeSignLogDTO;


public interface AlipayNativeSignLogWriteService {

	public Long insertAlipayNativeSignLogWithTx(AlipayNativeSignLogDTO dto);

	public int updateAlipayNativeSignLogWithTx(AlipayNativeSignLogDTO dto);

	public int deleteAlipayNativeSignLogWithTx(AlipayNativeSignLogDTO dto);
}
	