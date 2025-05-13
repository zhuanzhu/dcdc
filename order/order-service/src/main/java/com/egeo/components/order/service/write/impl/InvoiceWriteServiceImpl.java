package com.egeo.components.order.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.order.service.write.InvoiceWriteService;
import com.egeo.components.order.manage.write.InvoiceWriteManage;
import com.egeo.components.order.converter.InvoiceConverter;
import com.egeo.components.order.dto.InvoiceDTO;
import com.egeo.components.order.po.InvoicePO;

@Service("invoiceWriteService")
public class InvoiceWriteServiceImpl  implements InvoiceWriteService {
	@Autowired
	private InvoiceWriteManage invoiceWriteManage;

	@Override
	public Long insertInvoiceWithTx(InvoiceDTO dto) {
		InvoicePO po = InvoiceConverter.toPO(dto);
		Long rt = invoiceWriteManage.insertInvoiceWithTx(po);		
		return rt;
	}

	@Override
	public int updateInvoiceWithTx(InvoiceDTO dto) {
		InvoicePO po = InvoiceConverter.toPO(dto);
		int rt = invoiceWriteManage.updateInvoiceWithTx(po);		
		return rt;
	}

	@Override
	public int deleteInvoiceWithTx(InvoiceDTO dto) {
		InvoicePO po = InvoiceConverter.toPO(dto);
		int rt = invoiceWriteManage.deleteInvoiceWithTx(po);		
		return rt;
	}
}
	