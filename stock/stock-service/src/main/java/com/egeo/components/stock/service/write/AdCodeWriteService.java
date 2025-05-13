package com.egeo.components.stock.service.write;

import com.egeo.components.stock.dto.AdCodeDTO;


public interface AdCodeWriteService {

	public Long insertAdCodeWithTx(AdCodeDTO dto);

	public int updateAdCodeWithTx(AdCodeDTO dto);

	public int deleteAdCodeWithTx(AdCodeDTO dto);
}
	