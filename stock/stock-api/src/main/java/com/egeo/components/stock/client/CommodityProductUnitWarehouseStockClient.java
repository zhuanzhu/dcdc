package com.egeo.components.stock.client;
import java.util.List;

import com.egeo.components.stock.dto.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.egeo.components.stock.fo.UnfreezeAndDeductStockBatchWithTxFO;
import com.egeo.components.stock.vo.CommodityProductUnitWarehouseStockVO;
import com.egeo.web.JsonResult;


@FeignClient(name = "service-stock-fgj",contextId="CommodityProductUnitWarehouseStockClient")
public interface CommodityProductUnitWarehouseStockClient {

	@RequestMapping(value = { "/client/stock/commodityProductUnitWarehouseStock/findCommodityProductUnitWarehouseStockByPuId" }, method = { RequestMethod.POST }) 
	public CommodityProductUnitWarehouseStockDTO findCommodityProductUnitWarehouseStockByPuId(Long puId); 
 
 
	@RequestMapping(value = { "/client/stock/commodityProductUnitWarehouseStock/realStockNumByCommodityProductUnitId" }, method = { RequestMethod.POST }) 
	public Long realStockNumByCommodityProductUnitId(Long id); 
 
	
	@RequestMapping(value = { "/client/stock/commodityProductUnitWarehouseStock/merchandiseStockWithTx" }, method = { RequestMethod.POST }) 
	public JsonResult<Integer> merchandiseStockWithTx(CommodityProductUnitWarehouseStockVO vo); 
 
	@RequestMapping(value = { "/client/stock/commodityProductUnitWarehouseStock/findCommodityProductUnitWarehouseStockAll" }, method = { RequestMethod.POST }) 
	public List<CommodityProductUnitWarehouseStockDTO> findCommodityProductUnitWarehouseStockAll(CommodityProductUnitWarehouseStockDTO dto); 
 
 
	@RequestMapping(value = { "/client/stock/commodityProductUnitWarehouseStock/findIsSkuIdsBySkuIds" }, method = { RequestMethod.POST }) 
	public List<String> findIsSkuIdsBySkuIds(List<String> skuIds); 
 
 
	@RequestMapping(value = { "/client/stock/commodityProductUnitWarehouseStock/findByPUId" }, method = { RequestMethod.POST }) 
	public List<CommodityProductUnitWarehouseStockDTO> findByPUId(List<String> commodityProductUnitIds); 
 
 
	@RequestMapping(value = { "/client/stock/commodityProductUnitWarehouseStock/residueStockNumByStandardUnitId" }, method = { RequestMethod.POST }) 
	public Long residueStockNumByStandardUnitId(Long standardUnitId);


	@RequestMapping(value = { "/client/stock/commodityProductUnitWarehouseStock/residueStockNumByStandardUnitIds" }, method = { RequestMethod.POST })
	List<ResidueStockNumConditionDTO> residueStockNumByStandardUnitIds(List<Long> standardUnitIds);

	@RequestMapping(value = { "/client/stock/commodityProductUnitWarehouseStock/findPuSellOutSumByPuIds" }, method = { RequestMethod.POST }) 
	public Integer findPuSellOutSumByPuIds(List<String> puIds); 
 
 
	@RequestMapping(value = { "/client/stock/commodityProductUnitWarehouseStock/unfreezeAndDeductStockBatchWithTx" }, method = { RequestMethod.POST }) 
	public int unfreezeAndDeductStockBatchWithTx(UnfreezeAndDeductStockBatchWithTxFO fo);

 
	@RequestMapping(value = { "/client/stock/commodityProductUnitWarehouseStock/unfreezeAndDeductStockWithTx" }, method = { RequestMethod.POST }) 
	public int unfreezeAndDeductStockWithTx(@RequestParam("puId") Long puId,@RequestParam("puCount")  Integer puCount,@RequestParam("type")  Integer type,@RequestParam("productUnitSerialNumber")  String productUnitSerialNumber,
			@RequestParam("puName") String puName,@RequestParam("orderCode")  String orderCode,@RequestParam("userId")  Long userId, @RequestParam("userName") String userName, @RequestParam("ip") String ip, @RequestParam("mac") String mac);

 
	@RequestMapping(value = { "/client/stock/commodityProductUnitWarehouseStock/saveCommodityProductUnitWarehouseStock" }, method = { RequestMethod.POST }) 
	public void saveCommodityProductUnitWarehouseStock(@RequestParam("puIdList") List<String> puIdList,@RequestParam("suIdList")  List<String> suIdList, @RequestParam("merchantProductIdList") List<String> merchantProductIdList); 
 
 
	@RequestMapping(value = { "/client/stock/commodityProductUnitWarehouseStock/insertCommodityProductUnitWarehouseStockAllWithTx" }, method = { RequestMethod.POST }) 
	public int insertCommodityProductUnitWarehouseStockAllWithTx(@RequestParam("puIdList") List<String> puIdList, @RequestParam("merchantProductId") Long merchantProductId, 
			@RequestParam("platformId") Long platformId);
 
