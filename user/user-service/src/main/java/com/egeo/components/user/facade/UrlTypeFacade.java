package com.egeo.components.user.facade;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.egeo.components.user.dto.UrlTypeDTO;
import com.egeo.components.user.service.read.UrlTypeReadService;
import com.egeo.components.user.service.write.UrlTypeWriteService;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class UrlTypeFacade {
	
	@Resource
	private UrlTypeReadService urlTypeReadService;
	
	@Resource
	private UrlTypeWriteService urlTypeWriteService;
	
	
	public UrlTypeDTO findUrlTypeById(UrlTypeDTO dto){
		
		return urlTypeReadService.findUrlTypeById(dto);
	}

	public PageResult<UrlTypeDTO> findUrlTypeOfPage(UrlTypeDTO dto,Pagination page){
		
		return urlTypeReadService.findUrlTypeOfPage(dto, page);
		
	}

	public List<UrlTypeDTO> findUrlTypeAll(UrlTypeDTO dto){
		
		return urlTypeReadService.findUrlTypeAll(dto);
		
	}

	public Long insertUrlTypeWithTx(UrlTypeDTO dto){
		
		return urlTypeWriteService.insertUrlTypeWithTx(dto);
	}

	public int updateUrlTypeWithTx(UrlTypeDTO dto){
		
		return urlTypeWriteService.updateUrlTypeWithTx(dto);
	}

	public int deleteUrlTypeWithTx(UrlTypeDTO dto){
		
		return urlTypeWriteService.deleteUrlTypeWithTx(dto);
		
	}

}
	