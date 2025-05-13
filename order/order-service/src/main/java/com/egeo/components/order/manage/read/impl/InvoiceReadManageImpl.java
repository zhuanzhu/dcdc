package com.egeo.components.order.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.order.manage.read.InvoiceReadManage;
import com.egeo.components.order.dao.read.InvoiceReadDAO;
import com.egeo.components.order.po.InvoicePO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class InvoiceReadManageImpl implements InvoiceReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private InvoiceReadDAO invoiceReadDAO;
	
	public InvoicePO findInvoiceById(InvoicePO po) {
		InvoicePO invoicepo = new InvoicePO();
		invoicepo.setId(po.getId());
		return invoiceReadDAO.findById(invoicepo);
	}

	public PageResult<InvoicePO> findInvoiceOfPage(InvoicePO po, Pagination page) {
		
		PageResult<InvoicePO> pageResult = new PageResult<InvoicePO>();
		List<InvoicePO> list = null;

		int cnt = invoiceReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = invoiceReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<InvoicePO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<InvoicePO> findInvoiceAll(InvoicePO po) {

		return invoiceReadDAO.findAll(po,null);
	}
	
}
	