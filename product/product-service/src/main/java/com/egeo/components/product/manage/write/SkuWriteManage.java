package com.egeo.components.product.manage.write;

import java.util.List;

import com.egeo.components.product.po.CommodityProductUnitPO;
import com.egeo.components.product.po.SkuPO;


public interface SkuWriteManage {

	Long insertSkuWithTx(SkuPO po);

	int updateSkuWithTx(SkuPO po);

	int deleteSkuWithTx(SkuPO po);
	/**
	 * 根据spuid更新supu信息
	 * @param standardProductUnitId
	 * @param id
	 */
	List<CommodityProductUnitPO> updateSuPuWithTx(Long standardProductUnitId, Long merchantProductId);

    int updateSkuParamsWithTx(SkuPO po);

    void saveSku(List<SkuPO> skuPOList);

    void updateSkuList(List<SkuPO> skuPOList);
}
	