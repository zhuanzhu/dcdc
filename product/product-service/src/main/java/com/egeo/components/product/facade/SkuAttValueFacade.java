package com.egeo.components.product.facade;

import java.util.List;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.product.service.read.SkuAttValueReadService;
import com.egeo.components.product.service.write.SkuAttValueWriteService;
import com.egeo.components.product.dto.SkuAttValueDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class SkuAttValueFacade {
	
	@Resource
	private SkuAttValueReadService skuAttValueReadService;
	
	@Resource
	private SkuAttValueWriteService skuAttValueWriteService;
	
	
	public SkuAttValueDTO findSkuAttValueById(SkuAttValueDTO dto){
		
		return skuAttValueReadService.findSkuAttValueById(dto);
	}

	public PageResult<SkuAttValueDTO> findSkuAttValueOfPage(SkuAttValueDTO dto,Pagination page){
		
		return skuAttValueReadService.findSkuAttValueOfPage(dto, page);
		
	}

	public List<SkuAttValueDTO> findSkuAttValueAll(SkuAttValueDTO dto){
		
		return skuAttValueReadService.findSkuAttValueAll(dto);
		
	}

	public Long insertSkuAttValueWithTx(SkuAttValueDTO dto){
		
		return skuAttValueWriteService.insertSkuAttValueWithTx(dto);
	}

	public int updateSkuAttValueWithTx(SkuAttValueDTO dto){
		
		return skuAttValueWriteService.updateSkuAttValueWithTx(dto);
	}

	public int deleteSkuAttValueWithTx(SkuAttValueDTO dto){
		
		return skuAttValueWriteService.deleteSkuAttValueWithTx(dto);
		
	}

}
	