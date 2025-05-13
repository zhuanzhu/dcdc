package com.egeo.components.product.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.read.StandardUnitDescribeReadManage;
import com.egeo.components.product.dao.read.StandardUnitDescribeReadDAO;
import com.egeo.components.product.po.StandardUnitDescribePO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class StandardUnitDescribeReadManageImpl implements StandardUnitDescribeReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private StandardUnitDescribeReadDAO standardUnitDescribeReadDAO;
	
	public StandardUnitDescribePO findStandardUnitDescribeById(StandardUnitDescribePO po) {
		StandardUnitDescribePO standardUnitDescribepo = new StandardUnitDescribePO();
		standardUnitDescribepo.setId(po.getId());
		return standardUnitDescribeReadDAO.findById(standardUnitDescribepo);
	}

	public PageResult<StandardUnitDescribePO> findStandardUnitDescribeOfPage(StandardUnitDescribePO po, Pagination page) {
		
		PageResult<StandardUnitDescribePO> pageResult = new PageResult<StandardUnitDescribePO>();
		List<StandardUnitDescribePO> list = null;

		int cnt = standardUnitDescribeReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = standardUnitDescribeReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<StandardUnitDescribePO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<StandardUnitDescribePO> findStandardUnitDescribeAll(StandardUnitDescribePO po) {

		return standardUnitDescribeReadDAO.findAll(po,null);
	}
	/**
	 * 根据su商品id查询su商品星详情
	 * @param standardUnitId
	 * @return
	 */
	@Override
	public String findContentByStandardUnitId(Long standardUnitId) {
		// TODO Auto-generated method stub
		return standardUnitDescribeReadDAO.findContentByStandardUnitId(standardUnitId);
	}
	
}
	