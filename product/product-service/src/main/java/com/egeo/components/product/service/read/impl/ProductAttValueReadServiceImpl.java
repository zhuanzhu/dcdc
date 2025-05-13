package com.egeo.components.product.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.service.read.ProductAttValueReadService;
import com.egeo.components.product.manage.read.ProductAttValueReadManage;
import com.egeo.components.product.converter.ProductAttValueConverter;
import com.egeo.components.product.dto.ProductAttValueDTO;
import com.egeo.components.product.po.ProductAttValuePO;


@Service("productAttValueReadService")
public class ProductAttValueReadServiceImpl  implements ProductAttValueReadService {
	@Autowired
	private ProductAttValueReadManage productAttValueReadManage;

        @Override
        public List<ProductAttValueDTO> findAll(ProductAttValueDTO dto) {
            List<ProductAttValuePO> list = productAttValueReadManage.findAll(ProductAttValueConverter.toPO(dto));
            return ProductAttValueConverter.toDTO(list);
        }
}
	