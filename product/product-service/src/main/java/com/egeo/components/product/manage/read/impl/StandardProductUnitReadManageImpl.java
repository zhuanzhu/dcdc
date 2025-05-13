package com.egeo.components.product.manage.read.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.read.StandardProductUnitReadManage;
import com.egeo.components.product.condition.StandardProductUnitCondition;
import com.egeo.components.product.dao.read.StandardProductUnitReadDAO;
import com.egeo.components.product.po.StandardProductUnitPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class StandardProductUnitReadManageImpl implements StandardProductUnitReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private StandardProductUnitReadDAO standardProductUnitReadDAO;
	
	public StandardProductUnitPO findStandardProductUnitById(StandardProductUnitPO po) {
		StandardProductUnitPO standardProductUnitpo = new StandardProductUnitPO();
		standardProductUnitpo.setId(po.getId());
		return standardProductUnitReadDAO.findById(standardProductUnitpo);
	}

	public PageResult<StandardProductUnitPO> findStandardProductUnitOfPage(StandardProductUnitPO po, Pagination page) {
		
		PageResult<StandardProductUnitPO> pageResult = new PageResult<StandardProductUnitPO>();
		List<StandardProductUnitPO> list = null;

		int cnt = standardProductUnitReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = standardProductUnitReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<StandardProductUnitPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<StandardProductUnitPO> findStandardProductUnitAll(StandardProductUnitPO po) {

		return standardProductUnitReadDAO.findAll(po,null);
	}
	/**
	 * 根据以通过的spu草稿id集合查询spu信息
	 * @param ids
	 * @return
	 */
	@Override
	public List<StandardProductUnitPO> findProductByIds(List<Long> ids) {
		// TODO Auto-generated method stub
		return standardProductUnitReadDAO.findProductByIds(ids);
	}
	/**
	 * 根据spuid查询spu信息及su序列号
	 * @param standardProductUnitId
	 * @return
	 */
	@Override
	public StandardProductUnitCondition findSerialNumberByspuId(Long standardProductUnitId) {
		return standardProductUnitReadDAO.findSerialNumberByspuId(standardProductUnitId);
	}

	@Override
	public StandardProductUnitPO querySpuBySuId(Long suId) {
		return standardProductUnitReadDAO.querySpuBySuId(suId);
	}
	/**
	 * 根据spuId查询spu信息
	 * @param standardProductUnitId
	 * @return
	 */
	@Override
	public StandardProductUnitCondition queryStandardProductUnitById(Long standardProductUnitId) {
		// TODO Auto-generated method stub
		return standardProductUnitReadDAO.queryStandardProductUnitById(standardProductUnitId);
	}

	@Override
	public Long findCommodityTemplateIdByStandardUnitId(Long standardUnitId) {
		return standardProductUnitReadDAO.findCommodityTemplateIdByStandardUnitId(standardUnitId);
	}

	@Override
	public Long findLastId() {
		return standardProductUnitReadDAO.findLastId();
	}


}
	