package com.egeo.components.product.facade;

import java.util.List;

import com.egeo.components.product.service.write.*;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.product.service.read.JdPriceConfigReadService;
import com.egeo.components.product.dto.JdPriceConfigDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;


@Component
public class JdPriceConfigFacade {
	
	@Resource
	private JdPriceConfigReadService jdPriceConfigReadService;
	
	@Resource
	private JdPriceConfigWriteService jdPriceConfigWriteService;
	@Resource
	private StandardUnitWriteService standardUnitWriteService;
	@Resource
	private CommodityProductUnitWriteService commodityProductUnitWriteService;
	@Resource
	private MerchantProductWriteService merchantProductWriteService;
	@Resource
	private ProductUnitWriteService productUnitWriteService;
	
	public JdPriceConfigDTO findJdPriceConfigById(JdPriceConfigDTO dto){
		
		return jdPriceConfigReadService.findJdPriceConfigById(dto);
	}

	public PageResult<JdPriceConfigDTO> findJdPriceConfigOfPage(JdPriceConfigDTO dto,Pagination page){
		
		return jdPriceConfigReadService.findJdPriceConfigOfPage(dto, page);
		
	}

	public JdPriceConfigDTO findJdPriceConfigAll(JdPriceConfigDTO dto){
		List<JdPriceConfigDTO> jpcList = jdPriceConfigReadService.findJdPriceConfigAll(dto);
		if (EmptyUtil.isNotEmpty(jpcList)) {
			return jpcList.get(0);
		}
		return null;
	}

	public Long insertJdPriceConfigWithTx(JdPriceConfigDTO dto){
		
		return jdPriceConfigWriteService.insertJdPriceConfigWithTx(dto);
	}

	public int updateJdPriceConfigWithTx(JdPriceConfigDTO dto){
		
		return jdPriceConfigWriteService.updateJdPriceConfigWithTx(dto);
	}

	public int deleteJdPriceConfigWithTx(JdPriceConfigDTO dto){
		
		return jdPriceConfigWriteService.deleteJdPriceConfigWithTx(dto);
		
	}

    public void updateJdProductPriceByRate(Integer competingCompanyRate, Integer democompanysCompanyRate, Integer standardCompanyRate) {
		standardUnitWriteService.updateJdProductPriceByRate(competingCompanyRate, democompanysCompanyRate, standardCompanyRate);
		//同意在一个sql中更新
		/*commodityProductUnitWriteService.updateJdProductPriceByRate(competingCompanyRate, democompanysCompanyRate, standardCompanyRate);
		merchantProductWriteService.updateJdProductPriceByRate(competingCompanyRate, democompanysCompanyRate, standardCompanyRate);
		productUnitWriteService.updateJdProductPriceByRate(competingCompanyRate, democompanysCompanyRate, standardCompanyRate);*/
	}
}
	