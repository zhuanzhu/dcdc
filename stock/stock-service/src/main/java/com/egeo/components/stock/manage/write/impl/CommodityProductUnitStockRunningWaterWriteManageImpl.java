package com.egeo.components.stock.manage.write.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.stock.manage.write.CommodityProductUnitStockRunningWaterWriteManage;
import com.egeo.components.stock.dao.write.CommodityProductUnitStockRunningWaterWriteDAO;
import com.egeo.components.stock.po.CommodityProductUnitStockRunningWaterPO;
import com.egeo.exception.BusinessException;

@Service
public class CommodityProductUnitStockRunningWaterWriteManageImpl implements CommodityProductUnitStockRunningWaterWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CommodityProductUnitStockRunningWaterWriteDAO commodityProductUnitStockRunningWaterWriteDAO;

	@Override
	public Long insertCommodityProductUnitStockRunningWaterWithTx(CommodityProductUnitStockRunningWaterPO po) {
		
		int i ;
		try {
				i = commodityProductUnitStockRunningWaterWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateCommodityProductUnitStockRunningWaterWithTx(CommodityProductUnitStockRunningWaterPO po) {
		int i;
		i = commodityProductUnitStockRunningWaterWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteCommodityProductUnitStockRunningWaterWithTx(CommodityProductUnitStockRunningWaterPO po) {
		int i;
		i = commodityProductUnitStockRunningWaterWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}
	
	/**
	 * 批量生成流水
	 */
	@Override
	public int insertBatchCommodityProductUnitStockRunningWaterWithTx(List<CommodityProductUnitStockRunningWaterPO> pos) {
		
		int i ;
		try {
			i = commodityProductUnitStockRunningWaterWriteDAO.insertBatchCommodityProductUnitStockRunningWaterWithTx(pos);
			if (i < pos.size())
				throw new BusinessException("未能成功插入数据!");
		} catch (DuplicateKeyException e) {
			logger.error("", e);
			throw new BusinessException("未能成功生成流水!");
		}
		return  i;
	}

}
	