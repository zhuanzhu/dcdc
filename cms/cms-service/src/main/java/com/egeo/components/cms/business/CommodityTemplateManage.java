package com.egeo.components.cms.business;

import java.util.List;
import java.util.Map;

import com.egeo.components.cms.dto.CommodityTemplateDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface CommodityTemplateManage {
	/**
	 * 根据商品模版id查询商品模版信息
	 * @param commodityTemplateId
	 * @return
	 */
	public Map<String, Object> findCommodityTemplateById(Long commodityTemplateId);	

	public PageResult<CommodityTemplateDTO> findCommodityTemplateOfPage(CommodityTemplateDTO dto,Pagination page);

	public List<CommodityTemplateDTO> findCommodityTemplateAll(CommodityTemplateDTO dto);

	Long insertCommodityTemplateWithTx(CommodityTemplateDTO dto);

	int updateCommodityTemplateWithTx(CommodityTemplateDTO dto);

	int deleteCommodityTemplateWithTx(CommodityTemplateDTO dto);
	/**
	 * 分页查询所有商品类型模版信息
	 * @param vo
	 * @param page
	 * @param req
	 * @return
	 */
	public PageResult<Map<String, Object>> queryCommodityTemplateOfPage(CommodityTemplateDTO dto, Pagination page);
	/**
	 * 根据商品类型模版id启用模版
	 * @param commodityTemplateId
	 * @return
	 */
	public boolean showCommodityTemplateWithTx(Long commodityTemplateId);
	/**
	 * 根据商品类型查询商品模版
	 * @param commodityTemplateId
	 * @return
	 */
	public Map<String, Object> findCommodityTemplateByType(Integer type,Long platformId);
}
	