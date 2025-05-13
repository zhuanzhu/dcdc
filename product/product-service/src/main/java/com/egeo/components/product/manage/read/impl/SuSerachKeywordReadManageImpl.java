package com.egeo.components.product.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.read.SuSerachKeywordReadManage;
import com.egeo.components.product.dao.read.SuSerachKeywordReadDAO;
import com.egeo.components.product.po.SuSerachKeywordPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class SuSerachKeywordReadManageImpl implements SuSerachKeywordReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private SuSerachKeywordReadDAO suSerachKeywordReadDAO;
	
	public SuSerachKeywordPO findSuSerachKeywordById(SuSerachKeywordPO po) {
		SuSerachKeywordPO suSerachKeywordpo = new SuSerachKeywordPO();
		suSerachKeywordpo.setId(po.getId());
		return suSerachKeywordReadDAO.findById(suSerachKeywordpo);
	}

	public PageResult<SuSerachKeywordPO> findSuSerachKeywordOfPage(SuSerachKeywordPO po, Pagination page) {
		
		PageResult<SuSerachKeywordPO> pageResult = new PageResult<SuSerachKeywordPO>();
		List<SuSerachKeywordPO> list = null;

		int cnt = suSerachKeywordReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = suSerachKeywordReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<SuSerachKeywordPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<SuSerachKeywordPO> findSuSerachKeywordAll(SuSerachKeywordPO po) {

		return suSerachKeywordReadDAO.findAll(po,null);
	}

	@Override
	public List<String> keywordByStandardUnitId(Long suId, Long platformId) {
		// TODO Auto-generated method stub
		return suSerachKeywordReadDAO.keywordByStandardUnitId(suId, platformId);
	}
	
}
	