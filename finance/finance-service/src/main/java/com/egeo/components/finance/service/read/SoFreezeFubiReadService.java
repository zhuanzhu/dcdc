package com.egeo.components.finance.service.read;


import java.math.BigDecimal;
import java.util.List;

import com.egeo.components.finance.dto.SoFreezeFubiDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface SoFreezeFubiReadService {

	public SoFreezeFubiDTO findSoFreezeFubiById(SoFreezeFubiDTO dto);

	public PageResult<SoFreezeFubiDTO> findSoFreezeFubiOfPage(SoFreezeFubiDTO dto,Pagination page);

	public List<SoFreezeFubiDTO> findSoFreezeFubiAll(SoFreezeFubiDTO dto);
	/**
	 * 根据订单id查询订单冻结积分
	 * @param soId
	 * @return
	 */
	public BigDecimal findSoFreezeBalanceBySoId(Long soId);
}
	