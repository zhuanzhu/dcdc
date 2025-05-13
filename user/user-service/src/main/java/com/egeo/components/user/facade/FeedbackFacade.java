package com.egeo.components.user.facade;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.egeo.components.user.dto.FeedbackDTO;
import com.egeo.components.user.service.read.FeedbackReadService;
import com.egeo.components.user.service.write.FeedbackWriteService;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class FeedbackFacade {
	
	@Resource
	private FeedbackReadService feedbackReadService;
	
	@Resource
	private FeedbackWriteService feedbackWriteService;
	
	
	public FeedbackDTO findFeedbackById(FeedbackDTO dto){
		
		return feedbackReadService.findFeedbackById(dto);
	}

	public PageResult<FeedbackDTO> findFeedbackOfPage(FeedbackDTO dto,Pagination page){
		
		return feedbackReadService.findFeedbackOfPage(dto, page);
		
	}

	public List<FeedbackDTO> findFeedbackAll(FeedbackDTO dto){
		
		return feedbackReadService.findFeedbackAll(dto);
		
	}

	public Long insertFeedbackWithTx(FeedbackDTO dto){
		
		return feedbackWriteService.insertFeedbackWithTx(dto);
	}

	public int updateFeedbackWithTx(FeedbackDTO dto){
		
		return feedbackWriteService.updateFeedbackWithTx(dto);
	}

	public int deleteFeedbackWithTx(FeedbackDTO dto){
		
		return feedbackWriteService.deleteFeedbackWithTx(dto);
		
	}

}
	