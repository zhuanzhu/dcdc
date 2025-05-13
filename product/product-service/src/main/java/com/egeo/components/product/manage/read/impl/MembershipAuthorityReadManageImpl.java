package com.egeo.components.product.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.read.MembershipAuthorityReadManage;
import com.egeo.components.product.condition.MembershipAuthorityCondition;
import com.egeo.components.product.dao.read.MembershipAuthorityReadDAO;
import com.egeo.components.product.po.MembershipAuthorityPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class MembershipAuthorityReadManageImpl implements MembershipAuthorityReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private MembershipAuthorityReadDAO membershipAuthorityReadDAO;
	
	public MembershipAuthorityPO findMembershipAuthorityById(MembershipAuthorityPO po) {
		MembershipAuthorityPO membershipAuthoritypo = new MembershipAuthorityPO();
		membershipAuthoritypo.setId(po.getId());
		return membershipAuthorityReadDAO.findById(membershipAuthoritypo);
	}

	public PageResult<MembershipAuthorityPO> findMembershipAuthorityOfPage(MembershipAuthorityPO po, Pagination page) {
		
		PageResult<MembershipAuthorityPO> pageResult = new PageResult<MembershipAuthorityPO>();
		List<MembershipAuthorityPO> list = null;

		int cnt = membershipAuthorityReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = membershipAuthorityReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<MembershipAuthorityPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<MembershipAuthorityPO> findMembershipAuthorityAll(MembershipAuthorityPO po) {

		return membershipAuthorityReadDAO.findAll(po,null);
	}

	@Override
	public List<MembershipAuthorityCondition> findModifyYesterday() {
		return membershipAuthorityReadDAO.findModifyYesterday();
	}
	
}
	