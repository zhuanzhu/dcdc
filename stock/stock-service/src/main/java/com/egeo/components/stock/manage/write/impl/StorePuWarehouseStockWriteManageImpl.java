package com.egeo.components.stock.manage.write.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.stock.dao.read.StorePuWarehouseStockReadDAO;
import com.egeo.components.stock.dao.write.StorePuWarehouseStockWriteDAO;
import com.egeo.components.stock.manage.write.StorePuWarehouseStockWriteManage;
import com.egeo.components.stock.po.StorePuStockRunningWaterPO;
import com.egeo.components.stock.po.StorePuWarehouseStockPO;
import com.egeo.components.stock.store.pu.updater.CancelOrderStockUpdaterFactory;
import com.egeo.components.stock.store.pu.updater.PayOrderStockUpdaterFactory;
import com.egeo.components.stock.store.pu.updater.PurchaseInputStockUpdaterFactory;
import com.egeo.components.stock.store.pu.updater.ReceiveOrderStockUpdaterFactory;
import com.egeo.components.stock.store.pu.updater.StockUpdater;
import com.egeo.components.stock.store.pu.updater.SubmitOrderStockUpdaterFactory;
import com.egeo.exception.BusinessException;
import com.egeo.utils.EmptyUtil;

@Service
public class StorePuWarehouseStockWriteManageImpl implements StorePuWarehouseStockWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private StorePuWarehouseStockWriteDAO storePuWarehouseStockWriteDAO;
	
	@Autowired
	private StorePuWarehouseStockReadDAO storePuWarehouseStockReadDAO;
	
	/**
	 * 采购入库库存更新命令工厂
	 */
	@Autowired
	private PurchaseInputStockUpdaterFactory<List<StorePuStockRunningWaterPO>> 
			purchaseInputStockUpdateCommandFactory;
	/**
	 * 提交订单库存更新组件工厂
	 */
	@Autowired
	private SubmitOrderStockUpdaterFactory<List<StorePuStockRunningWaterPO>> 
			submitOrderStockUpdaterFactory;
	/**
	 * 支付订单库存更新组件工厂
	 */
	@Autowired
	private PayOrderStockUpdaterFactory<List<StorePuStockRunningWaterPO>> 
			payOrderStockUpdaterFactory;
	/**
	 * 取消订单库存更新组件工厂
	 */
	@Autowired
	private CancelOrderStockUpdaterFactory<List<StorePuStockRunningWaterPO>> 
			cancelOrderStockUpdaterFactory;
	/**
	 * 签收订单库存更新组件工厂
	 */
	@Autowired
	private ReceiveOrderStockUpdaterFactory<List<StorePuStockRunningWaterPO>> 
			receiveOrderStockUpdaterFactory;

	@Override
	public Long insertStorePuWarehouseStockWithTx(StorePuWarehouseStockPO po) {
		
		int i ;
		try {
				i = storePuWarehouseStockWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateStorePuWarehouseStockWithTx(StorePuWarehouseStockPO po) {
		int i;
		i = storePuWarehouseStockWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteStorePuWarehouseStockWithTx(StorePuWarehouseStockPO po) {
		int i;
		i = storePuWarehouseStockWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}

	/*@Override
	public int insertAllWithTx(List<StorePuWarehouseStockPO> list) {
		if(EmptyUtil.isNotEmpty(list)){
			for (Iterator<StorePuWarehouseStockPO> iterator = list.iterator(); iterator.hasNext();) {
				StorePuWarehouseStockPO storePuWarehouseStockPO = iterator.next();
				// 根据门店puId查询库存信息
				StorePuWarehouseStockPO storePuWarehouseStockPO2 = storePuWarehouseStockReadDAO.findByStorePuId(storePuWarehouseStockPO.getStoreProductUnitId());
				if(EmptyUtil.isNotEmpty(storePuWarehouseStockPO2))
					iterator.remove();
				
			}
			if(EmptyUtil.isNotEmpty(list)){
				return storePuWarehouseStockWriteDAO.insertAllWithTx(list);
			}
		}
		return 0;
	}*/
	
	@Override
	public int insertAllWithTx(List<StorePuWarehouseStockPO> list) {
		List<StorePuWarehouseStockPO> insertList = new ArrayList<>();
		Set<Long> storeProductUnitIdSet = new HashSet<>();
		if(EmptyUtil.isNotEmpty(list)){
			for (StorePuWarehouseStockPO sp : list) {
				//去除list本身重复的元素
				if (storeProductUnitIdSet.contains(sp.getStoreProductUnitId())) {
					continue;
				}
				//去除list与数据库重复的元素
				StorePuWarehouseStockPO storePuWarehouseStockPO2 = storePuWarehouseStockReadDAO.findByStorePuId(sp.getStoreProductUnitId());
				if (EmptyUtil.isEmpty(storePuWarehouseStockPO2)) {
					storeProductUnitIdSet.add(sp.getStoreProductUnitId());
					insertList.add(sp);
				}
			}
			if(EmptyUtil.isNotEmpty(insertList)){
				return storePuWarehouseStockWriteDAO.insertAllWithTx(insertList);
			}
		}
		return 0;
	}

	@Override
	public int updateStorePuWarehouseStock(List<StorePuStockRunningWaterPO> storePuStockRunningWaterPOs, int type) {
		StockUpdater storePuStockUpdateCommand = null;
		// 库存流水类型:1提交订单 2支付(门店签收共用) 3 取消订单（未支付） 4 取消订单（已支付） 5 进货 6 出货 
		switch (type) {
		case 1:
			storePuStockUpdateCommand = 
					submitOrderStockUpdaterFactory.create(storePuStockRunningWaterPOs); 
			break;
		case 2:
//			storePuStockUpdateCommand = 
//					payOrderStockUpdaterFactory.create(storePuStockRunningWaterPOs); 
			break;
		case 3:
		case 4:
			storePuStockUpdateCommand = 
					cancelOrderStockUpdaterFactory.create(storePuStockRunningWaterPOs); 
			break;
		case 5:
			storePuStockUpdateCommand = 
					receiveOrderStockUpdaterFactory.create(storePuStockRunningWaterPOs); 
			break;
		case 101:
			storePuStockUpdateCommand = 
					purchaseInputStockUpdateCommandFactory.create(storePuStockRunningWaterPOs); 
			break;
		default:
			break;
		}
		// 更新本地库存
		if (storePuStockUpdateCommand != null) {
			storePuStockUpdateCommand.updateGoodsStock();
		}
		return 0;
	}	
}
	