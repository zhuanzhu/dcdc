package com.egeo.components.finance.manage.read;

import java.util.List;

import com.egeo.components.finance.po.AdjustReasonPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface AdjustReasonReadManage {

	public AdjustReasonPO findAdjustReasonById(AdjustReasonPO po);

	public PageResult<AdjustReasonPO> findAdjustReasonOfPage(AdjustReasonPO po,Pagination page);

	public List<AdjustReasonPO> findAdjustReasonAll(AdjustReasonPO po);

	/**
	 * 查询所有调整原因
	 * @return
	 */
	public List<AdjustReasonPO> queryAdjustReasons(Long companyId);

	/**
	 * 原因分页列表
	 * @param type
	 * @param companyId
	 * @param disabled
	 * @param page
	 * @return
	 */
	public PageResult<AdjustReasonPO> queryAdjustReasonPage(Integer type, Long companyId, Integer disabled,
			Long platformId,Pagination page);

	/**
	 * 查询几个指定类型的原因
	 *
     * @param platformId
     * @param typeList
     * @param companyId
     * @return
	 */
	public List<AdjustReasonPO> queryAdjustReasonsByTypes(Long platformId, List<Integer> typeList, Long accountId, Long companyId);

}
	