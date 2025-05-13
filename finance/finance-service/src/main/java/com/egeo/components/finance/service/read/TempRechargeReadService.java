package com.egeo.components.finance.service.read;


import java.util.List;

import com.egeo.components.finance.dto.TempRechargeDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface TempRechargeReadService {

	public TempRechargeDTO findTempRechargeById(TempRechargeDTO dto);

	public PageResult<TempRechargeDTO> findTempRechargeOfPage(TempRechargeDTO dto,Pagination page);

	public List<TempRechargeDTO> findTempRechargeAll(TempRechargeDTO dto);

	/**
	 * 根据sn查询导入草稿总金额
	 * @param sn
	 * @return
	 */
	public double queryTempRechargeSummaryBySn(String sn);

	/**
	 * 根据sn查询草稿列表
	 * @param sn
	 * @return
	 */
	public List<TempRechargeDTO> queryTempRechargeBySn(String sn);

	/**
	 * 根据sn查询记录数量
	 * @param sn
	 * @return
	 */
	public int queryTempRechargeCountBySn(String sn);
}
	