package com.egeo.components.product.manage.write;

import com.egeo.components.product.po.ProductDescriptionPO;

import java.util.List;

public interface ProductDescriptionWriteManage {

    Long saveProductDescription(ProductDescriptionPO po);

    String updateProductDescriptionWithTx(ProductDescriptionPO productDescriptionPO2);

    String deleteByProductId(Long productId);

    void saveProductDescriptionList(List<ProductDescriptionPO> productDescriptionPOList);

    void updateProductDescriptionSWithTx(ProductDescriptionPO productDescriptionPO);
}
	