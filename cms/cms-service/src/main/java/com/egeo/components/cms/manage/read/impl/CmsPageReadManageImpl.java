package com.egeo.components.cms.manage.read.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.cms.manage.read.CmsPageReadManage;
import com.egeo.components.cms.condition.CmsPageCondition;
import com.egeo.components.cms.dao.read.CmsPageReadDAO;
import com.egeo.components.cms.po.CmsPagePO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class CmsPageReadManageImpl implements CmsPageReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CmsPageReadDAO cmsPageReadDAO;
	
	public CmsPagePO findCmsPageById(CmsPagePO po) {
		CmsPagePO cmsPagepo = new CmsPagePO();
		cmsPagepo.setId(po.getId());
		return cmsPageReadDAO.findById(cmsPagepo);
	}

	public List<CmsPagePO> findCmsPageAll(CmsPagePO po) {

		return cmsPageReadDAO.findAll(po,null);
	}

	@Override
	public List<CmsPageCondition> findCmsPageAllByClientType(CmsPageCondition condition) {
		
		return cmsPageReadDAO.findAllByClientType(condition);
	}

	@Override
	public int findSupportPageByVersionCode(Long pageId, Long platformId, Integer androidVersionCode,
			Integer iosVersionCode) {
		return cmsPageReadDAO.findSupportPageByVersionCode(pageId, platformId, androidVersionCode, iosVersionCode);
	}

	@Override
	public Long findSupportPageByPageTypeAndVersionCode(Integer pageType, Long platformId, Integer androidVersionCode,
			Integer iosVersionCode, Long companyId, Long companyAllId) {
		return cmsPageReadDAO.findSupportPageByPageTypeAndVersionCode(pageType, platformId, androidVersionCode, iosVersionCode, companyId, companyAllId);
	}

	@Override
	public PageResult<CmsPageCondition> findCmsPageOfPage(CmsPageCondition condition, Pagination page) {
		PageResult<CmsPageCondition> pageResult = new PageResult<CmsPageCondition>();
		List<CmsPageCondition> list = null;

		int cnt = cmsPageReadDAO.countBlurOfPage(condition);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = cmsPageReadDAO.findBlurOfPage(condition, page);
		} else {
			list = new ArrayList<CmsPageCondition>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;
	}

	@Override
	public CmsPageCondition findCmsPageByPageId(CmsPagePO po) {
		
		return cmsPageReadDAO.findCmsPageByPageId(po);
	}

	@Override
	public List<CmsPageCondition> findCmsPageByCompanyIdAndVersion(CmsPageCondition condition) {
		
		return cmsPageReadDAO.findCmsPageByCompanyIdAndVersion(condition);
	}
	
	
}
	