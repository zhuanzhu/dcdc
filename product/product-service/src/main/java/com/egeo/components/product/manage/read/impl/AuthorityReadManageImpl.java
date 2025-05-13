package com.egeo.components.product.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.read.AuthorityReadManage;
import com.egeo.components.product.dao.read.AuthorityReadDAO;
import com.egeo.components.product.po.AuthorityPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class AuthorityReadManageImpl implements AuthorityReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private AuthorityReadDAO authorityReadDAO;
	
	public AuthorityPO findAuthorityById(AuthorityPO po) {
		AuthorityPO authoritypo = new AuthorityPO();
		authoritypo.setId(po.getId());
		return authorityReadDAO.findById(authoritypo);
	}

	public PageResult<AuthorityPO> findAuthorityOfPage(AuthorityPO po, Pagination page) {
		
		PageResult<AuthorityPO> pageResult = new PageResult<AuthorityPO>();
		List<AuthorityPO> list = null;

		int cnt = authorityReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = authorityReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<AuthorityPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<AuthorityPO> findAuthorityAll(AuthorityPO po) {

		return authorityReadDAO.findAll(po,null);
	}
	
}
	