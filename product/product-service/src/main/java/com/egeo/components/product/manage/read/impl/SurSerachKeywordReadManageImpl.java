package com.egeo.components.product.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.read.SurSerachKeywordReadManage;
import com.egeo.components.product.dao.read.SurSerachKeywordReadDAO;
import com.egeo.components.product.po.SurSerachKeywordPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class SurSerachKeywordReadManageImpl implements SurSerachKeywordReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private SurSerachKeywordReadDAO surSerachKeywordReadDAO;
	
	public SurSerachKeywordPO findSurSerachKeywordById(SurSerachKeywordPO po) {
		SurSerachKeywordPO surSerachKeywordpo = new SurSerachKeywordPO();
		surSerachKeywordpo.setId(po.getId());
		return surSerachKeywordReadDAO.findById(surSerachKeywordpo);
	}

	public PageResult<SurSerachKeywordPO> findSurSerachKeywordOfPage(SurSerachKeywordPO po, Pagination page) {
		
		PageResult<SurSerachKeywordPO> pageResult = new PageResult<SurSerachKeywordPO>();
		List<SurSerachKeywordPO> list = null;

		int cnt = surSerachKeywordReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = surSerachKeywordReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<SurSerachKeywordPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<SurSerachKeywordPO> findSurSerachKeywordAll(SurSerachKeywordPO po) {

		return surSerachKeywordReadDAO.findAll(po,null);
	}
	
}
	