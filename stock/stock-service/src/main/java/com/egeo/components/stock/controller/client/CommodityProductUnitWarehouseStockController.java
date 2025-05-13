package com.egeo.components.stock.controller.client;

import java.util.List;

import javax.annotation.Resource;

import com.egeo.components.stock.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.stock.business.CommodityProductUnitWarehouseStockManage;
import com.egeo.components.stock.client.CommodityProductUnitWarehouseStockClient;
import com.egeo.components.stock.converter.CommodityProductUnitWarehouseStockConverter;
import com.egeo.components.stock.fo.UnfreezeAndDeductStockBatchWithTxFO;
import com.egeo.components.stock.service.read.CommodityProductUnitWarehouseStockReadService;
import com.egeo.components.stock.service.write.CommodityProductUnitWarehouseStockWriteService;
import com.egeo.components.stock.vo.CommodityProductUnitWarehouseStockVO;
import com.egeo.config.RuntimeContext;
import com.egeo.entity.CacheUser;
import com.egeo.utils.log.XLogger;
import com.egeo.web.JsonResult;
@Controller
@RequestMapping("/client/stock/commodityProductUnitWarehouseStock") 
public class CommodityProductUnitWarehouseStockController implements CommodityProductUnitWarehouseStockClient{ 

	public XLogger logger = XLogger.getLogger(this.getClass().getName());
	@Autowired
	private CommodityProductUnitWarehouseStockReadService commodityProductUnitWarehouseStockReadService;
	@Autowired
	private CommodityProductUnitWarehouseStockWriteService commodityProductUnitWarehouseStockWriteService;
	@Resource(name="commodityProductUnitWarehouseStock")
	private CommodityProductUnitWarehouseStockManage commodityProductUnitWarehouseStockManage;


	@Override
	@RequestMapping(value = "/findCommodityProductUnitWarehouseStockByPuId", method = { RequestMethod.POST })
	@ResponseBody
	public CommodityProductUnitWarehouseStockDTO findCommodityProductUnitWarehouseStockByPuId(@RequestBody  Long puId) {
		return commodityProductUnitWarehouseStockReadService.findCommodityProductUnitWarehouseStockByPuId(puId);
	} 
 
	@Override
	@RequestMapping(value = "/realStockNumByCommodityProductUnitId", method = { RequestMethod.POST })
	@ResponseBody
	public Long realStockNumByCommodityProductUnitId(@RequestBody Long id) {
		return commodityProductUnitWarehouseStockReadService.realStockNumByCommodityProductUnitId(id);
	} 
 
	@Override
	@RequestMapping(value = "/findCommodityProductUnitWarehouseStockAll", method = { RequestMethod.POST })
	@ResponseBody
	public List<CommodityProductUnitWarehouseStockDTO> findCommodityProductUnitWarehouseStockAll(@RequestBody CommodityProductUnitWarehouseStockDTO dto) {
		return commodityProductUnitWarehouseStockReadService.findCommodityProductUnitWarehouseStockAll(dto);
	} 
 
	@Override
	@RequestMapping(value = "/findIsSkuIdsBySkuIds", method = { RequestMethod.POST })
	@ResponseBody
	public List<String> findIsSkuIdsBySkuIds(@RequestBody  List<String> skuIds) {
		return com.egeo.utils.StringUtils.longsToStrings(commodityProductUnitWarehouseStockReadService.findIsSkuIdsBySkuIds(com.egeo.utils.StringUtils.stringsToLongs(skuIds)));
	} 
 
	@Override
	@RequestMapping(value = "/findByPUId", method = { RequestMethod.POST })
	@ResponseBody
	public List<CommodityProductUnitWarehouseStockDTO> findByPUId(@RequestBody  List<String> commodityProductUnitIds) {
		return commodityProductUnitWarehouseStockReadService.findByPUId(com.egeo.utils.StringUtils.stringsToLongs(commodityProductUnitIds));
	} 
 
	@Override
	@RequestMapping(value = "/residueStockNumByStandardUnitId", method = { RequestMethod.POST })
	@ResponseBody
	public Long residueStockNumByStandardUnitId(@RequestBody  Long standardUnitId) {
		return commodityProductUnitWarehouseStockReadService.residueStockNumByStandardUnitId(standardUnitId);
	}

	@Override
	@RequestMapping(value = "/residueStockNumByStandardUnitIds", method = { RequestMethod.POST })
	@ResponseBody
	public List<ResidueStockNumConditionDTO> residueStockNumByStandardUnitIds(@RequestBody List<Long> standardUnitIds) {
		return commodityProductUnitWarehouseStockReadService.residueStockNumByStandardUnitIds(standardUnitIds);
	}

	@Override
	@RequestMapping(value = "/findPuSellOutSumByPuIds", method = { RequestMethod.POST })
	@ResponseBody
	public Integer findPuSellOutSumByPuIds(@RequestBody  List<String> puIds) {
		return commodityProductUnitWarehouseStockReadService.findPuSellOutSumByPuIds(com.egeo.utils.StringUtils.stringsToLongs(puIds));
	} 
 
