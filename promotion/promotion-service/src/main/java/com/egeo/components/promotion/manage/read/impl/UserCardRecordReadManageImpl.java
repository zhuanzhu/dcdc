package com.egeo.components.promotion.manage.read.impl;

import com.egeo.components.promotion.dao.read.UserCardRecordReadDAO;
import com.egeo.components.promotion.manage.read.UserCardRecordReadManage;
import com.egeo.components.promotion.po.UserCardRecordPO;
import com.egeo.components.promotion.vo.SumUserCardTypeAmountVO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserCardRecordReadManageImpl implements UserCardRecordReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private UserCardRecordReadDAO userCardRecordReadDAO;

	@Override
	public UserCardRecordPO findUserCardRecordById(UserCardRecordPO po) {
		UserCardRecordPO UserCardRecordpo = new UserCardRecordPO();
		UserCardRecordpo.setId(po.getId());
		return userCardRecordReadDAO.findById(UserCardRecordpo);
	}

	@Override
	public PageResult<UserCardRecordPO> findUserCardRecordOfPage(UserCardRecordPO po, Pagination page) {

		PageResult<UserCardRecordPO> pageResult = new PageResult<>();
		List<UserCardRecordPO> list = null;

		int cnt = userCardRecordReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = userCardRecordReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	@Override
	public List<UserCardRecordPO> findUserCardRecordAll(UserCardRecordPO po) {

		return userCardRecordReadDAO.findAll(po,null);
	}

	@Override
	public Integer countUserCardRecord(UserCardRecordPO po){
		int cnt = userCardRecordReadDAO.countOfPage(po);
		return cnt;
	}

	@Override
	public List<UserCardRecordPO> sumUserCardTypeAmount(UserCardRecordPO po){

		return userCardRecordReadDAO.sumUserCardTypeAmount(po);
	}
}
