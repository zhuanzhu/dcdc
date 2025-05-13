package com.egeo.components.cms.manage.write.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.cms.manage.write.CommodityTemplateWriteManage;
import com.egeo.components.cms.dao.read.CommodityTemplateReadDAO;
import com.egeo.components.cms.dao.write.CommodityTemplateWriteDAO;
import com.egeo.components.cms.po.CommodityTemplatePO;
import com.egeo.exception.BusinessException;

@Service
public class CommodityTemplateWriteManageImpl implements CommodityTemplateWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CommodityTemplateWriteDAO commodityTemplateWriteDAO;
	
	@Autowired
	private CommodityTemplateReadDAO commodityTemplateReadDAO;

	@Override
	public Long insertCommodityTemplateWithTx(CommodityTemplatePO po) {
		
		int i ;
		try {
				i = commodityTemplateWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateCommodityTemplateWithTx(CommodityTemplatePO po) {
		int i;
		i = commodityTemplateWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteCommodityTemplateWithTx(CommodityTemplatePO po) {
		int i;
		i = commodityTemplateWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}
	/**
	 * 根据商品类型模版id启用模版
	 * @param commodityTemplateId
	 * @return
	 */
	@Override
	public boolean showCommodityTemplateWithTx(Long commodityTemplateId) {
		boolean ifTrue = false;
		CommodityTemplatePO commodityTemplatePO = new CommodityTemplatePO();
		commodityTemplatePO.setId(commodityTemplateId);
		CommodityTemplatePO commodityTemplatePO2 = commodityTemplateReadDAO.findById(commodityTemplatePO);
		
		//根据要修改的商品模版类型查询启用商品模版设为停用
		CommodityTemplatePO commodityTemplatePO3 = new CommodityTemplatePO();
		commodityTemplatePO3.setTemplateType(commodityTemplatePO2.getTemplateType());
		commodityTemplatePO3.setPlatformId(commodityTemplatePO2.getPlatformId());
		List<CommodityTemplatePO> list = commodityTemplateReadDAO.findAll(commodityTemplatePO3,null);
		for (CommodityTemplatePO commodityTemplatePO4 : list) {
			CommodityTemplatePO po = new CommodityTemplatePO();
			po.setId(commodityTemplatePO4.getId());
			po.setShowTemplate(0);
			commodityTemplateWriteDAO.update(po);
		}
		
		//根据商品类型模版id启用模版
		commodityTemplatePO.setShowTemplate(1);
		commodityTemplateWriteDAO.update(commodityTemplatePO);
		ifTrue = true;
		return ifTrue;
	}	
}
	