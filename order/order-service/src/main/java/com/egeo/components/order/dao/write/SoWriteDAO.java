package com.egeo.components.order.dao.write;


import org.apache.ibatis.annotations.Param;

import com.egeo.components.order.po.SoPO;
import com.egeo.orm.BaseWriteDAO;

import java.math.BigDecimal;

public interface SoWriteDAO extends BaseWriteDAO<SoPO> {

	/**
	 * 删除订单(逻辑删除)
	 * @param orderCode
	 * @param platformId
	 * @param deleteStatus
	 *            1、为可以复原订单 2、为不能复原订单
	 * @return
	 */
	int deleteByOrderCode(@Param("orderCode") String orderCode, @Param("userId") Long userId,
			@Param("deleteStatus") int deleteStatus);

	/**
	 * 根据订单编号修改订单状态
	 * @param orderCode
	 * @param status
	 * @param deliveryStatus 
	 * @param payStatus 
	 * @param confirmStatus 
	 * @return
	 */
	int updateOrderStatus(@Param("orderCode")String orderCode, 
			@Param("status")Integer status,
			@Param("confirmStatus")Integer confirmStatus, 
			@Param("payStatus")Integer payStatus, 
			@Param("deliveryStatus")Integer deliveryStatus, 
			@Param("limitCashPayAmount")Integer limitCashPayAmount);

	/**
	 * 根据订单id改变状态
	 * @param orderCode
	 * @param status
	 * @param confirmStatus
	 * @param payStatus
	 * @param deliveryStatus
	 * @return
	 */
	int updateOrderStatusById(@Param("id")Long id, 
			@Param("status")Integer status,
			@Param("confirmStatus")Integer confirmStatus, 
			@Param("payStatus")Integer payStatus, 
			@Param("deliveryStatus")Integer deliveryStatus, 
			@Param("limitCashPayAmount")Integer limitCashPayAmount);

	/**
	 * 订单退款
	 * @param po
	 * @return
	 */
	int orderRefund(@Param("po")SoPO po);

    void updateSoPayByFuBi(@Param("orderId")Long orderId,@Param("soFreezeBalance")BigDecimal soFreezeBalance);

    int updateOrderCancelStatus(@Param("orderCode")String orderCode);

    int updateOrderCancelOverStatusByOrderCode(@Param("orderCode")String orderCode);
}
