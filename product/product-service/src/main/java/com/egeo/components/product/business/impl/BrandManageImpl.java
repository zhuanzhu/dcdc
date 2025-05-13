package com.egeo.components.product.business.impl;
	

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.egeo.components.product.business.BrandManage;
import com.egeo.components.product.converter.BrandConverter;
import com.egeo.components.product.dto.BrandDTO;
import com.egeo.components.product.facade.BrandFacade;
import com.egeo.components.product.vo.BrandVO;

@Service("brand")
public class BrandManageImpl implements BrandManage{

	
	@Resource(name="brandFacade")
	private BrandFacade brandFacade;
    
        @Override
        public Long saveBrand(BrandVO brandVO) {
            return brandFacade.saveBrand(BrandConverter.toDTO(brandVO));
        }

        @Override
        public String updateBrand(BrandVO brandVO) {
            return brandFacade.updateBrand(BrandConverter.toDTO(brandVO));
        }

		@Override
		public BrandVO findById(BrandVO brandVO) {
			BrandDTO brandDTO = brandFacade.findById(BrandConverter.toDTO(brandVO));
			return BrandConverter.toVO(brandDTO);
		}
	


}
	