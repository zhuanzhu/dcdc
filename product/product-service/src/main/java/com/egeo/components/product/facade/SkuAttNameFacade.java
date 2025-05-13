package com.egeo.components.product.facade;

import java.util.List;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.product.service.read.SkuAttNameReadService;
import com.egeo.components.product.service.write.SkuAttNameWriteService;
import com.egeo.components.product.dto.SkuAttNameDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class SkuAttNameFacade {
	
	@Resource
	private SkuAttNameReadService skuAttNameReadService;
	
	@Resource
	private SkuAttNameWriteService skuAttNameWriteService;
	
	
	public SkuAttNameDTO findSkuAttNameById(SkuAttNameDTO dto){
		
		return skuAttNameReadService.findSkuAttNameById(dto);
	}

	public PageResult<SkuAttNameDTO> findSkuAttNameOfPage(SkuAttNameDTO dto,Pagination page){
		
		return skuAttNameReadService.findSkuAttNameOfPage(dto, page);
		
	}

	public List<SkuAttNameDTO> findSkuAttNameAll(SkuAttNameDTO dto){
		
		return skuAttNameReadService.findSkuAttNameAll(dto);
		
	}

	public Long insertSkuAttNameWithTx(SkuAttNameDTO dto){
		
		return skuAttNameWriteService.insertSkuAttNameWithTx(dto);
	}

	public int updateSkuAttNameWithTx(SkuAttNameDTO dto){
		
		return skuAttNameWriteService.updateSkuAttNameWithTx(dto);
	}

	public int deleteSkuAttNameWithTx(SkuAttNameDTO dto){
		
		return skuAttNameWriteService.deleteSkuAttNameWithTx(dto);
		
	}

}
	