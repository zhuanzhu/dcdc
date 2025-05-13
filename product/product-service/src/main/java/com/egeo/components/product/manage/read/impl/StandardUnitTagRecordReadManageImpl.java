package com.egeo.components.product.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.read.StandardUnitTagRecordReadManage;
import com.egeo.components.product.dao.read.StandardUnitTagRecordReadDAO;
import com.egeo.components.product.po.StandardUnitTagRecordPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class StandardUnitTagRecordReadManageImpl implements StandardUnitTagRecordReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private StandardUnitTagRecordReadDAO standardUnitTagRecordReadDAO;
	
	public StandardUnitTagRecordPO findStandardUnitTagRecordById(StandardUnitTagRecordPO po) {
		StandardUnitTagRecordPO standardUnitTagRecordpo = new StandardUnitTagRecordPO();
		standardUnitTagRecordpo.setId(po.getId());
		return standardUnitTagRecordReadDAO.findById(standardUnitTagRecordpo);
	}

	public PageResult<StandardUnitTagRecordPO> findStandardUnitTagRecordOfPage(StandardUnitTagRecordPO po, Pagination page) {
		
		PageResult<StandardUnitTagRecordPO> pageResult = new PageResult<StandardUnitTagRecordPO>();
		List<StandardUnitTagRecordPO> list = null;

		int cnt = standardUnitTagRecordReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = standardUnitTagRecordReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<StandardUnitTagRecordPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<StandardUnitTagRecordPO> findStandardUnitTagRecordAll(StandardUnitTagRecordPO po) {

		return standardUnitTagRecordReadDAO.findAll(po,null);
	}
	
}
	