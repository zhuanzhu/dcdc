package com.egeo.components.product.service.read;


import java.util.List;
	
import com.egeo.components.product.dto.SuSerachKeywordDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface SuSerachKeywordReadService {

	public SuSerachKeywordDTO findSuSerachKeywordById(SuSerachKeywordDTO dto);

	public PageResult<SuSerachKeywordDTO> findSuSerachKeywordOfPage(SuSerachKeywordDTO dto,Pagination page);

	public List<SuSerachKeywordDTO> findSuSerachKeywordAll(SuSerachKeywordDTO dto);
	/**
	 * 根据suid查询su关键词
	 * @param id
	 * @param platformId
	 * @return
	 */
	public List<String> keywordByStandardUnitId(Long suId, Long platformId);
}
	