package com.egeo.components.promotion.manage.read;

import java.util.List;
	
import com.egeo.components.promotion.po.FuCoinHistoryPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface FuCoinHistoryReadManage {

	public FuCoinHistoryPO findFuCoinHistoryById(FuCoinHistoryPO po);

	public PageResult<FuCoinHistoryPO> findFuCoinHistoryOfPage(FuCoinHistoryPO po,Pagination page);

	public List<FuCoinHistoryPO> findFuCoinHistoryAll(FuCoinHistoryPO po);
}
	