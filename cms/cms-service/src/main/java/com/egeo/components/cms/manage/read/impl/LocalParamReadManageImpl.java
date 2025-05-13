package com.egeo.components.cms.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.cms.manage.read.LocalParamReadManage;
import com.egeo.components.cms.dao.read.LocalParamReadDAO;
import com.egeo.components.cms.po.LocalParamPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class LocalParamReadManageImpl implements LocalParamReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private LocalParamReadDAO localParamReadDAO;
	
	public LocalParamPO findLocalParamById(LocalParamPO po) {
		LocalParamPO localParampo = new LocalParamPO();
		localParampo.setId(po.getId());
		return localParamReadDAO.findById(localParampo);
	}

	public PageResult<LocalParamPO> findLocalParamOfPage(LocalParamPO po, Pagination page) {
		
		PageResult<LocalParamPO> pageResult = new PageResult<LocalParamPO>();
		List<LocalParamPO> list = null;

		int cnt = localParamReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = localParamReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<LocalParamPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<LocalParamPO> findLocalParamAll(LocalParamPO po) {

		return localParamReadDAO.findAll(po,null);
	}
	
}
	