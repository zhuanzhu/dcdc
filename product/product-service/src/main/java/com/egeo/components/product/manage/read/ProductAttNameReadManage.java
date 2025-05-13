package com.egeo.components.product.manage.read;

import java.util.List;

import com.egeo.components.product.po.ProductAttNamePO;
import com.egeo.components.product.po.StandardProductUnitPO;

public interface ProductAttNameReadManage {

    List<ProductAttNamePO> findAll(ProductAttNamePO po);


    ProductAttNamePO queryIsElectronicBySpuId(StandardProductUnitPO po);

    ProductAttNamePO queryIsUnitBySpuId(StandardProductUnitPO po);

    Long findLastId();
}
	