package com.egeo.components.user.service.read;


import java.util.List;
	
import com.egeo.components.user.dto.FeedbackDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface FeedbackReadService {

	public FeedbackDTO findFeedbackById(FeedbackDTO dto);

	public PageResult<FeedbackDTO> findFeedbackOfPage(FeedbackDTO dto,Pagination page);

	public List<FeedbackDTO> findFeedbackAll(FeedbackDTO dto);
}
	