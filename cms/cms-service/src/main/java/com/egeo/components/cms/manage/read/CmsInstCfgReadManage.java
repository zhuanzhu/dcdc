package com.egeo.components.cms.manage.read;

import java.util.List;

import com.egeo.components.cms.condition.CmsInstCfgCondition;
import com.egeo.components.cms.po.CmsInstCfgPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface CmsInstCfgReadManage {

	public CmsInstCfgPO findCmsInstCfgById(CmsInstCfgPO po);

	public PageResult<CmsInstCfgPO> findCmsInstCfgOfPage(CmsInstCfgPO po,Pagination page);

	public List<CmsInstCfgPO> findCmsInstCfgAll(CmsInstCfgPO po);
	
	/**
	 * 根据页面ID查询配置信息
	 * @param pageId
	 * @return
	 */
	List<CmsInstCfgCondition> findPageInstCfgByPageId(Long pageId, List<Long> instIdList);
}
	