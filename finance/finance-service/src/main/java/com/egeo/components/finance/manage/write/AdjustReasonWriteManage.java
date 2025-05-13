package com.egeo.components.finance.manage.write;

import java.util.List;

import com.egeo.components.finance.po.AdjustReasonPO;


public interface AdjustReasonWriteManage {

	Long insertAdjustReasonWithTx(AdjustReasonPO po);

	int updateAdjustReasonWithTx(AdjustReasonPO po);

	int deleteAdjustReasonWithTx(AdjustReasonPO po);

	/**
	 * 新建原因,并建立与公司的关系
	 * @param po
	 * @param cIds 
	 * @param cIds
	 * @return
	 */
	Long createAdjustReasonWithTx(AdjustReasonPO po, List<Long> cIds);

	/**
	 * 更改原因,并更改与公司的关系
	 * @param po
	 * @param cIds 
	 * @param cIds
	 * @return
	 */
	int editAdjustReasonWithTx(AdjustReasonPO po, List<Long> cIds);
}
	