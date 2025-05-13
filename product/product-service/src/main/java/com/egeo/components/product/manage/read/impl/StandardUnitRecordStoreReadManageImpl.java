package com.egeo.components.product.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.read.StandardUnitRecordStoreReadManage;
import com.egeo.components.product.dao.read.StandardUnitRecordStoreReadDAO;
import com.egeo.components.product.po.StandardUnitRecordStorePO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class StandardUnitRecordStoreReadManageImpl implements StandardUnitRecordStoreReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private StandardUnitRecordStoreReadDAO standardUnitRecordStoreReadDAO;
	
	public StandardUnitRecordStorePO findStandardUnitRecordStoreById(StandardUnitRecordStorePO po) {
		StandardUnitRecordStorePO standardUnitRecordStorepo = new StandardUnitRecordStorePO();
		standardUnitRecordStorepo.setId(po.getId());
		return standardUnitRecordStoreReadDAO.findById(standardUnitRecordStorepo);
	}

	public PageResult<StandardUnitRecordStorePO> findStandardUnitRecordStoreOfPage(StandardUnitRecordStorePO po, Pagination page) {
		
		PageResult<StandardUnitRecordStorePO> pageResult = new PageResult<StandardUnitRecordStorePO>();
		List<StandardUnitRecordStorePO> list = null;

		int cnt = standardUnitRecordStoreReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = standardUnitRecordStoreReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<StandardUnitRecordStorePO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<StandardUnitRecordStorePO> findStandardUnitRecordStoreAll(StandardUnitRecordStorePO po) {

		return standardUnitRecordStoreReadDAO.findAll(po,null);
	}
	
}
	