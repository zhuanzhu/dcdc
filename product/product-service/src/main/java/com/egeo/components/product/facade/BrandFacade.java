package com.egeo.components.product.facade;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.product.service.read.BrandReadService;
import com.egeo.components.product.service.write.BrandWriteService;
import com.egeo.components.product.dto.BrandDTO;


@Component
public class BrandFacade {
	
	@Resource
	private BrandReadService brandReadService;
	
	@Resource
        private BrandWriteService brandWriteService;

        public Long saveBrand(BrandDTO dto) {
            return brandWriteService.saveBrandWithTx(dto);
        }

        public String updateBrand(BrandDTO dto) {
            return brandWriteService.updateBrandWithTx(dto);
        }

		public BrandDTO findById(BrandDTO dto) {
			return brandReadService.findById(dto);
		}
    	


}
	