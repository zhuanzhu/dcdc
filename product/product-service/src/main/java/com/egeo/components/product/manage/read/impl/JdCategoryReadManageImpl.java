package com.egeo.components.product.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.read.JdCategoryReadManage;
import com.egeo.components.product.dao.read.JdCategoryReadDAO;
import com.egeo.components.product.po.JdCategoryPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class JdCategoryReadManageImpl implements JdCategoryReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private JdCategoryReadDAO jdCategoryReadDAO;
	
	public JdCategoryPO findJdCategoryById(JdCategoryPO po) {
		JdCategoryPO jdCategorypo = new JdCategoryPO();
		jdCategorypo.setId(po.getId());
		return jdCategoryReadDAO.findById(jdCategorypo);
	}

	public PageResult<JdCategoryPO> findJdCategoryOfPage(JdCategoryPO po, Pagination page) {
		
		PageResult<JdCategoryPO> pageResult = new PageResult<JdCategoryPO>();
		List<JdCategoryPO> list = null;

		int cnt = jdCategoryReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = jdCategoryReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<JdCategoryPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<JdCategoryPO> findJdCategoryAll(JdCategoryPO po) {

		return jdCategoryReadDAO.findAll(po,null);
	}

	@Override
	public List<Long> findJdCategoryIdByCatClass(int catClass) {
		return jdCategoryReadDAO.findJdCategoryIdByCatClass(catClass);
	}

}
	