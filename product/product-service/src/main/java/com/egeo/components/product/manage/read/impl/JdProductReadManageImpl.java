package com.egeo.components.product.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.read.JdProductReadManage;
import com.egeo.components.product.dao.read.JdProductReadDAO;
import com.egeo.components.product.po.JdProductPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class JdProductReadManageImpl implements JdProductReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private JdProductReadDAO jdProductReadDAO;
	
	public JdProductPO findJdProductById(JdProductPO po) {
		JdProductPO jdProductpo = new JdProductPO();
		jdProductpo.setId(po.getId());
		return jdProductReadDAO.findById(jdProductpo);
	}

	public PageResult<JdProductPO> findJdProductOfPage(JdProductPO po, Pagination page) {
		
		PageResult<JdProductPO> pageResult = new PageResult<JdProductPO>();
		List<JdProductPO> list = null;

		int cnt = jdProductReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = jdProductReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<JdProductPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<JdProductPO> findJdProductAll(JdProductPO po) {

		return jdProductReadDAO.findAll(po,null);
	}

	@Override
	public List<Long> findAllIdList() {
		return jdProductReadDAO.findAllIdList();
	}

	@Override
	public List<JdProductPO> findJdProductListByIds(List<Long> skuIdList) {
		return jdProductReadDAO.findJdProductListByIds(skuIdList);
	}

	@Override
	public PageResult<JdProductPO> getJdProductListByParams(Pagination page, Long skuId, List<String> skuNameList, Long updateTimeStart, Long updateTimeEnd, Integer profitStart, Integer profitEnd, Integer state, Integer sycStatus, Long catId, Integer isShow) {


		PageResult<JdProductPO> pageResult = new PageResult<JdProductPO>();
		List<JdProductPO> list = null;

		int count=jdProductReadDAO.getJdProductListByParamsCount(skuId, skuNameList, updateTimeStart, updateTimeEnd,
				profitStart, profitEnd, state, sycStatus, catId, isShow);

		if (count >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = jdProductReadDAO.getJdProductListByParams(page,skuId,skuNameList,updateTimeStart,updateTimeEnd,
					profitStart,profitEnd,state,sycStatus,catId,isShow);
		} else {
			list = new ArrayList<JdProductPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(count);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;
	}

	@Override
	public Integer findJdProductCountByProfit(Integer profit) {
		return jdProductReadDAO.findJdProductCountByProfit(profit);
	}

	@Override
	public List<JdProductPO> findJdProductListByProfit(Integer profit, Integer start, Integer pageSize) {
		return jdProductReadDAO.findJdProductListByProfit(profit,start,pageSize);
	}

	@Override
	public List<Long> findHavedIdListByIdList(List<Long> ids) {
		return jdProductReadDAO.findHavedIdListByIdList(ids);
	}

}
	