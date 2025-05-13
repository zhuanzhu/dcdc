package com.egeo.components.order.service.write;

import com.egeo.components.order.dto.FrozenPuDTO;


public interface FrozenPuWriteService {

	public Long insertFrozenPuWithTx(FrozenPuDTO dto);

	public int updateFrozenPuWithTx(FrozenPuDTO dto);

	public int deleteFrozenPuWithTx(FrozenPuDTO dto);
}
	