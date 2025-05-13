package com.egeo.components.finance.manage.read;

import java.math.BigDecimal;
import java.util.List;

import com.egeo.components.finance.po.SoFreezeFubiPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface SoFreezeFubiReadManage {

	public SoFreezeFubiPO findSoFreezeFubiById(SoFreezeFubiPO po);

	public PageResult<SoFreezeFubiPO> findSoFreezeFubiOfPage(SoFreezeFubiPO po,Pagination page);

	public List<SoFreezeFubiPO> findSoFreezeFubiAll(SoFreezeFubiPO po);
	/**
	 * 根据订单id查询订单冻结积分
	 * @param soId
	 * @return
	 */
	public BigDecimal findSoFreezeBalanceBySoId(Long soId);
}
	