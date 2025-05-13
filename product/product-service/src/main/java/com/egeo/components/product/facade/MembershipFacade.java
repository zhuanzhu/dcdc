package com.egeo.components.product.facade;

import java.util.List;

import com.egeo.components.product.vo.MembershipVO;
import com.egeo.components.product.dto.SkuDTO;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.product.service.read.MembershipReadService;
import com.egeo.components.product.service.write.MembershipWriteService;
import com.egeo.components.product.dto.MembershipDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class MembershipFacade {
	
	@Resource
	private MembershipReadService membershipReadService;
	
	@Resource
	private MembershipWriteService membershipWriteService;
	
	
	public MembershipDTO findMembershipById(MembershipDTO dto){
		
		return membershipReadService.findMembershipById(dto);
	}

	public PageResult<MembershipDTO> findMembershipOfPage(MembershipDTO dto,Pagination page){
		
		return membershipReadService.findMembershipOfPage(dto, page);
		
	}

	public List<MembershipDTO> findMembershipAll(MembershipDTO dto){
		
		return membershipReadService.findMembershipAll(dto);
		
	}

	public Long insertMembershipWithTx(MembershipDTO dto){
		
		return membershipWriteService.insertMembershipWithTx(dto);
	}

	public int updateMembershipWithTx(MembershipDTO dto){
		
		return membershipWriteService.updateMembershipWithTx(dto);
	}

	public int deleteMembershipWithTx(MembershipDTO dto){
		
		return membershipWriteService.deleteMembershipWithTx(dto);
		
	}


	public PageResult<MembershipDTO> findMembershipOfPageByParam(Long categoryId, String membershipName, List<Long> skuIdList , Long platformId, Pagination page) {
		return membershipReadService.findMembershipOfPageByParam(categoryId,membershipName,skuIdList,platformId,page);

	}
}
	