package com.egeo.components.cms.service.read;


import java.util.List;
	
import com.egeo.components.cms.dto.CmsInstCfgDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface CmsInstCfgReadService {

	public CmsInstCfgDTO findCmsInstCfgById(CmsInstCfgDTO dto);

	public PageResult<CmsInstCfgDTO> findCmsInstCfgOfPage(CmsInstCfgDTO dto,Pagination page);

	public List<CmsInstCfgDTO> findCmsInstCfgAll(CmsInstCfgDTO dto);
	
	/**
	 * 根据页面ID查询配置信息
	 * @param pageId
	 * @return
	 */
	public List<CmsInstCfgDTO> findPageInstCfgByPageId(Long pageId, List<Long> instIdList);
}
	