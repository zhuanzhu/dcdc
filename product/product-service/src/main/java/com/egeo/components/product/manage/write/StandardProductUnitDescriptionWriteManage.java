package com.egeo.components.product.manage.write;

import com.egeo.components.product.po.StandardProductUnitDescriptionPO;

import java.util.List;


public interface StandardProductUnitDescriptionWriteManage {

	Long insertStandardProductUnitDescriptionWithTx(StandardProductUnitDescriptionPO po);

	int updateStandardProductUnitDescriptionWithTx(StandardProductUnitDescriptionPO po);

	int deleteStandardProductUnitDescriptionWithTx(StandardProductUnitDescriptionPO po);

    void saveStandardProductUnitDescription(List<StandardProductUnitDescriptionPO> standardProductUnitDescriptionPOList);

    void updateStandardProductUnitDescriptionSWithTx(StandardProductUnitDescriptionPO standardProductUnitDescriptionPO);
}
	