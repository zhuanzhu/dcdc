package com.egeo.components.product.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.read.StandardUnitRecordReadManage;
import com.egeo.components.product.dao.read.StandardUnitRecordReadDAO;
import com.egeo.components.product.po.StandardUnitRecordPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class StandardUnitRecordReadManageImpl implements StandardUnitRecordReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private StandardUnitRecordReadDAO standardUnitRecordReadDAO;
	
	public StandardUnitRecordPO findStandardUnitRecordById(StandardUnitRecordPO po) {
		StandardUnitRecordPO standardUnitRecordpo = new StandardUnitRecordPO();
		standardUnitRecordpo.setId(po.getId());
		return standardUnitRecordReadDAO.findById(standardUnitRecordpo);
	}

	public PageResult<StandardUnitRecordPO> findStandardUnitRecordOfPage(StandardUnitRecordPO po, Pagination page) {
		
		PageResult<StandardUnitRecordPO> pageResult = new PageResult<StandardUnitRecordPO>();
		List<StandardUnitRecordPO> list = null;

		int cnt = standardUnitRecordReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = standardUnitRecordReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<StandardUnitRecordPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<StandardUnitRecordPO> findStandardUnitRecordAll(StandardUnitRecordPO po) {

		return standardUnitRecordReadDAO.findAll(po,null);
	}
	
}
	