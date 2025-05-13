package com.egeo.components.product.service.read;


import java.util.List;
	
import com.egeo.components.product.dto.CommodityProductUnitCompareDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface CommodityProductUnitCompareReadService {

	public CommodityProductUnitCompareDTO findCommodityProductUnitCompareById(CommodityProductUnitCompareDTO dto);

	public PageResult<CommodityProductUnitCompareDTO> findCommodityProductUnitCompareOfPage(CommodityProductUnitCompareDTO dto,Pagination page);

	public List<CommodityProductUnitCompareDTO> findCommodityProductUnitCompareAll(CommodityProductUnitCompareDTO dto);
}
	