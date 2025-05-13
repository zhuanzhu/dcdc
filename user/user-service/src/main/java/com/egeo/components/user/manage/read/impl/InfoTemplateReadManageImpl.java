package com.egeo.components.user.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.user.manage.read.InfoTemplateReadManage;
import com.egeo.components.user.dao.read.InfoTemplateReadDAO;
import com.egeo.components.user.po.InfoTemplatePO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class InfoTemplateReadManageImpl implements InfoTemplateReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private InfoTemplateReadDAO infoTemplateReadDAO;
	
	public InfoTemplatePO findInfoTemplateById(InfoTemplatePO po) {
		InfoTemplatePO infoTemplatepo = new InfoTemplatePO();
		infoTemplatepo.setId(po.getId());
		return infoTemplateReadDAO.findById(infoTemplatepo);
	}

	public PageResult<InfoTemplatePO> findInfoTemplateOfPage(InfoTemplatePO po, Pagination page) {
		
		PageResult<InfoTemplatePO> pageResult = new PageResult<InfoTemplatePO>();
		List<InfoTemplatePO> list = null;

		int cnt = infoTemplateReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = infoTemplateReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<InfoTemplatePO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<InfoTemplatePO> findInfoTemplateAll(InfoTemplatePO po) {

		return infoTemplateReadDAO.findAll(po,null);
	}
	
}
	