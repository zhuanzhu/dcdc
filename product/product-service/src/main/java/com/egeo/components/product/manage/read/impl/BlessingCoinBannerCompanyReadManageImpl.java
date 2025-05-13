package com.egeo.components.product.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.read.BlessingCoinBannerCompanyReadManage;
import com.egeo.components.product.dao.read.BlessingCoinBannerCompanyReadDAO;
import com.egeo.components.product.po.BlessingCoinBannerCompanyPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class BlessingCoinBannerCompanyReadManageImpl implements BlessingCoinBannerCompanyReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private BlessingCoinBannerCompanyReadDAO blessingCoinBannerCompanyReadDAO;
	
	public BlessingCoinBannerCompanyPO findBlessingCoinBannerCompanyById(BlessingCoinBannerCompanyPO po) {
		BlessingCoinBannerCompanyPO blessingCoinBannerCompanypo = new BlessingCoinBannerCompanyPO();
		blessingCoinBannerCompanypo.setId(po.getId());
		return blessingCoinBannerCompanyReadDAO.findById(blessingCoinBannerCompanypo);
	}

	public PageResult<BlessingCoinBannerCompanyPO> findBlessingCoinBannerCompanyOfPage(BlessingCoinBannerCompanyPO po, Pagination page) {
		
		PageResult<BlessingCoinBannerCompanyPO> pageResult = new PageResult<BlessingCoinBannerCompanyPO>();
		List<BlessingCoinBannerCompanyPO> list = null;

		int cnt = blessingCoinBannerCompanyReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = blessingCoinBannerCompanyReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<BlessingCoinBannerCompanyPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<BlessingCoinBannerCompanyPO> findBlessingCoinBannerCompanyAll(BlessingCoinBannerCompanyPO po) {

		return blessingCoinBannerCompanyReadDAO.findAll(po,null);
	}
	
}
	