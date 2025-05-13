package com.egeo.components.user.manage.read.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.user.manage.read.UserInformationReadManage;
import com.egeo.components.user.condition.UserInformationCondition;
import com.egeo.components.user.dao.read.UserInformationReadDAO;
import com.egeo.components.user.po.UserInformationPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class UserInformationReadManageImpl implements UserInformationReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private UserInformationReadDAO userInformationReadDAO;
	
	public UserInformationPO findUserInformationById(UserInformationPO po) {
		UserInformationPO userInformationpo = new UserInformationPO();
		userInformationpo.setId(po.getId());
		return userInformationReadDAO.findById(userInformationpo);
	}

	public PageResult<UserInformationPO> findUserInformationOfPage(UserInformationPO po, Pagination page) {
		
		PageResult<UserInformationPO> pageResult = new PageResult<UserInformationPO>();
		List<UserInformationPO> list = null;

		int cnt = userInformationReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = userInformationReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<UserInformationPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<UserInformationPO> findUserInformationAll(UserInformationPO po) {

		return userInformationReadDAO.findAll(po,null);
	}
	/**
	 * 根据用户id查询用户消息
	 * @param dto
	 * @param page
	 * @return
	 */
	@Override
	public PageResult<UserInformationCondition> findUserInformationOfByUserIdPage(UserInformationPO po, Pagination page) {
		PageResult<UserInformationCondition> pageResult = new PageResult<UserInformationCondition>();
		List<UserInformationCondition> list = null;

		int cnt = userInformationReadDAO.countUserInformationOfByUserIdPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = userInformationReadDAO.findUserInformationOfByUserIdPage(po, page);
		} else {
			list = new ArrayList<UserInformationCondition>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;
	}
	/**
	 * 根据用户id查询用户消息未读信息
	 * @param vo
	 * @param page
	 * @param req
	 * @return
	 */
	@Override
	public int findUnreadByUserId(UserInformationPO po) {
		// TODO Auto-generated method stub
		return userInformationReadDAO.findUnreadByUserId(po);
	}
	
}
	