package com.egeo.components.user.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.user.manage.read.InsuranceLoginReadManage;
import com.egeo.components.user.dao.read.InsuranceLoginReadDAO;
import com.egeo.components.user.po.InsuranceLoginPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class InsuranceLoginReadManageImpl implements InsuranceLoginReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private InsuranceLoginReadDAO insuranceLoginReadDAO;
	
	public InsuranceLoginPO findInsuranceLoginById(InsuranceLoginPO po) {
		InsuranceLoginPO insuranceLoginpo = new InsuranceLoginPO();
		insuranceLoginpo.setId(po.getId());
		return insuranceLoginReadDAO.findById(insuranceLoginpo);
	}

	public PageResult<InsuranceLoginPO> findInsuranceLoginOfPage(InsuranceLoginPO po, Pagination page) {
		
		PageResult<InsuranceLoginPO> pageResult = new PageResult<InsuranceLoginPO>();
		List<InsuranceLoginPO> list = null;

		int cnt = insuranceLoginReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = insuranceLoginReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<InsuranceLoginPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<InsuranceLoginPO> findInsuranceLoginAll(InsuranceLoginPO po) {

		return insuranceLoginReadDAO.findAll(po,null);
	}

	@Override
	public InsuranceLoginPO queryInsuranceLoginByUserId(Long userId) {
		
		return insuranceLoginReadDAO.queryInsuranceLoginByUserId(userId);
	}
	
}
	