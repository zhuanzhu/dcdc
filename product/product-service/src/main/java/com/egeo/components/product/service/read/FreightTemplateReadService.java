package com.egeo.components.product.service.read;


import java.util.List;
	
import com.egeo.components.product.dto.FreightTemplateDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface FreightTemplateReadService {

	public FreightTemplateDTO findFreightTemplateById(FreightTemplateDTO dto);

	public PageResult<FreightTemplateDTO> findFreightTemplateOfPage(FreightTemplateDTO dto,Pagination page);

	public List<FreightTemplateDTO> findFreightTemplateAll(FreightTemplateDTO dto);
}
	