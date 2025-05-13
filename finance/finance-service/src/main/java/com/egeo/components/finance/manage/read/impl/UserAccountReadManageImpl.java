package com.egeo.components.finance.manage.read.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.finance.dao.read.UserAccountReadDAO;
import com.egeo.components.finance.manage.read.UserAccountReadManage;
import com.egeo.components.finance.po.UserAccountPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class UserAccountReadManageImpl implements UserAccountReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private UserAccountReadDAO userAccountReadDAO;
	
	public UserAccountPO findUserAccountById(UserAccountPO po) {
		UserAccountPO userAccountpo = new UserAccountPO();
		userAccountpo.setId(po.getId());
		return userAccountReadDAO.findById(userAccountpo);
	}

	public PageResult<UserAccountPO> findUserAccountOfPage(UserAccountPO po, Pagination page) {
		
		PageResult<UserAccountPO> pageResult = new PageResult<UserAccountPO>();
		List<UserAccountPO> list = null;

		int cnt = userAccountReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = userAccountReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<UserAccountPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<UserAccountPO> findUserAccountAll(UserAccountPO po) {

		return userAccountReadDAO.findAll(po,null);
	}

	@Override
	public List<UserAccountPO> queryUserAccountByUserId(Long userId) {
		return userAccountReadDAO.queryUserAccountByUserId(userId);
	}

	@Override
	public UserAccountPO queryUserAccountByUserIdAndType(Long id, Integer type) {
		return userAccountReadDAO.queryUserAccountByUserIdAndType(id,type);
	}

	@Override
	public UserAccountPO queryUserAccountById(Long accountId) {
		
		return userAccountReadDAO.queryUserAccountById(accountId);
	}

	@Override
	public List<UserAccountPO> queryUserAccountByUserIds(List<Long> userIdList) {
		return userAccountReadDAO.queryUserAccountByUserIds(userIdList);
	}

	@Override
	public  List<UserAccountPO> queryUserAccountByParam(UserAccountPO po) {
		return userAccountReadDAO.queryUserAccountByParam(po);
	}

	@Override
	public BigDecimal findBeforeDisabledBalance(Long userId){
		return userAccountReadDAO.findBeforeDisabledBalance(userId);
	}
}
	