package com.egeo.components.user.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.user.converter.FeedbackConverter;
import com.egeo.components.user.dto.FeedbackDTO;
import com.egeo.components.user.manage.write.FeedbackWriteManage;
import com.egeo.components.user.po.FeedbackPO;
import com.egeo.components.user.service.write.FeedbackWriteService;

@Service("feedbackWriteService")
public class FeedbackWriteServiceImpl implements FeedbackWriteService {
	@Autowired
	private FeedbackWriteManage feedbackWriteManage;

	@Override
	public Long insertFeedbackWithTx(FeedbackDTO dto) {
		FeedbackPO po = FeedbackConverter.toPO(dto);
		Long rt = feedbackWriteManage.insertFeedbackWithTx(po);		
		return rt;
	}

	@Override
	public int updateFeedbackWithTx(FeedbackDTO dto) {
		FeedbackPO po = FeedbackConverter.toPO(dto);
		int rt = feedbackWriteManage.updateFeedbackWithTx(po);		
		return rt;
	}

	@Override
	public int deleteFeedbackWithTx(FeedbackDTO dto) {
		FeedbackPO po = FeedbackConverter.toPO(dto);
		int rt = feedbackWriteManage.deleteFeedbackWithTx(po);		
		return rt;
	}
}
	