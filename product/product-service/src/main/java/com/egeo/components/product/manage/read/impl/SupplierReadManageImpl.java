package com.egeo.components.product.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.read.SupplierReadManage;
import com.egeo.components.product.dao.read.SupplierReadDAO;
import com.egeo.components.product.po.SupplierPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class SupplierReadManageImpl implements SupplierReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private SupplierReadDAO supplierReadDAO;
	
	public SupplierPO findSupplierById(SupplierPO po) {
		SupplierPO supplierpo = new SupplierPO();
		supplierpo.setId(po.getId());
		return supplierReadDAO.findById(supplierpo);
	}

	public PageResult<SupplierPO> findSupplierOfPage(SupplierPO po, Pagination page) {
		
		PageResult<SupplierPO> pageResult = new PageResult<SupplierPO>();
		List<SupplierPO> list = null;

		int cnt = supplierReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = supplierReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<SupplierPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<SupplierPO> findSupplierAll(SupplierPO po) {

		return supplierReadDAO.findAll(po,null);
	}

	@Override
	public List<SupplierPO> findByIdList(List<Long> ids) {
		return supplierReadDAO.findByIdList(ids);
	}
}
	