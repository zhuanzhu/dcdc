package com.egeo.components.product.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.read.StandardUnitCompanyRecordReadManage;
import com.egeo.components.product.dao.read.StandardUnitCompanyRecordReadDAO;
import com.egeo.components.product.po.StandardUnitCompanyRecordPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class StandardUnitCompanyRecordReadManageImpl implements StandardUnitCompanyRecordReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private StandardUnitCompanyRecordReadDAO standardUnitCompanyRecordReadDAO;
	
	public StandardUnitCompanyRecordPO findStandardUnitCompanyRecordById(StandardUnitCompanyRecordPO po) {
		StandardUnitCompanyRecordPO standardUnitCompanyRecordpo = new StandardUnitCompanyRecordPO();
		standardUnitCompanyRecordpo.setId(po.getId());
		return standardUnitCompanyRecordReadDAO.findById(standardUnitCompanyRecordpo);
	}

	public PageResult<StandardUnitCompanyRecordPO> findStandardUnitCompanyRecordOfPage(StandardUnitCompanyRecordPO po, Pagination page) {
		
		PageResult<StandardUnitCompanyRecordPO> pageResult = new PageResult<StandardUnitCompanyRecordPO>();
		List<StandardUnitCompanyRecordPO> list = null;

		int cnt = standardUnitCompanyRecordReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = standardUnitCompanyRecordReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<StandardUnitCompanyRecordPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<StandardUnitCompanyRecordPO> findStandardUnitCompanyRecordAll(StandardUnitCompanyRecordPO po) {

		return standardUnitCompanyRecordReadDAO.findAll(po,null);
	}
	
}
	