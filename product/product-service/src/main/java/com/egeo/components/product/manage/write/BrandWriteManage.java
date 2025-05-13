package com.egeo.components.product.manage.write;

import com.egeo.components.product.po.BrandPO;

public interface BrandWriteManage {

    Long saveBrandWithTx(BrandPO po);

    String updateBrand(BrandPO po);
}
	