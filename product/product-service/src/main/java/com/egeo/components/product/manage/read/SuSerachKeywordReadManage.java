package com.egeo.components.product.manage.read;

import java.util.List;
	
import com.egeo.components.product.po.SuSerachKeywordPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface SuSerachKeywordReadManage {

	public SuSerachKeywordPO findSuSerachKeywordById(SuSerachKeywordPO po);

	public PageResult<SuSerachKeywordPO> findSuSerachKeywordOfPage(SuSerachKeywordPO po,Pagination page);

	public List<SuSerachKeywordPO> findSuSerachKeywordAll(SuSerachKeywordPO po);
	/**
	 * 根据suid查询关键词信息
	 * @param suId
	 * @param platformId
	 * @return
	 */
	public List<String> keywordByStandardUnitId(Long suId, Long platformId);
}
	