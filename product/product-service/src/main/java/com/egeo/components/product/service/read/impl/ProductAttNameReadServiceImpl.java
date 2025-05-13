package com.egeo.components.product.service.read.impl;

import java.util.List;

import com.egeo.components.product.converter.StandardProductUnitConverter;
import com.egeo.components.product.dto.StandardProductUnitDTO;
import com.egeo.components.product.po.StandardProductUnitPO;
import com.egeo.utils.EmptyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.service.read.ProductAttNameReadService;
import com.egeo.components.product.manage.read.ProductAttNameReadManage;
import com.egeo.components.product.converter.ProductAttNameConverter;
import com.egeo.components.product.dto.ProductAttNameDTO;
import com.egeo.components.product.po.ProductAttNamePO;


@Service("productAttNameReadService")
public class ProductAttNameReadServiceImpl  implements ProductAttNameReadService {
	@Autowired
	private ProductAttNameReadManage productAttNameReadManage;

        @Override
        public List<ProductAttNameDTO> findAll(ProductAttNameDTO dto) {
            List<ProductAttNamePO> list = productAttNameReadManage.findAll(ProductAttNameConverter.toPO(dto));
            return ProductAttNameConverter.toDTO(list);
        }

    @Override
    public ProductAttNameDTO queryIsElectronicBySpuId(StandardProductUnitDTO dto) {
        StandardProductUnitPO po = StandardProductUnitConverter.toPO(dto);
        ProductAttNamePO productAttNamePO = productAttNameReadManage.queryIsElectronicBySpuId(po);
        if (EmptyUtil.isNotEmpty(productAttNamePO)) {
            return ProductAttNameConverter.toDTO(productAttNamePO);
        }
        return null;
    }

    @Override
    public ProductAttNameDTO queryIsUnitBySpuId(StandardProductUnitDTO dto) {
        StandardProductUnitPO po = StandardProductUnitConverter.toPO(dto);
        ProductAttNamePO productAttNamePO = productAttNameReadManage.queryIsUnitBySpuId(po);
        if (EmptyUtil.isNotEmpty(productAttNamePO)) {
            return ProductAttNameConverter.toDTO(productAttNamePO);
        }
       return null;
    }

    @Override
    public Long findLastId() {
        return productAttNameReadManage.findLastId();
    }
}
	