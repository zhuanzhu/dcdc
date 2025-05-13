package com.egeo.components.config.manage.read.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.config.dao.read.HeadImportRecordsReadDAO;
import com.egeo.components.config.manage.read.HeadImportRecordsReadManage;
import com.egeo.components.config.po.HeadImportRecordsPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class HeadImportRecordsReadManageImpl implements HeadImportRecordsReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private HeadImportRecordsReadDAO headImportRecordsReadDAO;
	
	public HeadImportRecordsPO findHeadImportRecordsById(HeadImportRecordsPO po) {
		HeadImportRecordsPO headImportRecordspo = new HeadImportRecordsPO();
		headImportRecordspo.setId(po.getId());
		return headImportRecordsReadDAO.findById(headImportRecordspo);
	}

	public PageResult<HeadImportRecordsPO> findHeadImportRecordsOfPage(HeadImportRecordsPO po, Pagination page) {
		
		PageResult<HeadImportRecordsPO> pageResult = new PageResult<HeadImportRecordsPO>();
		List<HeadImportRecordsPO> list = null;

		int cnt = headImportRecordsReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = headImportRecordsReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<HeadImportRecordsPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<HeadImportRecordsPO> findHeadImportRecordsAll(HeadImportRecordsPO po) {

		return headImportRecordsReadDAO.findAll(po,null);
	}

	@Override
	public HeadImportRecordsPO queryRecordBySn(String sn) {
		return headImportRecordsReadDAO.queryRecordBySn(sn);
	}

	@Override
	public List<HeadImportRecordsPO> queryRecordsBySn(String sn) {
		return headImportRecordsReadDAO.queryRecordsBySn(sn);
	}
	
}
	