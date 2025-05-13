package com.egeo.components.product.service.write;

import com.egeo.components.product.dto.CommodityProductUnitDTO;

import java.math.BigDecimal;
import java.util.List;


public interface CommodityProductUnitWriteService {

	public Long insertCommodityProductUnitWithTx(CommodityProductUnitDTO dto);

	public int updateCommodityProductUnitWithTx(CommodityProductUnitDTO dto);

	public int deleteCommodityProductUnitWithTx(CommodityProductUnitDTO dto);
	/**
	 * 根据pu草稿id修改pu信息
	 * @param commodityProductUnitDTO
	 * @return
	 */
	public int updateCommodityProductUnitByProductUnitIdWithTx(CommodityProductUnitDTO commodityProductUnitDTO);

    void saveCommodityProductUnit(List<Long> jdSpuIdList,List<Long> puIdList, List<String> productUnitSerialNumberList, List<Long> productUnitIdList, List<Long> skuIdList, List<Long> suIdList, List<String> nameList, List<BigDecimal> salePriceList, List<BigDecimal> demoSalePriceList, List<BigDecimal> competingSalePriceList);

    void updateCommodityProductUnitPrice(List<Long> puIdList, List<BigDecimal> salePriceList, List<BigDecimal> demoPriceList, List<BigDecimal> competingPriceList, List<BigDecimal> marketPriceList);

    void updateJdProductPriceByRate(Integer competingCompanyRate, Integer democompanysCompanyRate, Integer standardCompanyRate);

    void updateCommodityProductUnitList(List<Long> puIdList, List<String> nameList, List<BigDecimal> salePriceList, List<BigDecimal> demoSalePriceList, List<BigDecimal> competingSalePriceList);
}
	