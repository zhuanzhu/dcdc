package com.egeo.components.promotion.manage.read;

import com.egeo.components.promotion.po.BuyCardBasePO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

import java.util.List;

public interface BuyCardBaseReadManage {

	public BuyCardBasePO findBuyCardBaseById(BuyCardBasePO po);

	public PageResult<BuyCardBasePO> findBuyCardBaseOfPage(BuyCardBasePO po,Pagination page);

	public List<BuyCardBasePO> findBuyCardBaseAll(BuyCardBasePO po);

	public int findMaxSortValue();
}
