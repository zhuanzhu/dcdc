package com.egeo.components.product.service.read;


import java.util.List;
	
import com.egeo.components.product.dto.JdPriceConfigDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface JdPriceConfigReadService {

	public JdPriceConfigDTO findJdPriceConfigById(JdPriceConfigDTO dto);

	public PageResult<JdPriceConfigDTO> findJdPriceConfigOfPage(JdPriceConfigDTO dto,Pagination page);

	public List<JdPriceConfigDTO> findJdPriceConfigAll(JdPriceConfigDTO dto);
}
	