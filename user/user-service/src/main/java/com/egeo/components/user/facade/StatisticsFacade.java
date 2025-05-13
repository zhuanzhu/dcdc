package com.egeo.components.user.facade;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.egeo.components.order.client.SoClient;
import com.egeo.components.product.client.StandardUnitStoreClient;
import com.egeo.components.product.client.StoreProductUnitClient;
import com.egeo.components.stock.client.CommodityProductUnitWarehouseStockClient;
import com.egeo.components.stock.client.StorePuWarehouseStockClient;
import com.egeo.components.user.service.read.UserExtendReadService;
import com.egeo.utils.EmptyUtil;

@Component
public class StatisticsFacade {
	
	@Resource
	private StandardUnitStoreClient standardUnitStoreReadService;
	
	@Autowired
	private CommodityProductUnitWarehouseStockClient commodityProductUnitWarehouseStockReadService;
	
	@Autowired
	private StorePuWarehouseStockClient storePuWarehouseStockReadService;
	
	@Autowired
	private SoClient soReadService;
	
	@Resource
	private UserExtendReadService userExtendReadService;
	
	@Autowired
	private StoreProductUnitClient storeProductUnitReadService;
	/**
	 * 统计平台商品、订单、用户数据
	 * @param platformId
	 * @return
	 */
	public Map<String, Object> dataStatistics(Long storeId, Long platformId) {
		// 统计数据(待优化，里面name和explain可以考虑写数据库，从数据库读, 目前没有改数据库权限，比较急先完成功能)
		Map<String, Object> data = new HashMap<String, Object>();
		List<Map<String, Object>> statisticsList = new ArrayList<>();
		
		// 商品统计数据
		Map<String, Object> standardUnitData = new HashMap<String, Object>();
		standardUnitData.put("categoryName", "商品");
		List<Map<String, Object>> standardUnitDataList = new ArrayList<>();
		
		Map<String, Object> standardUnitSumData = new HashMap<>();
		// 根据门店id查询在售商品数量
		Integer standardUnitSum = standardUnitStoreReadService.standardUnitSumByStoreId(storeId,platformId);
		standardUnitSumData.put("name", "在售商品");
		standardUnitSumData.put("sum", standardUnitSum);
		standardUnitSumData.put("explain", "配置到该门店下的上架状态的SU数量");
		standardUnitSumData.put("isShow", 0);
		standardUnitDataList.add(standardUnitSumData);
		
		Map<String, Object> puSellOutSumData = new HashMap<>();
		// 根据门店id获取沽清商品规格数量(PU在线库存数量为0的PU数量)
		Integer puSellOutSum = getPuSellOutSum(storeId,platformId);
		puSellOutSumData.put("name", "沽清规格数量");
		puSellOutSumData.put("sum", puSellOutSum);
		puSellOutSumData.put("explain", "所有门店共用的在线库存为0的商品规格数量");
		puSellOutSumData.put("isShow", 1);
		standardUnitDataList.add(puSellOutSumData);
		
		Map<String, Object> storePuSellOutSumData = new HashMap<>();
		// 根据门店id获取缺货商品规格数量(门店PU分身的在线库存数量为0的PU数量)
		Integer storePuSellOutSum = getStorePuSellOutSum(storeId,platformId);
		storePuSellOutSumData.put("name", "缺货规格数量");
		storePuSellOutSumData.put("sum", storePuSellOutSum);
		storePuSellOutSumData.put("explain", "门店库存数量为0的商品规格数量");
		storePuSellOutSumData.put("isShow", 1);
		standardUnitDataList.add(storePuSellOutSumData);
		standardUnitData.put("statisticsDataList", standardUnitDataList);
		statisticsList.add(standardUnitData);
		
		// 订单统计数据
		Map<String, Object> orderData = new HashMap<String, Object>();
		orderData.put("categoryName", "订单");
		List<Map<String, Object>> orderDataList = new ArrayList<>();
		
		// 当月订单：所属平台门店为该门店的创建于当月（自然月）订单数量，包括已取消、已完成等所有订单状态。
		Integer currentMonthOrderSum = soReadService.findCurrentMonthOrder(storeId,platformId);
		Map<String, Object> currentMonthOrderSumData = new HashMap<>();
		currentMonthOrderSumData.put("name", "当月订单");
		currentMonthOrderSumData.put("sum", currentMonthOrderSum);
		currentMonthOrderSumData.put("explain", "所属平台门店为该门店的创建于当月（自然月）订单数量，包括已取消、已完成等所有订单状态");
		currentMonthOrderSumData.put("isShow", 0);
		orderDataList.add(currentMonthOrderSumData);
		
		// 今日订单：所属平台门店为该门店的创建于当日（自然日）订单数量，包括已取消、已完成等所有订单状态。
		Integer currentDayOrderSum = soReadService.findCurrentDayOrder(storeId,platformId);
		Map<String, Object> currentDayOrderSumData = new HashMap<>();
		currentDayOrderSumData.put("name", "今日订单");
		currentDayOrderSumData.put("sum", currentDayOrderSum);
		currentDayOrderSumData.put("explain", "所属平台门店为该门店的创建于当日（自然日）订单数量，包括已取消、已完成等所有订单状态");
		currentDayOrderSumData.put("isShow", 0);
		orderDataList.add(currentDayOrderSumData);
		
		// 待签收订单(待签收:确认状态为已确认，支付状态为已付款)
		Integer orderConfirmStatus = 1; // 订单确认状态  0:未确认，1:已确认，2:已取消 3:已完成
		Integer orderPayStatus = 1;// 订单支付状态 0:未支付、1:已支付、2:已退款
		Integer noSignOrderSum = soReadService.findNoSignOrderSumByStatus(orderConfirmStatus,orderPayStatus,storeId,platformId);
		Map<String, Object> noSignOrderSumData = new HashMap<>();
		noSignOrderSumData.put("name", "待签收订单");
		noSignOrderSumData.put("sum", noSignOrderSum);
		noSignOrderSumData.put("explain", "该门店中状态为“待签收”的订单数量");
		noSignOrderSumData.put("isShow", 0);
		orderDataList.add(noSignOrderSumData);
		
		// 当月订单总额
		BigDecimal currentMonthOrderAmount = soReadService.findCurrentMonthOrderAmount(storeId,platformId);
		if(EmptyUtil.isEmpty(currentMonthOrderAmount)){
			currentMonthOrderAmount = BigDecimal.valueOf(0);
		}
		Map<String, Object> currentMonthOrderAmountData = new HashMap<>();
		currentMonthOrderAmountData.put("name", "当月订单总额");
		currentMonthOrderAmountData.put("sum", currentMonthOrderAmount);
		currentMonthOrderAmountData.put("explain", "所属平台门店为该门店的创建于当月（自然月）订单总金额，包括已取消、已完成等所有订单状态");
		currentMonthOrderAmountData.put("isShow", 0);
		orderDataList.add(currentMonthOrderAmountData);
		
		// 今日订单总额
		BigDecimal currentDayOrderAmount = soReadService.findcurrentDayOrderAmount(storeId,platformId);
		if(EmptyUtil.isEmpty(currentDayOrderAmount)){
			currentDayOrderAmount = BigDecimal.valueOf(0);
		}
		Map<String, Object> currentDayOrderAmountData = new HashMap<>();
		currentDayOrderAmountData.put("name", "今日订单总额");
		currentDayOrderAmountData.put("sum", currentDayOrderAmount);
		currentDayOrderAmountData.put("explain", "所属平台门店为该门店的创建于当日（自然日）订单总金额，包括已取消、已完成等所有订单状态");
		currentDayOrderAmountData.put("isShow", 0);
		orderDataList.add(currentDayOrderAmountData);
		orderData.put("statisticsDataList", orderDataList);
		statisticsList.add(orderData);
		
		
		Map<String, Object> userData = new HashMap<String, Object>();
		userData.put("categoryName", "用户");
		List<Map<String, Object>> userDataList = new ArrayList<>();
		
		Integer userSum = userExtendReadService.findUserSumByStoreId(storeId,platformId);
		Map<String, Object> userSumData = new HashMap<>();
		userSumData.put("name", "用户总数量");
		userSumData.put("sum", userSum);
		userSumData.put("explain", "注册门店码为该门店ID的账号");
		userSumData.put("isShow", 0);
		userDataList.add(userSumData);
		
		Integer currentMonthUserSum = userExtendReadService.findCurrentMonthUserSumByStoreId(storeId,platformId);
		Map<String, Object> currentMonthUserSumData = new HashMap<>();
		currentMonthUserSumData.put("name", "当月新用户");
		currentMonthUserSumData.put("sum", currentMonthUserSum);
		currentMonthUserSumData.put("explain", "创建于当月（自然月）且注册门店码为该门店ID的账号");
		currentMonthUserSumData.put("isShow", 0);
		userDataList.add(currentMonthUserSumData);
		
		Integer currentDayUserSum = userExtendReadService.findCurrentDayUserSumByStoreId(storeId,platformId);
		Map<String, Object> currentDayUserSumData = new HashMap<>();
		currentDayUserSumData.put("name", "今日新用户");
		currentDayUserSumData.put("sum", currentDayUserSum);
		currentDayUserSumData.put("explain", "创建于当日（自然日）且注册门店码为该门店ID的账号");
		currentDayUserSumData.put("isShow", 0);
		userDataList.add(currentDayUserSumData);
		userData.put("statisticsDataList", userDataList);
		statisticsList.add(userData);
		data.put("statisticsList", statisticsList);
		return data;
	}
	/**
	 * 根据门店id获取缺货商品规格数量(门店PU分身的在线库存数量为0的PU数量)
	 * @param storeId
	 * @param platformId
	 * @return
	 */
	private Integer getStorePuSellOutSum(Long storeId, Long platformId) {
		// 根据门店id查询门店puid集合
		List<Long> storePuIds = com.egeo.utils.StringUtils.stringsToLongs(storeProductUnitReadService.findStorePuIdsByStoreId(storeId,platformId));
		// 缺货商品规格数量
		Integer puSellOutSum = 0;
		if(EmptyUtil.isNotEmpty(storePuIds)){
			int i = 0;
			while (i < storePuIds.size()) {
				if(i + 200 < storePuIds.size()){
					List<Long> subList = storePuIds.subList(i, i+200);
					// 根据截取puId集合查询pu库存为0的数量
					Integer puSum = storePuWarehouseStockReadService.findPuSellOutSumStoreByPuIds(com.egeo.utils.StringUtils.longsToStrings(subList));
					puSellOutSum = puSellOutSum + puSum;
				}else{
					List<Long> subList = storePuIds.subList(i, storePuIds.size());
					// 根据截取puId集合查询pu库存为0的数量
					Integer puSum = storePuWarehouseStockReadService.findPuSellOutSumStoreByPuIds(com.egeo.utils.StringUtils.longsToStrings(subList));
					puSellOutSum = puSellOutSum + puSum;
				}
				i = i + 200;
			}
		}
		return puSellOutSum;
	}
	/**
	 * 根据门店id获取沽清商品规格数量
	 * @param storeId
	 * @param platformId
	 * @return
	 */
	private Integer getPuSellOutSum(Long storeId, Long platformId) {
		// 沽清商品规格数量
		Integer puSellOutSum = 0;
		// 根据门店id查询puid集合
		List<Long> puIds = com.egeo.utils.StringUtils.stringsToLongs(standardUnitStoreReadService.findByPuIdsByStoreId(storeId,platformId));
		if(EmptyUtil.isNotEmpty(puIds)){
			int i = 0;
			while (i < puIds.size()) {
				if(i + 200 < puIds.size()){
					List<Long> subList = puIds.subList(i, i+200);
					// 根据截取puId集合查询pu库存为0的数量
					Integer puSum = commodityProductUnitWarehouseStockReadService.findPuSellOutSumByPuIds(com.egeo.utils.StringUtils.longsToStrings(subList));
					puSellOutSum = puSellOutSum + puSum;
				}else{
					List<Long> subList = puIds.subList(i, puIds.size());
					// 根据截取puId集合查询pu库存为0的数量
					Integer puSum = commodityProductUnitWarehouseStockReadService.findPuSellOutSumByPuIds(com.egeo.utils.StringUtils.longsToStrings(subList));
					puSellOutSum = puSellOutSum + puSum;
				}
				i = i + 200;
			}
		}
		return puSellOutSum;
	}

}