	@Override
	@RequestMapping(value = "/unfreezeAndDeductStockBatchWithTx", method = { RequestMethod.POST })
	@ResponseBody
	public int unfreezeAndDeductStockBatchWithTx(@RequestBody UnfreezeAndDeductStockBatchWithTxFO fo) {
		return commodityProductUnitWarehouseStockWriteService.unfreezeAndDeductStockBatchWithTx(fo.getPuId(), fo.getPuCount(), fo.getType(), fo.getOrderCode(), fo.getUserId(), fo.getUserName(), fo.getIp(), fo.getMac(), fo.getPuIds(), fo.getCommodityProductUnitDTOs());
	} 
 
	@Override
	@RequestMapping(value = "/unfreezeAndDeductStockWithTx", method = { RequestMethod.POST })
	@ResponseBody
	public int unfreezeAndDeductStockWithTx(@RequestParam("puId") Long puId,@RequestParam("puCount")  Integer puCount,@RequestParam("type")  Integer type,@RequestParam("productUnitSerialNumber")  String productUnitSerialNumber,
			@RequestParam("puName") String puName,@RequestParam("orderCode")  String orderCode,@RequestParam("userId")  Long userId, @RequestParam("userName") String userName, @RequestParam("ip") String ip, @RequestParam("mac") String mac) {
		return commodityProductUnitWarehouseStockWriteService.unfreezeAndDeductStockWithTx(puId, puCount, type, productUnitSerialNumber, puName, orderCode, userId, userName, ip, mac);
	} 
 
	@Override
	@RequestMapping(value = "/saveCommodityProductUnitWarehouseStock", method = { RequestMethod.POST })
	@ResponseBody
	public void saveCommodityProductUnitWarehouseStock(@RequestParam("puIdList") List<String> puIdList,@RequestParam("suIdList")  List<String> suIdList, @RequestParam("merchantProductIdList") List<String> merchantProductIdList) {
		commodityProductUnitWarehouseStockWriteService.saveCommodityProductUnitWarehouseStock(com.egeo.utils.StringUtils.stringsToLongs(puIdList), com.egeo.utils.StringUtils.stringsToLongs(suIdList), com.egeo.utils.StringUtils.stringsToLongs(merchantProductIdList));
	} 
 
	@Override
	@RequestMapping(value = "/insertCommodityProductUnitWarehouseStockAllWithTx", method = { RequestMethod.POST })
	@ResponseBody
	public int insertCommodityProductUnitWarehouseStockAllWithTx(@RequestParam("puIdList") List<String> puIdList, @RequestParam("merchantProductId") Long merchantProductId, 
			@RequestParam("platformId") Long platformId) {
		return commodityProductUnitWarehouseStockWriteService.insertCommodityProductUnitWarehouseStockAllWithTx(com.egeo.utils.StringUtils.stringsToLongs(puIdList), merchantProductId, platformId);
	} 
 
	@Override
	@RequestMapping(value = "/insertCommodityProductUnitWarehouseStockWithTx", method = { RequestMethod.POST })
	@ResponseBody
	public Long insertCommodityProductUnitWarehouseStockWithTx(@RequestBody CommodityProductUnitWarehouseStockDTO dto) {
		return commodityProductUnitWarehouseStockWriteService.insertCommodityProductUnitWarehouseStockWithTx(dto);
	} 
 
	@Override
	@RequestMapping(value = "/updateCommodityProductUnitWarehouseStockWithTx", method = { RequestMethod.POST })
	@ResponseBody
	public void updateCommodityProductUnitWarehouseStockWithTx(@RequestParam("puIds") List<String> puIds, @RequestParam("nowRealStockNum") Long nowRealStockNum, 
			@RequestParam("nowRealFrozenStockNum") Long nowRealFrozenStockNum) {
		commodityProductUnitWarehouseStockWriteService.updateCommodityProductUnitWarehouseStockWithTx(com.egeo.utils.StringUtils.stringsToLongs(puIds), nowRealStockNum, nowRealFrozenStockNum);
	} 
 
	@Override
	@RequestMapping(value = "/updateJdPUStock", method = { RequestMethod.POST })
	@ResponseBody
	public void updateJdPUStock() {
		commodityProductUnitWarehouseStockWriteService.updateJdPUStock();
	} 
 
	@Override
	@RequestMapping(value = "/unFreeseOrderStock", method = { RequestMethod.POST })
	@ResponseBody
	public int unFreeseOrderStock(@RequestParam("puId") Long puId, @RequestParam("puCount") Integer puCount, @RequestParam("orderCode") String orderCode, @RequestParam("type") Integer type,
			@RequestParam("productUnitSerialNumber") String productUnitSerialNumber,@RequestParam("puName")  String puName, @RequestParam("userId") Long userId, @RequestParam("userName") String userName, @RequestParam("ip") String ip,@RequestParam("mac")  String mac) {
		return commodityProductUnitWarehouseStockWriteService.unFreeseOrderStock(puId, puCount, orderCode, type, productUnitSerialNumber, puName, userId, userName, ip, mac);
	} 
 
