package com.egeo.components.user.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.user.manage.read.UserTempReadManage;
import com.egeo.components.user.dao.read.UserTempReadDAO;
import com.egeo.components.user.po.UserTempPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class UserTempReadManageImpl implements UserTempReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private UserTempReadDAO userTempReadDAO;
	
	public UserTempPO findUserTempById(UserTempPO po) {
		UserTempPO userTemppo = new UserTempPO();
		userTemppo.setId(po.getId());
		return userTempReadDAO.findById(userTemppo);
	}

	public PageResult<UserTempPO> findUserTempOfPage(UserTempPO po, Pagination page) {
		
		PageResult<UserTempPO> pageResult = new PageResult<UserTempPO>();
		List<UserTempPO> list = null;

		int cnt = userTempReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = userTempReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<UserTempPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<UserTempPO> findUserTempAll(UserTempPO po) {

		return userTempReadDAO.findAll(po,null);
	}
	/**
	 * 根据用户id查询预导入数据id
	 * @param createUserid 用户id
	 * @param platformId 平台id
	 * @return
	 */
	@Override
	public List<Long> findIdsByCreateUserid(Long createUserid, Long platformId) {
		// TODO Auto-generated method stub
		return userTempReadDAO.findIdsByCreateUserid(createUserid, platformId);
	}
	
}
	