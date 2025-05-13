package com.egeo.components.order.manage.read.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.order.manage.read.SoInvoiceReadManage;
import com.egeo.components.order.dao.read.SoInvoiceReadDAO;
import com.egeo.components.order.po.SoInvoicePO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class SoInvoiceReadManageImpl implements SoInvoiceReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private SoInvoiceReadDAO soInvoiceReadDAO;
	
	public SoInvoicePO findSoInvoiceById(SoInvoicePO po) {
		SoInvoicePO soInvoicepo = new SoInvoicePO();
		soInvoicepo.setId(po.getId());
		return soInvoiceReadDAO.findById(soInvoicepo);
	}

	public PageResult<SoInvoicePO> findSoInvoiceOfPage(SoInvoicePO po, Pagination page) {
		
		PageResult<SoInvoicePO> pageResult = new PageResult<SoInvoicePO>();
		List<SoInvoicePO> list = null;

		int cnt = soInvoiceReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = soInvoiceReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<SoInvoicePO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<SoInvoicePO> findSoInvoiceAll(SoInvoicePO po) {

		return soInvoiceReadDAO.findAll(po,null);
	}

	@Override
	public SoInvoicePO querySoInvoiceByOrderCode(String orderCode,Long platformId) {
		return soInvoiceReadDAO.querySoInvoiceByOrderCode(orderCode,platformId);
	}

	@Override
	public List<SoInvoicePO> queryDistinctInvoiceByUserId(Long userId, Long platformId) {
		return soInvoiceReadDAO.queryDistinctInvoiceByUserId(userId,platformId);
	}

	@Override
	public SoInvoicePO queryMainSoInvoiceByOrderId(Long orderId) {
		return soInvoiceReadDAO.queryMainSoInvoiceByOrderId(orderId);
	}

	@Override
	public SoInvoicePO querySoInvoiceBySoChildId(Long id) {
		return soInvoiceReadDAO.querySoInvoiceBySoChildId(id);
	}
	
}
	