package com.egeo.components.cms.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.cms.manage.read.InstCompanyReadManage;
import com.egeo.components.cms.dao.read.InstCompanyReadDAO;
import com.egeo.components.cms.po.InstCompanyPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class InstCompanyReadManageImpl implements InstCompanyReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private InstCompanyReadDAO instCompanyReadDAO;
	
	public InstCompanyPO findInstCompanyById(InstCompanyPO po) {
		InstCompanyPO instCompanypo = new InstCompanyPO();
		instCompanypo.setId(po.getId());
		return instCompanyReadDAO.findById(instCompanypo);
	}

	public PageResult<InstCompanyPO> findInstCompanyOfPage(InstCompanyPO po, Pagination page) {
		
		PageResult<InstCompanyPO> pageResult = new PageResult<InstCompanyPO>();
		List<InstCompanyPO> list = null;

		int cnt = instCompanyReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = instCompanyReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<InstCompanyPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<InstCompanyPO> findInstCompanyAll(InstCompanyPO po) {
		return instCompanyReadDAO.findAll(po,null);
	}

	@Override
	public List<InstCompanyPO> queryInstCompanyListByInstIdAndCompanyId(Long id, Long companyId, Long companyIdByType) {
		return instCompanyReadDAO.queryInstCompanyListByInstIdAndCompanyId(id,companyId, companyIdByType);
	}

}
	