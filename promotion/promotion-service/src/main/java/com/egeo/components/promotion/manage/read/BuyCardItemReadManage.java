package com.egeo.components.promotion.manage.read;

import com.egeo.components.promotion.po.BuyCardItemPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

import java.util.List;

public interface BuyCardItemReadManage {

	public BuyCardItemPO findBuyCardItemById(BuyCardItemPO po);

	public PageResult<BuyCardItemPO> findBuyCardItemOfPage(BuyCardItemPO po,Pagination page);

	public List<BuyCardItemPO> findBuyCardItemAll(BuyCardItemPO po);
}
