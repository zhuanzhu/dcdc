package com.egeo.components.user.facade;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.egeo.components.user.dto.UrlWhiteListDTO;
import com.egeo.components.user.service.read.UrlWhiteListReadService;
import com.egeo.components.user.service.write.UrlWhiteListWriteService;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class UrlWhiteListFacade {
	
	@Resource
	private UrlWhiteListReadService urlWhiteListReadService;
	
	@Resource
	private UrlWhiteListWriteService urlWhiteListWriteService;
	
	
	public UrlWhiteListDTO findUrlWhiteListById(UrlWhiteListDTO dto){
		
		return urlWhiteListReadService.findUrlWhiteListById(dto);
	}

	public PageResult<UrlWhiteListDTO> findUrlWhiteListOfPage(UrlWhiteListDTO dto,Pagination page){
		
		return urlWhiteListReadService.findUrlWhiteListOfPage(dto, page);
		
	}

	public List<UrlWhiteListDTO> findUrlWhiteListAll(UrlWhiteListDTO dto){
		
		return urlWhiteListReadService.findUrlWhiteListAll(dto);
		
	}


	public List<String> findUrlWhiteList(Long platformId){

		return urlWhiteListReadService.findUrlWhiteList(platformId);

	}

	public Long insertUrlWhiteListWithTx(UrlWhiteListDTO dto, List<Long> platformIdList){
		
		return urlWhiteListWriteService.insertUrlWhiteListWithTx(dto, platformIdList);
	}

	public int updateUrlWhiteListWithTx(UrlWhiteListDTO dto, List<Long> platformIdList){
		
		return urlWhiteListWriteService.updateUrlWhiteListWithTx(dto, platformIdList);
	}

	public int deleteUrlWhiteListWithTx(UrlWhiteListDTO dto){
		
		return urlWhiteListWriteService.deleteUrlWhiteListWithTx(dto);
		
	}

}
	