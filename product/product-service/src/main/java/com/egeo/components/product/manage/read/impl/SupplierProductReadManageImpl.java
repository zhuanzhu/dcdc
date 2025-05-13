package com.egeo.components.product.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.read.SupplierProductReadManage;
import com.egeo.components.product.dao.read.SupplierProductReadDAO;
import com.egeo.components.product.po.SupplierProductPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class SupplierProductReadManageImpl implements SupplierProductReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private SupplierProductReadDAO supplierProductReadDAO;
	
	public SupplierProductPO findSupplierProductById(SupplierProductPO po) {
		SupplierProductPO supplierProductpo = new SupplierProductPO();
		supplierProductpo.setId(po.getId());
		return supplierProductReadDAO.findById(supplierProductpo);
	}

	public PageResult<SupplierProductPO> findSupplierProductOfPage(SupplierProductPO po, Pagination page) {
		
		PageResult<SupplierProductPO> pageResult = new PageResult<SupplierProductPO>();
		List<SupplierProductPO> list = null;

		int cnt = supplierProductReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = supplierProductReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<SupplierProductPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<SupplierProductPO> findSupplierProductAll(SupplierProductPO po) {

		return supplierProductReadDAO.findAll(po,null);
	}
	
}
	