package com.egeo.components.promotion.manage.read.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.promotion.manage.read.ActivityMerchantProdReadManage;
import com.egeo.components.promotion.condition.ActivityMerchantProdCondition;
import com.egeo.components.promotion.dao.read.ActivityMerchantProdReadDAO;
import com.egeo.components.promotion.po.ActivityMerchantProdPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service
public class ActivityMerchantProdReadManageImpl implements ActivityMerchantProdReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ActivityMerchantProdReadDAO activityMerchantProdReadDAO;
	
	@Override
	public PageResult<ActivityMerchantProdPO> findPage(Pagination page, ActivityMerchantProdPO po) {
		int cnt = activityMerchantProdReadDAO.countOfPage(po);
        List<ActivityMerchantProdPO> list = new ArrayList<ActivityMerchantProdPO>();
        if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
            page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
            list = activityMerchantProdReadDAO.findOfPage(po, page);
        }
        PageResult<ActivityMerchantProdPO> pageResult = new PageResult<ActivityMerchantProdPO>();
        pageResult.setList(list);
        pageResult.setTotalSize(cnt);
        pageResult.setPageNo(page.getPageNo());
        pageResult.setPageSize(page.getPageSize());
        return pageResult;
	}

	@Override
	public List<ActivityMerchantProdPO> findAll(ActivityMerchantProdPO po) {
		return activityMerchantProdReadDAO.findAll(po,null);
	}
	/**
	 * 查询指定第几个有效活动及商品id
	 * @param date
	 * @param pages
	 * @return
	 */
	@Override
	public List<ActivityMerchantProdCondition> activityMerchantProdByPages(Date date, Integer pages,Long platformId) {
		//指针因为从0开始加1
		return activityMerchantProdReadDAO.activityMerchantProdByPages(date, pages - 1,platformId);
	}
	
}
	