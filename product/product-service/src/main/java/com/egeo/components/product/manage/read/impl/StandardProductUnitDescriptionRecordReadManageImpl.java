package com.egeo.components.product.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.read.StandardProductUnitDescriptionRecordReadManage;
import com.egeo.components.product.dao.read.StandardProductUnitDescriptionRecordReadDAO;
import com.egeo.components.product.po.StandardProductUnitDescriptionRecordPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class StandardProductUnitDescriptionRecordReadManageImpl implements StandardProductUnitDescriptionRecordReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private StandardProductUnitDescriptionRecordReadDAO standardProductUnitDescriptionRecordReadDAO;
	
	public StandardProductUnitDescriptionRecordPO findStandardProductUnitDescriptionRecordById(StandardProductUnitDescriptionRecordPO po) {
		StandardProductUnitDescriptionRecordPO standardProductUnitDescriptionRecordpo = new StandardProductUnitDescriptionRecordPO();
		standardProductUnitDescriptionRecordpo.setId(po.getId());
		return standardProductUnitDescriptionRecordReadDAO.findById(standardProductUnitDescriptionRecordpo);
	}

	public PageResult<StandardProductUnitDescriptionRecordPO> findStandardProductUnitDescriptionRecordOfPage(StandardProductUnitDescriptionRecordPO po, Pagination page) {
		
		PageResult<StandardProductUnitDescriptionRecordPO> pageResult = new PageResult<StandardProductUnitDescriptionRecordPO>();
		List<StandardProductUnitDescriptionRecordPO> list = null;

		int cnt = standardProductUnitDescriptionRecordReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = standardProductUnitDescriptionRecordReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<StandardProductUnitDescriptionRecordPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<StandardProductUnitDescriptionRecordPO> findStandardProductUnitDescriptionRecordAll(StandardProductUnitDescriptionRecordPO po) {

		return standardProductUnitDescriptionRecordReadDAO.findAll(po,null);
	}
	
}
	