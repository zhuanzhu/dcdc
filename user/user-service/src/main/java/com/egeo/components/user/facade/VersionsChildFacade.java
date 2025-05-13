package com.egeo.components.user.facade;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.egeo.components.user.dto.VersionsChildDTO;
import com.egeo.components.user.service.read.VersionsChildReadService;
import com.egeo.components.user.service.write.VersionsChildWriteService;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class VersionsChildFacade {
	
	@Resource
	private VersionsChildReadService versionsChildReadService;
	
	@Resource
	private VersionsChildWriteService versionsChildWriteService;
	
	
	public VersionsChildDTO findVersionsChildById(VersionsChildDTO dto){
		
		return versionsChildReadService.findVersionsChildById(dto);
	}

	public PageResult<VersionsChildDTO> findVersionsChildOfPage(VersionsChildDTO dto,Pagination page){
		
		return versionsChildReadService.findVersionsChildOfPage(dto, page);
		
	}

	public List<VersionsChildDTO> findVersionsChildAll(VersionsChildDTO dto){
		
		return versionsChildReadService.findVersionsChildAll(dto);
		
	}

	public Long insertVersionsChildWithTx(VersionsChildDTO dto){
		
		return versionsChildWriteService.insertVersionsChildWithTx(dto);
	}

	public int updateVersionsChildWithTx(VersionsChildDTO dto){
		
		return versionsChildWriteService.updateVersionsChildWithTx(dto);
	}

	public int deleteVersionsChildWithTx(VersionsChildDTO dto){
		
		return versionsChildWriteService.deleteVersionsChildWithTx(dto);
		
	}
	/**
	 * 根据条件分页查询子版本和及其渠道信息
	 * @param vo
	 * @param page
	 * @param req
	 * @return
	 */
	public PageResult<VersionsChildDTO> versionsChildAndChannelOfPage(VersionsChildDTO dto, Pagination page) {
		// TODO Auto-generated method stub
		return versionsChildReadService.versionsChildAndChannelOfPage(dto, page);
	}

}
	