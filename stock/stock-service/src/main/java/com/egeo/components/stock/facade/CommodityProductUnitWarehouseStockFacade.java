package com.egeo.components.stock.facade;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.egeo.components.product.client.CommodityProductUnitClient;
import com.egeo.components.product.dto.CommodityProductUnitDTO;
import com.egeo.components.promotion.client.ECardClient;
import com.egeo.components.promotion.dto.ECardDTO;
import com.egeo.components.stock.client.ContactGroupPuStockClient;
import com.egeo.components.stock.controller.client.ContactGroupPuStockController;
import com.egeo.components.stock.dto.CommodityProductUnitWarehouseStockDTO;
import com.egeo.components.stock.dto.MerchantProductVirtualStockDTO;
import com.egeo.components.stock.service.CommodityProductUnitWarehouseStockService;
import com.egeo.components.stock.service.read.CommodityProductUnitWarehouseStockReadService;
import com.egeo.components.stock.service.read.ContactGroupPuStockReadService;
import com.egeo.components.stock.service.read.MerchantProductVirtualStockReadService;
import com.egeo.components.stock.service.write.CommodityProductUnitWarehouseStockWriteService;
import com.egeo.exception.BusinessException;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;

@Component
public class CommodityProductUnitWarehouseStockFacade {

	private static final Logger logger = LoggerFactory.getLogger(CommodityProductUnitWarehouseStockFacade.class);
	
	@Resource
	private CommodityProductUnitWarehouseStockReadService commodityProductUnitWarehouseStockReadService;

	@Resource
	private CommodityProductUnitWarehouseStockWriteService commodityProductUnitWarehouseStockWriteService;

	@Resource
	private CommodityProductUnitClient commodityProductUnitReadService;

	@Autowired
	private CommodityProductUnitWarehouseStockService commodityProductUnitWarehouseStockService;
	
	@Autowired
	private ContactGroupPuStockController contactGroupPuStockReadService;
	
	@Resource
	private MerchantProductVirtualStockReadService merchantProductVirtualStockReadService;
	
	@Autowired
	private ECardClient eCardReadService;

	public CommodityProductUnitWarehouseStockDTO findCommodityProductUnitWarehouseStockById(
			CommodityProductUnitWarehouseStockDTO dto) {

		return commodityProductUnitWarehouseStockReadService.findCommodityProductUnitWarehouseStockById(dto);
	}

	public PageResult<CommodityProductUnitWarehouseStockDTO> findCommodityProductUnitWarehouseStockOfPage(
			CommodityProductUnitWarehouseStockDTO dto, Pagination page) {

		return commodityProductUnitWarehouseStockReadService.findCommodityProductUnitWarehouseStockOfPage(dto, page);

	}

	public List<CommodityProductUnitWarehouseStockDTO> findCommodityProductUnitWarehouseStockAll(
			CommodityProductUnitWarehouseStockDTO dto) {

		return commodityProductUnitWarehouseStockReadService.findCommodityProductUnitWarehouseStockAll(dto);

	}

	public Long insertCommodityProductUnitWarehouseStockWithTx(CommodityProductUnitWarehouseStockDTO dto) {

		return commodityProductUnitWarehouseStockWriteService.insertCommodityProductUnitWarehouseStockWithTx(dto);
	}

	public int updateCommodityProductUnitWarehouseStockWithTx(CommodityProductUnitWarehouseStockDTO dto) {

		return commodityProductUnitWarehouseStockWriteService.updateCommodityProductUnitWarehouseStockWithTx(dto);
	}

	public int deleteCommodityProductUnitWarehouseStockWithTx(CommodityProductUnitWarehouseStockDTO dto) {

		return commodityProductUnitWarehouseStockWriteService.deleteCommodityProductUnitWarehouseStockWithTx(dto);

	}

