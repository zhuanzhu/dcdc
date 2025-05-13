package com.egeo.components.product.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.read.JdPriceConfigReadManage;
import com.egeo.components.product.dao.read.JdPriceConfigReadDAO;
import com.egeo.components.product.po.JdPriceConfigPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class JdPriceConfigReadManageImpl implements JdPriceConfigReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private JdPriceConfigReadDAO jdPriceConfigReadDAO;
	
	public JdPriceConfigPO findJdPriceConfigById(JdPriceConfigPO po) {
		JdPriceConfigPO jdPriceConfigpo = new JdPriceConfigPO();
		jdPriceConfigpo.setId(po.getId());
		return jdPriceConfigReadDAO.findById(jdPriceConfigpo);
	}

	public PageResult<JdPriceConfigPO> findJdPriceConfigOfPage(JdPriceConfigPO po, Pagination page) {
		
		PageResult<JdPriceConfigPO> pageResult = new PageResult<JdPriceConfigPO>();
		List<JdPriceConfigPO> list = null;

		int cnt = jdPriceConfigReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = jdPriceConfigReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<JdPriceConfigPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<JdPriceConfigPO> findJdPriceConfigAll(JdPriceConfigPO po) {

		return jdPriceConfigReadDAO.findAll(po,null);
	}
	
}
	