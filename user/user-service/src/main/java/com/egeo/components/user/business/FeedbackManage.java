package com.egeo.components.user.business;

import java.util.List;
	
import com.egeo.components.user.dto.FeedbackDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface FeedbackManage {

	public FeedbackDTO findFeedbackById(FeedbackDTO dto);	

	public PageResult<FeedbackDTO> findFeedbackOfPage(FeedbackDTO dto,Pagination page);

	public List<FeedbackDTO> findFeedbackAll(FeedbackDTO dto);

	Long insertFeedbackWithTx(FeedbackDTO dto);

	int updateFeedbackWithTx(FeedbackDTO dto);

	int deleteFeedbackWithTx(FeedbackDTO dto);
}
	