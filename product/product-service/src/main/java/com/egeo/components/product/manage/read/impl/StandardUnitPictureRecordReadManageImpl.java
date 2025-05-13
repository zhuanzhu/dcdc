package com.egeo.components.product.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.read.StandardUnitPictureRecordReadManage;
import com.egeo.components.product.dao.read.StandardUnitPictureRecordReadDAO;
import com.egeo.components.product.po.StandardUnitPictureRecordPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class StandardUnitPictureRecordReadManageImpl implements StandardUnitPictureRecordReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private StandardUnitPictureRecordReadDAO standardUnitPictureRecordReadDAO;
	
	public StandardUnitPictureRecordPO findStandardUnitPictureRecordById(StandardUnitPictureRecordPO po) {
		StandardUnitPictureRecordPO standardUnitPictureRecordpo = new StandardUnitPictureRecordPO();
		standardUnitPictureRecordpo.setId(po.getId());
		return standardUnitPictureRecordReadDAO.findById(standardUnitPictureRecordpo);
	}

	public PageResult<StandardUnitPictureRecordPO> findStandardUnitPictureRecordOfPage(StandardUnitPictureRecordPO po, Pagination page) {
		
		PageResult<StandardUnitPictureRecordPO> pageResult = new PageResult<StandardUnitPictureRecordPO>();
		List<StandardUnitPictureRecordPO> list = null;

		int cnt = standardUnitPictureRecordReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = standardUnitPictureRecordReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<StandardUnitPictureRecordPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<StandardUnitPictureRecordPO> findStandardUnitPictureRecordAll(StandardUnitPictureRecordPO po) {

		return standardUnitPictureRecordReadDAO.findAll(po,null);
	}
	
}
	