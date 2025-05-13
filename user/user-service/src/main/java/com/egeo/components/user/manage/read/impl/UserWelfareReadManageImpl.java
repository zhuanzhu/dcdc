package com.egeo.components.user.manage.read.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.user.dao.read.UserWelfareReadDAO;
import com.egeo.components.user.manage.read.UserWelfareReadManage;
import com.egeo.components.user.po.UserPraiseCountRankPO;
import com.egeo.components.user.po.UserWelfarePO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class UserWelfareReadManageImpl implements UserWelfareReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private UserWelfareReadDAO userWelfareReadDAO;
	
	public UserWelfarePO findUserWelfareById(UserWelfarePO po) {
		UserWelfarePO userWelfarepo = new UserWelfarePO();
		userWelfarepo.setId(po.getId());
		return userWelfareReadDAO.findById(userWelfarepo);
	}

	public PageResult<UserWelfarePO> findUserWelfareOfPage(UserWelfarePO po, Pagination page) {
		
		PageResult<UserWelfarePO> pageResult = new PageResult<UserWelfarePO>();
		List<UserWelfarePO> list = null;

		int cnt = userWelfareReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = userWelfareReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<UserWelfarePO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<UserWelfarePO> findUserWelfareAll(UserWelfarePO po) {

		return userWelfareReadDAO.findAll(po,null);
	}

	@Override
	public List<UserWelfarePO> queryUserWelfaresByUserIds(List<Long> userIdList) {
		
		return userWelfareReadDAO.queryUserWelfaresByUserIds(userIdList);
	}

	@Override
	public PageResult<UserPraiseCountRankPO> queryPraiseCountRankPage(Long companyId, Integer type, Pagination page) {
		List<UserPraiseCountRankPO> poList=userWelfareReadDAO.queryPraiseCountRankPage(companyId,type,page);
		Integer totalCount=userWelfareReadDAO.queryPraiseCountRankTotalCount(companyId,type);
		PageResult<UserPraiseCountRankPO> res=new PageResult<>();
		res.setList(poList);
		res.setTotalSize(totalCount);
		res.copy(page);
		return res;
	}

	@Override
	public UserPraiseCountRankPO queryPraiseCountRankByUserId(Long userId, Integer type,Long companyId) {
		return userWelfareReadDAO.queryPraiseCountRankByUserId(userId,type,companyId);
	}

	@Override
	public UserWelfarePO queryUserWelfareByUserId(Long userId) {
		return userWelfareReadDAO.queryUserWelfareByUserId(userId);
	}
	
}
	