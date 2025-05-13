package com.egeo.components.product.manage.read.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.egeo.components.product.po.MembershipPO;
import com.egeo.utils.EmptyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.read.MembershipUserReadManage;
import com.egeo.components.product.dao.read.MembershipUserReadDAO;
import com.egeo.components.product.po.MembershipUserPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class MembershipUserReadManageImpl implements MembershipUserReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private MembershipUserReadDAO membershipUserReadDAO;
	
	public MembershipUserPO findMembershipUserById(MembershipUserPO po) {
		MembershipUserPO membershipUserpo = new MembershipUserPO();
		membershipUserpo.setId(po.getId());
		return membershipUserReadDAO.findById(membershipUserpo);
	}

	public PageResult<MembershipUserPO> findMembershipUserOfPage(MembershipUserPO po, Pagination page) {
		
		PageResult<MembershipUserPO> pageResult = new PageResult<MembershipUserPO>();
		List<MembershipUserPO> list = null;

		int cnt = membershipUserReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = membershipUserReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<MembershipUserPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<MembershipUserPO> findMembershipUserAll(MembershipUserPO po) {

		return membershipUserReadDAO.findAll(po,null);
	}

	@Override
	public PageResult<MembershipUserPO> findMembershipUserAllByParams(Long membershipId, Date startTime,
																	  Date endTime, Integer statusCode,
																	  Pagination page, List<Long> result) {
		PageResult<MembershipUserPO> pageResult = new PageResult<>();
		List<MembershipUserPO> list = new ArrayList<>();
		int cnt ;
		if(EmptyUtil.isEmpty( result)){
			cnt = membershipUserReadDAO.countOfPageByParam(membershipId,startTime,endTime,statusCode,null);

			if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
				page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
				list = membershipUserReadDAO.findOfPageByParam(membershipId,startTime,endTime,statusCode,null);
			} else {
				list = new ArrayList<MembershipUserPO>();
			}

		}else {
			cnt = membershipUserReadDAO.countOfPageByParams(membershipId,startTime,endTime,statusCode,result,null);

			if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
				page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
				list = membershipUserReadDAO.findOfPageByParams(membershipId,startTime,endTime,statusCode,result,null);
			} else {
				list = new ArrayList<MembershipUserPO>();
			}
		}

		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;
	}

}
	