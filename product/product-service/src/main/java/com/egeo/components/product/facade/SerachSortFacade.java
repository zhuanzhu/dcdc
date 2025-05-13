package com.egeo.components.product.facade;

import java.util.List;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.product.service.read.SerachSortReadService;
import com.egeo.components.product.service.write.SerachSortWriteService;
import com.egeo.components.product.dto.SerachSortDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class SerachSortFacade {
	
	@Resource
	private SerachSortReadService serachSortReadService;
	
	@Resource
	private SerachSortWriteService serachSortWriteService;
	
	
	public SerachSortDTO findSerachSortById(SerachSortDTO dto){
		
		return serachSortReadService.findSerachSortById(dto);
	}

	public PageResult<SerachSortDTO> findSerachSortOfPage(SerachSortDTO dto,Pagination page){
		
		return serachSortReadService.findSerachSortOfPage(dto, page);
		
	}

	public List<SerachSortDTO> findSerachSortAll(SerachSortDTO dto){
		
		return serachSortReadService.findSerachSortAll(dto);
		
	}

	public Long insertSerachSortWithTx(SerachSortDTO dto){
		
		return serachSortWriteService.insertSerachSortWithTx(dto);
	}

	public int updateSerachSortWithTx(SerachSortDTO dto){
		
		return serachSortWriteService.updateSerachSortWithTx(dto);
	}

	public int deleteSerachSortWithTx(SerachSortDTO dto){
		
		return serachSortWriteService.deleteSerachSortWithTx(dto);
		
	}

}
	