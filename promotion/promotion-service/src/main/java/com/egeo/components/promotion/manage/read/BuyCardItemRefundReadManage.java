package com.egeo.components.promotion.manage.read;

import com.egeo.components.promotion.po.BuyCardItemRefundPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

import java.util.List;

public interface BuyCardItemRefundReadManage {

	public BuyCardItemRefundPO findBuyCardItemRefundById(BuyCardItemRefundPO po);

	public PageResult<BuyCardItemRefundPO> findBuyCardItemRefundOfPage(BuyCardItemRefundPO po,Pagination page);

	public List<BuyCardItemRefundPO> findBuyCardItemRefundAll(BuyCardItemRefundPO po);
}
