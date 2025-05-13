package com.egeo.components.stock.manage.read;

import java.util.List;
	
import com.egeo.components.stock.po.StockDictPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface StockDictReadManage {

	public StockDictPO findStockDictById(StockDictPO po);

	public PageResult<StockDictPO> findStockDictOfPage(StockDictPO po,Pagination page);

	public List<StockDictPO> findStockDictAll(StockDictPO po);
}
	