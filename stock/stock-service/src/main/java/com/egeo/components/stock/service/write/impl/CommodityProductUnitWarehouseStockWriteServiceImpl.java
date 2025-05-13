package com.egeo.components.stock.service.write.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.dto.CommodityProductUnitDTO;
import com.egeo.components.stock.ServerApp;
import com.egeo.components.stock.constant.StockConstant;
import com.egeo.components.stock.converter.CommodityProductUnitWarehouseStockConverter;
import com.egeo.components.stock.converter.ContactGroupPuStockConverter;
import com.egeo.components.stock.dto.CommodityProductUnitWarehouseStockDTO;
import com.egeo.components.stock.dto.ContactGroupPuStockDTO;
import com.egeo.components.stock.manage.read.CommodityProductUnitWarehouseStockReadManage;
import com.egeo.components.stock.manage.read.ContactGroupPuStockReadManage;
import com.egeo.components.stock.manage.read.ContactGroupSkuStockReadManage;
import com.egeo.components.stock.manage.read.ContactGroupStockReadManage;
import com.egeo.components.stock.manage.write.CommodityProductUnitStockRunningWaterWriteManage;
import com.egeo.components.stock.manage.write.CommodityProductUnitWarehouseStockWriteManage;
import com.egeo.components.stock.manage.write.ContactGroupPuStockWriteManage;
import com.egeo.components.stock.po.CommodityProductUnitStockRunningWaterPO;
import com.egeo.components.stock.po.CommodityProductUnitWarehouseStockPO;
import com.egeo.components.stock.po.ContactGroupPuStockPO;
import com.egeo.components.stock.po.ContactGroupSkuStockPO;
import com.egeo.components.stock.po.ContactGroupStockPO;
import com.egeo.components.stock.service.write.CommodityProductUnitWarehouseStockWriteService;
import com.egeo.components.stock.service.write.impl.Thread.SaveListFactory;
import com.egeo.config.RuntimeContext;
import com.egeo.utils.EmptyUtil;
import com.egeo.utils.cache.JedisUtil;
import com.egeo.utils.log.XLogger;

@Service("commodityProductUnitWarehouseStockWriteService")
public class CommodityProductUnitWarehouseStockWriteServiceImpl	implements CommodityProductUnitWarehouseStockWriteService {
	/**
	 * 
	 */
	private static final XLogger logger = XLogger.getLogger(CommodityProductUnitWarehouseStockWriteServiceImpl.class);
	private static final long serialVersionUID = -6162122586888076494L;

	@Autowired
	private CommodityProductUnitWarehouseStockWriteManage commodityProductUnitWarehouseStockWriteManage;

	@Autowired
	private CommodityProductUnitWarehouseStockReadManage commodityProductUnitWarehouseStockReadManage;

	@Autowired
	private CommodityProductUnitStockRunningWaterWriteManage commodityProductUnitStockRunningWaterWriteManage;
	
	@Autowired
	private ContactGroupStockReadManage contactGroupStockReadManage;
	
	@Autowired
	private ContactGroupSkuStockReadManage contactGroupSkuStockReadManage;
	@Autowired
	private ContactGroupPuStockWriteManage contactGroupPuStockWriteManage;
	@Autowired
	private ContactGroupPuStockReadManage contactGroupPuStockReadManage;

	@Resource
	private JedisUtil cache;


	@Override
	public Long insertCommodityProductUnitWarehouseStockWithTx(CommodityProductUnitWarehouseStockDTO dto) {
		CommodityProductUnitWarehouseStockPO po = CommodityProductUnitWarehouseStockConverter.toPO(dto);
		Long rt = commodityProductUnitWarehouseStockWriteManage.insertCommodityProductUnitWarehouseStockWithTx(po);
		return rt;
	}

	@Override
	public int updateCommodityProductUnitWarehouseStockWithTx(CommodityProductUnitWarehouseStockDTO dto) {
		CommodityProductUnitWarehouseStockPO po = CommodityProductUnitWarehouseStockConverter.toPO(dto);
		int rt = commodityProductUnitWarehouseStockWriteManage.updateCommodityProductUnitWarehouseStockWithTx(po);
		return rt;
	}

