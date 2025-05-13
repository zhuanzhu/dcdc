package com.egeo.components.product.business;

import com.egeo.components.product.vo.BrandVO;

public interface BrandManage {

    Long saveBrand(BrandVO brandVO);

    String updateBrand(BrandVO brandVO);

	BrandVO findById(BrandVO brandVO);
	

}
	