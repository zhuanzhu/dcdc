package com.egeo.components.product.business.impl;
	

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.egeo.components.product.business.JdPriceConfigManage;
import com.egeo.components.product.facade.JdPriceConfigFacade;
import com.egeo.components.product.dto.JdPriceConfigDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("jdPriceConfig")
public class JdPriceConfigManageImpl implements JdPriceConfigManage{

	
	@Resource(name="jdPriceConfigFacade")
	private JdPriceConfigFacade jdPriceConfigFacade;

	@Override
	public JdPriceConfigDTO findJdPriceConfigById(JdPriceConfigDTO dto) {
		return jdPriceConfigFacade.findJdPriceConfigById(dto);
	}

	@Override
	public PageResult<JdPriceConfigDTO> findJdPriceConfigOfPage(JdPriceConfigDTO dto, Pagination page) {
		return jdPriceConfigFacade.findJdPriceConfigOfPage(dto, page);
	}

	@Override
	public JdPriceConfigDTO findJdPriceConfigAll(JdPriceConfigDTO dto) {
		return jdPriceConfigFacade.findJdPriceConfigAll(dto);
	}

	@Override
	public Long insertJdPriceConfigWithTx(JdPriceConfigDTO dto) {
		return jdPriceConfigFacade.insertJdPriceConfigWithTx(dto);
	}

	@Override
	public int updateJdPriceConfigWithTx(JdPriceConfigDTO dto) {
		return jdPriceConfigFacade.updateJdPriceConfigWithTx(dto);
	}

	@Override
	public int deleteJdPriceConfigWithTx(JdPriceConfigDTO dto) {
		return jdPriceConfigFacade.deleteJdPriceConfigWithTx(dto);
	}

	@Override
	public void updateJdProductPriceByRate(Integer competingCompanyRate, Integer democompanysCompanyRate, Integer standardCompanyRate) {
		jdPriceConfigFacade.updateJdProductPriceByRate(competingCompanyRate, democompanysCompanyRate, standardCompanyRate);
	}


}
	