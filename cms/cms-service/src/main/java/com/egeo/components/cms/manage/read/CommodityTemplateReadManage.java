package com.egeo.components.cms.manage.read;

import java.util.List;
	
import com.egeo.components.cms.po.CommodityTemplatePO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface CommodityTemplateReadManage {

	public CommodityTemplatePO findCommodityTemplateById(CommodityTemplatePO po);

	public PageResult<CommodityTemplatePO> findCommodityTemplateOfPage(CommodityTemplatePO po,Pagination page);

	public List<CommodityTemplatePO> findCommodityTemplateAll(CommodityTemplatePO po);
	/**
	 * 根据类型查询默认模版信息
	 * @param type
	 * @return
	 */
	public CommodityTemplatePO findDefaultByType(Integer type,Long platformId);
}
	