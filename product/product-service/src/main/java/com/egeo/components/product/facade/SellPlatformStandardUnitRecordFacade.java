package com.egeo.components.product.facade;

import java.util.List;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.product.service.read.SellPlatformStandardUnitRecordReadService;
import com.egeo.components.product.service.write.SellPlatformStandardUnitRecordWriteService;
import com.egeo.components.product.dto.SellPlatformStandardUnitRecordDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class SellPlatformStandardUnitRecordFacade {
	
	@Resource
	private SellPlatformStandardUnitRecordReadService sellPlatformStandardUnitRecordReadService;
	
	@Resource
	private SellPlatformStandardUnitRecordWriteService sellPlatformStandardUnitRecordWriteService;
	
	
	public SellPlatformStandardUnitRecordDTO findSellPlatformStandardUnitRecordById(SellPlatformStandardUnitRecordDTO dto){
		
		return sellPlatformStandardUnitRecordReadService.findSellPlatformStandardUnitRecordById(dto);
	}

	public PageResult<SellPlatformStandardUnitRecordDTO> findSellPlatformStandardUnitRecordOfPage(SellPlatformStandardUnitRecordDTO dto,Pagination page){
		
		return sellPlatformStandardUnitRecordReadService.findSellPlatformStandardUnitRecordOfPage(dto, page);
		
	}

	public List<SellPlatformStandardUnitRecordDTO> findSellPlatformStandardUnitRecordAll(SellPlatformStandardUnitRecordDTO dto){
		
		return sellPlatformStandardUnitRecordReadService.findSellPlatformStandardUnitRecordAll(dto);
		
	}

	public Long insertSellPlatformStandardUnitRecordWithTx(SellPlatformStandardUnitRecordDTO dto){
		
		return sellPlatformStandardUnitRecordWriteService.insertSellPlatformStandardUnitRecordWithTx(dto);
	}

	public int updateSellPlatformStandardUnitRecordWithTx(SellPlatformStandardUnitRecordDTO dto){
		
		return sellPlatformStandardUnitRecordWriteService.updateSellPlatformStandardUnitRecordWithTx(dto);
	}

	public int deleteSellPlatformStandardUnitRecordWithTx(SellPlatformStandardUnitRecordDTO dto){
		
		return sellPlatformStandardUnitRecordWriteService.deleteSellPlatformStandardUnitRecordWithTx(dto);
		
	}

}
	