package com.egeo.components.finance.business;

import java.math.BigDecimal;
import java.util.List;

import com.egeo.components.finance.dto.SoFreezeFubiDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface SoFreezeFubiManage {

	public SoFreezeFubiDTO findSoFreezeFubiById(SoFreezeFubiDTO dto);	

	public PageResult<SoFreezeFubiDTO> findSoFreezeFubiOfPage(SoFreezeFubiDTO dto,Pagination page);

	public List<SoFreezeFubiDTO> findSoFreezeFubiAll(SoFreezeFubiDTO dto);

	Long insertSoFreezeFubiWithTx(SoFreezeFubiDTO dto);

	int updateSoFreezeFubiWithTx(SoFreezeFubiDTO dto);

	int deleteSoFreezeFubiWithTx(SoFreezeFubiDTO dto);
	/**
	 * 根据订单id查询订单冻结积分
	 * @param soId
	 * @return
	 */
	public BigDecimal findSoFreezeBalanceBySoId(Long soId);
}
	