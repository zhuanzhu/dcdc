package com.egeo.components.user.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.user.manage.read.DownloadReadManage;
import com.egeo.components.user.dao.read.DownloadReadDAO;
import com.egeo.components.user.po.DownloadPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class DownloadReadManageImpl implements DownloadReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private DownloadReadDAO downloadReadDAO;
	
	public DownloadPO findDownloadById(DownloadPO po) {
		DownloadPO downloadpo = new DownloadPO();
		downloadpo.setId(po.getId());
		return downloadReadDAO.findById(downloadpo);
	}

	public PageResult<DownloadPO> findDownloadOfPage(DownloadPO po, Pagination page) {
		
		PageResult<DownloadPO> pageResult = new PageResult<DownloadPO>();
		List<DownloadPO> list = null;

		int cnt = downloadReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = downloadReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<DownloadPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<DownloadPO> findDownloadAll(DownloadPO po) {

		return downloadReadDAO.findAll(po,null);
	}

	@Override
	public PageResult<DownloadPO> findDownloadOfPageByBlurry(DownloadPO po, Pagination page) {
		PageResult<DownloadPO> pageResult = new PageResult<DownloadPO>();
		List<DownloadPO> list = null;

		int cnt = downloadReadDAO.countOfPageByBlurry(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = downloadReadDAO.findOfPageByBlurry(po, page);
		} else {
			list = new ArrayList<DownloadPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;
	}
	
}
	