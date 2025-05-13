package com.egeo.components.order.manage.write;

import com.egeo.components.order.po.InvoicePO;


public interface InvoiceWriteManage {

	Long insertInvoiceWithTx(InvoicePO po);

	int updateInvoiceWithTx(InvoicePO po);

	int deleteInvoiceWithTx(InvoicePO po);
}
	