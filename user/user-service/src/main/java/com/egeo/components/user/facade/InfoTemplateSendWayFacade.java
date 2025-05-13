package com.egeo.components.user.facade;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.egeo.components.user.dto.InfoTemplateSendWayDTO;
import com.egeo.components.user.service.read.InfoTemplateSendWayReadService;
import com.egeo.components.user.service.write.InfoTemplateSendWayWriteService;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class InfoTemplateSendWayFacade {
	
	@Resource
	private InfoTemplateSendWayReadService infoTemplateSendWayReadService;
	
	@Resource
	private InfoTemplateSendWayWriteService infoTemplateSendWayWriteService;
	
	
	public InfoTemplateSendWayDTO findInfoTemplateSendWayById(InfoTemplateSendWayDTO dto){
		
		return infoTemplateSendWayReadService.findInfoTemplateSendWayById(dto);
	}

	public PageResult<InfoTemplateSendWayDTO> findInfoTemplateSendWayOfPage(InfoTemplateSendWayDTO dto,Pagination page){
		
		return infoTemplateSendWayReadService.findInfoTemplateSendWayOfPage(dto, page);
		
	}

	public List<InfoTemplateSendWayDTO> findInfoTemplateSendWayAll(InfoTemplateSendWayDTO dto){
		
		return infoTemplateSendWayReadService.findInfoTemplateSendWayAll(dto);
		
	}

	public Long insertInfoTemplateSendWayWithTx(InfoTemplateSendWayDTO dto){
		
		return infoTemplateSendWayWriteService.insertInfoTemplateSendWayWithTx(dto);
	}

	public int updateInfoTemplateSendWayWithTx(InfoTemplateSendWayDTO dto){
		
		return infoTemplateSendWayWriteService.updateInfoTemplateSendWayWithTx(dto);
	}

	public int deleteInfoTemplateSendWayWithTx(InfoTemplateSendWayDTO dto){
		
		return infoTemplateSendWayWriteService.deleteInfoTemplateSendWayWithTx(dto);
		
	}

}
	