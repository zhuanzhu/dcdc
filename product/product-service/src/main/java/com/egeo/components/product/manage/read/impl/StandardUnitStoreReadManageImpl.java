package com.egeo.components.product.manage.read.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.read.StandardUnitStoreReadManage;
import com.egeo.components.product.condition.StandardUnitStoreCondition;
import com.egeo.components.product.dao.read.StandardUnitStoreReadDAO;
import com.egeo.components.product.po.StandardUnitStorePO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class StandardUnitStoreReadManageImpl implements StandardUnitStoreReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private StandardUnitStoreReadDAO standardUnitStoreReadDAO;
	
	public StandardUnitStorePO findStandardUnitStoreById(StandardUnitStorePO po) {
		StandardUnitStorePO standardUnitStorepo = new StandardUnitStorePO();
		standardUnitStorepo.setId(po.getId());
		return standardUnitStoreReadDAO.findById(standardUnitStorepo);
	}

	public PageResult<StandardUnitStoreCondition> findStandardUnitStoreOfPage(StandardUnitStorePO po, Pagination page) {
		
		PageResult<StandardUnitStoreCondition> pageResult = new PageResult<StandardUnitStoreCondition>();
		List<StandardUnitStoreCondition> list = null;

		int cnt = standardUnitStoreReadDAO.countStandardUnitStoreOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = standardUnitStoreReadDAO.findStandardUnitStoreOfPage(po, page);
		} else {
			list = new ArrayList<StandardUnitStoreCondition>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<StandardUnitStoreCondition> findStandardUnitStoreAll(StandardUnitStorePO po) {

		return standardUnitStoreReadDAO.findStandardUnitStoreAll(po);
	}

	@Override
	public List<StandardUnitStorePO> findAll(StandardUnitStorePO standardUnitStorePO) {
		return standardUnitStoreReadDAO.findAll(standardUnitStorePO,null);
	}

	@Override
	public Integer standardUnitSumByStoreId(Long storeId, Long platformId) {
		return standardUnitStoreReadDAO.standardUnitSumByStoreId(storeId, platformId);
	}

	@Override
	public List<Long> findByPuIdsByStoreId(Long storeId, Long platformId) {
		return standardUnitStoreReadDAO.findByPuIdsByStoreId(storeId, platformId);
	}

	@Override
	public List<StandardUnitStoreCondition> standardUnitStoreByStandardUnitId(StandardUnitStorePO po) {
		return standardUnitStoreReadDAO.standardUnitStoreByStandardUnitId(po);
	}
	
	@Override
	public List<StandardUnitStorePO> findByStoreAndSu(List<Long> suIdList, Long storeId, Long platformId) {
		return standardUnitStoreReadDAO.findByStoreAndSu(suIdList, storeId, platformId);
	}

	@Override
	public int findStandardUnitStoreCount(Long suId, Long storeId) {
		return standardUnitStoreReadDAO.findStandardUnitStoreCount(suId,storeId);
	}
}
	