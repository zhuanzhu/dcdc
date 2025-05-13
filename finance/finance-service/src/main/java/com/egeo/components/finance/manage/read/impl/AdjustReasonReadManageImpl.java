package com.egeo.components.finance.manage.read.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.finance.dao.read.AdjustReasonReadDAO;
import com.egeo.components.finance.manage.read.AdjustReasonReadManage;
import com.egeo.components.finance.po.AdjustReasonPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class AdjustReasonReadManageImpl implements AdjustReasonReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private AdjustReasonReadDAO adjustReasonReadDAO;
	
	public AdjustReasonPO findAdjustReasonById(AdjustReasonPO po) {
		AdjustReasonPO adjustReasonpo = new AdjustReasonPO();
		adjustReasonpo.setId(po.getId());
		return adjustReasonReadDAO.findById(adjustReasonpo);
	}

	public PageResult<AdjustReasonPO> findAdjustReasonOfPage(AdjustReasonPO po, Pagination page) {
		
		PageResult<AdjustReasonPO> pageResult = new PageResult<AdjustReasonPO>();
		List<AdjustReasonPO> list = null;

		int cnt = adjustReasonReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = adjustReasonReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<AdjustReasonPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<AdjustReasonPO> findAdjustReasonAll(AdjustReasonPO po) {

		return adjustReasonReadDAO.findAll(po,null);
	}

	@Override
	public List<AdjustReasonPO> queryAdjustReasons(Long companyId) {
		
		return adjustReasonReadDAO.queryAdjustReasons(companyId);
	}

	@Override
	public PageResult<AdjustReasonPO> queryAdjustReasonPage(Integer type, Long companyId, Integer disabled,
			Long platformId,Pagination page) {
		List<AdjustReasonPO> poList=adjustReasonReadDAO.queryAdjustReasonPage(type, companyId, disabled,platformId, page);
		Integer totalCount=adjustReasonReadDAO.queryAdjustReasonPageTotalSize(type, companyId, disabled,platformId);
		PageResult<AdjustReasonPO> poPage=new PageResult<>();
		poPage.setList(poList);
		poPage.setTotalSize(totalCount);
		poPage.copy(page);
		return poPage;
	}

	@Override
	public List<AdjustReasonPO> queryAdjustReasonsByTypes(Long platformId, List<Integer> typeList, Long accountId, Long companyId) {
		return adjustReasonReadDAO.queryAdjustReasonsByTypes(platformId,typeList, accountId,companyId);
	}
	
}
	