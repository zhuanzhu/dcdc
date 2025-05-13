package com.egeo.components.product.facade;

import java.util.List;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.product.service.read.MerchantProductTagReadService;
import com.egeo.components.product.service.write.MerchantProductTagWriteService;
import com.egeo.components.product.dto.MerchantProductTagDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class MerchantProductTagFacade {
	
	@Resource
	private MerchantProductTagReadService merchantProductTagReadService;
	
	@Resource
	private MerchantProductTagWriteService merchantProductTagWriteService;
	
	
	public MerchantProductTagDTO findMerchantProductTagById(MerchantProductTagDTO dto){
		
		return merchantProductTagReadService.findMerchantProductTagById(dto);
	}

	public PageResult<MerchantProductTagDTO> findMerchantProductTagOfPage(MerchantProductTagDTO dto,Pagination page){
		
		return merchantProductTagReadService.findMerchantProductTagOfPage(dto, page);
		
	}

	public List<MerchantProductTagDTO> findMerchantProductTagAll(MerchantProductTagDTO dto){
		
		return merchantProductTagReadService.findMerchantProductTagAll(dto);
		
	}

	public Long insertMerchantProductTagWithTx(MerchantProductTagDTO dto){
		
		return merchantProductTagWriteService.insertMerchantProductTagWithTx(dto);
	}

	public int updateMerchantProductTagWithTx(MerchantProductTagDTO dto){
		
		return merchantProductTagWriteService.updateMerchantProductTagWithTx(dto);
	}

	public int deleteMerchantProductTagWithTx(MerchantProductTagDTO dto){
		
		return merchantProductTagWriteService.deleteMerchantProductTagWithTx(dto);
		
	}

}
	