	@Override
	public int deleteCommodityProductUnitWarehouseStockWithTx(CommodityProductUnitWarehouseStockDTO dto) {
		CommodityProductUnitWarehouseStockPO po = CommodityProductUnitWarehouseStockConverter.toPO(dto);
		int rt = commodityProductUnitWarehouseStockWriteManage.deleteCommodityProductUnitWarehouseStockWithTx(po);
		return rt;
	}

	/**
	 * 进货
	 */
	@Override
	public int merchandiseStockWithTx(CommodityProductUnitWarehouseStockDTO dto, Long userId, String userName,
			String ip, String mac, String productUnitSerialNumber, String puName) {
		
		logger.info("用户名称：" + userName + "进货：puId:" + dto.getCommodityProductUnitId() + ",数量:" + dto.getRealStockNum());
		// 根据puid查询pu库存信息
		CommodityProductUnitWarehouseStockPO commodityProductUnitWarehouseStockPO = commodityProductUnitWarehouseStockReadManage
				.findCommodityProductUnitWarehouseStockByPuId(dto.getCommodityProductUnitId());
		logger.info("用户名称：" + userName + "根据puid查询pu真实库存信息：puId:" + dto.getCommodityProductUnitId() + ",pu真实库存数量:"
				+ commodityProductUnitWarehouseStockPO.getRealStockNum());
		// 进货
		int i = commodityProductUnitWarehouseStockWriteManage
				.merchandiseStock(CommodityProductUnitWarehouseStockConverter.toPO(dto));
		logger.info("用户名称：" + userName + "进货：puId:" + dto.getCommodityProductUnitId() + ",数量:" + dto.getRealStockNum()
				+ "成功");
		saveCommodityProductUnitStockRunningWaterWithTx(commodityProductUnitWarehouseStockPO, userId, userName, ip, mac,
				null, StockConstant.STOCK_STATUS_STOCK.getStatus(), dto.getCommodityProductUnitId(),
				productUnitSerialNumber, puName);
		return i;
	}

	/**
	 * 
	 * @param commodityProductUnitWarehouseStockPO
	 *            更新前pu库存信息
	 * @param userId
	 *            用户id
	 * @param userName
	 *            用户名称
	 * @param ip
	 * @param mac
	 * @param orderCode
	 *            订单编号
	 * @param type
	 *            类型
	 * @param puId
	 * @param productUnitSerialNumber
	 *            pu编号
	 * @param puName
	 *            pu名称
	 */
	private void saveCommodityProductUnitStockRunningWaterWithTx(
			CommodityProductUnitWarehouseStockPO commodityProductUnitWarehouseStockPO, Long userId, String userName,
			String ip, String mac, String orderCode, Integer type, Long puId, String productUnitSerialNumber,
			String puName) {
		// 根据puid查询pu库存信息
		CommodityProductUnitWarehouseStockPO commodityProductUnitWarehouseStock = commodityProductUnitWarehouseStockReadManage
				.findCommodityProductUnitWarehouseStockByPuId(puId);
		// 同步保存库存流水记录
		CommodityProductUnitStockRunningWaterPO commodityProductUnitStockRunningWaterPO = new CommodityProductUnitStockRunningWaterPO();
		commodityProductUnitStockRunningWaterPO.setCommodityProductUnitId(puId);
		commodityProductUnitStockRunningWaterPO.setProductUnitSerialNumber(productUnitSerialNumber);
		commodityProductUnitStockRunningWaterPO.setCommodityProductUnitName(puName);
		commodityProductUnitStockRunningWaterPO
				.setPreoperativeStockNum(commodityProductUnitWarehouseStockPO.getRealStockNum());
		commodityProductUnitStockRunningWaterPO
				.setOperationBackStockNum(commodityProductUnitWarehouseStock.getRealStockNum());
		commodityProductUnitStockRunningWaterPO
				.setStockChange(commodityProductUnitWarehouseStock.getRealStockNum().longValue()
						- commodityProductUnitWarehouseStockPO.getRealStockNum().longValue());
		commodityProductUnitStockRunningWaterPO.setCreateUserid(userId);
		commodityProductUnitStockRunningWaterPO.setCreateUsername(userName);
		commodityProductUnitStockRunningWaterPO.setCreateUserip(ip);
		commodityProductUnitStockRunningWaterPO.setCreateUsermac(mac);
		commodityProductUnitStockRunningWaterPO.setPlatformId(commodityProductUnitWarehouseStock.getPlatformId());
		commodityProductUnitStockRunningWaterPO.setType(type);
		commodityProductUnitStockRunningWaterPO.setOrderCode(orderCode);
		commodityProductUnitStockRunningWaterPO
				.setPreoperativeRealStockNum(commodityProductUnitWarehouseStockPO.getRealFrozenStockNum());
		commodityProductUnitStockRunningWaterPO
				.setOperationBackRealStockNum(commodityProductUnitWarehouseStock.getRealFrozenStockNum());
		commodityProductUnitStockRunningWaterPO.setPlatformId(commodityProductUnitWarehouseStock.getPlatformId());
		
		if(RuntimeContext.cacheUser()!=null && RuntimeContext.cacheUser().getEnterpriseId()!=null) {
			commodityProductUnitStockRunningWaterPO.setEnterpriseId(RuntimeContext.cacheUser().getEnterpriseId());
		}
		
		commodityProductUnitStockRunningWaterWriteManage
				.insertCommodityProductUnitStockRunningWaterWithTx(commodityProductUnitStockRunningWaterPO);
	}
	

