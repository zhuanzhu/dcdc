package com.egeo.components.cms.manage.read.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.cms.manage.read.CmsCfgKeyReadManage;
import com.egeo.components.cms.condition.CmsCfgKeyCondition;
import com.egeo.components.cms.dao.read.CmsCfgKeyReadDAO;
import com.egeo.components.cms.po.CmsCfgKeyPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class CmsCfgKeyReadManageImpl implements CmsCfgKeyReadManage {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CmsCfgKeyReadDAO cmsCfgKeyReadDAO;
	
	public CmsCfgKeyPO findCmsCfgKeyById(CmsCfgKeyPO po) {
		CmsCfgKeyPO cmsCfgKeypo = new CmsCfgKeyPO();
		cmsCfgKeypo.setId(po.getId());
		return cmsCfgKeyReadDAO.findById(cmsCfgKeypo);
	}

	public PageResult<CmsCfgKeyPO> findCmsCfgKeyOfPage(CmsCfgKeyPO po, Pagination page) {
		
		PageResult<CmsCfgKeyPO> pageResult = new PageResult<CmsCfgKeyPO>();
		List<CmsCfgKeyPO> list = null;

		int cnt = cmsCfgKeyReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = cmsCfgKeyReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<CmsCfgKeyPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<CmsCfgKeyPO> findCmsCfgKeyAll(CmsCfgKeyPO po) {

		return cmsCfgKeyReadDAO.findAll(po,null);
	}

	@Override
	public List<CmsCfgKeyCondition> findElementCfgKeyByTemplateId(Long templateId) {
		return cmsCfgKeyReadDAO.findElementCfgKeyByTemplateId(templateId);
	}
	
	@Override
	public List<CmsCfgKeyCondition> findTemplateCfgKeyByTemplateId(Long templateId) {
		return cmsCfgKeyReadDAO.findTemplateCfgKeyByTemplateId(templateId);
	}
	
}
	