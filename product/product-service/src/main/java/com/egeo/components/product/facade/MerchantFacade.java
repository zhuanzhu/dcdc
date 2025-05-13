package com.egeo.components.product.facade;

import java.util.List;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.product.service.read.MerchantReadService;
import com.egeo.components.product.service.write.MerchantWriteService;
import com.egeo.components.product.dto.MerchantDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class MerchantFacade {
	
	@Resource
	private MerchantReadService merchantReadService;
	
	@Resource
	private MerchantWriteService merchantWriteService;
	
	
	public MerchantDTO findMerchantById(MerchantDTO dto){
		
		return merchantReadService.findMerchantById(dto);
	}

	public PageResult<MerchantDTO> findMerchantOfPage(MerchantDTO dto,Pagination page){
		
		return merchantReadService.findMerchantOfPage(dto, page);
		
	}

	public List<MerchantDTO> findMerchantAll(MerchantDTO dto){
		
		return merchantReadService.findMerchantAll(dto);
		
	}

	public Long insertMerchantWithTx(MerchantDTO dto){
		
		return merchantWriteService.insertMerchantWithTx(dto);
	}

	public int updateMerchantWithTx(MerchantDTO dto){
		
		return merchantWriteService.updateMerchantWithTx(dto);
	}

	public int deleteMerchantWithTx(MerchantDTO dto){
		
		return merchantWriteService.deleteMerchantWithTx(dto);
		
	}

}
	