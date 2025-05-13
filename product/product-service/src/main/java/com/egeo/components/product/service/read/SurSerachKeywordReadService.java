package com.egeo.components.product.service.read;


import java.util.List;
	
import com.egeo.components.product.dto.SurSerachKeywordDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface SurSerachKeywordReadService {

	public SurSerachKeywordDTO findSurSerachKeywordById(SurSerachKeywordDTO dto);

	public PageResult<SurSerachKeywordDTO> findSurSerachKeywordOfPage(SurSerachKeywordDTO dto,Pagination page);

	public List<SurSerachKeywordDTO> findSurSerachKeywordAll(SurSerachKeywordDTO dto);
}
	