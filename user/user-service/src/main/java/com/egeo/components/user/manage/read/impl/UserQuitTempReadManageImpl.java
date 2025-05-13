package com.egeo.components.user.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.user.manage.read.UserQuitTempReadManage;
import com.egeo.components.user.dao.read.UserQuitTempReadDAO;
import com.egeo.components.user.po.UserQuitTempPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class UserQuitTempReadManageImpl implements UserQuitTempReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private UserQuitTempReadDAO userQuitTempReadDAO;
	
	public UserQuitTempPO findUserQuitTempById(UserQuitTempPO po) {
		UserQuitTempPO userQuitTemppo = new UserQuitTempPO();
		userQuitTemppo.setId(po.getId());
		return userQuitTempReadDAO.findById(userQuitTemppo);
	}

	public PageResult<UserQuitTempPO> findUserQuitTempOfPage(UserQuitTempPO po, Pagination page) {
		
		PageResult<UserQuitTempPO> pageResult = new PageResult<UserQuitTempPO>();
		List<UserQuitTempPO> list = null;

		int cnt = userQuitTempReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = userQuitTempReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<UserQuitTempPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<UserQuitTempPO> findUserQuitTempAll(UserQuitTempPO po) {

		return userQuitTempReadDAO.findAll(po,null);
	}
	
}
	