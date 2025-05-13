package com.egeo.components.product.manage.read.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.read.TopSearchReadManage;
import com.egeo.components.product.dao.read.TopSearchReadDAO;
import com.egeo.components.product.po.TopSearchPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class TopSearchReadManageImpl implements TopSearchReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private TopSearchReadDAO topSearchReadDAO;
	
	public TopSearchPO findTopSearchById(TopSearchPO po) {
		TopSearchPO topSearchpo = new TopSearchPO();
		topSearchpo.setId(po.getId());
		return topSearchReadDAO.findById(topSearchpo);
	}

	public PageResult<TopSearchPO> findTopSearchOfPage(TopSearchPO po, Pagination page) {
		
		PageResult<TopSearchPO> pageResult = new PageResult<TopSearchPO>();
		List<TopSearchPO> list = null;

		int cnt = topSearchReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = topSearchReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<TopSearchPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<TopSearchPO> findTopSearchAll(TopSearchPO po) {

		return topSearchReadDAO.findAll(po,null);
	}
	/**
	 * 查询排序最大值
	 */
	@Override
	public int sortValueMax() {
		// TODO Auto-generated method stub
		return topSearchReadDAO.sortValueMax();
	}
	/**
	 * 查询启用的热搜条数
	 * @return
	 */
	@Override
	public int findStartTopSearchNum(Long platformId) {
		// TODO Auto-generated method stub
		return topSearchReadDAO.findStartTopSearchNum(platformId);
	}
	
}
	