	@Override
	@RequestMapping(value = "/syncStockWithTx", method = { RequestMethod.POST })
	@ResponseBody
	public void syncStockWithTx(@RequestParam("puId") Long puId,@RequestParam("quantity")  Long quantity,@RequestParam("puSerialNumber")  String puSerialNumber, @RequestParam("puName") String puName) {
		commodityProductUnitWarehouseStockWriteService.syncStockWithTx(puId, quantity, puSerialNumber, puName);
	}

	@Override
	@RequestMapping(value = "/recoverOrderStockBatch", method = { RequestMethod.POST })
	@ResponseBody
	public void recoverOrderStockBatch(@RequestBody RecoverOrderStockBatchDTO dto) {
		// TODO Auto-generated method stub
		commodityProductUnitWarehouseStockWriteService.recoverOrderStockBatch(dto.getPuId(), dto.getPuCount(), dto.getOrderCode(), dto.getStatus(), dto.getUserId(), dto.getUserName(), dto.getIp(), dto.getMac(), dto.getPuIdList(), dto.getCommodityProductUnitDTOs());
	}

	@Override
	@RequestMapping(value = "/recoverOrderStock", method = { RequestMethod.POST })
	@ResponseBody
	public int recoverOrderStock(@RequestParam("puId") Long puId, @RequestParam("puCount") Integer puCount, @RequestParam("orderCode") String orderCode, 
			@RequestParam("type") Integer type,@RequestParam("productUnitSerialNumber") String productUnitSerialNumber,@RequestParam("puName")  String puName,
			@RequestParam("userId") Long userId,@RequestParam("userName")  String userName, @RequestParam("ip") String ip,@RequestParam("mac")  String mac) {
		// TODO Auto-generated method stub
		return commodityProductUnitWarehouseStockWriteService.recoverOrderStock(puId, puCount, orderCode, type, productUnitSerialNumber, puName, userId, userName, ip, mac);
	}

	@Override
	@RequestMapping(value = "/unFreeseOrderStockBatch", method = { RequestMethod.POST })
	@ResponseBody
	public int unFreeseOrderStockBatch(@RequestBody UnFreeseOrderStockBatchDTO dto) {
		// TODO Auto-generated method stub
		return commodityProductUnitWarehouseStockWriteService.unFreeseOrderStockBatch(dto.getPuId(), dto.getPuCount(), dto.getOrderCode(), dto.getType(), dto.getUserId(), dto.getUserName(), dto.getIp(), dto.getMac(), dto.getPuIds(), dto.getCommodityProductUnitDTOs());
	}

	@Override
	@RequestMapping(value = "/freezeStockBatchWithTx", method = { RequestMethod.POST })
	@ResponseBody
	public int freezeStockBatchWithTx(@RequestBody FreeseOrderStockBatchDTO dto) {
		// TODO Auto-generated method stub
		return commodityProductUnitWarehouseStockWriteService.freezeStockBatchWithTx(dto.getPuId(), dto.getPuCount(), dto.getOrderCode(), dto.getType(), dto.getUserId(), dto.getUserName(), dto.getIp(), dto.getMac(), dto.getPuIds(), dto.getCommodityProductUnitDTOs());
	}

	@Override
	@RequestMapping(value = "/freezeStockWithTx", method = { RequestMethod.POST })
	@ResponseBody
	public int freezeStockWithTx(@RequestBody FreezeStockWithTxDTO dto) {
		// TODO Auto-generated method stub
		return commodityProductUnitWarehouseStockWriteService.freezeStockWithTx(dto.getPuId(), dto.getPuCount(), dto.getOrderCode(), dto.getType(), dto.getProductUnitSerialNumber(), dto.getPuName(), dto.getUserId(), dto.getUserName(), dto.getIp(), dto.getMac());
	}

	@Override
	@RequestMapping(value = { "/merchandiseStockWithTx" }, method = { RequestMethod.POST }) 
	@ResponseBody
	public JsonResult<Integer> merchandiseStockWithTx(@RequestBody CommodityProductUnitWarehouseStockVO vo
			) {
		// TODO Auto-generated method stub
		logger.info("action进货：puId:" + vo.getCommodityProductUnitId() + ",数量:"+ vo.getRealStockNum());
		CommodityProductUnitWarehouseStockDTO dto = CommodityProductUnitWarehouseStockConverter.toDTO(vo);
		CacheUser userCache = RuntimeContext.cacheUser();		
		Long userId = userCache.getId();
		String userName = userCache.getName();
		int rt = commodityProductUnitWarehouseStockManage.merchandiseStockWithTx(dto,userId,userName,vo.getIp(),vo.getMac());	
		return JsonResult.success(rt);					 
	
	} 
 
}