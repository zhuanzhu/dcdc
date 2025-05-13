package com.egeo.components.user.business.impl;
	

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.egeo.components.user.business.FeedbackManage;
import com.egeo.components.user.dto.FeedbackDTO;
import com.egeo.components.user.facade.FeedbackFacade;
import com.egeo.exception.BusinessException;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;

@Service("feedback")
public class FeedbackManageImpl implements FeedbackManage{

	
	@Resource(name="feedbackFacade")
	private FeedbackFacade feedbackFacade;

	@Override
	public FeedbackDTO findFeedbackById(FeedbackDTO dto) {
		return feedbackFacade.findFeedbackById(dto);
	}

	@Override
	public PageResult<FeedbackDTO> findFeedbackOfPage(FeedbackDTO dto, Pagination page) {
		return feedbackFacade.findFeedbackOfPage(dto, page);
	}

	@Override
	public List<FeedbackDTO> findFeedbackAll(FeedbackDTO dto) {
		return feedbackFacade.findFeedbackAll(dto);
	}

	@Override
	public Long insertFeedbackWithTx(FeedbackDTO dto) {
		if(EmptyUtil.isNotEmpty(dto.getFeedback())){
			if(dto.getFeedback().length() > 200){
				throw new BusinessException("反馈意见不能超过200字");
			}else if(dto.getFeedback().length() < 5){
				throw new BusinessException("反馈内容不少于5个字");
			}
		}else{
			throw new BusinessException("请填写反馈意见");
		}
		
		
		return feedbackFacade.insertFeedbackWithTx(dto);
	}

	@Override
	public int updateFeedbackWithTx(FeedbackDTO dto) {
		return feedbackFacade.updateFeedbackWithTx(dto);
	}

	@Override
	public int deleteFeedbackWithTx(FeedbackDTO dto) {
		return feedbackFacade.deleteFeedbackWithTx(dto);
	}


}
	