package com.egeo.components.promotion.condition;

import com.egeo.components.promotion.po.ExchangeActivityPO;
import com.egeo.components.promotion.po.ExchangeBatchPO;

import java.util.List;

/**
 * 
 * @author feng
 * @date 2018-12-14 10:57:17
 */
public class ExchangeActivityCondition extends ExchangeActivityPO {
	private static final long serialVersionUID = 1L;
	private List<Long> oldBatchIdList;
	private List<ExchangeBatchPO> newBatchList;

	private List<Integer> unitStatus;

	public List<Long> getOldBatchIdList() {
		return oldBatchIdList;
	}

	public void setOldBatchIdList(List<Long> oldBatchIdList) {
		this.oldBatchIdList = oldBatchIdList;
	}

	public List<ExchangeBatchPO> getNewBatchList() {
		return newBatchList;
	}

	public void setNewBatchList(List<ExchangeBatchPO> newBatchList) {
		this.newBatchList = newBatchList;
	}

	public List<Integer> getUnitStatus() {
		return unitStatus;
	}

	public void setUnitStatus(List<Integer> unitStatus) {
		this.unitStatus = unitStatus;
	}
}
	