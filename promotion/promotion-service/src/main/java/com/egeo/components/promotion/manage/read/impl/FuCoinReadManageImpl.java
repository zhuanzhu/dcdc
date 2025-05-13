package com.egeo.components.promotion.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.promotion.manage.read.FuCoinReadManage;
import com.egeo.components.promotion.dao.read.FuCoinReadDAO;
import com.egeo.components.promotion.po.FuCoinPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class FuCoinReadManageImpl implements FuCoinReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private FuCoinReadDAO fuCoinReadDAO;
	
	public FuCoinPO findFuCoinById(FuCoinPO po) {
		FuCoinPO fuCoinpo = new FuCoinPO();
		fuCoinpo.setId(po.getId());
		return fuCoinReadDAO.findById(fuCoinpo);
	}

	public PageResult<FuCoinPO> findFuCoinOfPage(FuCoinPO po, Pagination page) {
		
		PageResult<FuCoinPO> pageResult = new PageResult<FuCoinPO>();
		List<FuCoinPO> list = null;

		int cnt = fuCoinReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = fuCoinReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<FuCoinPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<FuCoinPO> findFuCoinAll(FuCoinPO po) {

		return fuCoinReadDAO.findAll(po,null);
	}
	/**
	 * 根据用户id查询用户积分额度
	 * @param memberId
	 * @param platformId
	 * @return
	 */
	@Override
	public FuCoinPO findFCoinByUserId(Long memberId, Long platformId) {
		
		return fuCoinReadDAO.findFCoinByUserId(memberId, platformId);
	}
	
}
	