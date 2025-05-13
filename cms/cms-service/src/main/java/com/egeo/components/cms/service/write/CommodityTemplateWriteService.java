package com.egeo.components.cms.service.write;

import com.egeo.components.cms.dto.CommodityTemplateDTO;


public interface CommodityTemplateWriteService {

	public Long insertCommodityTemplateWithTx(CommodityTemplateDTO dto);

	public int updateCommodityTemplateWithTx(CommodityTemplateDTO dto);

	public int deleteCommodityTemplateWithTx(CommodityTemplateDTO dto);
	/**
	 * 根据商品类型模版id启用模版
	 * @param commodityTemplateId
	 * @return
	 */
	public boolean showCommodityTemplateWithTx(Long commodityTemplateId);
}
	