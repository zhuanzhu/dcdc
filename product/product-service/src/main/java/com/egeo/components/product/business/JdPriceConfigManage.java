package com.egeo.components.product.business;

import com.egeo.components.product.dto.JdPriceConfigDTO;
import com.egeo.components.product.dto.JdPriceDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface JdPriceConfigManage {

	public JdPriceConfigDTO findJdPriceConfigById(JdPriceConfigDTO dto);	

	public PageResult<JdPriceConfigDTO> findJdPriceConfigOfPage(JdPriceConfigDTO dto,Pagination page);

	public JdPriceConfigDTO findJdPriceConfigAll(JdPriceConfigDTO dto);

	Long insertJdPriceConfigWithTx(JdPriceConfigDTO dto);

	int updateJdPriceConfigWithTx(JdPriceConfigDTO dto);

	int deleteJdPriceConfigWithTx(JdPriceConfigDTO dto);

    void updateJdProductPriceByRate(Integer competingCompanyRate, Integer democompanysCompanyRate, Integer standardCompanyRate);
}
	