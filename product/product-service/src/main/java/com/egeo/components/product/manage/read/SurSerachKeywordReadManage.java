package com.egeo.components.product.manage.read;

import java.util.List;
	
import com.egeo.components.product.po.SurSerachKeywordPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface SurSerachKeywordReadManage {

	public SurSerachKeywordPO findSurSerachKeywordById(SurSerachKeywordPO po);

	public PageResult<SurSerachKeywordPO> findSurSerachKeywordOfPage(SurSerachKeywordPO po,Pagination page);

	public List<SurSerachKeywordPO> findSurSerachKeywordAll(SurSerachKeywordPO po);
}
	