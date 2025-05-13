package com.egeo.components.product.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.read.StandardUnitCombinationTagReadManage;
import com.egeo.components.product.condition.StandardUnitCombinationTagCondition;
import com.egeo.components.product.dao.read.StandardUnitCombinationTagReadDAO;
import com.egeo.components.product.po.StandardUnitCombinationTagPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class StandardUnitCombinationTagReadManageImpl implements StandardUnitCombinationTagReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private StandardUnitCombinationTagReadDAO standardUnitCombinationTagReadDAO;
	
	public StandardUnitCombinationTagPO findStandardUnitCombinationTagById(StandardUnitCombinationTagPO po) {
		StandardUnitCombinationTagPO standardUnitCombinationTagpo = new StandardUnitCombinationTagPO();
		standardUnitCombinationTagpo.setId(po.getId());
		return standardUnitCombinationTagReadDAO.findById(standardUnitCombinationTagpo);
	}

	public PageResult<StandardUnitCombinationTagPO> findStandardUnitCombinationTagOfPage(StandardUnitCombinationTagPO po, Pagination page) {
		
		PageResult<StandardUnitCombinationTagPO> pageResult = new PageResult<StandardUnitCombinationTagPO>();
		List<StandardUnitCombinationTagPO> list = null;

		int cnt = standardUnitCombinationTagReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = standardUnitCombinationTagReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<StandardUnitCombinationTagPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<StandardUnitCombinationTagPO> findStandardUnitCombinationTagAll(StandardUnitCombinationTagPO po) {

		return standardUnitCombinationTagReadDAO.findAll(po,null);
	}
	/**
	 * 根据su组合id查询su组合标签信息
	 * @param standardUnitCombinationId
	 * @return
	 */
	@Override
	public List<StandardUnitCombinationTagCondition> findTagByStandardUnitCombinationId(
			Long standardUnitCombinationId) {
		// TODO Auto-generated method stub
		return standardUnitCombinationTagReadDAO.findTagByStandardUnitCombinationId(standardUnitCombinationId);
	}
	
}
	