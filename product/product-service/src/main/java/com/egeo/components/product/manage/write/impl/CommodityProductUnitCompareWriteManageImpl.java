package com.egeo.components.product.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.write.CommodityProductUnitCompareWriteManage;
import com.egeo.components.product.dao.write.CommodityProductUnitCompareWriteDAO;
import com.egeo.components.product.po.CommodityProductUnitComparePO;
import com.egeo.exception.BusinessException;

@Service
public class CommodityProductUnitCompareWriteManageImpl implements CommodityProductUnitCompareWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CommodityProductUnitCompareWriteDAO commodityProductUnitCompareWriteDAO;

	@Override
	public Long insertCommodityProductUnitCompareWithTx(CommodityProductUnitComparePO po) {
		
		int i ;
		try {
				i = commodityProductUnitCompareWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateCommodityProductUnitCompareWithTx(CommodityProductUnitComparePO po) {
		int i;
		i = commodityProductUnitCompareWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteCommodityProductUnitCompareWithTx(CommodityProductUnitComparePO po) {
		int i;
		i = commodityProductUnitCompareWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}	
}
	