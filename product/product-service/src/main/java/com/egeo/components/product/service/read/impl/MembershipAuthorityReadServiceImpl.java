package com.egeo.components.product.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.service.read.MembershipAuthorityReadService;
import com.egeo.components.product.manage.read.MembershipAuthorityReadManage;
import com.egeo.components.product.condition.MembershipAuthorityCondition;
import com.egeo.components.product.converter.MembershipAuthorityConverter;
import com.egeo.components.product.dto.MembershipAuthorityDTO;
import com.egeo.components.product.po.MembershipAuthorityPO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("membershipAuthorityReadService")
public class MembershipAuthorityReadServiceImpl  implements MembershipAuthorityReadService {
	@Autowired
	private MembershipAuthorityReadManage membershipAuthorityReadManage;

	@Override
	public MembershipAuthorityDTO findMembershipAuthorityById(MembershipAuthorityDTO dto) {
		MembershipAuthorityPO po = MembershipAuthorityConverter.toPO(dto);
		MembershipAuthorityPO list = membershipAuthorityReadManage.findMembershipAuthorityById(po);		
		return MembershipAuthorityConverter.toDTO(list);
	}

	@Override
	public PageResult<MembershipAuthorityDTO> findMembershipAuthorityOfPage(MembershipAuthorityDTO dto, Pagination page) {
		MembershipAuthorityPO po = MembershipAuthorityConverter.toPO(dto);
        PageResult<MembershipAuthorityPO> pageResult = membershipAuthorityReadManage.findMembershipAuthorityOfPage(po, page);
        
        List<MembershipAuthorityDTO> list = MembershipAuthorityConverter.toDTO(pageResult.getList());
        PageResult<MembershipAuthorityDTO> result = new PageResult<MembershipAuthorityDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<MembershipAuthorityDTO> findMembershipAuthorityAll(MembershipAuthorityDTO dto) {
		MembershipAuthorityPO po = MembershipAuthorityConverter.toPO(dto);
		List<MembershipAuthorityPO> list = membershipAuthorityReadManage.findMembershipAuthorityAll(po);		
		return MembershipAuthorityConverter.toDTO(list);
	}

	@Override
	public List<MembershipAuthorityDTO> findModifyYesterday() {
		List<MembershipAuthorityCondition> list = membershipAuthorityReadManage.findModifyYesterday();		
		return MembershipAuthorityConverter.conditionToDTO(list);
	}

}
	