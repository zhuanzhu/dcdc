package com.egeo.components.product.service.write;

import com.egeo.components.product.dto.StandardUnitDTO;

import java.math.BigDecimal;
import java.util.List;


public interface StandardUnitWriteService {

	public Long insertStandardUnitWithTx(StandardUnitDTO dto);

	public int updateStandardUnitWithTx(StandardUnitDTO dto);

	public int deleteStandardUnitWithTx(StandardUnitDTO dto);
	/**
	 * su上下架
	 * @param vo
	 * @param req
	 * @return
	 */
	public int putawaySoldOutWithTx(StandardUnitDTO dto,int type);

    void saveStandardUnit(List<Integer> profitList, List<Long> suIdList, List<Long> spuIdList, List<String> merchantProductSerialNumberList, List<String> productCategoryList, List<String> nameList, List<BigDecimal> salePriceList, List<BigDecimal> marketPriceList, List<BigDecimal> demoSalePriceList, List<BigDecimal> competingSalePriceList);

    void updateStandardUnitPrice(List<Integer> profits, List<Integer> statusList, List<Integer> isVisibleList, List<Long> standardUnitIdList, List<BigDecimal> salePriceList, List<BigDecimal> demoPriceList, List<BigDecimal> competingPriceList, List<BigDecimal> marketPriceList);

    void updateJdProductPriceByRate(Integer competingCompanyRate, Integer democompanysCompanyRate, Integer standardCompanyRate);

    int updateSuVisible(StandardUnitDTO standardUnitDTO);

    void updateSUList(List<Integer> profitList, List<Long> suIdList, List<String> nameList, List<BigDecimal> marketPriceList, List<BigDecimal> salePriceList, List<BigDecimal> demoSalePriceList, List<BigDecimal> competingSalePriceList);

    void updateSuAndPuStatusByJd();

    void updateJdProductStatusByProfit(Integer productLimitProfit);
}
	