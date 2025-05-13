package com.egeo.components.user.facade;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.egeo.components.user.dto.TrialApplyDTO;
import com.egeo.components.user.service.read.TrialApplyReadService;
import com.egeo.components.user.service.write.TrialApplyWriteService;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class TrialApplyFacade {
	
	@Resource
	private TrialApplyReadService trialApplyReadService;
	
	@Resource
	private TrialApplyWriteService trialApplyWriteService;
	
	
	public TrialApplyDTO findTrialApplyById(TrialApplyDTO dto){
		
		return trialApplyReadService.findTrialApplyById(dto);
	}

	public PageResult<TrialApplyDTO> findTrialApplyOfPage(TrialApplyDTO dto,Pagination page){
		
		return trialApplyReadService.findTrialApplyOfPage(dto, page);
		
	}

	public List<TrialApplyDTO> findTrialApplyAll(TrialApplyDTO dto){
		
		return trialApplyReadService.findTrialApplyAll(dto);
		
	}

	public Long insertTrialApplyWithTx(TrialApplyDTO dto){
		
		return trialApplyWriteService.insertTrialApplyWithTx(dto);
	}

	public int updateTrialApplyWithTx(TrialApplyDTO dto){
		
		return trialApplyWriteService.updateTrialApplyWithTx(dto);
	}

	public int deleteTrialApplyWithTx(TrialApplyDTO dto){
		
		return trialApplyWriteService.deleteTrialApplyWithTx(dto);
		
	}

	/**
	 * 模糊搜索试用申请分页信息
	 * @param dto
	 * @param page
	 * @return
	 */
	public PageResult<TrialApplyDTO> findTrialApplyOfPageByBlurry(TrialApplyDTO dto, Pagination page) {
		return trialApplyReadService.findTrialApplyOfPageByBlurry(dto, page);
	}

}
	