package com.egeo.components.product.service.write;

import com.egeo.components.product.dto.CommodityProductUnitCompareDTO;


public interface CommodityProductUnitCompareWriteService {

	public Long insertCommodityProductUnitCompareWithTx(CommodityProductUnitCompareDTO dto);

	public int updateCommodityProductUnitCompareWithTx(CommodityProductUnitCompareDTO dto);

	public int deleteCommodityProductUnitCompareWithTx(CommodityProductUnitCompareDTO dto);
}
	