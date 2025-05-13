package com.egeo.components.user.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.user.manage.read.UserQuitReadManage;
import com.egeo.components.user.dao.read.UserQuitReadDAO;
import com.egeo.components.user.po.UserQuitPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class UserQuitReadManageImpl implements UserQuitReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private UserQuitReadDAO userQuitReadDAO;
	
	public UserQuitPO findUserQuitById(UserQuitPO po) {
		UserQuitPO userQuitpo = new UserQuitPO();
		userQuitpo.setId(po.getId());
		return userQuitReadDAO.findById(userQuitpo);
	}

	public PageResult<UserQuitPO> findUserQuitOfPage(UserQuitPO po, Pagination page) {
		
		PageResult<UserQuitPO> pageResult = new PageResult<UserQuitPO>();
		List<UserQuitPO> list = null;

		int cnt = userQuitReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = userQuitReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<UserQuitPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<UserQuitPO> findUserQuitAll(UserQuitPO po) {

		return userQuitReadDAO.findAll(po,null);
	}
	
}
	