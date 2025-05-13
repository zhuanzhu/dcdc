package com.egeo.components.pay.facade;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.egeo.components.pay.dto.ThirdpartyAwaitQueueDTO;
import com.egeo.components.pay.service.read.ThirdpartyAwaitQueueReadService;
import com.egeo.components.pay.service.write.ThirdpartyAwaitQueueWriteService;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class ThirdpartyAwaitQueueFacade {
	
	@Resource
	private ThirdpartyAwaitQueueReadService thirdpartyAwaitQueueReadService;
	
	@Resource
	private ThirdpartyAwaitQueueWriteService thirdpartyAwaitQueueWriteService;
	
	
	public ThirdpartyAwaitQueueDTO findThirdpartyAwaitQueueById(ThirdpartyAwaitQueueDTO dto){
		
		return thirdpartyAwaitQueueReadService.findThirdpartyAwaitQueueById(dto);
	}

	public PageResult<ThirdpartyAwaitQueueDTO> findThirdpartyAwaitQueueOfPage(ThirdpartyAwaitQueueDTO dto,Pagination page){
		
		return thirdpartyAwaitQueueReadService.findThirdpartyAwaitQueueOfPage(dto, page);
		
	}

	public List<ThirdpartyAwaitQueueDTO> findThirdpartyAwaitQueueAll(ThirdpartyAwaitQueueDTO dto){
		
		return thirdpartyAwaitQueueReadService.findThirdpartyAwaitQueueAll(dto);
		
	}

	public Long insertThirdpartyAwaitQueueWithTx(ThirdpartyAwaitQueueDTO dto){
		
		return thirdpartyAwaitQueueWriteService.insertThirdpartyAwaitQueueWithTx(dto);
	}

	public int updateThirdpartyAwaitQueueWithTx(ThirdpartyAwaitQueueDTO dto){
		
		return thirdpartyAwaitQueueWriteService.updateThirdpartyAwaitQueueWithTx(dto);
	}

	public int deleteThirdpartyAwaitQueueWithTx(ThirdpartyAwaitQueueDTO dto){
		
		return thirdpartyAwaitQueueWriteService.deleteThirdpartyAwaitQueueWithTx(dto);
		
	}

}
	