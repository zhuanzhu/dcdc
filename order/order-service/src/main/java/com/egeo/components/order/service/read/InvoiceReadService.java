package com.egeo.components.order.service.read;


import java.util.List;
	
import com.egeo.components.order.dto.InvoiceDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface InvoiceReadService {

	public InvoiceDTO findInvoiceById(InvoiceDTO dto);

	public PageResult<InvoiceDTO> findInvoiceOfPage(InvoiceDTO dto,Pagination page);

	public List<InvoiceDTO> findInvoiceAll(InvoiceDTO dto);
}
	