package com.egeo.components.product.service.write;

import com.egeo.components.product.dto.ProductUnitDTO;

import java.math.BigDecimal;
import java.util.List;


public interface ProductUnitWriteService {

	public Long insertProductUnitWithTx(ProductUnitDTO dto);

	public int updateProductUnitWithTx(ProductUnitDTO dto);

	public int deleteProductUnitWithTx(ProductUnitDTO dto);

    void saveProductUnit(List<Long> productUnitIdList, List<String> productUnitSerialNumberList, List<Long> skuIdList, List<Long> merchantProductIdList, List<String> nameList, List<BigDecimal> salePriceList, List<BigDecimal> demoSalePriceList, List<BigDecimal> competingSalePriceList);

    void updateProductUnitPrice(List<Long> productUnitIdList, List<BigDecimal> salePriceList, List<BigDecimal> demoPriceList, List<BigDecimal> competingPriceList, List<BigDecimal> marketPriceList);

    void updateJdProductPriceByRate(Integer competingCompanyRate, Integer democompanysCompanyRate, Integer standardCompanyRate);

    void updateProductUnitList(List<Long> productUnitIdList, List<String> nameList, List<BigDecimal> salePriceList, List<BigDecimal> demoSalePriceList, List<BigDecimal> competingSalePriceList);
}
	