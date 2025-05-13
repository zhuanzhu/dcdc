package com.egeo.components.product.manage.read;

import java.util.List;

import com.egeo.components.product.po.CategoryAttValuePO;

public interface CategoryAttValueReadManage {

    List<CategoryAttValuePO> findAll(CategoryAttValuePO po);
}
	