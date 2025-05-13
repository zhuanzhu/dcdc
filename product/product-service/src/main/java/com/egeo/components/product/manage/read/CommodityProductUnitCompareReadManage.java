package com.egeo.components.product.manage.read;

import java.util.List;
	
import com.egeo.components.product.po.CommodityProductUnitComparePO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface CommodityProductUnitCompareReadManage {

	public CommodityProductUnitComparePO findCommodityProductUnitCompareById(CommodityProductUnitComparePO po);

	public PageResult<CommodityProductUnitComparePO> findCommodityProductUnitCompareOfPage(CommodityProductUnitComparePO po,Pagination page);

	public List<CommodityProductUnitComparePO> findCommodityProductUnitCompareAll(CommodityProductUnitComparePO po);
}
	