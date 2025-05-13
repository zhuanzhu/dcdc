package com.egeo.components.user.manage.write;

import com.egeo.components.user.po.FeedbackPO;


public interface FeedbackWriteManage {

	Long insertFeedbackWithTx(FeedbackPO po);

	int updateFeedbackWithTx(FeedbackPO po);

	int deleteFeedbackWithTx(FeedbackPO po);
}
	