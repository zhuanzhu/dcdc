package com.egeo.components.product.service.write.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.service.write.BrandWriteService;
import com.egeo.components.product.manage.read.BrandReadManage;
import com.egeo.components.product.manage.write.BrandWriteManage;
import com.egeo.components.product.converter.BrandConverter;
import com.egeo.components.product.dto.BrandDTO;
import com.egeo.components.product.po.BrandPO;


@Service("brandWriteService")
public class BrandWriteServiceImpl  implements BrandWriteService {
	@Autowired
	private BrandWriteManage brandWriteManage;
	
	@Autowired
        private BrandReadManage brandReadManage;

        @Override
        public Long saveBrandWithTx(BrandDTO dto) {
            List<BrandPO> list = brandReadManage.findAll(BrandConverter.toPO(dto));
            //根据chineseName查询到值说明品牌名一样直接返回id
            if(list.size() > 0 ){
                return list.get(0).getId();
            }else{
                return brandWriteManage.saveBrandWithTx(BrandConverter.toPO(dto));
            }
        }

        @Override
        public String updateBrandWithTx(BrandDTO dto) {
            return brandWriteManage.updateBrand(BrandConverter.toPO(dto));
        }
}
	