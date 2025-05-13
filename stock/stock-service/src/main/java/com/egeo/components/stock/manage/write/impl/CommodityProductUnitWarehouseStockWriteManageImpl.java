package com.egeo.components.stock.manage.write.impl;

import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.common.PlatformKeyConstant;
import com.egeo.components.stock.constant.StockConstant;
import com.egeo.components.stock.dao.read.CommodityProductUnitWarehouseStockReadDAO;
import com.egeo.components.stock.dao.write.CommodityProductUnitStockRunningWaterWriteDAO;
import com.egeo.components.stock.dao.write.CommodityProductUnitWarehouseStockWriteDAO;
import com.egeo.components.stock.manage.write.CommodityProductUnitWarehouseStockWriteManage;
import com.egeo.components.stock.po.CommodityProductUnitStockRunningWaterPO;
import com.egeo.components.stock.po.CommodityProductUnitWarehouseStockPO;
import com.egeo.exception.BusinessException;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.SendMail;

@Service
public class CommodityProductUnitWarehouseStockWriteManageImpl implements CommodityProductUnitWarehouseStockWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CommodityProductUnitWarehouseStockWriteDAO commodityProductUnitWarehouseStockWriteDAO;
	
	@Autowired
	private CommodityProductUnitWarehouseStockReadDAO commodityProductUnitWarehouseStockReadDAO;
	
	@Autowired
	private CommodityProductUnitStockRunningWaterWriteDAO commodityProductUnitStockRunningWaterWriteDAO;

	@Override
	public Long insertCommodityProductUnitWarehouseStockWithTx(CommodityProductUnitWarehouseStockPO po) {
		
		int i ;
		try {
				i = commodityProductUnitWarehouseStockWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateCommodityProductUnitWarehouseStockWithTx(CommodityProductUnitWarehouseStockPO po) {
		int i;
		i = commodityProductUnitWarehouseStockWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteCommodityProductUnitWarehouseStockWithTx(CommodityProductUnitWarehouseStockPO po) {
		int i;
		i = commodityProductUnitWarehouseStockWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}
	/**
	 * 进货
	 * @param vo
	 * @param req
	 * @return
	 */
	@Override
	public int merchandiseStock(CommodityProductUnitWarehouseStockPO po) {
		return commodityProductUnitWarehouseStockWriteDAO.merchandiseStock(po);
	}
	
	
	/**
	 * 出货
	 * @param vo
	 * @param req
	 * @return
	 */
	@Override
	public int deliverStockWithTx(CommodityProductUnitWarehouseStockPO po) {
		int i = commodityProductUnitWarehouseStockWriteDAO.deliverStockWithTx(po);
		if(i == 0){
			//等于0说明真实库存不够、则有多少出货多少(在线真实库存=在线冻结库存)
			return commodityProductUnitWarehouseStockWriteDAO.deliverResidueStockWithTx(po);
		}
		return i;
	}

	@Override
	public int freezeStockWithTx(Long puId, Integer puCount,String orderCode,Integer type,String productUnitSerialNumber,String puName) {
		
		
		int i = commodityProductUnitWarehouseStockWriteDAO.freezeStockWithTx(puId,puCount);
		return i;
	}
	/**
	 * 根据puid扣除冻结库存扣除库存
	 * @param puId
	 * @param puCount
	 * @return
	 */
	@Override
	public int unfreezeAndDeductStockWithTx(Long puId, Integer puCount,Integer type,String productUnitSerialNumber,String puName) {
		int i = commodityProductUnitWarehouseStockWriteDAO.unfreezeAndDeductStockWithTx(puId, puCount);
		if(i == 0){
			logger.info("puid："+ puId + ",库存数量：" + puCount + "扣除冻结库存扣除库存失败");
			throw new BusinessException("puid："+ puId + ",库存数量：" + puCount + "扣除冻结库存扣除库存失败");
		}
		return i;
	}
	/**
	 * 根据puId扣除冻结库存失败
	 */
	@Override
	public int unFreeseOrderStockWithTx(Long puId, Integer puCount,String orderCode,Integer type,String productUnitSerialNumber,String puName) {
		
		int i = commodityProductUnitWarehouseStockWriteDAO.unFreeseOrderStock(puId,puCount);
		if(i == 0){
			logger.info("puid："+ puId + ",库存数量：" + puCount + "扣除冻结库存扣除库存失败");
			throw new BusinessException("puid："+ puId + ",库存数量：" + puCount + "扣除冻结库存失败");
		}
		return i;
	}
	/**
	 * 根据puId扣除冻结库存失败
	 */
	@Override
	public int recoverOrderStockWithTx(Long puId, Integer puCount,String orderCode,Integer type,String productUnitSerialNumber,String puName) {
		int i = commodityProductUnitWarehouseStockWriteDAO.recoverOrderStock(puId,puCount);
		if(i == 0){
			logger.info("puid："+ puId + ",库存数量：" + puCount + "扣除冻结库存扣除库存失败");
			throw new BusinessException("puid："+ puId + ",库存数量：" + puCount + "扣除冻结库存失败");
		}
		return i;
	}
	/**
	 * 批量根据pu生成库存信息
	 * @param puIdList
	 * @param merchantProductId
	 * @param platformId
	 */
	@Override
	public int insertCommodityProductUnitWarehouseStockAllWithTx(List<Long> puIdList, Long merchantProductId,
			Long platformId,Long merchantId) {
		if(EmptyUtil.isNotEmpty(puIdList)){
			for (Iterator<Long> iterator = puIdList.iterator(); iterator.hasNext();) {
				Long puId = iterator.next();
				// 根据puId查询库存信息
				CommodityProductUnitWarehouseStockPO commodityProductUnitWarehouseStockPO = 
						commodityProductUnitWarehouseStockReadDAO.findCommodityProductUnitWarehouseStockByPuId(puId);
				if(EmptyUtil.isNotEmpty(commodityProductUnitWarehouseStockPO))
					iterator.remove();
				
			}
			return commodityProductUnitWarehouseStockWriteDAO.insertCommodityProductUnitWarehouseStockAllWithTx(puIdList, merchantProductId,platformId,merchantId);
		}
		return 0;
	}	
	
	@Override
	public void syncStockWithTx(Long puId, Long quantity, String puSerialNumber, String puName) {
		CommodityProductUnitWarehouseStockPO puStock = commodityProductUnitWarehouseStockReadDAO.findCommodityProductUnitWarehouseStockByPuId(puId);
		if (EmptyUtil.isEmpty(puStock)) {
			throw new BusinessException("商品库存记录未找到");
		}
		Long oldQuantity = puStock.getRealStockNum();
		CommodityProductUnitStockRunningWaterPO stockWater = new CommodityProductUnitStockRunningWaterPO();
		stockWater.setCommodityProductUnitId(puStock.getCommodityProductUnitId());
		stockWater.setProductUnitSerialNumber(puSerialNumber);
		stockWater.setCommodityProductUnitName(puName);
		stockWater.setPreoperativeStockNum(puStock.getRealStockNum());
		stockWater.setOperationBackStockNum(quantity);
		stockWater.setStockChange(quantity - oldQuantity);
		stockWater.setPlatformId(puStock.getPlatformId());
		stockWater.setPreoperativeRealStockNum(puStock.getRealFrozenStockNum());
		stockWater.setOperationBackRealStockNum(puStock.getRealFrozenStockNum());
		stockWater.setType(StockConstant.STOCK_STATUS_SYNC_STOCK.getStatus());
		stockWater.setCreateUsername("库存同步");
		
		CommodityProductUnitWarehouseStockPO updatePuStock = new CommodityProductUnitWarehouseStockPO();
		updatePuStock.setId(puStock.getId());
		updatePuStock.setRealStockNum(quantity);
		commodityProductUnitWarehouseStockWriteDAO.update(updatePuStock);
		commodityProductUnitStockRunningWaterWriteDAO.insert(stockWater);
		
		if (quantity < puStock.getRealFrozenStockNum()) {
			Long cnt = puStock.getRealFrozenStockNum() - quantity;
			sendWarnMail(cnt, puName, puStock.getPlatformId());
		}
	}
	
	/**
	 * 发送预警邮件
	 * @param cnt
	 * @param puName
	 * @param platformId
	 */
	private void sendWarnMail(Long cnt,String puName, Long platformId) {
		String platformName = "";
		if (PlatformKeyConstant.MYY_PLATFORM_ID.equals(platformId)) {
			platformName = PlatformKeyConstant.MYY_PLATFORM_NAME;
		}
		if (PlatformKeyConstant.FGJ_PLATFORM_ID.equals(platformId)) {
			platformName = PlatformKeyConstant.FGJ_PLATFORM_NAME;
		}
		String subject = "网店管家的超卖提醒";
		String content = platformName + "在同步网店管家的库存数据时，判断有" + cnt + "个 " + puName + "将可能发生超卖，请及时检查商品库存，并与商品运营方进行对接方式的协调处理。";
		SendMail.sendMailAsync(subject, content);
	}

	@Override
	public int merchandiseStockBatch(List<Long> puIds,Long realStockNum) {
		int i = commodityProductUnitWarehouseStockWriteDAO.merchandiseStockBatch(puIds,realStockNum);
		if(i < puIds.size()) {
			logger.info("puid："+ puIds + ",库存数量：" + realStockNum + "进货失败，进货成功条数：" + i);
			throw new BusinessException("puid："+ puIds + ",库存数量：" + realStockNum + "库存冻结失败");
		}
		return 1;
	}
	
	/**
	 * 批量出货
	 * @param vo
	 * @param req
	 * @return
	 */
	@Override
	public int deliverStockBatchWithTx(List<Long> puids,Long realStockNum) {
		int i = commodityProductUnitWarehouseStockWriteDAO.deliverStockBatchWithTx(puids,realStockNum);
		logger.info("出货成功数：" + i +  "  出货预期数：" + puids.size());
		if(i < puids.size()){
			//等于0说明真实库存不够、则有多少出货多少(在线真实库存=在线冻结库存)
			return commodityProductUnitWarehouseStockWriteDAO.deliverResidueStockBatchWithTx(puids,realStockNum);
		}
		return 1;
	}

	/**
	 * 批量冻结库存
	 */
	@Override
	public int freezeStockBatchWithTx(List<Long> puIds, Integer puCount) {

		int i = commodityProductUnitWarehouseStockWriteDAO.freezeStockBatchWithTx(puIds, puCount);
		logger.info("puid："+ puIds + ",库存数量：" + puCount + "批量冻结库存数量：" + puIds.size() + " 实际成功数量：" + i);
		if(i < puIds.size()) {
			logger.info("puid："+ puIds + ",库存数量：" + puCount + "库存冻结失败");
			throw new BusinessException("puid："+ puIds + ",库存数量：" + puCount + "库存冻结失败");
		}
		return 1;
	}
	
	/**
	 * 根据puid扣除冻结库存扣除库存
	 * 场景支付成功
	 * @param puId
	 * @param puCount
	 * @return
	 */
	@Override
	public int unfreezeAndDeductStockBatchWithTx(List<Long> puIds, Integer puCount) {
		int i = commodityProductUnitWarehouseStockWriteDAO.unfreezeAndDeductStockBatchWithTx(puIds, puCount);
		logger.info("（支付成功）puid："+ puIds + ",库存数量：" + puCount + "根据puid扣除冻结库存扣除库存数量：" + puIds.size() + " 实际成功数量：" + i);
		if(i < puIds.size()){
			logger.info("puid："+ puIds + ",库存数量：" + puCount + "扣除冻结库存扣除库存失败");
			throw new BusinessException("puid："+ puIds + ",库存数量：" + puCount + "扣除冻结库存扣除库存失败");
		}
		return 1;
	}
	/**
	 * 根据puId扣除冻结库存
	 */
	@Override
	public int unFreeseOrderStockBatchWithTx(List<Long> puIds, Integer puCount) {
		
		int i = commodityProductUnitWarehouseStockWriteDAO.unFreeseOrderStockBatch(puIds,puCount);
		logger.info("puid："+ puIds + ",库存数量：" + puCount + "扣除冻结库存数量：" + puIds.size() + " 实际成功数量：" + i);
		if(i < puIds.size()){
			logger.info("puid："+ puIds + ",库存数量：" + puCount + "扣除冻结库存扣除库存失败");
			throw new BusinessException("puid："+ puIds + ",库存数量：" + puCount + "扣除冻结库存失败");
		}
		return 1;
	}
	/**
	 * 根据puId恢复真实库存，冻结库存不变
	 */
	@Override
	public int recoverOrderStockBatchWithTx(List<Long> puIds, Integer puCount) {
		
		int i = commodityProductUnitWarehouseStockWriteDAO.recoverOrderStockBatch(puIds,puCount);
		
		logger.info("puid："+ puIds + ",库存数量：" + puCount + "恢复真实库存，冻结库存不变数量：" + puIds.size() + " 实际成功数量：" + i);
		if(i < puIds.size()){
			logger.info("puid："+ puIds + ",库存数量：" + puCount + "扣除冻结库存扣除库存失败");
			throw new BusinessException("puid："+ puIds + ",库存数量：" + puCount + "扣除冻结库存失败");
		}
		return 1;
	}

	@Override
	public void updateCommodityProductUnitWarehouseStockWithTx(List<Long> puIds, Long nowRealStockNum,
			Long nowRealFrozenStockNum) {
		commodityProductUnitWarehouseStockWriteDAO.setCommodityProductUnitWarehouseStockWithTx(puIds,nowRealStockNum,nowRealFrozenStockNum);
		
	}

	@Override
	public void saveCommodityProductUnitWarehouseStock(List<CommodityProductUnitWarehouseStockPO> commodityProductUnitWarehouseStockPOList) {
		commodityProductUnitWarehouseStockWriteDAO.saveCommodityProductUnitWarehouseStock(commodityProductUnitWarehouseStockPOList);
	}

	@Override
	public void updateJdPUStock() {
		commodityProductUnitWarehouseStockWriteDAO.updateJdPUStock();
	}

}
	