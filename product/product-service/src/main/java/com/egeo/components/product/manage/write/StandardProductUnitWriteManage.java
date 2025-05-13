package com.egeo.components.product.manage.write;

import com.egeo.components.product.po.StandardProductUnitPO;

import java.util.List;


public interface StandardProductUnitWriteManage {

	Long insertStandardProductUnitWithTx(StandardProductUnitPO po);

	int updateStandardProductUnitWithTx(StandardProductUnitPO po);

	int deleteStandardProductUnitWithTx(StandardProductUnitPO po);

    void saveSPU(List<StandardProductUnitPO> standardProductUnitPOList);

    void updateStandardProductUnitList(List<StandardProductUnitPO> standardProductUnitPOList);
}
	