package com.egeo.components.finance.manage.read;

import java.util.List;

import com.egeo.components.finance.po.TempRechargePO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface TempRechargeReadManage {

	public TempRechargePO findTempRechargeById(TempRechargePO po);

	public PageResult<TempRechargePO> findTempRechargeOfPage(TempRechargePO po,Pagination page);

	public List<TempRechargePO> findTempRechargeAll(TempRechargePO po);

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
	public List<TempRechargePO> queryTempRechargeBySn(String sn);

	/**
	 * 根据sn查询记录数量
	 * @param sn
	 * @return
	 */
	public int queryTempRechargeCountBySn(String sn);
}
	