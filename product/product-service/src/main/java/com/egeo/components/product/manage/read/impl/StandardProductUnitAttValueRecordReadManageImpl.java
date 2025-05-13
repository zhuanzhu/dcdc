package com.egeo.components.product.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.read.StandardProductUnitAttValueRecordReadManage;
import com.egeo.components.product.dao.read.StandardProductUnitAttValueRecordReadDAO;
import com.egeo.components.product.po.StandardProductUnitAttValueRecordPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class StandardProductUnitAttValueRecordReadManageImpl implements StandardProductUnitAttValueRecordReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private StandardProductUnitAttValueRecordReadDAO standardProductUnitAttValueRecordReadDAO;
	
	public StandardProductUnitAttValueRecordPO findStandardProductUnitAttValueRecordById(StandardProductUnitAttValueRecordPO po) {
		StandardProductUnitAttValueRecordPO standardProductUnitAttValueRecordpo = new StandardProductUnitAttValueRecordPO();
		standardProductUnitAttValueRecordpo.setId(po.getId());
		return standardProductUnitAttValueRecordReadDAO.findById(standardProductUnitAttValueRecordpo);
	}

	public PageResult<StandardProductUnitAttValueRecordPO> findStandardProductUnitAttValueRecordOfPage(StandardProductUnitAttValueRecordPO po, Pagination page) {
		
		PageResult<StandardProductUnitAttValueRecordPO> pageResult = new PageResult<StandardProductUnitAttValueRecordPO>();
		List<StandardProductUnitAttValueRecordPO> list = null;

		int cnt = standardProductUnitAttValueRecordReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = standardProductUnitAttValueRecordReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<StandardProductUnitAttValueRecordPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<StandardProductUnitAttValueRecordPO> findStandardProductUnitAttValueRecordAll(StandardProductUnitAttValueRecordPO po) {

		return standardProductUnitAttValueRecordReadDAO.findAll(po,null);
	}
	
}
	