package com.egeo.components.promotion.manage.read.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.promotion.manage.read.ActivityReadManage;
import com.egeo.components.promotion.dao.read.ActivityReadDAO;
import com.egeo.components.promotion.po.ActivityPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service
public class ActivityReadManageImpl implements ActivityReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ActivityReadDAO activityReadDAO;
	
	@Override
	public PageResult<ActivityPO> findPage(Pagination page, ActivityPO po) {
		int cnt = activityReadDAO.countOfPage(po);
        List<ActivityPO> list = new ArrayList<ActivityPO>();
        if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
            page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
            list = activityReadDAO.findOfPage(po, page);
        }
        PageResult<ActivityPO> pageResult = new PageResult<ActivityPO>();
        pageResult.setList(list);
        pageResult.setTotalSize(cnt);
        pageResult.setPageNo(page.getPageNo());
        pageResult.setPageSize(page.getPageSize());
        return pageResult;
	}

	@Override
	public ActivityPO findById(ActivityPO po) {
		return activityReadDAO.findById(po);
	}

	@Override
	public List<ActivityPO> findAll(ActivityPO po) {
		return activityReadDAO.findAll(po,null);
	}

	@Override
	public boolean activityByMerchantProdIdAndDate(Date date, Long merchantProdId) {
		List<ActivityPO> list = activityReadDAO.activityByMerchantProdIdAndDate(date, merchantProdId);
		if(list.size() > 0){
			return true;
		}
		return false;
	}
	
}
	