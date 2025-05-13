package com.egeo.components.product.manage.write;

import com.egeo.components.product.po.SkuAttValuePO;

import java.util.List;


public interface SkuAttValueWriteManage {

	Long insertSkuAttValueWithTx(SkuAttValuePO po);

	int updateSkuAttValueWithTx(SkuAttValuePO po);

	int deleteSkuAttValueWithTx(SkuAttValuePO po);

    void saveSkuAttValuePO(List<SkuAttValuePO> skuAttValuePOList);
}
	