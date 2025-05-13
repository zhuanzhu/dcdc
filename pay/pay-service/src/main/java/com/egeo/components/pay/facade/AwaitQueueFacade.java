package com.egeo.components.pay.facade;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.egeo.components.pay.dto.AwaitQueueDTO;
import com.egeo.components.pay.service.read.AwaitQueueReadService;
import com.egeo.components.pay.service.write.AwaitQueueWriteService;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class AwaitQueueFacade {
	
	@Resource
	private AwaitQueueReadService awaitQueueReadService;
	
	@Resource
	private AwaitQueueWriteService awaitQueueWriteService;
	
	
	public AwaitQueueDTO findAwaitQueueById(AwaitQueueDTO dto){
		
		return awaitQueueReadService.findAwaitQueueById(dto);
	}

	public PageResult<AwaitQueueDTO> findAwaitQueueOfPage(AwaitQueueDTO dto,Pagination page){
		
		return awaitQueueReadService.findAwaitQueueOfPage(dto, page);
		
	}

	public List<AwaitQueueDTO> findAwaitQueueAll(AwaitQueueDTO dto){
		
		return awaitQueueReadService.findAwaitQueueAll(dto);
		
	}

	public Long insertAwaitQueueWithTx(AwaitQueueDTO dto){
		
		return awaitQueueWriteService.insertAwaitQueueWithTx(dto,null);
	}

	public int updateAwaitQueueWithTx(AwaitQueueDTO dto){
		
		return awaitQueueWriteService.updateAwaitQueueWithTx(dto);
	}

	public int deleteAwaitQueueWithTx(AwaitQueueDTO dto){
		
		return awaitQueueWriteService.deleteAwaitQueueWithTx(dto);
		
	}

	public int updateAwaitQueueBySoIdWithTx(AwaitQueueDTO awaitQueueDTO) {
		return awaitQueueWriteService.updateAwaitQueueBySoIdWithTx(awaitQueueDTO);
	}

}
	