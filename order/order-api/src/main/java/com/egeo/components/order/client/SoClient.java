package com.egeo.components.order.client;
import java.math.BigDecimal;
import java.util.List;

import com.egeo.components.order.dto.*;
import com.egeo.components.order.vo.BuyCardPayReqVO;
import com.egeo.components.order.vo.BuyCardPayRespVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.egeo.web.JsonResult;


@FeignClient(name = "service-order-fgj",contextId="SoClient")
public interface SoClient {

	@RequestMapping(value = { "/client/order/so/updateOrderPaymentInfo" }, method = { RequestMethod.POST })
	public int updateOrderPaymentInfo(SoDTO orderUpdateCondition);


	@RequestMapping(value = { "/client/order/so/changeOrderStatusByOrderCode" }, method = { RequestMethod.POST })
	public boolean changeOrderStatusByOrderCode(@RequestParam("orderCode") String orderCode, @RequestParam("status") Integer status,@RequestParam("confirmStatus") Integer confirmStatus,
			@RequestParam("payStatus") Integer payStatus,@RequestParam("deliveryStatus") Integer deliveryStatus);


	@RequestMapping(value = { "/client/order/so/updateSoPayByFuBi" }, method = { RequestMethod.POST })
	public void updateSoPayByFuBi(@RequestParam("orderId") Long orderId,@RequestParam("soFreezeBalance")  String soFreezeBalance);


	@RequestMapping(value = { "/client/order/so/updateOrderByOrderId" }, method = { RequestMethod.POST })
	public JsonResult<String> updateOrderByOrderId(SoDTO dto);


	@RequestMapping(value = { "/client/order/so/querySoByOrderCode" }, method = { RequestMethod.POST })
	public SoDTO querySoByOrderCode(String orderCode);


	@RequestMapping(value = { "/client/order/so/findSoById" }, method = { RequestMethod.POST })
	public  SoDTO findSoById(Long orderId);


	@RequestMapping(value = { "/client/order/so/querySoById" }, method = { RequestMethod.POST })
	public SoDTO querySoById(Long orderId);

	@RequestMapping(value = { "/client/order/so/querySoByCreateMonth" }, method = { RequestMethod.POST })
	public List<SoDetailDTO> querySoByCreateMonth(String month);

	@RequestMapping(value = { "/client/order/so/findSoByCode" }, method = { RequestMethod.POST })
	public List<SoDTO> findSoByCode(String orderCode);


	@RequestMapping(value = { "/client/order/so/findCurrentMonthOrder" }, method = { RequestMethod.POST })
	public Integer findCurrentMonthOrder(@RequestParam("storeId") Long storeId,@RequestParam("platformId") Long platformId);


	@RequestMapping(value = { "/client/order/so/findCurrentDayOrder" }, method = { RequestMethod.POST })
	public Integer findCurrentDayOrder(@RequestParam("storeId") Long storeId,@RequestParam("platformId")  Long platformId);


	@RequestMapping(value = { "/client/order/so/findNoSignOrderSumByStatus" }, method = { RequestMethod.POST })
	public Integer findNoSignOrderSumByStatus(@RequestParam("orderConfirmStatus") Integer orderConfirmStatus,@RequestParam("orderPayStatus") Integer orderPayStatus, @RequestParam("storeId") Long storeId,
			@RequestParam("platformId") Long platformId);


	@RequestMapping(value = { "/client/order/so/findCurrentMonthOrderAmount" }, method = { RequestMethod.POST })
	public BigDecimal findCurrentMonthOrderAmount(@RequestParam("storeId") Long storeId,@RequestParam("platformId") Long platformId);


	@RequestMapping(value = { "/client/order/so/findcurrentDayOrderAmount" }, method = { RequestMethod.POST })
	public BigDecimal findcurrentDayOrderAmount(@RequestParam("storeId") Long storeId,@RequestParam("platformId") Long platformId);

	/**
	 * 支付成功时,处理第三方订单
	 * @param id
	 * @param orderCode
	 * @return
	 */
	@RequestMapping(value = { "/client/order/so/dealThirdpartyOrderWithTx" }, method = { RequestMethod.POST })
	public Boolean dealThirdpartyOrderWithTx(@RequestParam("userName") String userName,@RequestParam("userId") Long userId,@RequestParam("orderId") Long orderId,@RequestParam("orderCode")  String orderCode);


	/**
	 * 取消订单,自动退款
	 * @param order
	 * @param items
	 * @param userId
	 * @param soRefundCodeByFubi
	 * @param soRefundCodeByCash
	 * @param httpServletRequestDTO
	 */
	@RequestMapping(value = { "/client/order/so/cancelAndRefundOrderWithTx" }, method = { RequestMethod.POST })
	public void cancelAndRefundOrderWithTx(CancelAndRefundOrderWithTxDTO dto);


	@RequestMapping(value = { "/client/order/so/exchangeOrderWithTx" }, method = { RequestMethod.POST })
	public boolean exchangeOrderWithTx(@RequestParam("orderId") Long orderId, @RequestParam("orderCode") String orderCode, @RequestParam("name") String name, @RequestParam("userId") Long userId);

	@RequestMapping(value = { "/client/order/so/refundCashWithTx" }, method = { RequestMethod.POST })
	boolean refundCashWithTx(RefundCashWithTxDTO dto);
	/**
	 * 订单退款
	 * @param soDTO
	 * @param reason
	 * @param operatorId
	 * @param soRefundCodeByFubi
	 * @param soRefundCodeByCash
	 * @param isCancel
	 * @param req
	 */
	@RequestMapping(value = { "/client/order/so/soRefundWithTx" }, method = { RequestMethod.POST })
	List<String> soRefundWithTx(SoRefundWithTxDTO dto);

	@RequestMapping(value = { "/client/order/so/queryQmOrderBySoId" }, method = { RequestMethod.POST })
	QmOrderDTO queryQmOrderBySoId(Long soId);

	@RequestMapping(value = { "/client/order/so/productRefundOrderWithTx" }, method = { RequestMethod.POST })
	void productRefundOrderWithTx(CancelAndRefundOrderExtendsWithTxDTO dto);


	@RequestMapping(value = { "/client/order/so/buyCardPay" }, method = { RequestMethod.POST })
	BuyCardPayRespVO buyCardPay(BuyCardPayReqVO vo);
}
