package com.egeo.components.cms.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.cms.manage.read.TemplateReadManage;
import com.egeo.components.cms.dao.read.TemplateReadDAO;
import com.egeo.components.cms.po.TemplatePO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class TemplateReadManageImpl implements TemplateReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private TemplateReadDAO templateReadDAO;
	
	public TemplatePO findTemplateById(TemplatePO po) {
		TemplatePO templatepo = new TemplatePO();
		templatepo.setId(po.getId());
		return templateReadDAO.findById(templatepo);
	}

	public PageResult<TemplatePO> findTemplateOfPage(TemplatePO po, Pagination page) {
		
		PageResult<TemplatePO> pageResult = new PageResult<TemplatePO>();
		List<TemplatePO> list = null;

		int cnt = templateReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = templateReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<TemplatePO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<TemplatePO> findTemplateAll(TemplatePO po) {

		return templateReadDAO.findAll(po,null);
	}

	@Override
	public PageResult<TemplatePO> queryTemplatePage(
			String name,Integer type , Pagination page,Integer status, Integer clientType,Integer companyType,Long platformId) {
		List<TemplatePO> poList=templateReadDAO.queryTemplatePage(name,type,page, status, clientType,companyType,platformId);
		Integer totalCount=templateReadDAO.queryTemplatePageTotalSize(name,type, status, clientType,companyType,platformId);
		PageResult<TemplatePO> poPage=new PageResult<>();
		poPage.setList(poList);
		poPage.setTotalSize(totalCount);
		poPage.copy(page);
		return poPage;
	}

	@Override
	public TemplatePO queryInUseTemplateByClientType(Integer clientType,Integer type, Integer companyType,Long platformId) {
		return templateReadDAO.queryInUseTemplateByClientType(clientType ,type, companyType,platformId);
	}
	
}
	