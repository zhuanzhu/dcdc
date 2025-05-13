package com.egeo.components.order.service;

import java.math.BigDecimal;
import java.util.List;

import com.egeo.components.order.dto.SoDTO;
import com.egeo.components.order.dto.SoItemDTO;
import com.egeo.components.order.vo.BuyCardPayReqVO;
import com.egeo.components.order.vo.BuyCardPayRespVO;
import com.egeo.components.order.vo.RefundProductRequestVO;
import com.egeo.dto.HttpServletRequestDTO;

public interface SoService  {

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
	public List<String> soRefundWithTx(SoDTO soDTO, String reason, Long operatorId,
			String soRefundCodeByFubi, String soRefundCodeByCash,String soRefundCodeByJiDian,String soRefundCodeByBuyCard, Boolean isCancel,String thirdRefundCode, HttpServletRequestDTO req);

	/**
	 * 取消订单,自动退款
	 * @param order
	 * @param items
	 * @param userId
	 * @param soRefundCodeByFubi
	 * @param soRefundCodeByCash
	 * @param httpServletRequestDTO
	 */
	public void cancelAndRefundOrderWithTx(String userName,SoDTO order, List<SoItemDTO> items, Long userId, String soRefundCodeByFubi,
			String soRefundCodeByCash, HttpServletRequestDTO req);




	/**
	 * 调用支付宝/微信现金退款接口
	 * @param userId 用户id
	 * @param order 订单
	 * @param refundCashAmount 退款金额
	 * @param items 退款商品明细
	 * @param soRefundCodeByCash 退款单号
	 * @return
	 */
	boolean refundCash(Long userId,SoDTO order, BigDecimal refundCashAmount, List<SoItemDTO> items, String soRefundCodeByCash);

	/**
	 * 支付成功时,处理第三方订单
	 * @param id
	 * @param orderCode
	 * @return
	 */
	public Boolean dealThirdpartyOrderWithTx(String userName,Long userId,Long orderId, String orderCode);

    boolean exchangeOrderWithTx(Long orderId, String orderCode, String name, Long userId);


	int jdOrderConfirm(String name, Long userDTOId, Long id, Long jdOrderId, Long soChildId, Long soId, String orderCode);

	/**
	 * 退款
	 * @param vo
	 */
	public void refundOrderProductWithTx(RefundProductRequestVO vo);

	/**
	 * 取消订单,自动退款
	 * @param order
	 * @param items
	 * @param userId
	 * @param soRefundCodeByFubi
	 * @param soRefundCodeByCash
	 */
	public void cancelAndRefundOrderWithTx(String userName,SoDTO order, List<SoItemDTO> items, Long userId, String soRefundCodeByFubi,
										   String soRefundCodeByCash,String soRefundCodeByJiDian,String soRefundCodeByBuyCard, HttpServletRequestDTO req);
}
