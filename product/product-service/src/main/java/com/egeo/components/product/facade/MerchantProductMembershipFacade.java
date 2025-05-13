package com.egeo.components.product.facade;

import java.util.List;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.product.service.read.MerchantProductMembershipReadService;
import com.egeo.components.product.service.write.MerchantProductMembershipWriteService;
import com.egeo.components.product.dto.MerchantProductMembershipDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class MerchantProductMembershipFacade {
	
	@Resource
	private MerchantProductMembershipReadService merchantProductMembershipReadService;
	
	@Resource
	private MerchantProductMembershipWriteService merchantProductMembershipWriteService;
	
	
	public MerchantProductMembershipDTO findMerchantProductMembershipById(MerchantProductMembershipDTO dto){
		
		return merchantProductMembershipReadService.findMerchantProductMembershipById(dto);
	}

	public PageResult<MerchantProductMembershipDTO> findMerchantProductMembershipOfPage(MerchantProductMembershipDTO dto,Pagination page){
		
		return merchantProductMembershipReadService.findMerchantProductMembershipOfPage(dto, page);
		
	}

	public List<MerchantProductMembershipDTO> findMerchantProductMembershipAll(MerchantProductMembershipDTO dto){
		
		return merchantProductMembershipReadService.findMerchantProductMembershipAll(dto);
		
	}

	public Long insertMerchantProductMembershipWithTx(MerchantProductMembershipDTO dto){
		
		return merchantProductMembershipWriteService.insertMerchantProductMembershipWithTx(dto);
	}

	public int updateMerchantProductMembershipWithTx(MerchantProductMembershipDTO dto){
		
		return merchantProductMembershipWriteService.updateMerchantProductMembershipWithTx(dto);
	}

	public int deleteMerchantProductMembershipWithTx(MerchantProductMembershipDTO dto){
		
		return merchantProductMembershipWriteService.deleteMerchantProductMembershipWithTx(dto);
		
	}

}
	