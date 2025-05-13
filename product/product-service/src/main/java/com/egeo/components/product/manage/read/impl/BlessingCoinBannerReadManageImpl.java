package com.egeo.components.product.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.read.BlessingCoinBannerReadManage;
import com.egeo.components.product.dao.read.BlessingCoinBannerReadDAO;
import com.egeo.components.product.po.BlessingCoinBannerPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class BlessingCoinBannerReadManageImpl implements BlessingCoinBannerReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private BlessingCoinBannerReadDAO blessingCoinBannerReadDAO;
	
	public BlessingCoinBannerPO findBlessingCoinBannerById(BlessingCoinBannerPO po) {
		BlessingCoinBannerPO blessingCoinBannerpo = new BlessingCoinBannerPO();
		blessingCoinBannerpo.setId(po.getId());
		return blessingCoinBannerReadDAO.findById(blessingCoinBannerpo);
	}

	public PageResult<BlessingCoinBannerPO> findBlessingCoinBannerOfPage(BlessingCoinBannerPO po, Pagination page) {
		
		PageResult<BlessingCoinBannerPO> pageResult = new PageResult<BlessingCoinBannerPO>();
		List<BlessingCoinBannerPO> list = null;

		int cnt = blessingCoinBannerReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = blessingCoinBannerReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<BlessingCoinBannerPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<BlessingCoinBannerPO> findBlessingCoinBannerAll(BlessingCoinBannerPO po) {

		return blessingCoinBannerReadDAO.findAll(po,null);
	}
	/**
	 * 客户端查询所有有效轮播图信息
	 * @param vo
	 * @param req
	 * @return
	 */
	@Override
	public List<BlessingCoinBannerPO> findBlessingCoinBannerAllApp(BlessingCoinBannerPO po) {
		// TODO Auto-generated method stub
		return blessingCoinBannerReadDAO.findBlessingCoinBannerAllApp(po,null);
	}
	
}
	