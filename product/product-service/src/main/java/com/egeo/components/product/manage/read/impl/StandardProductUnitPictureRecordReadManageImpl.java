package com.egeo.components.product.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.read.StandardProductUnitPictureRecordReadManage;
import com.egeo.components.product.dao.read.StandardProductUnitPictureRecordReadDAO;
import com.egeo.components.product.po.StandardProductUnitPictureRecordPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class StandardProductUnitPictureRecordReadManageImpl implements StandardProductUnitPictureRecordReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private StandardProductUnitPictureRecordReadDAO standardProductUnitPictureRecordReadDAO;
	
	public StandardProductUnitPictureRecordPO findStandardProductUnitPictureRecordById(StandardProductUnitPictureRecordPO po) {
		StandardProductUnitPictureRecordPO standardProductUnitPictureRecordpo = new StandardProductUnitPictureRecordPO();
		standardProductUnitPictureRecordpo.setId(po.getId());
		return standardProductUnitPictureRecordReadDAO.findById(standardProductUnitPictureRecordpo);
	}

	public PageResult<StandardProductUnitPictureRecordPO> findStandardProductUnitPictureRecordOfPage(StandardProductUnitPictureRecordPO po, Pagination page) {
		
		PageResult<StandardProductUnitPictureRecordPO> pageResult = new PageResult<StandardProductUnitPictureRecordPO>();
		List<StandardProductUnitPictureRecordPO> list = null;

		int cnt = standardProductUnitPictureRecordReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = standardProductUnitPictureRecordReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<StandardProductUnitPictureRecordPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<StandardProductUnitPictureRecordPO> findStandardProductUnitPictureRecordAll(StandardProductUnitPictureRecordPO po) {

		return standardProductUnitPictureRecordReadDAO.findAll(po,null);
	}
	
}
	