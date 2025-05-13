package com.egeo.components.product.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.read.StandardUnitClientRecordReadManage;
import com.egeo.components.product.dao.read.StandardUnitClientRecordReadDAO;
import com.egeo.components.product.po.StandardUnitClientRecordPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class StandardUnitClientRecordReadManageImpl implements StandardUnitClientRecordReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private StandardUnitClientRecordReadDAO standardUnitClientRecordReadDAO;
	
	public StandardUnitClientRecordPO findStandardUnitClientRecordById(StandardUnitClientRecordPO po) {
		StandardUnitClientRecordPO standardUnitClientRecordpo = new StandardUnitClientRecordPO();
		standardUnitClientRecordpo.setId(po.getId());
		return standardUnitClientRecordReadDAO.findById(standardUnitClientRecordpo);
	}

	public PageResult<StandardUnitClientRecordPO> findStandardUnitClientRecordOfPage(StandardUnitClientRecordPO po, Pagination page) {
		
		PageResult<StandardUnitClientRecordPO> pageResult = new PageResult<StandardUnitClientRecordPO>();
		List<StandardUnitClientRecordPO> list = null;

		int cnt = standardUnitClientRecordReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = standardUnitClientRecordReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<StandardUnitClientRecordPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<StandardUnitClientRecordPO> findStandardUnitClientRecordAll(StandardUnitClientRecordPO po) {

		return standardUnitClientRecordReadDAO.findAll(po,null);
	}
	
}
	