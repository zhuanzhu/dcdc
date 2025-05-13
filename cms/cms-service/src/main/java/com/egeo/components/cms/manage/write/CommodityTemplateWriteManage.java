package com.egeo.components.cms.manage.write;

import com.egeo.components.cms.po.CommodityTemplatePO;


public interface CommodityTemplateWriteManage {

	Long insertCommodityTemplateWithTx(CommodityTemplatePO po);

	int updateCommodityTemplateWithTx(CommodityTemplatePO po);

	int deleteCommodityTemplateWithTx(CommodityTemplatePO po);
	/**
	 * 根据商品类型模版id启用模版
	 * @param commodityTemplateId
	 * @return
	 */
	boolean showCommodityTemplateWithTx(Long commodityTemplateId);
}
	