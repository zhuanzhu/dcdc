package com.egeo.components.product.service.read.impl;

import java.util.List;

import com.egeo.utils.EmptyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.service.read.MembershipReadService;
import com.egeo.components.product.manage.read.MembershipReadManage;
import com.egeo.components.product.converter.MembershipConverter;
import com.egeo.components.product.dto.MembershipDTO;
import com.egeo.components.product.po.MembershipPO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("membershipReadService")
public class MembershipReadServiceImpl  implements MembershipReadService {
	@Autowired
	private MembershipReadManage membershipReadManage;

	@Override
	public MembershipDTO findMembershipById(MembershipDTO dto) {
		MembershipPO po = MembershipConverter.toPO(dto);
		MembershipPO list = membershipReadManage.findMembershipById(po);		
		return MembershipConverter.toDTO(list);
	}

	@Override
	public PageResult<MembershipDTO> findMembershipOfPage(MembershipDTO dto, Pagination page) {
		MembershipPO po = MembershipConverter.toPO(dto);
        PageResult<MembershipPO> pageResult = membershipReadManage.findMembershipOfPage(po, page);
        
        List<MembershipDTO> list = MembershipConverter.toDTO(pageResult.getList());
        PageResult<MembershipDTO> result = new PageResult<MembershipDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<MembershipDTO> findMembershipAll(MembershipDTO dto) {
		MembershipPO po = MembershipConverter.toPO(dto);
		List<MembershipPO> list = membershipReadManage.findMembershipAll(po);		
		return MembershipConverter.toDTO(list);
	}

	@Override
	public PageResult<MembershipDTO> findMembershipOfPageByParam(Long categoryId, String membershipName, List<Long> skuIdList , Long platformId, Pagination page) {

		PageResult<MembershipPO> membershipPOPage=membershipReadManage.findMembershipOfPageByParam(categoryId,membershipName,skuIdList ,platformId,page);
		PageResult<MembershipDTO> result = new PageResult<MembershipDTO>();
		result.setPageSize(membershipPOPage.getPageSize());
		result.setTotalSize(membershipPOPage.getTotalSize());
		result.setPageNo(membershipPOPage.getPageNo());
		if(EmptyUtil.isEmpty(membershipPOPage.getList())){
			result.setList(null);

			return result;
		}
		List<MembershipDTO> dtoList = MembershipConverter.toDTO(membershipPOPage.getList());
		result.setList(dtoList);
		return result;
	}

	@Override
	public MembershipDTO findMembershipBySkuId(Long skuId, Long platformId) {
		return MembershipConverter.toDTO(membershipReadManage.findMembershipBySkuId(skuId,platformId));
	}

	@Override
	public List<MembershipDTO> findNotifyMembership(Integer remainDays) {
		return MembershipConverter.conditionToDTO(membershipReadManage.findNotifyMembership(remainDays));
	}
}
	