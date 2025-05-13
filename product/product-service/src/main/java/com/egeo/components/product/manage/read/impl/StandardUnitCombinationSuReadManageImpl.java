package com.egeo.components.product.manage.read.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.read.StandardUnitCombinationSuReadManage;
import com.egeo.components.product.condition.StandardUnitCombinationSuCondition;
import com.egeo.components.product.dao.read.StandardUnitCombinationSuReadDAO;
import com.egeo.components.product.po.StandardUnitCombinationSuPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class StandardUnitCombinationSuReadManageImpl implements StandardUnitCombinationSuReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private StandardUnitCombinationSuReadDAO standardUnitCombinationSuReadDAO;
	
	public StandardUnitCombinationSuPO findStandardUnitCombinationSuById(StandardUnitCombinationSuPO po) {
		StandardUnitCombinationSuPO standardUnitCombinationSupo = new StandardUnitCombinationSuPO();
		standardUnitCombinationSupo.setId(po.getId());
		return standardUnitCombinationSuReadDAO.findById(standardUnitCombinationSupo);
	}

	public PageResult<StandardUnitCombinationSuPO> findStandardUnitCombinationSuOfPage(StandardUnitCombinationSuPO po, Pagination page) {
		
		PageResult<StandardUnitCombinationSuPO> pageResult = new PageResult<StandardUnitCombinationSuPO>();
		List<StandardUnitCombinationSuPO> list = null;

		int cnt = standardUnitCombinationSuReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = standardUnitCombinationSuReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<StandardUnitCombinationSuPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<StandardUnitCombinationSuPO> findStandardUnitCombinationSuAll(StandardUnitCombinationSuPO po) {

		return standardUnitCombinationSuReadDAO.findAll(po,null);
	}

	/**
	 * 根据su组合id查询su商品列表
	 * @param standardUnitCombinationId
	 * @return
	 */
	@Override
	public PageResult<StandardUnitCombinationSuCondition> findByStandardUnitCombinationIdOfPage(
			Long standardUnitCombinationId, Pagination page) {
		PageResult<StandardUnitCombinationSuCondition> pageResult = new PageResult<StandardUnitCombinationSuCondition>();
		List<StandardUnitCombinationSuCondition> list = null;

		int cnt = standardUnitCombinationSuReadDAO.countByStandardUnitCombinationIdOfPage(standardUnitCombinationId);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = standardUnitCombinationSuReadDAO.findByStandardUnitCombinationIdOfPage(standardUnitCombinationId, page);
		} else {
			list = new ArrayList<StandardUnitCombinationSuCondition>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;
	}
	/**
	 * 根据su组合id查询su商品数量
	 * @param id
	 * @return
	 */
	@Override
	public Integer findStandardUnitSize(Long standardUnitCombinationId) {
		// TODO Auto-generated method stub
		return standardUnitCombinationSuReadDAO.findStandardUnitSize(standardUnitCombinationId);
	}

	@Override
	public List<StandardUnitCombinationSuPO> syncJdSellState(Integer source, Date endCheckTime, int size) {
		return standardUnitCombinationSuReadDAO.syncJdSellState(source,endCheckTime,size);
	}
}
