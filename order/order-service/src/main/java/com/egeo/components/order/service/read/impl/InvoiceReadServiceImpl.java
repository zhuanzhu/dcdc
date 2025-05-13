package com.egeo.components.order.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.order.service.read.InvoiceReadService;
import com.egeo.components.order.manage.read.InvoiceReadManage;
import com.egeo.components.order.converter.InvoiceConverter;
import com.egeo.components.order.dto.InvoiceDTO;
import com.egeo.components.order.po.InvoicePO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("invoiceReadService")
public class InvoiceReadServiceImpl  implements InvoiceReadService {
	@Autowired
	private InvoiceReadManage invoiceReadManage;

	@Override
	public InvoiceDTO findInvoiceById(InvoiceDTO dto) {
		InvoicePO po = InvoiceConverter.toPO(dto);
		InvoicePO list = invoiceReadManage.findInvoiceById(po);		
		return InvoiceConverter.toDTO(list);
	}

	@Override
	public PageResult<InvoiceDTO> findInvoiceOfPage(InvoiceDTO dto, Pagination page) {
		InvoicePO po = InvoiceConverter.toPO(dto);
        PageResult<InvoicePO> pageResult = invoiceReadManage.findInvoiceOfPage(po, page);
        
        List<InvoiceDTO> list = InvoiceConverter.toDTO(pageResult.getList());
        PageResult<InvoiceDTO> result = new PageResult<InvoiceDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<InvoiceDTO> findInvoiceAll(InvoiceDTO dto) {
		InvoicePO po = InvoiceConverter.toPO(dto);
		List<InvoicePO> list = invoiceReadManage.findInvoiceAll(po);		
		return InvoiceConverter.toDTO(list);
	}
}
	