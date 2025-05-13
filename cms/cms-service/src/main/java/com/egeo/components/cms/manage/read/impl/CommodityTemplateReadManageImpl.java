package com.egeo.components.cms.manage.read.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.cms.manage.read.CommodityTemplateReadManage;
import com.egeo.components.cms.dao.read.CommodityTemplateReadDAO;
import com.egeo.components.cms.po.CommodityTemplatePO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class CommodityTemplateReadManageImpl implements CommodityTemplateReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CommodityTemplateReadDAO commodityTemplateReadDAO;
	
	public CommodityTemplatePO findCommodityTemplateById(CommodityTemplatePO po) {
		CommodityTemplatePO commodityTemplatepo = new CommodityTemplatePO();
		commodityTemplatepo.setId(po.getId());
		return commodityTemplateReadDAO.findById(commodityTemplatepo);
	}

	public PageResult<CommodityTemplatePO> findCommodityTemplateOfPage(CommodityTemplatePO po, Pagination page) {
		
		PageResult<CommodityTemplatePO> pageResult = new PageResult<CommodityTemplatePO>();
		List<CommodityTemplatePO> list = null;

		int cnt = commodityTemplateReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = commodityTemplateReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<CommodityTemplatePO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<CommodityTemplatePO> findCommodityTemplateAll(CommodityTemplatePO po) {

		return commodityTemplateReadDAO.findAll(po,null);
	}
	/**
	 * 根据类型查询默认模版信息
	 * @param type
	 * @return
	 */
	@Override
	public CommodityTemplatePO findDefaultByType(Integer type,Long platformId) {
		//某种类型的默认模版只可能是一个
		return commodityTemplateReadDAO.findDefaultByType(type,platformId);
	}
	
}
	