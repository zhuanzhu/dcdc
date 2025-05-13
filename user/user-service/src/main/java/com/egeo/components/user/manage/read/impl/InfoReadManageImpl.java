package com.egeo.components.user.manage.read.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.user.manage.read.InfoReadManage;
import com.egeo.components.user.dao.read.InfoReadDAO;
import com.egeo.components.user.dao.write.UserInfoWriteDAO;
import com.egeo.components.user.po.InfoPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class InfoReadManageImpl implements InfoReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private InfoReadDAO infoReadDAO;
	
	@Autowired
	private UserInfoWriteDAO userInfoWriteDAO;
	
	public InfoPO findInfoById(InfoPO po) {
		InfoPO infopo = new InfoPO();
		infopo.setId(po.getId());
		return infoReadDAO.findById(infopo);
	}

	public PageResult<InfoPO> findInfoOfPage(InfoPO po, Pagination page) {
		
		PageResult<InfoPO> pageResult = new PageResult<InfoPO>();
		List<InfoPO> list = null;

		int cnt = infoReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = infoReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<InfoPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<InfoPO> findInfoAll(InfoPO po) {

		return infoReadDAO.findAll(po,null);
	}

	@Override
	public PageResult<InfoPO> findUserInfoOfPage(InfoPO po, Pagination page) {
		
		PageResult<InfoPO> pageResult = new PageResult<InfoPO>();
		List<InfoPO> list = null;

		int cnt = infoReadDAO.countUserInfoOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = infoReadDAO.findUserInfoOfPage(po, page);
		} else {
			list = new ArrayList<InfoPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;
	}

	@Override
	public int findUnreadInfoSum(Long userId, int type, Long platformId) {
		return infoReadDAO.findUnreadInfoSum(userId,type,platformId);
	}

	@Override
	public List<InfoPO> findUserInfoForMsgBox(Long userId, Integer isRead, Integer type, Long platformId, Date createTime, Integer count) {
		return infoReadDAO.findUserInfoForMsgBox(userId, isRead, type, platformId, createTime, count);
	}

}
	