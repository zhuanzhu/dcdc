package com.egeo.components.product.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.read.JdProductInnerIdReadManage;
import com.egeo.components.product.dao.read.JdProductInnerIdReadDAO;
import com.egeo.components.product.po.JdProductInnerIdPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class JdProductInnerIdReadManageImpl implements JdProductInnerIdReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private JdProductInnerIdReadDAO jdProductInnerIdReadDAO;
	
	public JdProductInnerIdPO findJdProductInnerIdById(JdProductInnerIdPO po) {
		return jdProductInnerIdReadDAO.findById(po);
	}

	public PageResult<JdProductInnerIdPO> findJdProductInnerIdOfPage(JdProductInnerIdPO po, Pagination page) {
		
		PageResult<JdProductInnerIdPO> pageResult = new PageResult<JdProductInnerIdPO>();
		List<JdProductInnerIdPO> list = null;

		int cnt = jdProductInnerIdReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = jdProductInnerIdReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<JdProductInnerIdPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<JdProductInnerIdPO> findJdProductInnerIdAll(JdProductInnerIdPO po) {

		return jdProductInnerIdReadDAO.findAll(po,null);
	}

	@Override
	public List<JdProductInnerIdPO> findJdProductInnerIdAllByJdSkuIdList(List<Long> skuIdList) {
		return jdProductInnerIdReadDAO.findJdProductInnerIdAllByJdSkuIdList(skuIdList,null);
	}

	@Override
	public Integer findSuProfitById(Long suId) {
		return jdProductInnerIdReadDAO.findSuProfitById(suId);
	}

	@Override
	public Long findPuIdByJdSkuId(Long skuId) {
		return jdProductInnerIdReadDAO.findPuIdByJdSkuId(skuId);
	}

}
	