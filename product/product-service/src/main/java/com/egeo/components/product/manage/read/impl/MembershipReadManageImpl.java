package com.egeo.components.product.manage.read.impl;

import java.util.ArrayList;
import java.util.List;

import com.egeo.exception.BusinessException;
import com.egeo.utils.EmptyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.read.MembershipReadManage;
import com.egeo.components.product.condition.MembershipCondition;
import com.egeo.components.product.dao.read.MembershipReadDAO;
import com.egeo.components.product.po.MembershipPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class MembershipReadManageImpl implements MembershipReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private MembershipReadDAO membershipReadDAO;
	
	public MembershipPO findMembershipById(MembershipPO po) {
		MembershipPO membershippo = new MembershipPO();
		membershippo.setId(po.getId());
		return membershipReadDAO.findById(membershippo);
	}

	public PageResult<MembershipPO> findMembershipOfPage(MembershipPO po, Pagination page) {
		
		PageResult<MembershipPO> pageResult = new PageResult<MembershipPO>();
		List<MembershipPO> list = null;

		int cnt = membershipReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = membershipReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<MembershipPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<MembershipPO> findMembershipAll(MembershipPO po) {

		return membershipReadDAO.findAll(po,null);
	}

	@Override
	public PageResult<MembershipPO> findMembershipOfPageByParam(Long categoryId, String membershipName,List<Long> skuIdList , Long platformId, Pagination page) {

		PageResult<MembershipPO> pageResult = new PageResult<MembershipPO>();
		List<MembershipPO> list = new ArrayList<>();
		int cnt ;
		if(EmptyUtil.isEmpty(skuIdList)){
			cnt = membershipReadDAO.countOfPageByParams(categoryId,membershipName,platformId);

			if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
				page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
				list = membershipReadDAO.findOfPageByParams(categoryId,membershipName,platformId, page);
			} else {
				list = new ArrayList<MembershipPO>();
			}

		}else {
			cnt = membershipReadDAO.countOfPageByParam(categoryId,membershipName,skuIdList,platformId);

			if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
				page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
				list = membershipReadDAO.findOfPageByParam(categoryId,membershipName,skuIdList,platformId, page);
			} else {
				list = new ArrayList<MembershipPO>();
			}
		}

		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;
	}

	@Override
	public MembershipPO findMembershipBySkuId(Long skuId, Long platformId) {
		List<MembershipPO> membershipBySkuId = membershipReadDAO.findMembershipBySkuId(skuId, platformId);
		if(EmptyUtil.isEmpty(membershipBySkuId)||membershipBySkuId.size()==0){
			return null;
		}
		if(membershipBySkuId.size()>1){
			throw new BusinessException("商品数据错误"+skuId + "对用多个会籍");
		}
		return membershipBySkuId.get(0);
	}

	@Override
	public List<MembershipCondition> findNotifyMembership(Integer remainDays) {
		return membershipReadDAO.findNotifyMembership(remainDays);
	}

}
	