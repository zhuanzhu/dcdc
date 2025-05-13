package com.egeo.components.pay.service.write;

import com.egeo.components.pay.dto.JdOrderAwaitQueueDTO;


public interface JdOrderAwaitQueueWriteService {

	public Long insertJdOrderAwaitQueueWithTx(JdOrderAwaitQueueDTO dto);

	public int updateJdOrderAwaitQueueWithTx(JdOrderAwaitQueueDTO dto);

	public int deleteJdOrderAwaitQueueWithTx(JdOrderAwaitQueueDTO dto);
}
	