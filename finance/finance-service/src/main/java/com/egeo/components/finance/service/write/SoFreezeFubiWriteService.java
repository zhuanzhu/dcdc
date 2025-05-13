package com.egeo.components.finance.service.write;

import com.egeo.components.finance.dto.SoFreezeFubiDTO;


public interface SoFreezeFubiWriteService {

	public Long insertSoFreezeFubiWithTx(SoFreezeFubiDTO dto);

	public int updateSoFreezeFubiWithTx(SoFreezeFubiDTO dto);

	public int deleteSoFreezeFubiWithTx(SoFreezeFubiDTO dto);
	
	/**
	 * 根据订单id删除订单冻结积分
	 * @param soId
	 * @return
	 */
	public int delBySoId(Long soId);

}
	