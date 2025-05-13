package com.egeo.components.cms.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.cms.manage.read.ElementReadManage;
import com.egeo.components.cms.dao.read.ElementReadDAO;
import com.egeo.components.cms.po.ElementPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class ElementReadManageImpl implements ElementReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ElementReadDAO elementReadDAO;
	
	public ElementPO findElementById(ElementPO po) {
		ElementPO elementpo = new ElementPO();
		elementpo.setId(po.getId());
		return elementReadDAO.findById(elementpo);
	}

	public PageResult<ElementPO> findElementOfPage(ElementPO po, Pagination page) {
		
		PageResult<ElementPO> pageResult = new PageResult<ElementPO>();
		List<ElementPO> list = null;

		int cnt = elementReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = elementReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<ElementPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<ElementPO> findElementAll(ElementPO po) {

		return elementReadDAO.findAll(po,null);
	}

	@Override
	public List<ElementPO> queryElementListByTmlpId(Long templateId) {
		return elementReadDAO.queryElementListByTmlpId(templateId);
	}

	@Override
	public List<ElementPO> queryElementListByTmlpIdByPage(Long id, Pagination page) {
		return elementReadDAO.queryElementListByTmlpIdByPage(id,page);
	}

}
	