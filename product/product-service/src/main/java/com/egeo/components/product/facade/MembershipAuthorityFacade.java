package com.egeo.components.product.facade;

import java.util.List;

import com.egeo.components.product.dto.MembershipDTO;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.product.service.read.MembershipAuthorityReadService;
import com.egeo.components.product.service.write.MembershipAuthorityWriteService;
import com.egeo.components.product.dto.MembershipAuthorityDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class MembershipAuthorityFacade {
	
	@Resource
	private MembershipAuthorityReadService membershipAuthorityReadService;
	
	@Resource
	private MembershipAuthorityWriteService membershipAuthorityWriteService;
	
	
	public MembershipAuthorityDTO findMembershipAuthorityById(MembershipAuthorityDTO dto){
		
		return membershipAuthorityReadService.findMembershipAuthorityById(dto);
	}

	public PageResult<MembershipAuthorityDTO> findMembershipAuthorityOfPage(MembershipAuthorityDTO dto,Pagination page){
		
		return membershipAuthorityReadService.findMembershipAuthorityOfPage(dto, page);
		
	}

	public List<MembershipAuthorityDTO> findMembershipAuthorityAll(MembershipAuthorityDTO dto){
		
		return membershipAuthorityReadService.findMembershipAuthorityAll(dto);
		
	}

	public Long insertMembershipAuthorityWithTx(MembershipAuthorityDTO dto){
		
		return membershipAuthorityWriteService.insertMembershipAuthorityWithTx(dto);
	}

	public int updateMembershipAuthorityWithTx(MembershipAuthorityDTO dto){
		
		return membershipAuthorityWriteService.updateMembershipAuthorityWithTx(dto);
	}

	public int deleteMembershipAuthorityWithTx(MembershipAuthorityDTO dto){
		
		return membershipAuthorityWriteService.deleteMembershipAuthorityWithTx(dto);
		
	}

	public PageResult<MembershipAuthorityDTO> findMembershipAuthorityByMembershipId(MembershipAuthorityDTO membershipAuthorityDTO, Pagination page) {

		return membershipAuthorityReadService.findMembershipAuthorityOfPage(membershipAuthorityDTO,page);

	}

	public void updateMembershipAuthorityWithTx(List<MembershipAuthorityDTO> dtos, MembershipDTO membershipDTO) {
		membershipAuthorityWriteService.updateMembershipAuthorityWithTx(dtos,membershipDTO);
	}

    public void insertMembershipAuthorityWithTx(List<MembershipAuthorityDTO> dtos, MembershipDTO membershipDTO) {
		membershipAuthorityWriteService.insertMembershipAuthorityWithTx(dtos, membershipDTO);
	}
}
	