	/**
	 * 出货
	 * 
	 * @param vo
	 * @param req
	 * @return
	 */
	@Override
	public int deliverStockWithTx(CommodityProductUnitWarehouseStockDTO dto, Long userId, String userName, String ip,
			String mac, String productUnitSerialNumber, String puName) {
		
		logger.info("用户名称：" + userName + "出货：puId:" + dto.getCommodityProductUnitId() + ",数量:" + dto.getRealStockNum());
		// 根据puid查询pu库存信息
		CommodityProductUnitWarehouseStockPO commodityProductUnitWarehouseStockPO = commodityProductUnitWarehouseStockReadManage
				.findCommodityProductUnitWarehouseStockByPuId(dto.getCommodityProductUnitId());
		logger.info("用户名称：" + userName + "根据puid查询pu真实库存信息：puId:" + dto.getCommodityProductUnitId() + ",pu真实库存数量:"
				+ commodityProductUnitWarehouseStockPO.getRealStockNum());
		// 出货
		int i = commodityProductUnitWarehouseStockWriteManage
				.deliverStockWithTx(CommodityProductUnitWarehouseStockConverter.toPO(dto));
		logger.info("用户名称：" + userName + "出货：puId:" + dto.getCommodityProductUnitId() + ",数量:" + dto.getRealStockNum()
				+ "成功");
		saveCommodityProductUnitStockRunningWaterWithTx(commodityProductUnitWarehouseStockPO, userId, userName, ip, mac,
				null, StockConstant.STOCK_STATUS_SHIPMENT.getStatus(), dto.getCommodityProductUnitId(),
				productUnitSerialNumber, puName);
		return i;
	}

	/**
	 * 删除redis中的缓存
	 * 
	 * @param commodityProductUnitWarehouseStockDTO
	 */
	@Override
	public void removeCommodityProductUnitWarehouseStockCache(
			CommodityProductUnitWarehouseStockDTO commodityProductUnitWarehouseStockDTO) {
		String key = "productUnit:inventory:" + commodityProductUnitWarehouseStockDTO.getCommodityProductUnitId();
		cache.del(key);
		logger.info("===========日志===========: 已删除redis中的缓存，key=" + key);

	}

	/**
	 * 冻结库存
	 */
	@Override
	public int freezeStockWithTx(Long puId, Integer puCount, String orderCode, Integer type,
			String productUnitSerialNumber, String puName, Long userId, String userName, String ip, String mac) {
		
		// 根据puid查询pu库存信息
		CommodityProductUnitWarehouseStockPO commodityProductUnitWarehouseStockPO = commodityProductUnitWarehouseStockReadManage
				.findCommodityProductUnitWarehouseStockByPuId(puId);

		int i = commodityProductUnitWarehouseStockWriteManage.freezeStockWithTx(puId, puCount, orderCode, type,
				productUnitSerialNumber, puName);
		if (i != 0) {
			saveCommodityProductUnitStockRunningWaterWithTx(commodityProductUnitWarehouseStockPO, userId, userName, ip,
					mac, orderCode, type, puId, productUnitSerialNumber, puName);
		}

		return i;
	}

