package com.egeo.components.order.client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name = "service-order-fgj",contextId="CardClient")
public interface CardClient {
	/**
	 * 分配虚拟卡及扣除库存
	 * @param orderId 订单id
	 * @param orderCode 订单编号
	 * @param userId 用户id
	 * @param userName 用户名称
	 * @param ip
	 * @param mac
	 * @param companyType 公司类型 0:正式公司 1:测试公司 2:竞品公司
	 * @return
	 */
	@RequestMapping(value = { "/client/order/card/allocationCardAndTakeStock" }, method = { RequestMethod.POST }) 
	public Boolean allocationCardAndTakeStock(@RequestParam("orderId") Long orderId, @RequestParam("orderCode") String orderCode, @RequestParam("userId") Long userId, @RequestParam("userName") String userName,@RequestParam("ip")  String ip,@RequestParam("mac")  String mac,@RequestParam("companyType") Integer companyType);
 
 
}