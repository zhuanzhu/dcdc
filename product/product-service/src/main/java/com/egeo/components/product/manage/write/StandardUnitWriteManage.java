package com.egeo.components.product.manage.write;

import com.egeo.components.product.po.StandardUnitPO;

import java.util.List;


public interface StandardUnitWriteManage {

	Long insertStandardUnitWithTx(StandardUnitPO po);

	int updateStandardUnitWithTx(StandardUnitPO po);

	int deleteStandardUnitWithTx(StandardUnitPO po);
	/**
	 * su上下架
	 * @param vo
	 * @param req
	 * @return
	 */
	int putawaySoldOutWithTx(StandardUnitPO po,int type);

    void saveStandardUnit(List<StandardUnitPO> standardUnitPOList);

    void updateStandardUnitPrice(List<StandardUnitPO> standardUnitPricePOList);
    void updateStandardUnitPriceList(List<StandardUnitPO> standardUnitPricePOList);

    void updateJdProductPriceByRate(Integer competingCompanyRate, Integer democompanysCompanyRate, Integer standardCompanyRate);

    void updateSUList(List<StandardUnitPO> standardUnitPOList);

    void updateSuAndPuStatusByJd();

    void updateJdProductStatusByProfit(Integer productLimitProfit);
}
	