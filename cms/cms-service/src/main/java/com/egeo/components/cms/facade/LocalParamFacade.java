package com.egeo.components.cms.facade;

import java.util.List;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.cms.service.read.LocalParamReadService;
import com.egeo.components.cms.service.write.LocalParamWriteService;
import com.egeo.components.cms.dto.LocalParamDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class LocalParamFacade {
	
	@Resource
	private LocalParamReadService localParamReadService;
	
	@Resource
	private LocalParamWriteService localParamWriteService;
	
	
	public LocalParamDTO findLocalParamById(LocalParamDTO dto){
		
		return localParamReadService.findLocalParamById(dto);
	}

	public PageResult<LocalParamDTO> findLocalParamOfPage(LocalParamDTO dto,Pagination page){
		
		return localParamReadService.findLocalParamOfPage(dto, page);
		
	}

	public List<LocalParamDTO> findLocalParamAll(LocalParamDTO dto){
		
		return localParamReadService.findLocalParamAll(dto);
		
	}

	public Long insertLocalParamWithTx(LocalParamDTO dto){
		
		return localParamWriteService.insertLocalParamWithTx(dto);
	}

	public int updateLocalParamWithTx(LocalParamDTO dto){
		
		return localParamWriteService.updateLocalParamWithTx(dto);
	}

	public int deleteLocalParamWithTx(LocalParamDTO dto){
		
		return localParamWriteService.deleteLocalParamWithTx(dto);
		
	}

}
	