package com.egeo.components.user.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.user.manage.read.TrialApplyReadManage;
import com.egeo.components.user.dao.read.TrialApplyReadDAO;
import com.egeo.components.user.po.TrialApplyPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class TrialApplyReadManageImpl implements TrialApplyReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private TrialApplyReadDAO trialApplyReadDAO;
	
	public TrialApplyPO findTrialApplyById(TrialApplyPO po) {
		TrialApplyPO trialApplypo = new TrialApplyPO();
		trialApplypo.setId(po.getId());
		return trialApplyReadDAO.findById(trialApplypo);
	}

	public PageResult<TrialApplyPO> findTrialApplyOfPage(TrialApplyPO po, Pagination page) {
		
		PageResult<TrialApplyPO> pageResult = new PageResult<TrialApplyPO>();
		List<TrialApplyPO> list = null;

		int cnt = trialApplyReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = trialApplyReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<TrialApplyPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<TrialApplyPO> findTrialApplyAll(TrialApplyPO po) {

		return trialApplyReadDAO.findAll(po,null);
	}

	@Override
	public PageResult<TrialApplyPO> findTrialApplyOfPageByBlurry(TrialApplyPO po, Pagination page) {
		PageResult<TrialApplyPO> pageResult = new PageResult<TrialApplyPO>();
		List<TrialApplyPO> list = null;

		int cnt = trialApplyReadDAO.countOfPageByBlurry(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = trialApplyReadDAO.findOfPageByBlurry(po, page);
		} else {
			list = new ArrayList<TrialApplyPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;
	}
	
}
	