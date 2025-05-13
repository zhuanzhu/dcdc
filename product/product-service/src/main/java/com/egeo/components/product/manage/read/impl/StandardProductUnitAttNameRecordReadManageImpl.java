package com.egeo.components.product.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.read.StandardProductUnitAttNameRecordReadManage;
import com.egeo.components.product.dao.read.StandardProductUnitAttNameRecordReadDAO;
import com.egeo.components.product.po.StandardProductUnitAttNameRecordPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class StandardProductUnitAttNameRecordReadManageImpl implements StandardProductUnitAttNameRecordReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private StandardProductUnitAttNameRecordReadDAO standardProductUnitAttNameRecordReadDAO;
	
	public StandardProductUnitAttNameRecordPO findStandardProductUnitAttNameRecordById(StandardProductUnitAttNameRecordPO po) {
		StandardProductUnitAttNameRecordPO standardProductUnitAttNameRecordpo = new StandardProductUnitAttNameRecordPO();
		standardProductUnitAttNameRecordpo.setId(po.getId());
		return standardProductUnitAttNameRecordReadDAO.findById(standardProductUnitAttNameRecordpo);
	}

	public PageResult<StandardProductUnitAttNameRecordPO> findStandardProductUnitAttNameRecordOfPage(StandardProductUnitAttNameRecordPO po, Pagination page) {
		
		PageResult<StandardProductUnitAttNameRecordPO> pageResult = new PageResult<StandardProductUnitAttNameRecordPO>();
		List<StandardProductUnitAttNameRecordPO> list = null;

		int cnt = standardProductUnitAttNameRecordReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = standardProductUnitAttNameRecordReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<StandardProductUnitAttNameRecordPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<StandardProductUnitAttNameRecordPO> findStandardProductUnitAttNameRecordAll(StandardProductUnitAttNameRecordPO po) {

		return standardProductUnitAttNameRecordReadDAO.findAll(po,null);
	}
	
}
	