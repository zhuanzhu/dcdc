package com.egeo.components.order.manage.read;

import java.util.List;
	
import com.egeo.components.order.po.InvoicePO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface InvoiceReadManage {

	public InvoicePO findInvoiceById(InvoicePO po);

	public PageResult<InvoicePO> findInvoiceOfPage(InvoicePO po,Pagination page);

	public List<InvoicePO> findInvoiceAll(InvoicePO po);
}
	