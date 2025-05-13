package com.egeo.components.product.manage.read.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.read.StandardUnitCombinationCategoryReadManage;
import com.egeo.components.product.dao.read.CategoryTreeNodeCategoryReadDAO;
import com.egeo.components.product.dao.read.StandardUnitCombinationCategoryReadDAO;
import com.egeo.components.product.dao.read.StandardUnitReadDAO;
import com.egeo.components.product.po.StandardUnitCombinationCategoryPO;
import com.egeo.components.product.po.StandardUnitPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;


@Service
public class StandardUnitCombinationCategoryReadManageImpl implements StandardUnitCombinationCategoryReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private StandardUnitCombinationCategoryReadDAO standardUnitCombinationCategoryReadDAO;
	
	@Autowired
	private StandardUnitReadDAO standardUnitReadDAO;
	
	@Autowired
	private CategoryTreeNodeCategoryReadDAO categoryTreeNodeCategoryReadDAO;
	
	public StandardUnitCombinationCategoryPO findStandardUnitCombinationCategoryById(StandardUnitCombinationCategoryPO po) {
		StandardUnitCombinationCategoryPO standardUnitCombinationCategorypo = new StandardUnitCombinationCategoryPO();
		standardUnitCombinationCategorypo.setId(po.getId());
		return standardUnitCombinationCategoryReadDAO.findById(standardUnitCombinationCategorypo);
	}

	public PageResult<StandardUnitCombinationCategoryPO> findStandardUnitCombinationCategoryOfPage(StandardUnitCombinationCategoryPO po, Pagination page) {
		
		PageResult<StandardUnitCombinationCategoryPO> pageResult = new PageResult<StandardUnitCombinationCategoryPO>();
		List<StandardUnitCombinationCategoryPO> list = null;

		int cnt = standardUnitCombinationCategoryReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = standardUnitCombinationCategoryReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<StandardUnitCombinationCategoryPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<StandardUnitCombinationCategoryPO> findStandardUnitCombinationCategoryAll(StandardUnitCombinationCategoryPO po) {

		return standardUnitCombinationCategoryReadDAO.findAll(po,null);
	}
	/**
	 * 根据类目类型su组合id查询su商品数量
	 * @param standardUnitCombinationId
	 * @return
	 */
	@Override
	public Integer findStandardUnitSizeByCategoryType(Long standardUnitCombinationId) {
		List<Long> categoryTreeNodeIds = new ArrayList<>();
		//根据su商品组合id查询前台类目节点id
		StandardUnitCombinationCategoryPO standardUnitCombinationCategoryPO = new StandardUnitCombinationCategoryPO();
		standardUnitCombinationCategoryPO.setStandardUnitCombinationId(standardUnitCombinationId);
		List<StandardUnitCombinationCategoryPO> standardUnitCombinationCategoryList = standardUnitCombinationCategoryReadDAO.findAll(standardUnitCombinationCategoryPO,null);
		for (StandardUnitCombinationCategoryPO standardUnitCombinationCategoryPO2 : standardUnitCombinationCategoryList) {
			//根据前台类目节点id查询后台类目id信息
			List<Long> categoryTreeNodeIdList = categoryTreeNodeCategoryReadDAO.findCategoryIdsByCategoryTreeNodeId(standardUnitCombinationCategoryPO2.getCategoryTreeNodeId());
			categoryTreeNodeIds.addAll(categoryTreeNodeIdList);
		}
		//list去重
		List<Long> newList = new ArrayList<Long>(new HashSet<Long>(categoryTreeNodeIds));
		if(EmptyUtil.isNotEmpty(newList)){
			return standardUnitCombinationCategoryReadDAO.findStandardUnitSizeByCategoryList(newList);
		}
		return 0;
		
	}
	/**
	 * 根据类目类型su组合id查询su商品列表
	 * @param standardUnitCombinationId
	 * @return
	 */
	@Override
	public PageResult<StandardUnitPO> findByStandardUnitCombinationIdOfPage(Long standardUnitCombinationId,
			Pagination page) {
		PageResult<StandardUnitPO> pageResult = new PageResult<StandardUnitPO>();
		List<StandardUnitPO> list = null;

		int cnt = standardUnitCombinationCategoryReadDAO.findStandardUnitSizeByCategoryType(standardUnitCombinationId);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = standardUnitReadDAO.findByStandardUnitCombinationIdOfPage(standardUnitCombinationId, page);
		} else {
			list = new ArrayList<StandardUnitPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;
	}
	
}
	