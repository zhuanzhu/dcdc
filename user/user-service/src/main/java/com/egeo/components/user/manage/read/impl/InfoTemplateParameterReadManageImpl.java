package com.egeo.components.user.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.user.manage.read.InfoTemplateParameterReadManage;
import com.egeo.components.user.dao.read.InfoTemplateParameterReadDAO;
import com.egeo.components.user.po.InfoTemplateParameterPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class InfoTemplateParameterReadManageImpl implements InfoTemplateParameterReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private InfoTemplateParameterReadDAO infoTemplateParameterReadDAO;
	
	public InfoTemplateParameterPO findInfoTemplateParameterById(InfoTemplateParameterPO po) {
		InfoTemplateParameterPO infoTemplateParameterpo = new InfoTemplateParameterPO();
		infoTemplateParameterpo.setId(po.getId());
		return infoTemplateParameterReadDAO.findById(infoTemplateParameterpo);
	}

	public PageResult<InfoTemplateParameterPO> findInfoTemplateParameterOfPage(InfoTemplateParameterPO po, Pagination page) {
		
		PageResult<InfoTemplateParameterPO> pageResult = new PageResult<InfoTemplateParameterPO>();
		List<InfoTemplateParameterPO> list = null;

		int cnt = infoTemplateParameterReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = infoTemplateParameterReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<InfoTemplateParameterPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<InfoTemplateParameterPO> findInfoTemplateParameterAll(InfoTemplateParameterPO po) {

		return infoTemplateParameterReadDAO.findAll(po,null);
	}
	
}
	