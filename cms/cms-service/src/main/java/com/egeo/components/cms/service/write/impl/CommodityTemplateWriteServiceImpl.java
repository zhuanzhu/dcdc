package com.egeo.components.cms.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.cms.service.write.CommodityTemplateWriteService;
import com.egeo.components.cms.manage.write.CommodityTemplateWriteManage;
import com.egeo.components.cms.converter.CommodityTemplateConverter;
import com.egeo.components.cms.dto.CommodityTemplateDTO;
import com.egeo.components.cms.po.CommodityTemplatePO;

@Service("commodityTemplateWriteService")
public class CommodityTemplateWriteServiceImpl  implements CommodityTemplateWriteService {
	@Autowired
	private CommodityTemplateWriteManage commodityTemplateWriteManage;

	@Override
	public Long insertCommodityTemplateWithTx(CommodityTemplateDTO dto) {
		CommodityTemplatePO po = CommodityTemplateConverter.toPO(dto);
		Long rt = commodityTemplateWriteManage.insertCommodityTemplateWithTx(po);		
		return rt;
	}

	@Override
	public int updateCommodityTemplateWithTx(CommodityTemplateDTO dto) {
		CommodityTemplatePO po = CommodityTemplateConverter.toPO(dto);
		int rt = commodityTemplateWriteManage.updateCommodityTemplateWithTx(po);		
		return rt;
	}

	@Override
	public int deleteCommodityTemplateWithTx(CommodityTemplateDTO dto) {
		CommodityTemplatePO po = CommodityTemplateConverter.toPO(dto);
		int rt = commodityTemplateWriteManage.deleteCommodityTemplateWithTx(po);		
		return rt;
	}
	/**
	 * 根据商品类型模版id启用模版
	 * @param commodityTemplateId
	 * @return
	 */
	@Override
	public boolean showCommodityTemplateWithTx(Long commodityTemplateId) {
		// TODO Auto-generated method stub
		return commodityTemplateWriteManage.showCommodityTemplateWithTx(commodityTemplateId);
	}
}
	