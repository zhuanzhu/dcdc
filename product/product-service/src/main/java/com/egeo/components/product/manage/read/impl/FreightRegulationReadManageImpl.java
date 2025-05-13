package com.egeo.components.product.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.read.FreightRegulationReadManage;
import com.egeo.components.product.dao.read.FreightRegulationReadDAO;
import com.egeo.components.product.po.FreightRegulationPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class FreightRegulationReadManageImpl implements FreightRegulationReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private FreightRegulationReadDAO freightRegulationReadDAO;
	
	public FreightRegulationPO findFreightRegulationById(FreightRegulationPO po) {
		FreightRegulationPO freightRegulationpo = new FreightRegulationPO();
		freightRegulationpo.setId(po.getId());
		return freightRegulationReadDAO.findById(freightRegulationpo);
	}

	public PageResult<FreightRegulationPO> findFreightRegulationOfPage(FreightRegulationPO po, Pagination page) {
		
		PageResult<FreightRegulationPO> pageResult = new PageResult<FreightRegulationPO>();
		List<FreightRegulationPO> list = null;

		int cnt = freightRegulationReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = freightRegulationReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<FreightRegulationPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<FreightRegulationPO> findFreightRegulationAll(FreightRegulationPO po) {

		return freightRegulationReadDAO.findAll(po,null);
	}
	
}
	