package com.egeo.components.product.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.read.FreightTemplateReadManage;
import com.egeo.components.product.dao.read.FreightTemplateReadDAO;
import com.egeo.components.product.po.FreightTemplatePO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class FreightTemplateReadManageImpl implements FreightTemplateReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private FreightTemplateReadDAO freightTemplateReadDAO;
	
	public FreightTemplatePO findFreightTemplateById(FreightTemplatePO po) {
		FreightTemplatePO freightTemplatepo = new FreightTemplatePO();
		freightTemplatepo.setId(po.getId());
		return freightTemplateReadDAO.findById(freightTemplatepo);
	}

	public PageResult<FreightTemplatePO> findFreightTemplateOfPage(FreightTemplatePO po, Pagination page) {
		
		PageResult<FreightTemplatePO> pageResult = new PageResult<FreightTemplatePO>();
		List<FreightTemplatePO> list = null;

		int cnt = freightTemplateReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = freightTemplateReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<FreightTemplatePO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<FreightTemplatePO> findFreightTemplateAll(FreightTemplatePO po) {

		return freightTemplateReadDAO.findAll(po,null);
	}
	
}
	