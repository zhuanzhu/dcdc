package com.egeo.components.product.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.read.StandardUnitCompanyReadManage;
import com.egeo.components.product.dao.read.StandardUnitCompanyReadDAO;
import com.egeo.components.product.po.StandardUnitCompanyPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class StandardUnitCompanyReadManageImpl implements StandardUnitCompanyReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private StandardUnitCompanyReadDAO standardUnitCompanyReadDAO;
	
	public StandardUnitCompanyPO findStandardUnitCompanyById(StandardUnitCompanyPO po) {
		StandardUnitCompanyPO standardUnitCompanypo = new StandardUnitCompanyPO();
		standardUnitCompanypo.setId(po.getId());
		return standardUnitCompanyReadDAO.findById(standardUnitCompanypo);
	}

	public PageResult<StandardUnitCompanyPO> findStandardUnitCompanyOfPage(StandardUnitCompanyPO po, Pagination page) {
		
		PageResult<StandardUnitCompanyPO> pageResult = new PageResult<StandardUnitCompanyPO>();
		List<StandardUnitCompanyPO> list = null;

		int cnt = standardUnitCompanyReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = standardUnitCompanyReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<StandardUnitCompanyPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<StandardUnitCompanyPO> findStandardUnitCompanyAll(StandardUnitCompanyPO po) {

		return standardUnitCompanyReadDAO.findAll(po,null);
	}

	@Override
	public boolean querySuCompanyAvailable(Long suId, Long companyId,Long clientId, Integer companyType) {
		int count=standardUnitCompanyReadDAO.querySuCompanyAvailableCount(suId,companyId,clientId, companyType);
		return count>0;
	}

	@Override
	public List<StandardUnitCompanyPO> findSuCompanyByCompanyIdAndTypeAndSuIds(StandardUnitCompanyPO po,List<Long> suIds) {
		
		return standardUnitCompanyReadDAO.findSuCompanyByCompanyIdAndTypeAndSuIds(po,suIds);
	}
	
}
	