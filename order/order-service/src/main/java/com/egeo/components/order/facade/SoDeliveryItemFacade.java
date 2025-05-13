package com.egeo.components.order.facade;

import java.util.List;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.order.service.read.SoDeliveryItemReadService;
import com.egeo.components.order.service.write.SoDeliveryItemWriteService;
import com.egeo.components.order.dto.SoDeliveryItemDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class SoDeliveryItemFacade {
	
	@Resource
	private SoDeliveryItemReadService soDeliveryItemReadService;
	
	@Resource
	private SoDeliveryItemWriteService soDeliveryItemWriteService;
	
	
	public SoDeliveryItemDTO findSoDeliveryItemById(SoDeliveryItemDTO dto){
		
		return soDeliveryItemReadService.findSoDeliveryItemById(dto);
	}

	public PageResult<SoDeliveryItemDTO> findSoDeliveryItemOfPage(SoDeliveryItemDTO dto,Pagination page){
		
		return soDeliveryItemReadService.findSoDeliveryItemOfPage(dto, page);
		
	}

	public List<SoDeliveryItemDTO> findSoDeliveryItemAll(SoDeliveryItemDTO dto){
		
		return soDeliveryItemReadService.findSoDeliveryItemAll(dto);
		
	}

	public int insertSoDeliveryItemWithTx(SoDeliveryItemDTO dto){
		
		return soDeliveryItemWriteService.insertSoDeliveryItemWithTx(dto);
	}

	public int updateSoDeliveryItemWithTx(SoDeliveryItemDTO dto){
		
		return soDeliveryItemWriteService.updateSoDeliveryItemWithTx(dto);
	}

	public int deleteSoDeliveryItemWithTx(SoDeliveryItemDTO dto){
		
		return soDeliveryItemWriteService.deleteSoDeliveryItemWithTx(dto);
		
	}

}
	