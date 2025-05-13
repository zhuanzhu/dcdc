package com.egeo.components.product.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.read.StandardProductUnitRecordReadManage;
import com.egeo.components.product.dao.read.StandardProductUnitRecordReadDAO;
import com.egeo.components.product.po.StandardProductUnitRecordPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class StandardProductUnitRecordReadManageImpl implements StandardProductUnitRecordReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private StandardProductUnitRecordReadDAO standardProductUnitRecordReadDAO;
	
	public StandardProductUnitRecordPO findStandardProductUnitRecordById(StandardProductUnitRecordPO po) {
		StandardProductUnitRecordPO standardProductUnitRecordpo = new StandardProductUnitRecordPO();
		standardProductUnitRecordpo.setId(po.getId());
		return standardProductUnitRecordReadDAO.findById(standardProductUnitRecordpo);
	}

	public PageResult<StandardProductUnitRecordPO> findStandardProductUnitRecordOfPage(StandardProductUnitRecordPO po, Pagination page) {
		
		PageResult<StandardProductUnitRecordPO> pageResult = new PageResult<StandardProductUnitRecordPO>();
		List<StandardProductUnitRecordPO> list = null;

		int cnt = standardProductUnitRecordReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = standardProductUnitRecordReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<StandardProductUnitRecordPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<StandardProductUnitRecordPO> findStandardProductUnitRecordAll(StandardProductUnitRecordPO po) {

		return standardProductUnitRecordReadDAO.findAll(po,null);
	}
	
}
	