package com.egeo.components.cms.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.cms.manage.read.ElementDictReadManage;
import com.egeo.components.cms.dao.read.ElementDictReadDAO;
import com.egeo.components.cms.po.ElementDictPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class ElementDictReadManageImpl implements ElementDictReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ElementDictReadDAO elementDictReadDAO;
	
	public ElementDictPO findElementDictById(ElementDictPO po) {
		ElementDictPO elementDictpo = new ElementDictPO();
		elementDictpo.setId(po.getId());
		return elementDictReadDAO.findById(elementDictpo);
	}

	public PageResult<ElementDictPO> findElementDictOfPage(ElementDictPO po, Pagination page) {
		
		PageResult<ElementDictPO> pageResult = new PageResult<ElementDictPO>();
		List<ElementDictPO> list = null;

		int cnt = elementDictReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = elementDictReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<ElementDictPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<ElementDictPO> findElementDictAll(ElementDictPO po) {
		return elementDictReadDAO.findAll(po,null);
	}

	@Override
	public ElementDictPO queryElementDictByConfigType(Integer configType) {
		return elementDictReadDAO.queryElementDictByConfigType(configType);
	}

	@Override
	public List<ElementDictPO> queryElementDictByNotType(Integer type) {
		return elementDictReadDAO.queryElementDictByNotType(type);
	}
	
}
	