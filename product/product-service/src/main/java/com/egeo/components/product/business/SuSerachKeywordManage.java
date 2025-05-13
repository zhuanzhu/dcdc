package com.egeo.components.product.business;

import java.util.List;
	
import com.egeo.components.product.dto.SuSerachKeywordDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface SuSerachKeywordManage {

	public SuSerachKeywordDTO findSuSerachKeywordById(SuSerachKeywordDTO dto);	

	public PageResult<SuSerachKeywordDTO> findSuSerachKeywordOfPage(SuSerachKeywordDTO dto,Pagination page);

	public List<SuSerachKeywordDTO> findSuSerachKeywordAll(SuSerachKeywordDTO dto);

	Long insertSuSerachKeywordWithTx(SuSerachKeywordDTO dto);

	int updateSuSerachKeywordWithTx(SuSerachKeywordDTO dto);

	int deleteSuSerachKeywordWithTx(SuSerachKeywordDTO dto);
}
	