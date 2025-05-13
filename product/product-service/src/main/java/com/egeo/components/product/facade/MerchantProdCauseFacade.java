package com.egeo.components.product.facade;

import java.util.List;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.product.service.read.MerchantProdCauseReadService;
import com.egeo.components.product.service.write.MerchantProdCauseWriteService;
import com.egeo.components.product.dto.MerchantProdCauseDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class MerchantProdCauseFacade {
	
	@Resource
	private MerchantProdCauseReadService merchantProdCauseReadService;
	
	@Resource
	private MerchantProdCauseWriteService merchantProdCauseWriteService;
	
	
	public MerchantProdCauseDTO findMerchantProdCauseById(MerchantProdCauseDTO dto){
		
		return merchantProdCauseReadService.findMerchantProdCauseById(dto);
	}

	public PageResult<MerchantProdCauseDTO> findMerchantProdCauseOfPage(MerchantProdCauseDTO dto,Pagination page){
		
		return merchantProdCauseReadService.findMerchantProdCauseOfPage(dto, page);
		
	}

	public List<MerchantProdCauseDTO> findMerchantProdCauseAll(MerchantProdCauseDTO dto){
		
		return merchantProdCauseReadService.findMerchantProdCauseAll(dto);
		
	}

	public Long insertMerchantProdCauseWithTx(MerchantProdCauseDTO dto){
		
		return merchantProdCauseWriteService.insertMerchantProdCauseWithTx(dto);
	}

	public int updateMerchantProdCauseWithTx(MerchantProdCauseDTO dto){
		
		return merchantProdCauseWriteService.updateMerchantProdCauseWithTx(dto);
	}

	public int deleteMerchantProdCauseWithTx(MerchantProdCauseDTO dto){
		
		return merchantProdCauseWriteService.deleteMerchantProdCauseWithTx(dto);
		
	}

}
	