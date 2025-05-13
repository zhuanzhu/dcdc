package com.egeo.components.order.service.write;

import com.egeo.components.order.dto.SoInvoiceDTO;


public interface SoInvoiceWriteService {

	public Long insertSoInvoiceWithTx(SoInvoiceDTO dto, String mail);

	public int updateSoInvoiceWithTx(SoInvoiceDTO dto);
	
	public int updateSoInvoiceWithTx(SoInvoiceDTO dto, Long commonInvoiceId);

	public int deleteSoInvoiceWithTx(SoInvoiceDTO dto);

	/**
	 * 以订单编号为条件修改发票信息
	 * @param orderCode
	 * @param titleType
	 * @param title
	 * @param invoiceType
	 * @param taxNo
	 * @param invoiceContentType 
	 * @return
	 */
	public int updateSoInvoiceBySoId(Long soId, Integer titleType, String title, Integer invoiceType,
			String taxNo, Integer invoiceContentType);

	public Long insertSoInvoiceWithTx(SoInvoiceDTO inv);
	
	public Long insertSoInvoiceWithTx(SoInvoiceDTO inv, Long commonInvoiceId);

	/**
	 * 更换公共发票信息
	 * @param orderId
	 * @param commonInvoiceId
	 * @return
	 */
	public Integer changeCommonInvoiceInfoWithTx(Long orderId, Long commonInvoiceId);

	public Integer updateInvoiceTypeInfoWithTx(SoInvoiceDTO dto);
}
	