	/**
	 * 进货
	 * 
	 * @param vo
	 * @param req
	 * @return
	 */
	public int merchandiseStockWithTx(CommodityProductUnitWarehouseStockDTO dto, Long userId, String userName,
			String ip, String mac) {
		// 根据puid查询pu信息
		CommodityProductUnitDTO commodityProductUnitDTO = new CommodityProductUnitDTO();
		commodityProductUnitDTO.setId(dto.getCommodityProductUnitId());
		CommodityProductUnitDTO commodityProductUnitDTO2 = commodityProductUnitReadService
				.findCommodityProductUnitById(commodityProductUnitDTO);
		dto.setCommodityProductUnitName(commodityProductUnitDTO2.getName());
		dto.setProductUnitSerialNumber(commodityProductUnitDTO2.getProductUnitSerialNumber());
		// 修改库存类型：1、进货 2、出货
		dto.setType(1);
		dto.setUserId(userId);
		dto.setUserName(userName);
		dto.setIp(ip);
		dto.setMac(mac);
		// return
		// commodityProductUnitWarehouseStockService.updateCommodityProductUnitWarehouseStockDTO(dto);//此队列模式有时存在bug后期尝试使用
		//根据puId查询是否是电子卡券类商品
		boolean b = commodityProductUnitReadService.queryIsUnit(dto.getCommodityProductUnitId());
		if(b){
			//根据skuId查询卡密未分配库存数
			ECardDTO cardDTO = new ECardDTO();
			cardDTO.setSkuId(commodityProductUnitDTO2.getSkuId());
			cardDTO.setIsValid(1);
			cardDTO.setIsAllocation(0);
			List<ECardDTO> list = eCardReadService.findECardAll(cardDTO);
			// 根据skuId查询puid集合
			List<Long> puIds = com.egeo.utils.StringUtils.stringsToLongs(commodityProductUnitReadService.puIdsBySkuId(commodityProductUnitDTO2.getSkuId()));
			Long realStockNum = 0L;
			if(EmptyUtil.isNotEmpty(puIds)){
				//根据skuId查询剩余库存数量
				realStockNum = commodityProductUnitWarehouseStockReadService.residueStockNumByPuId(puIds);
			}
			
			if(realStockNum.longValue() + dto.getRealStockNum().longValue() > list.size()){
				//可分配 - 剩余 - 冻结
				MerchantProductVirtualStockDTO merchantProductVirtualStockDTO = findMerchantProductWarehouseStockAll(commodityProductUnitDTO2.getSkuId());
				long n = list.size() - merchantProductVirtualStockDTO.getRealStockNums() - merchantProductVirtualStockDTO.getRealFrozenStockNum();
				StringBuffer message = new StringBuffer("同 sku 的 pu 在线库存总量不能超过可分配库存量，剩余可分配库存量：") ;
				if(n < 0) {
					message.append(n + " . 请进行出货以防止超卖.");
				}else {
					message.append(n + " ");
				}
				throw new BusinessException(message.toString());
			}
		}
		List<Long> puIds = com.egeo.utils.StringUtils.stringsToLongs(contactGroupPuStockReadService.findPuIdListByPuId(dto.getCommodityProductUnitId()));
		
		logger.info("(进货)当前 pu  共用在线库存 pu :{} ,puIds :{}" ,dto.getCommodityProductUnitId(),puIds);
		if(puIds != null && puIds.size() > 0) {
			List<CommodityProductUnitDTO> list = commodityProductUnitReadService.findByPUIds(com.egeo.utils.StringUtils.longsToStrings(puIds));
			return commodityProductUnitWarehouseStockWriteService.merchandiseStockBatchWithTx(dto, userId, userName, ip, mac,  puIds,list);
		}
		
		
		return commodityProductUnitWarehouseStockWriteService.merchandiseStockWithTx(dto, dto.getUserId(),
				dto.getUserName(), dto.getIp(), dto.getMac(),commodityProductUnitDTO2.getProductUnitSerialNumber(),commodityProductUnitDTO2.getName());
	}
	
