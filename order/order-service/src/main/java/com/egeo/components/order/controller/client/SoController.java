package com.egeo.components.order.controller.client;

import java.math.BigDecimal;
import java.util.List;

import com.egeo.components.order.business.SoManage;
import com.egeo.components.order.business.SoRefundNewManage;
import com.egeo.components.order.dto.*;
import com.egeo.components.order.service.read.QmOrderReadService;
import com.egeo.components.order.vo.BuyCardPayReqVO;
import com.egeo.components.order.vo.BuyCardPayRespVO;
import com.egeo.components.order.vo.RefundProductRequestVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.order.client.SoClient;
import com.egeo.components.order.service.SoService;
import com.egeo.components.order.service.read.SoReadService;
import com.egeo.components.order.service.write.SoWriteService;
import com.egeo.web.JsonResult;

import javax.annotation.Resource;

@Controller
@RequestMapping("/client/order/so")
public class SoController implements SoClient{

	@Autowired
	private SoReadService soReadService;
	@Autowired
	private SoWriteService soWriteService;
	@Autowired
	private SoService soService;
	@Resource
	private QmOrderReadService qmOrderReadService;
	@Resource
	private SoRefundNewManage soRefundNewManage;

	@Resource(name = "so")
	private SoManage soManage;

	@Override
	@RequestMapping(value = "/updateOrderPaymentInfo", method = { RequestMethod.POST })
	@ResponseBody
	public int updateOrderPaymentInfo(@RequestBody SoDTO orderUpdateCondition) {
		return soWriteService.updateOrderPaymentInfo(orderUpdateCondition);
	}

	@Override
	@RequestMapping(value = "/changeOrderStatusByOrderCode", method = { RequestMethod.POST })
	@ResponseBody
	public boolean changeOrderStatusByOrderCode(@RequestParam("orderCode") String orderCode, @RequestParam("status") Integer status,@RequestParam("confirmStatus") Integer confirmStatus,
			@RequestParam("payStatus") Integer payStatus,@RequestParam("deliveryStatus") Integer deliveryStatus) {
		return soWriteService.changeOrderStatusByOrderCode(orderCode, confirmStatus, confirmStatus, payStatus, deliveryStatus);
	}

	@Override
	@RequestMapping(value = "/updateSoPayByFuBi", method = { RequestMethod.POST })
	@ResponseBody
	public void updateSoPayByFuBi(@RequestParam("orderId") Long orderId,@RequestParam("soFreezeBalance")  String soFreezeBalanceStr) {
		BigDecimal soFreezeBalance = new BigDecimal(soFreezeBalanceStr);
		soWriteService.updateSoPayByFuBi(orderId, soFreezeBalance);
	}

	@Override
	@RequestMapping(value = "/updateOrderByOrderId", method = { RequestMethod.POST })
	@ResponseBody
	public JsonResult<String> updateOrderByOrderId(@RequestBody SoDTO dto) {
		return soWriteService.updateOrderByOrderId(dto);
	}

	@Override
	@RequestMapping(value = "/querySoByOrderCode", method = { RequestMethod.POST })
	@ResponseBody
	public SoDTO querySoByOrderCode(@RequestBody String orderCode) {
		return soReadService.querySoByOrderCode(orderCode);
	}

	@Override
	@RequestMapping(value = "/findSoById", method = { RequestMethod.POST })
	@ResponseBody
	public SoDTO findSoById(@RequestBody  Long orderId) {
		return soReadService.findSoById(orderId);
	}

	@Override
	@RequestMapping(value = "/querySoById", method = { RequestMethod.POST })
	@ResponseBody
	public SoDTO querySoById(@RequestBody(required=false)  Long orderId) {
		return soReadService.querySoById(orderId);
	}

	@Override
	@RequestMapping(value = "/findSoByCode", method = { RequestMethod.POST })
	@ResponseBody
	public List<SoDTO> findSoByCode(@RequestBody(required=false) String orderCode) {
		return soReadService.findSoByCode(orderCode);
	}

	@Override
	@RequestMapping(value = "/findCurrentMonthOrder", method = { RequestMethod.POST })
	@ResponseBody
	public Integer findCurrentMonthOrder(@RequestParam("storeId") Long storeId,@RequestParam("platformId") Long platformId) {
		return soReadService.findCurrentMonthOrder(storeId,platformId);
	}

	@Override
	@RequestMapping(value = "/findCurrentDayOrder", method = { RequestMethod.POST })
	@ResponseBody
	public Integer findCurrentDayOrder(@RequestParam("storeId") Long storeId,@RequestParam("platformId")  Long platformId) {
		return soReadService.findCurrentDayOrder(storeId, platformId);
	}

	@Override
	@RequestMapping(value = "/findNoSignOrderSumByStatus", method = { RequestMethod.POST })
	@ResponseBody
	public Integer findNoSignOrderSumByStatus(@RequestParam("orderConfirmStatus") Integer orderConfirmStatus,@RequestParam("orderPayStatus") Integer orderPayStatus, @RequestParam("storeId") Long storeId,
			@RequestParam("platformId") Long platformId) {
		return soReadService.findNoSignOrderSumByStatus(orderConfirmStatus, orderPayStatus, storeId, platformId);
	}

