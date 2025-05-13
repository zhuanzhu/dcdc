package com.egeo.components.product.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.product.service.write.ProductCauseWriteService;
import com.egeo.components.product.manage.write.ProductCauseWriteManage;
import com.egeo.components.product.converter.ProductCauseConverter;
import com.egeo.components.product.dto.ProductCauseDTO;

@Service("productCauseWriteService")
public class ProductCauseWriteServiceImpl  implements ProductCauseWriteService {
	@Autowired
	private ProductCauseWriteManage productCauseWriteManage;

        @Override
        public Long saveProductCauseWithTx(ProductCauseDTO dto) {
            dto.setSortValue(1);
            return productCauseWriteManage.saveProductCause(ProductCauseConverter.toPO(dto));
        }
}
	