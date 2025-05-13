package com.egeo.components.user.service.write;

import com.egeo.components.user.dto.FeedbackDTO;


public interface FeedbackWriteService {

	public Long insertFeedbackWithTx(FeedbackDTO dto);

	public int updateFeedbackWithTx(FeedbackDTO dto);

	public int deleteFeedbackWithTx(FeedbackDTO dto);
}
	