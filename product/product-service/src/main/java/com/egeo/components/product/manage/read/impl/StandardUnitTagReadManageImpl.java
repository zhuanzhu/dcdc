package com.egeo.components.product.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.read.StandardUnitTagReadManage;
import com.egeo.components.product.dao.read.StandardUnitTagReadDAO;
import com.egeo.components.product.po.StandardUnitTagPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class StandardUnitTagReadManageImpl implements StandardUnitTagReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private StandardUnitTagReadDAO standardUnitTagReadDAO;
	
	public StandardUnitTagPO findStandardUnitTagById(StandardUnitTagPO po) {
		StandardUnitTagPO standardUnitTagpo = new StandardUnitTagPO();
		standardUnitTagpo.setId(po.getId());
		return standardUnitTagReadDAO.findById(standardUnitTagpo);
	}

	public PageResult<StandardUnitTagPO> findStandardUnitTagOfPage(StandardUnitTagPO po, Pagination page) {
		
		PageResult<StandardUnitTagPO> pageResult = new PageResult<StandardUnitTagPO>();
		List<StandardUnitTagPO> list = null;

		int cnt = standardUnitTagReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = standardUnitTagReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<StandardUnitTagPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<StandardUnitTagPO> findStandardUnitTagAll(StandardUnitTagPO po) {

		return standardUnitTagReadDAO.findAll(po,null);
	}

	@Override
	public List<String> findStandardUnitTagBySuId(Long suId) {
		// TODO Auto-generated method stub
		return standardUnitTagReadDAO.findStandardUnitTagBySuId(suId);
	}
	
}
	