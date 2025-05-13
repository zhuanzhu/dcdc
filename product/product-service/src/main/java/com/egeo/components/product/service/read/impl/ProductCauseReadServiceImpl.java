package com.egeo.components.product.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.service.read.ProductCauseReadService;
import com.egeo.components.product.manage.read.ProductCauseReadManage;
import com.egeo.components.product.converter.ProductCauseConverter;
import com.egeo.components.product.dto.ProductCauseDTO;
import com.egeo.components.product.po.ProductCausePO;


@Service("productCauseReadService")
public class ProductCauseReadServiceImpl  implements ProductCauseReadService {
	@Autowired
	private ProductCauseReadManage productCauseReadManage;

        @Override
        public List<ProductCauseDTO> findByProductId(Long productId) {
            List<ProductCausePO> list = productCauseReadManage.findByProductId(productId);
            return ProductCauseConverter.toDTO(list);
        }
}
	