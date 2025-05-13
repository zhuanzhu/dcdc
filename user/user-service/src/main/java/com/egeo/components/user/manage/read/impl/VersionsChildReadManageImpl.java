package com.egeo.components.user.manage.read.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.user.condition.VersionsChildCondition;
import com.egeo.components.user.dao.read.VersionsChildReadDAO;
import com.egeo.components.user.manage.read.VersionsChildReadManage;
import com.egeo.components.user.po.VersionsChildPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class VersionsChildReadManageImpl implements VersionsChildReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private VersionsChildReadDAO versionsChildReadDAO;
	
	public VersionsChildPO findVersionsChildById(VersionsChildPO po) {
		VersionsChildPO versionsChildpo = new VersionsChildPO();
		versionsChildpo.setId(po.getId());
		return versionsChildReadDAO.findById(versionsChildpo);
	}

	public PageResult<VersionsChildPO> findVersionsChildOfPage(VersionsChildPO po, Pagination page) {
		
		PageResult<VersionsChildPO> pageResult = new PageResult<VersionsChildPO>();
		List<VersionsChildPO> list = null;

		int cnt = versionsChildReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = versionsChildReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<VersionsChildPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<VersionsChildPO> findVersionsChildAll(VersionsChildPO po) {

		return versionsChildReadDAO.findAll(po,null);
	}
	/**
	 * 根据条件分页查询子版本和及其渠道信息
	 * @param vo
	 * @param page
	 * @param req
	 * @return
	 */
	@Override
	public PageResult<VersionsChildCondition> versionsChildAndChannelOfPage(VersionsChildPO po, Pagination page) {
		PageResult<VersionsChildCondition> pageResult = new PageResult<VersionsChildCondition>();
		List<VersionsChildCondition> list = null;

		int cnt = versionsChildReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = versionsChildReadDAO.versionsChildAndChannelOfPage(po, page);
		} else {
			list = new ArrayList<VersionsChildCondition>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;
	}
	
}
	