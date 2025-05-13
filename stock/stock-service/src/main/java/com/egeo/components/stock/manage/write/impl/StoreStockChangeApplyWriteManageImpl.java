package com.egeo.components.stock.manage.write.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.stock.dao.read.StorePuWarehouseStockReadDAO;
import com.egeo.components.stock.dao.read.StoreStockChangeApplyReadDAO;
import com.egeo.components.stock.dao.write.StoreStockChangeApplyPictureWriteDAO;
import com.egeo.components.stock.dao.write.StoreStockChangeApplyWriteDAO;
import com.egeo.components.stock.manage.write.StoreStockChangeApplyWriteManage;
import com.egeo.components.stock.po.StorePuStockRunningWaterPO;
import com.egeo.components.stock.po.StorePuWarehouseStockPO;
import com.egeo.components.stock.po.StoreStockChangeApplyPO;
import com.egeo.components.stock.po.StoreStockChangeApplyPicturePO;
import com.egeo.components.stock.store.pu.updater.PurchaseInputStockUpdaterFactory;
import com.egeo.components.stock.store.pu.updater.StockUpdater;
import com.egeo.exception.BusinessException;

@Service
public class StoreStockChangeApplyWriteManageImpl implements StoreStockChangeApplyWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private StoreStockChangeApplyWriteDAO storeStockChangeApplyWriteDAO;
	
	@Autowired
	private StoreStockChangeApplyPictureWriteDAO storeStockChangeApplyPictureWriteDAO;
	
	@Autowired
	private StorePuWarehouseStockReadDAO storePuWarehouseStockReadDAO;
	
	@Autowired
	private StoreStockChangeApplyReadDAO storeStockChangeApplyReadDAO;
	
	/**
	 * 采购入库库存更新命令工厂
	 */
	@Autowired
	private PurchaseInputStockUpdaterFactory<List<StorePuStockRunningWaterPO>> 
			purchaseInputStockUpdateCommandFactory;
	@Override
	public Long insertStoreStockChangeApplyWithTx(StoreStockChangeApplyPO po,List<String> pictures) {
		
		int i ;
		try {
				i = storeStockChangeApplyWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
				
				for (String picture : pictures) {
					StoreStockChangeApplyPicturePO stockChangeApplyPicturePO = new StoreStockChangeApplyPicturePO();
					stockChangeApplyPicturePO.setStoreStockChangeApplyId(po.getId());
					stockChangeApplyPicturePO.setPicturePath(picture);
					stockChangeApplyPicturePO.setPlatformId(po.getPlatformId());
					storeStockChangeApplyPictureWriteDAO.insert(stockChangeApplyPicturePO);
				}
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateStoreStockChangeApplyWithTx(StoreStockChangeApplyPO po) {
		
		// 如果同意则查询库存信息更新库存变化前变化后的值
		if(po.getIsConsent() == 1){
			StoreStockChangeApplyPO storeStockChangeApplyPO = storeStockChangeApplyReadDAO.findById(po);
			StorePuWarehouseStockPO storePuWarehouseStockPO = storePuWarehouseStockReadDAO.findByStorePuId(storeStockChangeApplyPO.getStoreProductUnitId());
			// 赋操作前数量
			po.setBeforeChangeValue(storePuWarehouseStockPO.getRealStockNum());
			
			List<StorePuStockRunningWaterPO> storePuStockRunningWaterPOs = new ArrayList<>();
			StorePuStockRunningWaterPO storePuStockRunningWaterPO = new StorePuStockRunningWaterPO();
			storePuStockRunningWaterPO.setStoreProductUnitId(storeStockChangeApplyPO.getStoreProductUnitId());
			storePuStockRunningWaterPO.setStockChange(storeStockChangeApplyPO.getStockChange());
			storePuStockRunningWaterPO.setCreateUserid(storeStockChangeApplyPO.getAfterUserId());
			storePuStockRunningWaterPO.setCreateUsername(storeStockChangeApplyPO.getAfterUserName());
			storePuStockRunningWaterPO.setStoreProductUnitId(storeStockChangeApplyPO.getStoreProductUnitId());
			storePuStockRunningWaterPO.setCommodityProductUnitName(storeStockChangeApplyPO.getCommodityProductUnitName());
			storePuStockRunningWaterPO.setProductUnitSerialNumber(storeStockChangeApplyPO.getProductUnitSerialNumber());
			storePuStockRunningWaterPO.setPlatformId(storeStockChangeApplyPO.getPlatformId());
			storePuStockRunningWaterPO.setType(storeStockChangeApplyPO.getApplyCauseId().intValue());
			storePuStockRunningWaterPOs.add(storePuStockRunningWaterPO);
			
			StockUpdater goodsStockUpdateCommand = 
					purchaseInputStockUpdateCommandFactory.create(storePuStockRunningWaterPOs);
			goodsStockUpdateCommand.updateGoodsStock();
			
			StorePuWarehouseStockPO storePuWarehouseStock = storePuWarehouseStockReadDAO.findByStorePuId(storeStockChangeApplyPO.getStoreProductUnitId());
			// 赋操作后数量
			po.setAfterChangeValue(storePuWarehouseStock.getRealStockNum());
			
		}
		
		int i;
		i = storeStockChangeApplyWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteStoreStockChangeApplyWithTx(StoreStockChangeApplyPO po) {
		int i;
		i = storeStockChangeApplyWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}	
}
	