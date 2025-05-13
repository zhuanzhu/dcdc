package com.egeo.components.promotion.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.promotion.manage.read.ErCardRecordReadManage;
import com.egeo.components.promotion.dao.read.ErCardRecordReadDAO;
import com.egeo.components.promotion.po.ErCardRecordPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class ErCardRecordReadManageImpl implements ErCardRecordReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ErCardRecordReadDAO erCardRecordReadDAO;
	
	public ErCardRecordPO findErCardRecordById(ErCardRecordPO po) {
		ErCardRecordPO erCardRecordpo = new ErCardRecordPO();
		erCardRecordpo.setId(po.getId());
		return erCardRecordReadDAO.findById(erCardRecordpo);
	}

	public PageResult<ErCardRecordPO> findErCardRecordOfPage(ErCardRecordPO po, Pagination page) {
		
		PageResult<ErCardRecordPO> pageResult = new PageResult<ErCardRecordPO>();
		List<ErCardRecordPO> list = null;

		int cnt = erCardRecordReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = erCardRecordReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<ErCardRecordPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<ErCardRecordPO> findErCardRecordAll(ErCardRecordPO po) {

		return erCardRecordReadDAO.findAll(po,null);
	}
	
}
	