package com.egeo.components.product.manage.read;

import java.util.List;

import com.egeo.components.product.po.BrandPO;

public interface BrandReadManage {

    List<BrandPO> findAll(BrandPO po);

	BrandPO findById(BrandPO po);
}
	