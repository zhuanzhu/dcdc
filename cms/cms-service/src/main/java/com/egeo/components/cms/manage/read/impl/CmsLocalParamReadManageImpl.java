package com.egeo.components.cms.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.cms.manage.read.CmsLocalParamReadManage;
import com.egeo.components.cms.dao.read.CmsLocalParamReadDAO;
import com.egeo.components.cms.po.CmsLocalParamPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class CmsLocalParamReadManageImpl implements CmsLocalParamReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CmsLocalParamReadDAO cmsLocalParamReadDAO;
	
	public CmsLocalParamPO findCmsLocalParamById(CmsLocalParamPO po) {
		CmsLocalParamPO cmsLocalParampo = new CmsLocalParamPO();
		cmsLocalParampo.setId(po.getId());
		return cmsLocalParamReadDAO.findById(cmsLocalParampo);
	}

	public PageResult<CmsLocalParamPO> findCmsLocalParamOfPage(CmsLocalParamPO po, Pagination page) {
		
		PageResult<CmsLocalParamPO> pageResult = new PageResult<CmsLocalParamPO>();
		List<CmsLocalParamPO> list = null;

		int cnt = cmsLocalParamReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = cmsLocalParamReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<CmsLocalParamPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<CmsLocalParamPO> findCmsLocalParamAll(CmsLocalParamPO po) {

		return cmsLocalParamReadDAO.findAll(po,null);
	}
	
}
	