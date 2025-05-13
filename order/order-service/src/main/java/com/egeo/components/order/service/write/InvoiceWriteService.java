package com.egeo.components.order.service.write;

import com.egeo.components.order.dto.InvoiceDTO;


public interface InvoiceWriteService {

	public Long insertInvoiceWithTx(InvoiceDTO dto);

	public int updateInvoiceWithTx(InvoiceDTO dto);

	public int deleteInvoiceWithTx(InvoiceDTO dto);
}
	