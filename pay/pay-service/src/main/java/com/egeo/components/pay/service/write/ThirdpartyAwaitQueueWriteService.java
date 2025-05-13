package com.egeo.components.pay.service.write;

import com.egeo.components.pay.dto.ThirdpartyAwaitQueueDTO;


public interface ThirdpartyAwaitQueueWriteService {

	public Long insertThirdpartyAwaitQueueWithTx(ThirdpartyAwaitQueueDTO dto);

	public int updateThirdpartyAwaitQueueWithTx(ThirdpartyAwaitQueueDTO dto);

	public int deleteThirdpartyAwaitQueueWithTx(ThirdpartyAwaitQueueDTO dto);

    int deleteThirdpartyAwaitQueueByCodeWithTx(ThirdpartyAwaitQueueDTO thirdpartyAwaitQueueDTO);
}
	