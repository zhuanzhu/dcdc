package com.egeo.components.user.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.user.manage.read.UserInformationReadReadManage;
import com.egeo.components.user.dao.read.UserInformationReadReadDAO;
import com.egeo.components.user.po.UserInformationReadPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class UserInformationReadReadManageImpl implements UserInformationReadReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private UserInformationReadReadDAO userInformationReadReadDAO;
	
	public UserInformationReadPO findUserInformationReadById(UserInformationReadPO po) {
		UserInformationReadPO userInformationReadpo = new UserInformationReadPO();
		userInformationReadpo.setId(po.getId());
		return userInformationReadReadDAO.findById(userInformationReadpo);
	}

	public PageResult<UserInformationReadPO> findUserInformationReadOfPage(UserInformationReadPO po, Pagination page) {
		
		PageResult<UserInformationReadPO> pageResult = new PageResult<UserInformationReadPO>();
		List<UserInformationReadPO> list = null;

		int cnt = userInformationReadReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = userInformationReadReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<UserInformationReadPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<UserInformationReadPO> findUserInformationReadAll(UserInformationReadPO po) {

		return userInformationReadReadDAO.findAll(po,null);
	}
	
}
	