	@Override
	@RequestMapping(value = "/findCurrentMonthOrderAmount", method = { RequestMethod.POST })
	@ResponseBody
	public BigDecimal findCurrentMonthOrderAmount(@RequestParam("storeId") Long storeId,@RequestParam("platformId") Long platformId) {
		return soReadService.findCurrentMonthOrderAmount(storeId, platformId);
	}

	@Override
	@RequestMapping(value = "/findcurrentDayOrderAmount", method = { RequestMethod.POST })
	@ResponseBody
	public BigDecimal findcurrentDayOrderAmount(@RequestParam("storeId") Long storeId,@RequestParam("platformId") Long platformId) {
		return soReadService.findcurrentDayOrderAmount(storeId, platformId);
	}

	@Override
	@RequestMapping(value = "/dealThirdpartyOrderWithTx", method = { RequestMethod.POST })
	@ResponseBody
	public Boolean dealThirdpartyOrderWithTx(@RequestParam("userName") String userName,@RequestParam("userId") Long userId,@RequestParam("orderId") Long orderId,@RequestParam("orderCode")  String orderCode) {
		// TODO Auto-generated method stub
		return soService.dealThirdpartyOrderWithTx(userName, userId, orderId, orderCode);
	}

	@Override
	@RequestMapping(value = "/cancelAndRefundOrderWithTx", method = { RequestMethod.POST })
	@ResponseBody
	public void cancelAndRefundOrderWithTx(@RequestBody CancelAndRefundOrderWithTxDTO dto) {
		// TODO Auto-generated method stub
		try {
			soService.cancelAndRefundOrderWithTx(dto.getUserName(), dto.getOrder(), dto.getItems(), dto.getUserId(), dto.getSoRefundCodeByFubi(), dto.getSoRefundCodeByCash(),dto.getSoRefundCodeByJiDian(), dto.getSoRefundCodeByBuyCard(), dto.getReq());
		}catch (Exception e){
			System.out.println("cancelAndRefundOrderWithTx:"+dto.getOrder().getOrderCode());
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	@RequestMapping(value = "/exchangeOrderWithTx", method = { RequestMethod.POST })
	@ResponseBody
	public boolean exchangeOrderWithTx(@RequestParam("orderId") Long orderId, @RequestParam("orderCode") String orderCode, @RequestParam("name") String name, @RequestParam("userId") Long userId) {
		// TODO Auto-generated method stub
		return soService.exchangeOrderWithTx(orderId, orderCode, name, userId);
	}

	@Override
	@RequestMapping(value = "/soRefundWithTx", method = { RequestMethod.POST })
	@ResponseBody
	public List<String> soRefundWithTx(@RequestBody SoRefundWithTxDTO dto) {
		// TODO Auto-generated method stub
		return soService.soRefundWithTx(dto.getSoDTO(), dto.getReason(), dto.getOperatorId(), dto.getSoRefundCodeByFubi(), dto.getSoRefundCodeByCash(),dto.getSoRefundCodeByJiDian(), dto.getSoRefundCodeByBuyCard(), dto.getIsCancel(), dto.getThirdRefundCode(),dto.getReq());
	}



	@Override
	@RequestMapping(value = "/refundCashWithTx", method = { RequestMethod.POST })
	@ResponseBody
	public boolean refundCashWithTx(@RequestBody RefundCashWithTxDTO dto) {
		return soService.refundCash(dto.getUserId(),dto.getOrder(),dto.getRefundCashAmount(),dto.getItems(), dto.getSoRefundCodeByCashCode());
	}

	@Override
	@RequestMapping(value = { "/querySoByCreateMonth" }, method = { RequestMethod.POST })
	@ResponseBody
	public List<SoDetailDTO> querySoByCreateMonth(String month) {
		// TODO Auto-generated method stub
		return soReadService.findOrderByMonth(month);
	}

	@Override
	@RequestMapping(value = "/queryQmOrderBySoId", method = { RequestMethod.POST })
	@ResponseBody
	public QmOrderDTO queryQmOrderBySoId(@RequestBody(required=false)  Long orderId) {
		return qmOrderReadService.findBySoId(orderId);
	}


	@Override
	@RequestMapping(value = "/productRefundOrderWithTx", method = { RequestMethod.POST })
	@ResponseBody
	public void productRefundOrderWithTx(@RequestBody CancelAndRefundOrderExtendsWithTxDTO dto) {
		// TODO Auto-generated method stub
		try {
			soRefundNewManage.productRefundOrderWithTx(dto);
		}catch (Exception e){
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	@RequestMapping(value = "/buyCardPay", method = { RequestMethod.POST })
	@ResponseBody
	public BuyCardPayRespVO buyCardPay(@RequestBody BuyCardPayReqVO vo){
		return soManage.buyCardPay(vo);
	}
}
