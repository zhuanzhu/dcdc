package com.egeo.components.order.manage.write;

import java.math.BigDecimal;
import java.util.List;

import com.egeo.components.order.condition.NewSoItemCondition;
import com.egeo.components.order.po.LimitRuleRecordPO;
import com.egeo.components.order.po.SoChildPO;
import com.egeo.components.order.po.SoDevicePO;
import com.egeo.components.order.po.SoPO;
import com.egeo.components.order.po.SoThirdpartyPO;
import com.egeo.web.JsonResult;



public interface SoWriteManage {

	Long createOrderWithTx(SoPO soPo, List<NewSoItemCondition> newSoItemConditions, List<Long> cartItemIds, List<SoChildPO> soChildPOList, SoDevicePO soDevicePO,
						   List<LimitRuleRecordPO> limitRuleRecordList, SoThirdpartyPO soThirdpartyPO, Long companyAllId);

	Long createOrderNewWithTx(SoPO soPo, List<NewSoItemCondition> newSoItemConditions, List<Long> cartItemIds, List<SoChildPO> soChildPOList, SoDevicePO soDevicePO,
						   List<LimitRuleRecordPO> limitRuleRecordList, List<SoThirdpartyPO> soThirdpartyPO, Long companyAllId);

	boolean changeOrderStatusByOrderCodeWithTx(String orderCode, Integer status,Integer confirmStatus,
			Integer payStatus,Integer deliveryStatus);


	boolean changeOrderStatusByOrderIdWithTx(String orderCode, Long id, Integer status, Integer confirmStatus,
                                             Integer payStatus, Integer deliveryStatus);
	/**
	 * 修改订单金额信息
	 * @param orderId
	 * @param orderAmount
	 * @param deliveryAmount
	 * @param payMoney
	 * @return
	 */
	int updateOrderMoneyInfoWithTx(Long orderId, BigDecimal orderAmount, BigDecimal deliveryAmount, BigDecimal payMoney);

	/**
	 * 修改订单收货信息
	 * @param orderId
	 * @param receiverName
	 * @param receiverPhone
	 * @param receiverAddress
	 * @return
	 */
	int updateDeliveryInfoWithTx(Long orderId, String receiverName, String receiverPhone, String receiverAddress);

	/**
	 * 修改订单支付状态
	 * @param po
	 * @return
	 */
	int updateOrderPaymentInfoWithTx(SoPO po);
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
	 * 根据订单id修改订单收货人信息
	 * @param soVO
	 * @param req
	 * @return
	 */
	JsonResult<String> updateOrderByOrderId(SoPO po);
	JsonResult<String> updateOrderByOrderIdWithTx(SoPO po);
	/**
	 * 母订单分拣
	 * @param soIdArr
	 * @param platformId
	 * @return
	 */
	int SoSort(List<Long> soIdArr, Long platformId);

	/**
	 * 订单退款
	 * @param po
	 * @return
	 */
	int orderRefund(SoPO po);

	/**
	 * 根据订单id保存订单自动完成时间
	 * @param soDTO2
	 * @return
	 */
	int saveOrderAutoCompleteDate(SoPO po);


    void updateSoPayByFuBi(Long orderId, BigDecimal soFreezeBalance);

    void repairOrderDataWithTx();

    int updateOrderCancelStatus(String orderCode);

    int updateOrderCancelOverStatusByOrderCode(String orderCode);
}
