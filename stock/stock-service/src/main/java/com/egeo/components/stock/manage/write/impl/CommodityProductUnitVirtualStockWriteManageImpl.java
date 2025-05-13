package com.egeo.components.stock.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.stock.manage.write.CommodityProductUnitVirtualStockWriteManage;
import com.egeo.components.stock.dao.write.CommodityProductUnitVirtualStockWriteDAO;
import com.egeo.components.stock.po.CommodityProductUnitVirtualStockPO;
import com.egeo.exception.BusinessException;

@Service
public class CommodityProductUnitVirtualStockWriteManageImpl implements CommodityProductUnitVirtualStockWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CommodityProductUnitVirtualStockWriteDAO commodityProductUnitVirtualStockWriteDAO;

	@Override
	public Long insertCommodityProductUnitVirtualStockWithTx(CommodityProductUnitVirtualStockPO po) {
		
		int i ;
		try {
				i = commodityProductUnitVirtualStockWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateCommodityProductUnitVirtualStockWithTx(CommodityProductUnitVirtualStockPO po) {
		int i;
		i = commodityProductUnitVirtualStockWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteCommodityProductUnitVirtualStockWithTx(CommodityProductUnitVirtualStockPO po) {
		int i;
		i = commodityProductUnitVirtualStockWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}	
}
	