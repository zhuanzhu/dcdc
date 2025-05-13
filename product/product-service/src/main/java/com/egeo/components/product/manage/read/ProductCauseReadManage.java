package com.egeo.components.product.manage.read;

import java.util.List;

import com.egeo.components.product.po.ProductCausePO;

public interface ProductCauseReadManage {

    List<ProductCausePO> findByProductId(Long productId);
}
	