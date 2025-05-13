package com.egeo.components.product.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.read.StandardUnitCombinationReadManage;
import com.egeo.components.product.dao.read.StandardUnitCombinationReadDAO;
import com.egeo.components.product.po.StandardUnitCombinationPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class StandardUnitCombinationReadManageImpl implements StandardUnitCombinationReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private StandardUnitCombinationReadDAO standardUnitCombinationReadDAO;
	
	public StandardUnitCombinationPO findStandardUnitCombinationById(StandardUnitCombinationPO po) {
		StandardUnitCombinationPO standardUnitCombinationpo = new StandardUnitCombinationPO();
		standardUnitCombinationpo.setId(po.getId());
		return standardUnitCombinationReadDAO.findById(standardUnitCombinationpo);
	}

	public PageResult<StandardUnitCombinationPO> findStandardUnitCombinationOfPage(StandardUnitCombinationPO po, Pagination page,List<Long> standardUnitCombinationIdList) {
		
		PageResult<StandardUnitCombinationPO> pageResult = new PageResult<StandardUnitCombinationPO>();
		List<StandardUnitCombinationPO> list = null;

		int cnt = standardUnitCombinationReadDAO.countStandardUnitCombinationOfPage(po,standardUnitCombinationIdList);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = standardUnitCombinationReadDAO.findStandardUnitCombinationOfPage(po, page,standardUnitCombinationIdList);
		} else {
			list = new ArrayList<StandardUnitCombinationPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<StandardUnitCombinationPO> findStandardUnitCombinationAll(StandardUnitCombinationPO po) {

		return standardUnitCombinationReadDAO.findAll(po,null);
	}
	/**
	 * 根据标签类型su组合id查询su商品数量
	 * @param id
	 * @return
	 */
	@Override
	public Integer findStandardUnitSizeByTag(Long standardUnitCombinationId,Long platformId) {
		// TODO Auto-generated method stub
		return standardUnitCombinationReadDAO.findStandardUnitSizeByTag(standardUnitCombinationId,platformId);
	}

	@Override
	public List<StandardUnitCombinationPO> findStandardUnitCombinationAllByBlurry(StandardUnitCombinationPO po) {
		
		return standardUnitCombinationReadDAO.findAllByBlurry(po,null);
	}

	@Override
	public List<StandardUnitCombinationPO> findStandardUnitCombinationAllLimit(String suCombinationName) {
		return standardUnitCombinationReadDAO.findStandardUnitCombinationAllLimit(suCombinationName);
	}

}
	