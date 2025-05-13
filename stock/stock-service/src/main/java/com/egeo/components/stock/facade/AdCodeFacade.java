package com.egeo.components.stock.facade;

import java.util.List;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.stock.service.read.AdCodeReadService;
import com.egeo.components.stock.service.write.AdCodeWriteService;
import com.egeo.components.stock.dto.AdCodeDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class AdCodeFacade {
	
	@Resource
	private AdCodeReadService adCodeReadService;
	
	@Resource
	private AdCodeWriteService adCodeWriteService;
	
	
	public AdCodeDTO findAdCodeById(AdCodeDTO dto){
		
		return adCodeReadService.findAdCodeById(dto);
	}

	public PageResult<AdCodeDTO> findAdCodeOfPage(AdCodeDTO dto,Pagination page){
		
		return adCodeReadService.findAdCodeOfPage(dto, page);
		
	}

	public List<AdCodeDTO> findAdCodeAll(AdCodeDTO dto){
		
		return adCodeReadService.findAdCodeAll(dto);
		
	}

	public Long insertAdCodeWithTx(AdCodeDTO dto){
		
		return adCodeWriteService.insertAdCodeWithTx(dto);
	}

	public int updateAdCodeWithTx(AdCodeDTO dto){
		
		return adCodeWriteService.updateAdCodeWithTx(dto);
	}

	public int deleteAdCodeWithTx(AdCodeDTO dto){
		
		return adCodeWriteService.deleteAdCodeWithTx(dto);
		
	}

}
	