package com.egeo.components.user.service.write;

import com.egeo.components.user.dto.PayTypeDTO;


public interface PayTypeWriteService {

	public Long insertPayTypeWithTx(PayTypeDTO dto);

	public int updatePayTypeWithTx(PayTypeDTO dto);

	public int deletePayTypeWithTx(PayTypeDTO dto);
}
	