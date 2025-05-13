package com.egeo.components.cms.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.cms.manage.read.BannerInstReadManage;
import com.egeo.components.cms.dao.read.BannerInstReadDAO;
import com.egeo.components.cms.po.BannerInstPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class BannerInstReadManageImpl implements BannerInstReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private BannerInstReadDAO bannerInstReadDAO;
	
	public BannerInstPO findBannerInstById(BannerInstPO po) {
		BannerInstPO bannerInstpo = new BannerInstPO();
		bannerInstpo.setId(po.getId());
		return bannerInstReadDAO.findById(bannerInstpo);
	}

	public PageResult<BannerInstPO> findBannerInstOfPage(BannerInstPO po, Pagination page) {
		
		PageResult<BannerInstPO> pageResult = new PageResult<BannerInstPO>();
		List<BannerInstPO> list = null;

		int cnt = bannerInstReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = bannerInstReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<BannerInstPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<BannerInstPO> findBannerInstAll(BannerInstPO po) {

		return bannerInstReadDAO.findAll(po,null);
	}
	
}
	