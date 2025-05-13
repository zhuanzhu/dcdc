package com.egeo.components.product.manage.read;

import java.util.List;

import com.egeo.components.product.po.ProductDescriptionPO;

public interface ProductDescriptionReadManage {

    List<ProductDescriptionPO> findAll(ProductDescriptionPO po);

    ProductDescriptionPO findById(ProductDescriptionPO po);
}
	