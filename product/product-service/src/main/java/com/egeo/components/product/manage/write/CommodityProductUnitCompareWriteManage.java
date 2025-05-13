package com.egeo.components.product.manage.write;

import com.egeo.components.product.po.CommodityProductUnitComparePO;


public interface CommodityProductUnitCompareWriteManage {

	Long insertCommodityProductUnitCompareWithTx(CommodityProductUnitComparePO po);

	int updateCommodityProductUnitCompareWithTx(CommodityProductUnitComparePO po);

	int deleteCommodityProductUnitCompareWithTx(CommodityProductUnitComparePO po);
}
	