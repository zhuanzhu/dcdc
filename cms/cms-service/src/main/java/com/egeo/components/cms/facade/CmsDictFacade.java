package com.egeo.components.cms.facade;

import java.util.List;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.cms.service.read.CmsDictReadService;
import com.egeo.components.cms.service.write.CmsDictWriteService;
import com.egeo.components.cms.dto.CmsDictDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class CmsDictFacade {
	
	@Resource
	private CmsDictReadService cmsDictReadService;
	
	@Resource
	private CmsDictWriteService cmsDictWriteService;
	
	
	public CmsDictDTO findCmsDictById(CmsDictDTO dto){
		
		return cmsDictReadService.findCmsDictById(dto);
	}

	public PageResult<CmsDictDTO> findCmsDictOfPage(CmsDictDTO dto,Pagination page){
		
		return cmsDictReadService.findCmsDictOfPage(dto, page);
		
	}

	public List<CmsDictDTO> findCmsDictAll(CmsDictDTO dto){
		
		return cmsDictReadService.findCmsDictAll(dto);
		
	}

	public Long insertCmsDictWithTx(CmsDictDTO dto){
		
		return cmsDictWriteService.insertCmsDictWithTx(dto);
	}

	public int updateCmsDictWithTx(CmsDictDTO dto){
		
		return cmsDictWriteService.updateCmsDictWithTx(dto);
	}

	public int deleteCmsDictWithTx(CmsDictDTO dto){
		
		return cmsDictWriteService.deleteCmsDictWithTx(dto);
		
	}

}
	