package com.egeo.components.order.dao.read;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.order.po.SoInvoicePO;
import com.egeo.orm.BaseReadDAO;

public interface SoInvoiceReadDAO extends BaseReadDAO<SoInvoicePO>{

	/**
	 * 根据订单编号查询发票信息
	 * @param orderCode
	 * @param platformId 
	 * @return
	 */
	SoInvoicePO querySoInvoiceByOrderCode(@Param("orderCode")String orderCode, 
			@Param("platformId")Long platformId);

	/**
	 * 查询不重复的用户发票记录
	 * @param userId
	 * @param platformId
	 * @return
	 */
	List<SoInvoicePO> queryDistinctInvoiceByUserId(
			@Param("userId")Long userId,
			@Param("platformId")Long platformId);

	/**
	 * 查询订单主发票
	 * @param orderId
	 * @return
	 */
	SoInvoicePO queryMainSoInvoiceByOrderId(@Param("orderId")Long orderId);

	/**
	 * 根据子订单id查询发票信息
	 * @param id
	 * @return
	 */
	SoInvoicePO querySoInvoiceBySoChildId(@Param("id")Long id);

}
	