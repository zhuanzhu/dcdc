package com.egeo.components.product.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.read.StandardUnitDescribeRecordReadManage;
import com.egeo.components.product.dao.read.StandardUnitDescribeRecordReadDAO;
import com.egeo.components.product.po.StandardUnitDescribeRecordPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class StandardUnitDescribeRecordReadManageImpl implements StandardUnitDescribeRecordReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private StandardUnitDescribeRecordReadDAO standardUnitDescribeRecordReadDAO;
	
	public StandardUnitDescribeRecordPO findStandardUnitDescribeRecordById(StandardUnitDescribeRecordPO po) {
		StandardUnitDescribeRecordPO standardUnitDescribeRecordpo = new StandardUnitDescribeRecordPO();
		standardUnitDescribeRecordpo.setId(po.getId());
		return standardUnitDescribeRecordReadDAO.findById(standardUnitDescribeRecordpo);
	}

	public PageResult<StandardUnitDescribeRecordPO> findStandardUnitDescribeRecordOfPage(StandardUnitDescribeRecordPO po, Pagination page) {
		
		PageResult<StandardUnitDescribeRecordPO> pageResult = new PageResult<StandardUnitDescribeRecordPO>();
		List<StandardUnitDescribeRecordPO> list = null;

		int cnt = standardUnitDescribeRecordReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = standardUnitDescribeRecordReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<StandardUnitDescribeRecordPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<StandardUnitDescribeRecordPO> findStandardUnitDescribeRecordAll(StandardUnitDescribeRecordPO po) {

		return standardUnitDescribeRecordReadDAO.findAll(po,null);
	}
	
}
	