	/**
	 * 根据puid扣除冻结库存扣除库存
	 * 
	 * @param puId
	 * @param puCount
	 * @return
	 */
	@Override
	public int unfreezeAndDeductStockWithTx(Long puId, Integer puCount, Integer type, String productUnitSerialNumber,
			String puName, String orderCode, Long userId, String userName, String ip, String mac) {
		// 根据puid查询pu库存信息
		CommodityProductUnitWarehouseStockPO commodityProductUnitWarehouseStockPO = commodityProductUnitWarehouseStockReadManage
				.findCommodityProductUnitWarehouseStockByPuId(puId);

		int i = commodityProductUnitWarehouseStockWriteManage.unfreezeAndDeductStockWithTx(puId, puCount, type,
				productUnitSerialNumber, puName);
		if (i != 0) {
			saveCommodityProductUnitStockRunningWaterWithTx(commodityProductUnitWarehouseStockPO, userId, userName, ip,
					mac, orderCode, type, puId, productUnitSerialNumber, puName);
		}
		return i;
	}

	@Override
	public int unFreeseOrderStock(Long puId, Integer puCount, String orderCode, Integer type,
			String productUnitSerialNumber, String puName, Long userId, String userName, String ip, String mac) {
		// 根据puid查询pu库存信息
		CommodityProductUnitWarehouseStockPO commodityProductUnitWarehouseStockPO = commodityProductUnitWarehouseStockReadManage
				.findCommodityProductUnitWarehouseStockByPuId(puId);
		int i = commodityProductUnitWarehouseStockWriteManage.unFreeseOrderStockWithTx(puId, puCount, orderCode, type,
				productUnitSerialNumber, puName);
		if (i != 0) {
			saveCommodityProductUnitStockRunningWaterWithTx(commodityProductUnitWarehouseStockPO, userId, userName, ip,
					mac, orderCode, type, puId, productUnitSerialNumber, puName);
		}
		return i;
	}

	@Override
	public int recoverOrderStock(Long puId, Integer puCount, String orderCode, Integer type,
			String productUnitSerialNumber, String puName, Long userId, String userName, String ip, String mac) {
		// 根据puid查询pu库存信息
		CommodityProductUnitWarehouseStockPO commodityProductUnitWarehouseStockPO = commodityProductUnitWarehouseStockReadManage
				.findCommodityProductUnitWarehouseStockByPuId(puId);
		int i = commodityProductUnitWarehouseStockWriteManage.recoverOrderStockWithTx(puId, puCount, orderCode, type,
				productUnitSerialNumber, puName);
		if (i != 0) {
			saveCommodityProductUnitStockRunningWaterWithTx(commodityProductUnitWarehouseStockPO, userId, userName, ip,
					mac, orderCode, type, puId, productUnitSerialNumber, puName);
		}
		return i;
	}

	/**
	 * 批量根据pu生成库存信息
	 * 
	 * @param puIdList
	 * @param merchantProductId
	 * @param platformId
	 */
	@Override
	public int insertCommodityProductUnitWarehouseStockAllWithTx(List<Long> puIdList, Long merchantProductId,
			Long platformId) {
		// 商家暂时写死
		Long merchantId = 1L;
		return commodityProductUnitWarehouseStockWriteManage.insertCommodityProductUnitWarehouseStockAllWithTx(puIdList,
				merchantProductId, platformId,merchantId);
	}
	
	/**
	 * 库存同步
	 * @param puId
	 * @param quantity
	 * @param puSerialNumber
	 * @param puName
	 */
	public void syncStockWithTx(Long puId, Long quantity, String puSerialNumber, String puName) {
		commodityProductUnitWarehouseStockWriteManage.syncStockWithTx(puId, quantity, puSerialNumber, puName);
	}
	
	
	/**
	 * 共用库存批量进货
	 * @param dto
	 * @param userId
	 * @param userName
	 * @param ip
	 * @param mac
	 * @param productUnitSerialNumber
	 * @param puName
	 * @return
	 */
	@Override
	public int merchandiseStockBatchWithTx(CommodityProductUnitWarehouseStockDTO dto, Long userId, String userName,
			String ip, String mac,List<Long> puIds,List<CommodityProductUnitDTO> commodityProductUnitDTOs){
		
		logger.info("用户名称：" + userName + "进货：puIds:" + puIds + ",数量:" + dto.getRealStockNum());
		
		//获取关联组Name
		ContactGroupStockPO contactGroupStockPO =  contactGroupStockReadManage.findContactGroupStockByPuId(dto.getCommodityProductUnitId());
		
		//批量查询更新前库存
		List<CommodityProductUnitWarehouseStockPO> befores = commodityProductUnitWarehouseStockReadManage.findByPUId(puIds);
		
		//批量更新库存
		int i = commodityProductUnitWarehouseStockWriteManage.merchandiseStockBatch(puIds,dto.getRealStockNum());
		
		//批量查询更新后库存
		List<CommodityProductUnitWarehouseStockPO> afters = commodityProductUnitWarehouseStockReadManage.findByPUId(puIds);
		
		saveBatchCommodityProductUnitStockRunningWaterWithTx(dto.getCommodityProductUnitId(),userId, userName, ip, mac, null, StockConstant.STOCK_STATUS_STOCK.getStatus(),  
				puIds, befores, afters,commodityProductUnitDTOs);
		
		
		return i;
	}
	
