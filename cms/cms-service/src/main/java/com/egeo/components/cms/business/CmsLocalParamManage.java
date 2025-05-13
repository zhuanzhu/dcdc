package com.egeo.components.cms.business;

import java.util.List;
	
import com.egeo.components.cms.dto.CmsLocalParamDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface CmsLocalParamManage {

	public CmsLocalParamDTO findCmsLocalParamById(CmsLocalParamDTO dto);	

	public PageResult<CmsLocalParamDTO> findCmsLocalParamOfPage(CmsLocalParamDTO dto,Pagination page);

	public List<CmsLocalParamDTO> findCmsLocalParamAll(CmsLocalParamDTO dto);

	Long insertCmsLocalParamWithTx(CmsLocalParamDTO dto);

	int updateCmsLocalParamWithTx(CmsLocalParamDTO dto);

	int deleteCmsLocalParamWithTx(CmsLocalParamDTO dto);
}
	