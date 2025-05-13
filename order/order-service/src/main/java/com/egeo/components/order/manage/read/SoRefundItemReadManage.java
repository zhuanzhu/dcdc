package com.egeo.components.order.manage.read;

import com.egeo.components.order.po.SoRefundItemPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

import java.util.List;

public interface SoRefundItemReadManage {

	SoRefundItemPO findSoRefundItemById(SoRefundItemPO po);

	PageResult<SoRefundItemPO> findSoRefundItemOfPage(SoRefundItemPO po, Pagination page);

	List<SoRefundItemPO> findSoRefundItemAll(SoRefundItemPO po);
}
	