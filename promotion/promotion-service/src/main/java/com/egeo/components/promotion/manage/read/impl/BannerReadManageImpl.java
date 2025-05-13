package com.egeo.components.promotion.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.promotion.manage.read.BannerReadManage;
import com.egeo.components.promotion.dao.read.BannerReadDAO;
import com.egeo.components.promotion.po.BannerPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class BannerReadManageImpl implements BannerReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private BannerReadDAO bannerReadDAO;
	
	public BannerPO findBannerById(BannerPO po) {
		BannerPO bannerpo = new BannerPO();
		bannerpo.setId(po.getId());
		return bannerReadDAO.findById(bannerpo);
	}

	public PageResult<BannerPO> findBannerOfPage(BannerPO po, Pagination page) {
		
		PageResult<BannerPO> pageResult = new PageResult<BannerPO>();
		List<BannerPO> list = null;

		int cnt = bannerReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = bannerReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<BannerPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}
	public List<BannerPO> findBannerAll(BannerPO po) {

		return bannerReadDAO.findAll(po,null);
	}
	/**
	 * 查询Banner最大排序值
	 * @return
	 */
	@Override
	public Integer maxSortValue() {
		return bannerReadDAO.maxSortValue();
	}
	
}
	