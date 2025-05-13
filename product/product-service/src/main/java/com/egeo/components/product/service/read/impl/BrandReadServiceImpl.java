package com.egeo.components.product.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.service.read.BrandReadService;
import com.egeo.components.product.manage.read.BrandReadManage;
import com.egeo.components.product.converter.BrandConverter;
import com.egeo.components.product.dto.BrandDTO;
import com.egeo.components.product.po.BrandPO;


@Service("brandReadService")
public class BrandReadServiceImpl  implements BrandReadService {
	@Autowired
	private BrandReadManage brandReadManage;

        @Override
        public List<BrandDTO> findAll(BrandDTO dto) {
            List<BrandPO> list = brandReadManage.findAll(BrandConverter.toPO(dto));
            return BrandConverter.toDTO(list);
        }

		@Override
		public BrandDTO findById(BrandDTO dto) {
			BrandPO brandPO = brandReadManage.findById(BrandConverter.toPO(dto));
			return BrandConverter.toDTO(brandPO);
		}
}
	