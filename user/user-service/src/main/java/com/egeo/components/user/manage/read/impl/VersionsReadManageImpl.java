package com.egeo.components.user.manage.read.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.user.dao.read.VersionsReadDAO;
import com.egeo.components.user.manage.read.VersionsReadManage;
import com.egeo.components.user.po.VersionsPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class VersionsReadManageImpl implements VersionsReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private VersionsReadDAO versionsReadDAO;
	
	public VersionsPO findVersionsById(VersionsPO po) {
		VersionsPO versionspo = new VersionsPO();
		versionspo.setId(po.getId());
		return versionsReadDAO.findById(versionspo);
	}

	public PageResult<VersionsPO> findVersionsOfPage(VersionsPO po, Pagination page) {
		
		PageResult<VersionsPO> pageResult = new PageResult<VersionsPO>();
		List<VersionsPO> list = null;

		int cnt = versionsReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = versionsReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<VersionsPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<VersionsPO> findVersionsAll(VersionsPO po) {

		return versionsReadDAO.findAll(po,null);
	}

	@Override
	public VersionsPO queryVerisonByVersionCode(Integer vCode, Integer type, Integer user, Long platformId) {
		
		return versionsReadDAO.queryVerisonByVersionCode(vCode,type,user,platformId);
	}

	@Override
	public VersionsPO queryLatestVersion(Integer type,Integer user) {
		
		return versionsReadDAO.queryLatestVersion(type,user);
	}

	@Override
	public List<VersionsPO> queryLaterVersionsByVersionCode(Integer vCode, Integer type) {
		return versionsReadDAO.queryLaterVersionsByVersionCode(vCode,type);
	}

	@Override
	public Integer queryMaxVersionCode(Integer type,Integer user,Long platformId) {
		return versionsReadDAO.queryMaxVersionCode(type,user,platformId);
	}

	@Override
	public VersionsPO queryLatestOfficialVersion(VersionsPO po) {
		return versionsReadDAO.queryLatestOfficialVersion(po);
	}

	@Override
	public PageResult<VersionsPO> findVersionsOfPageByBlurry(VersionsPO po, Pagination page) {
		PageResult<VersionsPO> pageResult = new PageResult<VersionsPO>();
		List<VersionsPO> list = null;

		int cnt = versionsReadDAO.countOfPageByBlurry(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = versionsReadDAO.findOfPageByBlurry(po, page);
		} else {
			list = new ArrayList<VersionsPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;
	}

    @Override
    public PageResult<VersionsPO> getVersionsOfPage(VersionsPO po, Pagination page) {
		PageResult<VersionsPO> pageResult = new PageResult<>();
		List<VersionsPO> list = null;
		int cnt = versionsReadDAO.countOfPageByBlurry(po);
		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = versionsReadDAO.getVersionsOfPage(po, page);
		} else {
			list = new ArrayList<VersionsPO>();
		}
		logger.info("po:"+po.getId());
		logger.info("查出来的list,size:"+list.size());
		for (VersionsPO l : list ) {
			logger.info("版本号:"+l.getVersionsMark());
			logger.info("创建时间:"+l.getCreateTime());
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;
    }

}
	