package com.egeo.components.user.manage.read.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.user.manage.read.UserLoginReadManage;
import com.egeo.components.user.condition.UserLoginCondition;
import com.egeo.components.user.dao.read.UserLoginReadDAO;
import com.egeo.components.user.po.UserLoginPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service
public class UserLoginReadManageImpl implements UserLoginReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private UserLoginReadDAO userLoginReadDAO;
	@Override
	public PageResult<UserLoginPO> findOfPage(UserLoginCondition condition, Pagination page) {
		PageResult<UserLoginPO> pageResult = new PageResult<UserLoginPO>();
		List<UserLoginPO> list = null;

		int cnt = userLoginReadDAO.countConditionOfPage(condition);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = userLoginReadDAO.findConditionOfPage(condition, page);
		} else {
			list = new ArrayList<UserLoginPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;
	}
	
	@Override
	public List<UserLoginPO> findByUserIds(List<Long> userIds, Long startTime, Long endTime) {
		Date start = null;
		Date end = null;
		if(startTime != null) {
			start = new Date(startTime);
		}
		if(endTime != null) {
			end = new Date(endTime);
		}
		return userLoginReadDAO.findByUserIds(userIds,start,end);
	}
	
}
	