package com.egeo.components.user.facade;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.egeo.components.user.dto.UrlTypeDictDTO;
import com.egeo.components.user.service.read.UrlTypeDictReadService;
import com.egeo.components.user.service.write.UrlTypeDictWriteService;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class UrlTypeDictFacade {
	
	@Resource
	private UrlTypeDictReadService urlTypeDictReadService;
	
	@Resource
	private UrlTypeDictWriteService urlTypeDictWriteService;
	
	
	public UrlTypeDictDTO findUrlTypeDictById(UrlTypeDictDTO dto){
		
		return urlTypeDictReadService.findUrlTypeDictById(dto);
	}

	public PageResult<UrlTypeDictDTO> findUrlTypeDictOfPage(UrlTypeDictDTO dto,Pagination page){
		
		return urlTypeDictReadService.findUrlTypeDictOfPage(dto, page);
		
	}

	public List<UrlTypeDictDTO> findUrlTypeDictAll(UrlTypeDictDTO dto){
		
		return urlTypeDictReadService.findUrlTypeDictAll(dto);
		
	}

	public Long insertUrlTypeDictWithTx(UrlTypeDictDTO dto){
		
		return urlTypeDictWriteService.insertUrlTypeDictWithTx(dto);
	}

	public int updateUrlTypeDictWithTx(UrlTypeDictDTO dto){
		
		return urlTypeDictWriteService.updateUrlTypeDictWithTx(dto);
	}

	public int deleteUrlTypeDictWithTx(UrlTypeDictDTO dto){
		
		return urlTypeDictWriteService.deleteUrlTypeDictWithTx(dto);
		
	}

}
	