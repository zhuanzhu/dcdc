package com.egeo.components.order.facade;

import java.util.List;

import com.egeo.exception.BusinessException;
import com.egeo.utils.EmptyUtil;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.order.service.read.InvoiceReadService;
import com.egeo.components.order.service.write.InvoiceWriteService;
import com.egeo.components.order.dto.InvoiceDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class InvoiceFacade {
	
	@Resource
	private InvoiceReadService invoiceReadService;
	
	@Resource
	private InvoiceWriteService invoiceWriteService;
	
	
	public InvoiceDTO findInvoiceById(InvoiceDTO dto){
		
		return invoiceReadService.findInvoiceById(dto);
	}

	public PageResult<InvoiceDTO> findInvoiceOfPage(InvoiceDTO dto,Pagination page){
		
		return invoiceReadService.findInvoiceOfPage(dto, page);
		
	}

	public List<InvoiceDTO> findInvoiceAll(InvoiceDTO dto){
		
		return invoiceReadService.findInvoiceAll(dto);
		
	}

	public Long insertInvoiceWithTx(InvoiceDTO dto){
		
		return invoiceWriteService.insertInvoiceWithTx(dto);
	}

	public int updateInvoiceWithTx(InvoiceDTO dto){
		
		return invoiceWriteService.updateInvoiceWithTx(dto);
	}

	public int deleteInvoiceWithTx(InvoiceDTO dto){
		
		return invoiceWriteService.deleteInvoiceWithTx(dto);
		
	}

    public List<InvoiceDTO> findDefaultInvoiceByUserId(InvoiceDTO invoiceDTO) {
		return invoiceReadService.findInvoiceAll(invoiceDTO);

	}
}
	