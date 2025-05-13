package com.egeo.components.product.facade;

import java.util.List;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.product.service.read.MerchantProdAttNameReadService;
import com.egeo.components.product.service.write.MerchantProdAttNameWriteService;
import com.egeo.components.product.dto.MerchantProdAttNameDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class MerchantProdAttNameFacade {
	
	@Resource
	private MerchantProdAttNameReadService merchantProdAttNameReadService;
	
	@Resource
	private MerchantProdAttNameWriteService merchantProdAttNameWriteService;
	
	
	public MerchantProdAttNameDTO findMerchantProdAttNameById(MerchantProdAttNameDTO dto){
		
		return merchantProdAttNameReadService.findMerchantProdAttNameById(dto);
	}

	public PageResult<MerchantProdAttNameDTO> findMerchantProdAttNameOfPage(MerchantProdAttNameDTO dto,Pagination page){
		
		return merchantProdAttNameReadService.findMerchantProdAttNameOfPage(dto, page);
		
	}

	public List<MerchantProdAttNameDTO> findMerchantProdAttNameAll(MerchantProdAttNameDTO dto){
		
		return merchantProdAttNameReadService.findMerchantProdAttNameAll(dto);
		
	}

	public Long insertMerchantProdAttNameWithTx(MerchantProdAttNameDTO dto){
		
		return merchantProdAttNameWriteService.insertMerchantProdAttNameWithTx(dto);
	}

	public int updateMerchantProdAttNameWithTx(MerchantProdAttNameDTO dto){
		
		return merchantProdAttNameWriteService.updateMerchantProdAttNameWithTx(dto);
	}

	public int deleteMerchantProdAttNameWithTx(MerchantProdAttNameDTO dto){
		
		return merchantProdAttNameWriteService.deleteMerchantProdAttNameWithTx(dto);
		
	}

}
	