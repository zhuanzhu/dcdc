package com.egeo.components.pay.facade;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.egeo.components.pay.dto.JdOrderAwaitQueueDTO;
import com.egeo.components.pay.service.read.JdOrderAwaitQueueReadService;
import com.egeo.components.pay.service.write.JdOrderAwaitQueueWriteService;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class JdOrderAwaitQueueFacade {
	
	@Resource
	private JdOrderAwaitQueueReadService jdOrderAwaitQueueReadService;
	
	@Resource
	private JdOrderAwaitQueueWriteService jdOrderAwaitQueueWriteService;
	
	
	public JdOrderAwaitQueueDTO findJdOrderAwaitQueueById(JdOrderAwaitQueueDTO dto){
		
		return jdOrderAwaitQueueReadService.findJdOrderAwaitQueueById(dto);
	}

	public PageResult<JdOrderAwaitQueueDTO> findJdOrderAwaitQueueOfPage(JdOrderAwaitQueueDTO dto,Pagination page){
		
		return jdOrderAwaitQueueReadService.findJdOrderAwaitQueueOfPage(dto, page);
		
	}

	public List<JdOrderAwaitQueueDTO> findJdOrderAwaitQueueAll(JdOrderAwaitQueueDTO dto){
		
		return jdOrderAwaitQueueReadService.findJdOrderAwaitQueueAll(dto);
		
	}

	public Long insertJdOrderAwaitQueueWithTx(JdOrderAwaitQueueDTO dto){
		
		return jdOrderAwaitQueueWriteService.insertJdOrderAwaitQueueWithTx(dto);
	}

	public int updateJdOrderAwaitQueueWithTx(JdOrderAwaitQueueDTO dto){
		
		return jdOrderAwaitQueueWriteService.updateJdOrderAwaitQueueWithTx(dto);
	}

	public int deleteJdOrderAwaitQueueWithTx(JdOrderAwaitQueueDTO dto){
		
		return jdOrderAwaitQueueWriteService.deleteJdOrderAwaitQueueWithTx(dto);
		
	}

}
	