package com.egeo.components.product.manage.read.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.read.StandardProductUnitAttNameReadManage;
import com.egeo.components.product.condition.StandardProductUnitAttNameCondition;
import com.egeo.components.product.dao.read.StandardProductUnitAttNameReadDAO;
import com.egeo.components.product.po.StandardProductUnitAttNamePO;
import com.egeo.orm.PageResult;
import com.egeo.exception.BusinessException;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;


@Service
public class StandardProductUnitAttNameReadManageImpl implements StandardProductUnitAttNameReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private StandardProductUnitAttNameReadDAO standardProductUnitAttNameReadDAO;
	
	public StandardProductUnitAttNamePO findStandardProductUnitAttNameById(StandardProductUnitAttNamePO po) {
		StandardProductUnitAttNamePO standardProductUnitAttNamepo = new StandardProductUnitAttNamePO();
		standardProductUnitAttNamepo.setId(po.getId());
		return standardProductUnitAttNameReadDAO.findById(standardProductUnitAttNamepo);
	}

	public PageResult<StandardProductUnitAttNamePO> findStandardProductUnitAttNameOfPage(StandardProductUnitAttNamePO po, Pagination page) {
		
		PageResult<StandardProductUnitAttNamePO> pageResult = new PageResult<StandardProductUnitAttNamePO>();
		List<StandardProductUnitAttNamePO> list = null;

		int cnt = standardProductUnitAttNameReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = standardProductUnitAttNameReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<StandardProductUnitAttNamePO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<StandardProductUnitAttNamePO> findStandardProductUnitAttNameAll(StandardProductUnitAttNamePO po) {

		return standardProductUnitAttNameReadDAO.findAll(po,null);
	}
	/**
	 * 根据spuid和除属性id查询的所有spu属性信息
	 * @param standardProductUnitId
	 * @param attNameId
	 * @return
	 */
	@Override
	public List<StandardProductUnitAttNamePO> findByStandardProductUnitIdAttNameId(Long standardProductUnitId,
			Long attNameId) {
		if(EmptyUtil.isEmpty(standardProductUnitId)){
			throw new BusinessException("根据spuid和除属性id查询的所有spu属性信息spuid为空");
		}
		if(EmptyUtil.isEmpty(attNameId)){
			throw new BusinessException("根据spuid和除属性id查询的所有spu属性信息属性id为空");
		}
		return standardProductUnitAttNameReadDAO.findByStandardProductUnitIdAttNameId(standardProductUnitId,attNameId);
	}
	/**
	 * 根据spuid查询su参数属性
	 * @param standardProductUnitId
	 * @return
	 */
	@Override
	public List<StandardProductUnitAttNameCondition> findByStandardProductUnitId(Long standardProductUnitId) {
		// TODO Auto-generated method stub
		return standardProductUnitAttNameReadDAO.findByStandardProductUnitId(standardProductUnitId);
	}
	/**
	 * 根据suid查询spu规格属性
	 * @param standardUnitId
	 * @param req
	 * @return
	 */
	@Override
	public List<StandardProductUnitAttNameCondition> findByStandardUnitId(Long standardUnitId) {
		// TODO Auto-generated method stub
		return standardProductUnitAttNameReadDAO.findByStandardUnitId(standardUnitId);
	}
	/**
	 * 根据spuId查询spu属性信息
	 * @param standardProductUnitId
	 * @return
	 */
	@Override
	public List<StandardProductUnitAttNameCondition> findStandardProductUnitAttNameAll(Long standardProductUnitId) {
		// TODO Auto-generated method stub
		return standardProductUnitAttNameReadDAO.findStandardProductUnitAttNameAll(standardProductUnitId);
	}

	@Override
	public Long findLastId() {
		return standardProductUnitAttNameReadDAO.findLastId();
	}

}
	