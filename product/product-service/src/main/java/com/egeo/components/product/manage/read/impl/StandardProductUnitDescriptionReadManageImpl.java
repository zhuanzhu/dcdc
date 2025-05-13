package com.egeo.components.product.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.read.StandardProductUnitDescriptionReadManage;
import com.egeo.components.product.dao.read.StandardProductUnitDescriptionReadDAO;
import com.egeo.components.product.po.StandardProductUnitDescriptionPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class StandardProductUnitDescriptionReadManageImpl implements StandardProductUnitDescriptionReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private StandardProductUnitDescriptionReadDAO standardProductUnitDescriptionReadDAO;
	
	public StandardProductUnitDescriptionPO findStandardProductUnitDescriptionById(StandardProductUnitDescriptionPO po) {
		StandardProductUnitDescriptionPO standardProductUnitDescriptionpo = new StandardProductUnitDescriptionPO();
		standardProductUnitDescriptionpo.setId(po.getId());
		return standardProductUnitDescriptionReadDAO.findById(standardProductUnitDescriptionpo);
	}

	public PageResult<StandardProductUnitDescriptionPO> findStandardProductUnitDescriptionOfPage(StandardProductUnitDescriptionPO po, Pagination page) {
		
		PageResult<StandardProductUnitDescriptionPO> pageResult = new PageResult<StandardProductUnitDescriptionPO>();
		List<StandardProductUnitDescriptionPO> list = null;

		int cnt = standardProductUnitDescriptionReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = standardProductUnitDescriptionReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<StandardProductUnitDescriptionPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<StandardProductUnitDescriptionPO> findStandardProductUnitDescriptionAll(StandardProductUnitDescriptionPO po) {

		return standardProductUnitDescriptionReadDAO.findAll(po,null);
	}
	
}
	