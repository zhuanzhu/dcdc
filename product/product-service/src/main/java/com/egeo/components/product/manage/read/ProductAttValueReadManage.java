package com.egeo.components.product.manage.read;

import java.util.List;

import com.egeo.components.product.po.ProductAttValuePO;

public interface ProductAttValueReadManage {

    List<ProductAttValuePO> findAll(ProductAttValuePO po);
}
	