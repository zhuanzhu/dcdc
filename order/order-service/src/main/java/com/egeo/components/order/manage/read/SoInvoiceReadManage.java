package com.egeo.components.order.manage.read;

import java.util.List;

import com.egeo.components.order.po.SoInvoicePO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface SoInvoiceReadManage {

	public SoInvoicePO findSoInvoiceById(SoInvoicePO po);

	public PageResult<SoInvoicePO> findSoInvoiceOfPage(SoInvoicePO po,Pagination page);

	public List<SoInvoicePO> findSoInvoiceAll(SoInvoicePO po);

	/**
	 * 根据订单编号查询发票信息
	 * @param orderCode
	 * @param platformId 
	 * @return
	 */
	public SoInvoicePO querySoInvoiceByOrderCode(String orderCode, Long platformId);

	/**
	 * 查询不重复的用户发票记录
	 * @param userId
	 * @param platformId
	 * @return
	 */
	public List<SoInvoicePO> queryDistinctInvoiceByUserId(Long userId, Long platformId);

	/**
	 * 查询订单主发票
	 * @param orderId
	 * @return
	 */
	public SoInvoicePO queryMainSoInvoiceByOrderId(Long orderId);

	/**
	 * 根据子订单id查询发票信息
	 * @param id
	 * @return
	 */
	public SoInvoicePO querySoInvoiceBySoChildId(Long id);
}
	