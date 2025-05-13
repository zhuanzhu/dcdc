package com.egeo.components.order.service.write;

import java.math.BigDecimal;
import java.util.List;

import com.egeo.components.order.dto.NewSoOrderDTO;
import com.egeo.components.order.dto.SoDTO;
import com.egeo.web.JsonResult;

public interface SoWriteService {

	/**
	 * 创建订单
	 * @param nsodto
	 * @return
	 */
	Long createOrder(NewSoOrderDTO nsodto);

	/**
	 * 改变订单各种状态
	 * @param orderCode
	 * @param status 总状态
	 * @param confirmStatus 确认状态
	 * @param payStatus 支付状态
	 * @param deliveryStatus 物流状态
	 * @return
	 */
	boolean changeOrderStatusByOrderCode(String orderCode, Integer status,Integer confirmStatus,
			Integer payStatus,Integer deliveryStatus);
	
	/**
	 * 改变订单各种状态
	 *
     * @param orderCode
     * @param id
     * @param status 总状态
     * @param confirmStatus 确认状态
     * @param payStatus 支付状态
     * @param deliveryStatus 物流状态
     * @return
	 */
	boolean changeOrderStatusByOrderId(String orderCode, Long id, Integer status, Integer confirmStatus,
                                       Integer payStatus, Integer deliveryStatus);
	
	/**
	 * 修改订单金额信息
	 * @param orderId
	 * @param orderAmount
	 * @param deliveryAmount
	 * @param payMoney
	 * @return
	 */
	int updateOrderMoneyInfo(Long orderId, BigDecimal orderAmount, BigDecimal deliveryAmount, BigDecimal payMoney);

	/**
	 * 修改订单收货信息
	 * @param orderId
	 * @param receiverName
	 * @param receiverPhone
	 * @param receiverAddress
	 * @return
	 */
	int updateDeliveryInfo(Long orderId, String receiverName, String receiverPhone, String receiverAddress);

	/**
	 * 修改订单支付状态
	 * @param orderUpdateCondition
	 * @return
	 */
	int updateOrderPaymentInfo(SoDTO orderUpdateCondition);
	/**
	 * 删除订单
	 * @param soId
	 * @param platformId
	 * @return
	 */
	int deleteByOrderCode(String orderCode, Long userId);
	/**
	 * 确认订单
	 * @param orderId
	 * @param platformId
	 * @return
	 */
	String affirmOrderBySoId(Long orderId, Long platformId);
	/**
	 * 根据订单id修改订单
	 * @param soVO
	 * @param req
	 * @return
	 */
	JsonResult<String> updateOrderByOrderId(SoDTO dto);

	/**
	 * 母单分拣
	 * @param soIdArr
	 * @param platformId
	 * @return
	 */
	int SoSort(List<Long> soIdArr, Long platformId);

	/**
	 * 订单退款
	 * @param orderId
	 * @param refundFubiD
	 * @param refundCashD
	 * @param fullRefund 全额退款
	 */
	int orderRefund(SoDTO soDTO);
	/**
	 * 根据订单id保存订单自动完成时间
	 * @param soDTO2
	 * @return
	 */
	int saveOrderAutoCompleteDate(SoDTO soDTO2);

    void updateSoPayByFuBi(Long orderId, BigDecimal soFreezeBalance);

    public void repairOrderDataWithTx();

    int updateOrderCancelStatus(String orderCode);

    int updateOrderCancelOverStatusByOrderCode(String orderCode);

    void updateOrderWithTX(SoDTO sodto);
}
	