package com.egeo.components.order.manage.read;

import java.util.List;

import com.egeo.components.order.po.SoRefundPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface SoRefundReadManage {

	public SoRefundPO findSoRefundById(SoRefundPO po);

	public PageResult<SoRefundPO> findSoRefundOfPage(SoRefundPO po, List<Long> userIdList, Pagination page);

	public List<SoRefundPO> findSoRefundAll(SoRefundPO po);

	public List<SoRefundPO> getByBatchJIDianId(Long batchId,Long orderId);

	public List<SoRefundPO> getByBatchFuBiId(Long batchId,Long orderId);

	public List<SoRefundPO> getByBatchCashId(Long batchId,Long orderId);
}