	/**
	 * 共用库存批量出货
	 * 
	 * @param vo
	 * @param req
	 * @return
	 */
	@Override
	public int deliverStockBatchWithTx(CommodityProductUnitWarehouseStockDTO dto, Long userId, String userName, String ip,
			String mac,List<Long> puIds,List<CommodityProductUnitDTO> commodityProductUnitDTOs) {
		
		logger.info("用户名称：" + userName + "出货：puId:" + puIds + ",数量:" + dto.getRealStockNum());
		//获取关联组Name
		ContactGroupStockPO contactGroupStockPO =  contactGroupStockReadManage.findContactGroupStockByPuId(dto.getCommodityProductUnitId());
		
		//批量查询更新前库存
		List<CommodityProductUnitWarehouseStockPO> befores = commodityProductUnitWarehouseStockReadManage.findByPUId(puIds);
		
		//批量更新库存
		int i = commodityProductUnitWarehouseStockWriteManage.deliverStockBatchWithTx(puIds,dto.getRealStockNum());
		
		//批量查询更新后库存
		List<CommodityProductUnitWarehouseStockPO> afters = commodityProductUnitWarehouseStockReadManage.findByPUId(puIds);
		
		saveBatchCommodityProductUnitStockRunningWaterWithTx(dto.getCommodityProductUnitId(),userId, userName, ip, mac, null, StockConstant.STOCK_STATUS_SHIPMENT.getStatus(),
				 puIds, befores, afters,commodityProductUnitDTOs);
		
		return i;
	}
	
	
	/**
	 * 共用库存批量冻结库存
	 * @param puId
	 * @param puCount
	 * @param orderCode
	 * @param type
	 * @param productUnitSerialNumber
	 * @param puName
	 * @param userId
	 * @param userName
	 * @param ip
	 * @param mac
	 * @return
	 */
	@Override
	public int freezeStockBatchWithTx(Long puId, Integer puCount, String orderCode, Integer type,
			 Long userId, String userName, String ip, String mac,
			List<Long> puIds,List<CommodityProductUnitDTO> commodityProductUnitDTOs) {
		
		logger.info("用户名称：" + userName + "库存冻结：puId:" + puIds + ",数量:" + puCount);
		//获取关联组Name
		ContactGroupStockPO contactGroupStockPO =  contactGroupStockReadManage.findContactGroupStockByPuId(puId);
		
		//批量查询更新前库存
		List<CommodityProductUnitWarehouseStockPO> befores = commodityProductUnitWarehouseStockReadManage.findByPUId(puIds);
		
		//批量更新库存
		int i = commodityProductUnitWarehouseStockWriteManage.freezeStockBatchWithTx(puIds,puCount);
		
		//批量查询更新后库存
		List<CommodityProductUnitWarehouseStockPO> afters = commodityProductUnitWarehouseStockReadManage.findByPUId(puIds);
		
		saveBatchCommodityProductUnitStockRunningWaterWithTx(puId,userId, userName, ip, mac, orderCode, type,
				puIds, befores, afters,commodityProductUnitDTOs);
		
		return i;
	}
	