	@RequestMapping(value = { "/client/stock/commodityProductUnitWarehouseStock/insertCommodityProductUnitWarehouseStockWithTx" }, method = { RequestMethod.POST }) 
	public Long insertCommodityProductUnitWarehouseStockWithTx(CommodityProductUnitWarehouseStockDTO dto); 
 
 
	@RequestMapping(value = { "/client/stock/commodityProductUnitWarehouseStock/updateCommodityProductUnitWarehouseStockWithTx" }, method = { RequestMethod.POST }) 
	public void updateCommodityProductUnitWarehouseStockWithTx(@RequestParam("puIds") List<String> puIds, @RequestParam("nowRealStockNum") Long nowRealStockNum, 
			@RequestParam("nowRealFrozenStockNum") Long nowRealFrozenStockNum);
 
	@RequestMapping(value = { "/client/stock/commodityProductUnitWarehouseStock/updateJdPUStock" }, method = { RequestMethod.POST }) 
	public  void updateJdPUStock(); 
 
 
	@RequestMapping(value = { "/client/stock/commodityProductUnitWarehouseStock/unFreeseOrderStock" }, method = { RequestMethod.POST }) 
	public int unFreeseOrderStock(@RequestParam("puId") Long puId, @RequestParam("puCount") Integer puCount, @RequestParam("orderCode") String orderCode, @RequestParam("type") Integer type,
			@RequestParam("productUnitSerialNumber") String productUnitSerialNumber,@RequestParam("puName")  String puName, @RequestParam("userId") Long userId, @RequestParam("userName") String userName, @RequestParam("ip") String ip,@RequestParam("mac")  String mac);

 
	@RequestMapping(value = { "/client/stock/commodityProductUnitWarehouseStock/syncStockWithTx" }, method = { RequestMethod.POST }) 
	public void syncStockWithTx(@RequestParam("puId") Long puId,@RequestParam("quantity")  Long quantity,@RequestParam("puSerialNumber")  String puSerialNumber, @RequestParam("puName") String puName); 
 

	@RequestMapping(value = { "/client/stock/commodityProductUnitWarehouseStock/recoverOrderStockBatch" }, method = { RequestMethod.POST }) 
	public void recoverOrderStockBatch(RecoverOrderStockBatchDTO dto);
	
	

	/**
	 * 回复订单库存
	 * 
	 * @param puId
	 * @param puCount
	 */
	@RequestMapping(value = { "/client/stock/commodityProductUnitWarehouseStock/recoverOrderStock" }, method = { RequestMethod.POST }) 
	public int recoverOrderStock(@RequestParam("puId") Long puId, @RequestParam("puCount") Integer puCount, @RequestParam("orderCode") String orderCode, 
			@RequestParam("type") Integer type,@RequestParam("productUnitSerialNumber") String productUnitSerialNumber,@RequestParam("puName")  String puName,
			@RequestParam("userId") Long userId,@RequestParam("userName")  String userName, @RequestParam("ip") String ip,@RequestParam("mac")  String mac);


	@RequestMapping(value = { "/client/stock/commodityProductUnitWarehouseStock/unFreeseOrderStockBatch" }, method = { RequestMethod.POST }) 
	public int unFreeseOrderStockBatch(UnFreeseOrderStockBatchDTO dto);


	@RequestMapping(value = { "/client/stock/commodityProductUnitWarehouseStock/freezeStockBatchWithTx" }, method = { RequestMethod.POST }) 
	public int freezeStockBatchWithTx(FreeseOrderStockBatchDTO dto);

	/**
	 * 冻结库存
	 * 
	 * @param puId
	 * @param puCount
	 * @return
	 */
	@RequestMapping(value = { "/client/stock/commodityProductUnitWarehouseStock/freezeStockWithTx" }, method = { RequestMethod.POST }) 
	public int freezeStockWithTx(FreezeStockWithTxDTO dto);
}