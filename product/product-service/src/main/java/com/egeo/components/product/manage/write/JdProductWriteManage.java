package com.egeo.components.product.manage.write;

import com.egeo.components.product.po.JdProductPO;
import com.egeo.components.product.po.ProductUnitPO;

import java.util.List;


public interface JdProductWriteManage {

	Long insertJdProductWithTx(JdProductPO po);

	int updateJdProductWithTx(JdProductPO po);

	int deleteJdProductWithTx(JdProductPO po);
	
	void updateSyncStatus(JdProductPO po);

	void saveJdProductListFirst(List<JdProductPO> jdProductPOList);

    void setAllSyncStatus(int status);

    void updateList(List<JdProductPO> updateList);

    void updateJdProductPrice(List<JdProductPO> jdProductPOList);

    void updateProductCreateTime(List<Long> jdProductIdList);
}
	