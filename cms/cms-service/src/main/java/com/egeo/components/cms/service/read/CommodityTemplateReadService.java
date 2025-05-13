package com.egeo.components.cms.service.read;


import java.util.List;
	
import com.egeo.components.cms.dto.CommodityTemplateDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface CommodityTemplateReadService {

	public CommodityTemplateDTO findCommodityTemplateById(CommodityTemplateDTO dto);

	public PageResult<CommodityTemplateDTO> findCommodityTemplateOfPage(CommodityTemplateDTO dto,Pagination page);

	public List<CommodityTemplateDTO> findCommodityTemplateAll(CommodityTemplateDTO dto);
	/**
	 * 根据类型查询默认模版信息
	 * @param type
	 * @return
	 */
	public CommodityTemplateDTO findDefaultByType(Integer type,Long platformId);
}
	