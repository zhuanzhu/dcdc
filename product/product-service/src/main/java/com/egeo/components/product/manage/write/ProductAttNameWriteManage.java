package com.egeo.components.product.manage.write;

import com.egeo.components.product.po.ProductAttNamePO;

import java.util.List;

public interface ProductAttNameWriteManage {

    Long saveProductAttNameWithTx(ProductAttNamePO po);

    String deleteByProductIdWithTx(Long productId);

    void saveProductAttName(List<ProductAttNamePO> productAttNamePOList);
}
	