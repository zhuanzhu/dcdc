package com.egeo.components.order.client;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.egeo.components.order.dto.SoItemDTO;


@FeignClient(name = "service-order-fgj",contextId="SoItemClient")
public interface SoItemClient {

	@RequestMapping(value = { "/client/order/soItem/querySoItemListBySoId" }, method = { RequestMethod.POST })
	public List<SoItemDTO> querySoItemListBySoId(Long id);


	@RequestMapping(value = { "/client/order/soItem/findPuIdBySoId" }, method = { RequestMethod.POST })
	public List<String> findPuIdBySoId(Long id);


	@RequestMapping(value = { "/client/order/soItem/querySoItemBySoId" }, method = { RequestMethod.POST })
	public List<SoItemDTO> querySoItemBySoId(Long soId);


	@RequestMapping(value = { "/client/order/soItem/findAll" }, method = { RequestMethod.POST })
	List<SoItemDTO> findAll(SoItemDTO soItemDTO);


	@RequestMapping(value = { "/client/order/soItem/findSoItemByPuIds" }, method = { RequestMethod.POST })
	public List<SoItemDTO> findSoItemByPuIds(@RequestParam("puIds") List<String> puIds, @RequestParam("status") Integer status);

	@RequestMapping(value = { "/client/order/soItem/findSoItemByPuId" }, method = { RequestMethod.POST })
	public List<SoItemDTO> findSoItemByPuId(@RequestParam("puId") Long puId, @RequestParam("status") Integer status);
	/**
	 * 根据商品id列表查询相关的订单项列表
	 * @param merchantIdList
	 * @param platformId
	 * @return
	 */
	@RequestMapping(value = { "/client/order/soItem/querySoItemListByMerchantIds" }, method = { RequestMethod.POST })
	public List<SoItemDTO> querySoItemListByMerchantIds(@RequestParam("merchantIdList") List<String> merchantIdList, @RequestParam("platformId") Long platformId);

	@RequestMapping(value = { "/client/order/soItem/findProductIdsSoChild" }, method = { RequestMethod.POST })
	public List<String> findProductIdsSoChild(Long soChildId);

	@RequestMapping(value = { "/client/order/soItem/findSoItemsBySoChild" }, method = { RequestMethod.POST })
	public List<SoItemDTO> findSoItemsBySoChild(Long soChildId);
}

