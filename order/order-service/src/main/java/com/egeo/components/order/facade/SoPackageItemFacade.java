package com.egeo.components.order.facade;

import java.util.List;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.order.service.read.SoPackageItemReadService;
import com.egeo.components.order.service.write.SoPackageItemWriteService;
import com.egeo.components.order.dto.SoPackageItemDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class SoPackageItemFacade {
	
	@Resource
	private SoPackageItemReadService soPackageItemReadService;
	
	@Resource
	private SoPackageItemWriteService soPackageItemWriteService;
	
	
	public SoPackageItemDTO findSoPackageItemById(SoPackageItemDTO dto){
		
		return soPackageItemReadService.findSoPackageItemById(dto);
	}

	public PageResult<SoPackageItemDTO> findSoPackageItemOfPage(SoPackageItemDTO dto,Pagination page){
		
		return soPackageItemReadService.findSoPackageItemOfPage(dto, page);
		
	}

	public List<SoPackageItemDTO> findSoPackageItemAll(SoPackageItemDTO dto){
		
		return soPackageItemReadService.findSoPackageItemAll(dto);
		
	}

	public int insertSoPackageItemWithTx(SoPackageItemDTO dto){
		
		return soPackageItemWriteService.insertSoPackageItemWithTx(dto);
	}

	public int updateSoPackageItemWithTx(SoPackageItemDTO dto){
		
		return soPackageItemWriteService.updateSoPackageItemWithTx(dto);
	}

	public int deleteSoPackageItemWithTx(SoPackageItemDTO dto){
		
		return soPackageItemWriteService.deleteSoPackageItemWithTx(dto);
		
	}

}
	