package com.egeo.components.user.facade;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.egeo.components.user.dto.InfoSendWayDTO;
import com.egeo.components.user.service.read.InfoSendWayReadService;
import com.egeo.components.user.service.write.InfoSendWayWriteService;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class InfoSendWayFacade {
	
	@Resource
	private InfoSendWayReadService infoSendWayReadService;
	
	@Resource
	private InfoSendWayWriteService infoSendWayWriteService;
	
	
	public InfoSendWayDTO findInfoSendWayById(InfoSendWayDTO dto){
		
		return infoSendWayReadService.findInfoSendWayById(dto);
	}

	public PageResult<InfoSendWayDTO> findInfoSendWayOfPage(InfoSendWayDTO dto,Pagination page){
		
		return infoSendWayReadService.findInfoSendWayOfPage(dto, page);
		
	}

	public List<InfoSendWayDTO> findInfoSendWayAll(InfoSendWayDTO dto){
		
		return infoSendWayReadService.findInfoSendWayAll(dto);
		
	}

	public Long insertInfoSendWayWithTx(InfoSendWayDTO dto){
		
		return infoSendWayWriteService.insertInfoSendWayWithTx(dto);
	}

	public int updateInfoSendWayWithTx(InfoSendWayDTO dto){
		
		return infoSendWayWriteService.updateInfoSendWayWithTx(dto);
	}

	public int deleteInfoSendWayWithTx(InfoSendWayDTO dto){
		
		return infoSendWayWriteService.deleteInfoSendWayWithTx(dto);
		
	}

}
	