	/**
	 * 根据puid批量扣除冻结库存扣除库存
	 * 
	 * @param puId
	 * @param puCount
	 * @return
	 */
	@Override
	public int unfreezeAndDeductStockBatchWithTx(Long puId, Integer puCount, Integer type, 
			String orderCode, Long userId, String userName, String ip, String mac,List<Long> puIds,
			List<CommodityProductUnitDTO> commodityProductUnitDTOs) {
		
		logger.info("用户名称：" + userName + "库存冻结：puId:" + puIds + ",数量:" + puCount);
		//获取关联组Name
		ContactGroupStockPO contactGroupStockPO =  contactGroupStockReadManage.findContactGroupStockByPuId(puId);
		
		//批量查询更新前库存
		List<CommodityProductUnitWarehouseStockPO> befores = commodityProductUnitWarehouseStockReadManage.findByPUId(puIds);
		
		//批量更新库存
		int i = commodityProductUnitWarehouseStockWriteManage.unfreezeAndDeductStockBatchWithTx(puIds,puCount);
		//批量查询更新后库存
		List<CommodityProductUnitWarehouseStockPO> afters = commodityProductUnitWarehouseStockReadManage.findByPUId(puIds);
		
		saveBatchCommodityProductUnitStockRunningWaterWithTx(puId,userId, userName, ip, mac, orderCode, type,
				 puIds, befores, afters,commodityProductUnitDTOs);
		
		
		return i;
	}

	/**
	 * 批量解冻 订单冻结库存
	 * @param puId
	 * @param puCount
	 * @param orderCode
	 * @param type
	 * @param productUnitSerialNumber
	 * @param puName
	 * @param userId
	 * @param userName
	 * @param ip
	 * @param mac
	 * @return
	 */
	@Override
	public int unFreeseOrderStockBatch(Long puId, Integer puCount, String orderCode, Integer type,
			 Long userId, String userName, String ip, String mac,List<Long> puIds,List<CommodityProductUnitDTO> commodityProductUnitDTOs) {
		
		logger.info("用户名称：" + userName + "库存冻结：puId:" + puIds + ",数量:" + puCount);
		//获取关联组Name
		ContactGroupStockPO contactGroupStockPO =  contactGroupStockReadManage.findContactGroupStockByPuId(puId);
		
		//批量查询更新前库存
		List<CommodityProductUnitWarehouseStockPO> befores = commodityProductUnitWarehouseStockReadManage.findByPUId(puIds);
		
		//批量更新库存
		int i = commodityProductUnitWarehouseStockWriteManage.unFreeseOrderStockBatchWithTx(puIds,puCount);
		//批量查询更新后库存
		List<CommodityProductUnitWarehouseStockPO> afters = commodityProductUnitWarehouseStockReadManage.findByPUId(puIds);
		
		saveBatchCommodityProductUnitStockRunningWaterWithTx(puId,userId, userName, ip, mac, orderCode, type,
				 puIds, befores, afters,commodityProductUnitDTOs);
		
		return i;
	}
	
	
	/**
	 * 批量生成流水
	 * @param userId
	 * @param userName
	 * @param ip
	 * @param mac
	 * @param orderCode
	 * @param type
	 * @param puId
	 * @param productUnitSerialNumber
	 * @param puName
	 * @param puIds
	 * @param befores
	 * @param afters
	 */
	private void saveBatchCommodityProductUnitStockRunningWaterWithTx(Long puId,
			Long userId, String userName,
			String ip, String mac, String orderCode, Integer type,
			List<Long> puIds,List<CommodityProductUnitWarehouseStockPO> befores,
			List<CommodityProductUnitWarehouseStockPO> afters,List<CommodityProductUnitDTO> commodityProductUnitDTOs) {
		
		List<CommodityProductUnitStockRunningWaterPO> poList = new ArrayList<CommodityProductUnitStockRunningWaterPO>();
		logger.info("puIds size:{} ,commodityProductUnitDTOs :{},afters:{},befores:{}",puIds.size(),commodityProductUnitDTOs.size(),
				afters.size(),befores.size());
		//生成订单流水
		for (Long puid : puIds) {
			logger.info("puid:{}",puid);
			CommodityProductUnitStockRunningWaterPO po = new CommodityProductUnitStockRunningWaterPO();
			Long stockChange = 0L;
			for (CommodityProductUnitDTO commodityProductUnitDTO : commodityProductUnitDTOs) {
				
				if(puid.equals(commodityProductUnitDTO.getId())) {
					logger.info("puid:{},ProductUnitSerialNumber:{}",puid,commodityProductUnitDTO.getProductUnitSerialNumber());
					po.setProductUnitSerialNumber(commodityProductUnitDTO.getProductUnitSerialNumber());
					po.setCommodityProductUnitName(commodityProductUnitDTO.getName());
					break;
				}
			}
			for (CommodityProductUnitWarehouseStockPO after : afters) {
				
				if(puid.equals(after.getCommodityProductUnitId())) {
					
					po.setCommodityProductUnitId(puid);
					po.setOperationBackStockNum(after.getRealStockNum());
					po.setCreateUserid(userId);
					po.setCreateUsername(userName);
					po.setCreateUserip(ip);
					po.setCreateUsermac(mac);
					po.setPlatformId(after.getPlatformId());
					
					po.setOperationBackRealStockNum(after.getRealFrozenStockNum());
					po.setPlatformId(after.getPlatformId());
					
					stockChange = after.getRealStockNum() == null ? 0 : after.getRealStockNum().longValue();
					break;
				}
			}
			
			for (CommodityProductUnitWarehouseStockPO before : befores) {
				if(puid.equals(before.getCommodityProductUnitId())) {
					po.setPreoperativeStockNum(before.getRealStockNum());
					po.setPreoperativeRealStockNum(before.getRealFrozenStockNum());
					stockChange -= before.getRealStockNum() == null ? 0 : before.getRealStockNum().longValue();
					break;
				}
				
			}
			
			if(puId.equals(puid)) {
				po.setType(type);
			}else {
				po.setType(StockConstant.STOCK_STATUS_CONTACT_STOCK.getStatus());
			}
			
			po.setStockChange(stockChange);
			
			po.setOrderCode(orderCode);
			poList.add(po);
		}

		//批量生成库存流水
		commodityProductUnitStockRunningWaterWriteManage.insertBatchCommodityProductUnitStockRunningWaterWithTx(poList);
	}

