package com.egeo.components.order.manage.write;

import com.egeo.components.order.po.SoInvoicePO;


public interface SoInvoiceWriteManage {

	Long insertSoInvoiceWithTx(SoInvoicePO po, String mail);

	int updateSoInvoiceWithTx(SoInvoicePO po);
	
	int updateSoInvoiceWithTx(SoInvoicePO po, Long commonInvoiceId);

	int deleteSoInvoiceWithTx(SoInvoicePO po);

	int updateSoInvoiceBySoIdWithTx(Long soId, Integer titleType, String title, Integer invoiceType,
			String taxNo,Integer invoiceContentType);

	Long insertSoInvoiceWithTx(SoInvoicePO po);
	
	Long insertSoInvoiceWithTx(SoInvoicePO po, Long commonInvoiceId);

	/**
	 * 更换公共发票信息
	 * @param orderId
	 * @param commonInvoiceId
	 * @return
	 */
	Integer changeCommonInvoiceInfoWithTx(Long orderId, Long commonInvoiceId);

	Integer updateInvoiceTypeInfoWithTx(SoInvoicePO po);
}
	