	/**
	 * 根据skuid查询sku库存信息
	 * @param dto
	 * @return
	 */
	public MerchantProductVirtualStockDTO findMerchantProductWarehouseStockAll(Long skuId){
		CommodityProductUnitDTO commodityProductUnitDTO = new CommodityProductUnitDTO();
		commodityProductUnitDTO.setSkuId(skuId);
		List<CommodityProductUnitDTO> commodityProductUnitList = commodityProductUnitReadService.findCommodityProductUnitAll(commodityProductUnitDTO);
		//拼接puid集合
		List<Long> commodityProductUnitIds = new ArrayList<>();
		for (CommodityProductUnitDTO commodityProductUnitDTO2 : commodityProductUnitList) {
			commodityProductUnitIds.add(commodityProductUnitDTO2.getId());
		}
		MerchantProductVirtualStockDTO merchantProductVirtualStockDTO = merchantProductVirtualStockReadService.merchantProductVirtualStockBySkuId(skuId);
		if(EmptyUtil.isEmpty(merchantProductVirtualStockDTO)){
			throw new BusinessException("sku库存信息异常，skuId："+skuId);
		}
		List<CommodityProductUnitWarehouseStockDTO> commodityProductUnitWarehouseStockList = null;
		if(EmptyUtil.isNotEmpty(commodityProductUnitIds)){
			//根据skuid查询pu库存
			commodityProductUnitWarehouseStockList = commodityProductUnitWarehouseStockReadService.findByPUId(commodityProductUnitIds);
		}
		
		//拼接所有pu真实库存之和
		Long realStockNums = 0L;
		if(EmptyUtil.isNotEmpty(commodityProductUnitWarehouseStockList)){
			for (CommodityProductUnitWarehouseStockDTO commodityProductUnitWarehouseStockPO : commodityProductUnitWarehouseStockList) {
				realStockNums = realStockNums + (commodityProductUnitWarehouseStockPO.getRealStockNum()-commodityProductUnitWarehouseStockPO.getRealFrozenStockNum());
			}
		}
		
		merchantProductVirtualStockDTO.setRealStockNums(realStockNums);
		return merchantProductVirtualStockDTO;
	}

	/**
	 * 出货
	 * 
	 * @param vo
	 * @param req
	 * @return
	 */
	public int deliverStockWithTx(CommodityProductUnitWarehouseStockDTO dto, Long userId, String userName, String ip,
			String mac) {
		// 根据puid查询pu信息
		CommodityProductUnitDTO commodityProductUnitDTO = new CommodityProductUnitDTO();
		commodityProductUnitDTO.setId(dto.getCommodityProductUnitId());
		CommodityProductUnitDTO commodityProductUnitDTO2 = commodityProductUnitReadService
				.findCommodityProductUnitById(commodityProductUnitDTO);
		dto.setCommodityProductUnitName(commodityProductUnitDTO2.getName());
		dto.setProductUnitSerialNumber(commodityProductUnitDTO2.getProductUnitSerialNumber());
		// 修改库存类型：1、进货 2、出货
		dto.setType(2);
		dto.setUserId(userId);
		dto.setUserName(userName);
		dto.setIp(ip);
		dto.setMac(mac);
		// return
		// commodityProductUnitWarehouseStockService.updateCommodityProductUnitWarehouseStockDTO(dto);//此队列模式有时存在bug后期尝试使用
		
		List<Long> puIds = com.egeo.utils.StringUtils.stringsToLongs(contactGroupPuStockReadService.findPuIdListByPuId(dto.getCommodityProductUnitId()));
		logger.info("(出货)当前 pu  共用在线库存 pu :{} ,puIds :{}" ,dto.getCommodityProductUnitId(),puIds);
		if(puIds != null && puIds.size() > 0) {
			List<CommodityProductUnitDTO> list = commodityProductUnitReadService.findByPUIds(com.egeo.utils.StringUtils.longsToStrings(puIds));
			return commodityProductUnitWarehouseStockWriteService.deliverStockBatchWithTx(dto, userId, userName, ip, mac,  puIds,list);
		}
		return commodityProductUnitWarehouseStockWriteService.deliverStockWithTx(dto, dto.getUserId(),
				dto.getUserName(), dto.getIp(), dto.getMac(),commodityProductUnitDTO2.getProductUnitSerialNumber(),commodityProductUnitDTO2.getName());
	}

	/**
	 * 根据puid查询pu库存信息
	 * 
	 * @param id
	 * @return
	 */
	public CommodityProductUnitWarehouseStockDTO findByProductUnitId(Long productUnitId) {
		return commodityProductUnitWarehouseStockService.findByProductUnitId(productUnitId);
	}

}