	@Override
	public void updateCommodityProductUnitWarehouseStockWithTx(List<Long> puIds, Long nowRealStockNum,
			Long nowRealFrozenStockNum) {
		commodityProductUnitWarehouseStockWriteManage.updateCommodityProductUnitWarehouseStockWithTx(puIds,nowRealStockNum,nowRealFrozenStockNum);
	}

	@Override
	public void recoverOrderStockBatch(Long puId, Integer puCount, String orderCode, int status, Long userId,
			String userName, String ip, String mac, List<Long> puIdList,
			List<CommodityProductUnitDTO> commodityProductUnitDTOs) {
		
		logger.info("用户名称：" + userName + "库存冻结：puId:" + puIdList + ",数量:" + puCount);
		//获取关联组Name
		ContactGroupStockPO contactGroupStockPO =  contactGroupStockReadManage.findContactGroupStockByPuId(puId);
		
		//批量查询更新前库存
		List<CommodityProductUnitWarehouseStockPO> befores = commodityProductUnitWarehouseStockReadManage.findByPUId(puIdList);
		
		//批量更新库存
		int i = commodityProductUnitWarehouseStockWriteManage.recoverOrderStockBatchWithTx(puIdList,puCount);
		
		//批量查询更新后库存
		List<CommodityProductUnitWarehouseStockPO> afters = commodityProductUnitWarehouseStockReadManage.findByPUId(puIdList);
		
		saveBatchCommodityProductUnitStockRunningWaterWithTx(puId,userId, userName, ip, mac, orderCode, status,
				puIdList, befores, afters,commodityProductUnitDTOs);
		
	}
	
