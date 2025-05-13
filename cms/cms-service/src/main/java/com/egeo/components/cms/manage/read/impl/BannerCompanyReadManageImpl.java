package com.egeo.components.cms.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.cms.manage.read.BannerCompanyReadManage;
import com.egeo.components.cms.dao.read.BannerCompanyReadDAO;
import com.egeo.components.cms.po.BannerCompanyPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class BannerCompanyReadManageImpl implements BannerCompanyReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private BannerCompanyReadDAO bannerCompanyReadDAO;
	
	public BannerCompanyPO findBannerCompanyById(BannerCompanyPO po) {
		BannerCompanyPO bannerCompanypo = new BannerCompanyPO();
		bannerCompanypo.setId(po.getId());
		return bannerCompanyReadDAO.findById(bannerCompanypo);
	}

	public PageResult<BannerCompanyPO> findBannerCompanyOfPage(BannerCompanyPO po, Pagination page) {
		
		PageResult<BannerCompanyPO> pageResult = new PageResult<BannerCompanyPO>();
		List<BannerCompanyPO> list = null;

		int cnt = bannerCompanyReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = bannerCompanyReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<BannerCompanyPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	@Override
	public PageResult<BannerCompanyPO> findBannerCompanysOfPage(BannerCompanyPO po, Pagination page,
			List<Long> companyIdList) {
		
		PageResult<BannerCompanyPO> pageResult = new PageResult<BannerCompanyPO>();
		List<BannerCompanyPO> list = null;

		int cnt = bannerCompanyReadDAO.countEnterpriseOfPage(po,companyIdList);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = bannerCompanyReadDAO.findEnterpriseOfPage(po, page,companyIdList);
		} else {
			list = new ArrayList<BannerCompanyPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}
	public List<BannerCompanyPO> findBannerCompanyAll(BannerCompanyPO po) {

		return bannerCompanyReadDAO.findAll(po,null);
	}


	@Override
	public List<BannerCompanyPO> findBannerCompanysAll(BannerCompanyPO po, List<Long> companyIdList) {
		// TODO Auto-generated method stub

		return bannerCompanyReadDAO.findEnterpriseAll(po,null,companyIdList);
	}
	
}
	