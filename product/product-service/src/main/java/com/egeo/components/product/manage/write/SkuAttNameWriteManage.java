package com.egeo.components.product.manage.write;

import com.egeo.components.product.po.SkuAttNamePO;

import java.util.List;


public interface SkuAttNameWriteManage {

	Long insertSkuAttNameWithTx(SkuAttNamePO po);

	int updateSkuAttNameWithTx(SkuAttNamePO po);

	int deleteSkuAttNameWithTx(SkuAttNamePO po);

    void saveSkuAttName(List<SkuAttNamePO> skuAttNamePOList);
}
	