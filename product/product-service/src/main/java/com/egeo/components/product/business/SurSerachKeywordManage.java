package com.egeo.components.product.business;

import java.util.List;
	
import com.egeo.components.product.dto.SurSerachKeywordDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface SurSerachKeywordManage {

	public SurSerachKeywordDTO findSurSerachKeywordById(SurSerachKeywordDTO dto);	

	public PageResult<SurSerachKeywordDTO> findSurSerachKeywordOfPage(SurSerachKeywordDTO dto,Pagination page);

	public List<SurSerachKeywordDTO> findSurSerachKeywordAll(SurSerachKeywordDTO dto);

	Long insertSurSerachKeywordWithTx(SurSerachKeywordDTO dto);

	int updateSurSerachKeywordWithTx(SurSerachKeywordDTO dto);

	int deleteSurSerachKeywordWithTx(SurSerachKeywordDTO dto);
}
	