	/**
	 * 初始化pu库存
	 * 如果当前pu有共用库存则计算新库存 并生成相应流水
	 */
	@Override
	public void initPuStock(List<CommodityProductUnitDTO> commodityProductUnitDTOs,Long suId) {
		
		List<ContactGroupPuStockDTO> list = new ArrayList<>();
		List<ContactGroupSkuStockPO> contactGroupSkuStockPOs = contactGroupSkuStockReadManage.findContactGroupSkuStockBySuId(suId);
		
		for (CommodityProductUnitDTO commodityProductUnitDTO : commodityProductUnitDTOs) {
			
			ContactGroupPuStockDTO contactGroupPuStockDTO = new ContactGroupPuStockDTO();
			for (ContactGroupSkuStockPO contactGroupSkuStockPO : contactGroupSkuStockPOs) {
				
				if(contactGroupSkuStockPO.getSkuId().equals(commodityProductUnitDTO.getSkuId())) {
					contactGroupPuStockDTO.setContactGroupSkuId(contactGroupSkuStockPO.getId());
					contactGroupPuStockDTO.setPuId(commodityProductUnitDTO.getId());
					contactGroupPuStockDTO.setSuId(suId);
					
					//根据skuId 查询当前库存
					List<ContactGroupPuStockPO> POs = contactGroupPuStockReadManage.findContactGroupPuStockBySkuId(contactGroupSkuStockPO.getId());
					if(POs != null && POs.size() > 0) {
						//查询修改前库存
						CommodityProductUnitWarehouseStockPO po = commodityProductUnitWarehouseStockReadManage.findCommodityProductUnitWarehouseStockByPuId(POs.get(0).getPuId());
						CommodityProductUnitWarehouseStockPO poNew = new CommodityProductUnitWarehouseStockPO();
						
						poNew.setCommodityProductUnitId(commodityProductUnitDTO.getId());
						poNew.setMerchantId(commodityProductUnitDTO.getMerchantId());
						poNew.setMerchantProductId(suId);
						poNew.setPlatformId(commodityProductUnitDTO.getPlatformId());
						poNew.setStandardUnitId(suId);
						poNew.setRealStockNum(po.getRealStockNum());
						poNew.setRealFrozenStockNum(po.getRealFrozenStockNum());
						//生成库存
						commodityProductUnitWarehouseStockWriteManage.insertCommodityProductUnitWarehouseStockWithTx(poNew);
						
						//生成流水
						
					}
				}
			}
			list.add(contactGroupPuStockDTO);
			
			
		}
		//建立库存关系
		contactGroupPuStockWriteManage.insertContactGroupPuListStockWithTx(ContactGroupPuStockConverter.toPO(list));
	}

		@Override
		public void saveCommodityProductUnitWarehouseStock(List<Long> puIdList,List<Long> suIdList,List<Long> merchantProductIdList){
			Integer SIZE=10000;
			ExecutorService executor = Executors.newCachedThreadPool();
			//库存
			List<CommodityProductUnitWarehouseStockPO> res = new ArrayList<>();
			for(int i=0;i<puIdList.size();i++){
				CommodityProductUnitWarehouseStockPO stock = new CommodityProductUnitWarehouseStockPO();
				stock.setCommodityProductUnitId(puIdList.get(i));
				stock.setStandardUnitId(suIdList.get(i));
				stock.setMerchantId(6L);
				stock.setMerchantProductId(merchantProductIdList.get(i));
				stock.setRealStockNum(9999L);
				stock.setRealFrozenStockNum(0L);
				stock.setPlatformId(7L);
				res.add(stock);
			}
			if(EmptyUtil.isEmpty(res)){
				return;
			}
			logger.info("开始异步处理CommodityProductUnitWarehouseStock保存");
			CountDownLatch countDownLatch = new CountDownLatch(1);


			int page=((res.size()-1)/SIZE)+1;
			try {
				cache.set("CommodityProductUnitWarehouseStock",24*60*60,page);
			}catch (Exception e){
				logger.info("[CommodityProductUnitWarehouseStock]设置redis失败");
			}
			for(int i=0;i<page;i++) {
				List<CommodityProductUnitWarehouseStockPO> list = new ArrayList<>();
				if (i == (page - 1)) {
					for (int j = i * SIZE; j < res.size(); j++) {
						list.add(res.get(j));
					}
				} else {
					for (int j = i * SIZE; j < (i + 1) * SIZE; j++) {
						list.add(res.get(j));
					}
				}
				SaveListFactory saveListFactory = new SaveListFactory();


				saveListFactory.setLatch(countDownLatch);
				saveListFactory.setSaveType("CommodityProductUnitWarehouseStock");
				saveListFactory.setCommodityProductUnitWarehouseStockPOList(list);
				saveListFactory.setCommodityProductUnitWarehouseStockWriteManage(commodityProductUnitWarehouseStockWriteManage);
				saveListFactory.setJedisUtil(cache);

				executor.execute(saveListFactory);

			}
			countDownLatch.countDown();
executor.shutdown();
		}

	@Override
	public void updateJdPUStock() {
		commodityProductUnitWarehouseStockWriteManage.updateJdPUStock();
	}


}
