package com.egeo.components.product.business;

import java.util.List;
	
import com.egeo.components.product.dto.CommodityProductUnitCompareDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface CommodityProductUnitCompareManage {

	public CommodityProductUnitCompareDTO findCommodityProductUnitCompareById(CommodityProductUnitCompareDTO dto);	

	public PageResult<CommodityProductUnitCompareDTO> findCommodityProductUnitCompareOfPage(CommodityProductUnitCompareDTO dto,Pagination page);

	public List<CommodityProductUnitCompareDTO> findCommodityProductUnitCompareAll(CommodityProductUnitCompareDTO dto);

	Long insertCommodityProductUnitCompareWithTx(CommodityProductUnitCompareDTO dto);

	int updateCommodityProductUnitCompareWithTx(CommodityProductUnitCompareDTO dto);

	int deleteCommodityProductUnitCompareWithTx(CommodityProductUnitCompareDTO dto);
}
	