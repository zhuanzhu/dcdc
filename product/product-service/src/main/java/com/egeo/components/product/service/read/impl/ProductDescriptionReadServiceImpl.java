package com.egeo.components.product.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.service.read.ProductDescriptionReadService;
import com.egeo.components.product.manage.read.ProductDescriptionReadManage;
import com.egeo.components.product.converter.ProductDescriptionConverter;
import com.egeo.components.product.dto.ProductDescriptionDTO;
import com.egeo.components.product.po.ProductDescriptionPO;


@Service("productDescriptionReadService")
public class ProductDescriptionReadServiceImpl  implements ProductDescriptionReadService {
	@Autowired
	private ProductDescriptionReadManage productDescriptionReadManage;

        @Override
        public List<ProductDescriptionDTO> findAll(ProductDescriptionDTO dto) {
            List<ProductDescriptionPO> lists = productDescriptionReadManage.findAll(ProductDescriptionConverter.toPO(dto));
            return ProductDescriptionConverter.toDTO(lists);
        }

        @Override
        public ProductDescriptionDTO findById(ProductDescriptionDTO dto) {
            ProductDescriptionPO productDescriptionPO = productDescriptionReadManage.findById(ProductDescriptionConverter.toPO(dto));
            return ProductDescriptionConverter.toDTO(productDescriptionPO);
        }
}
	