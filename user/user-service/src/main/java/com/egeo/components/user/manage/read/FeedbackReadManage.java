package com.egeo.components.user.manage.read;

import java.util.List;
	
import com.egeo.components.user.po.FeedbackPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface FeedbackReadManage {

	public FeedbackPO findFeedbackById(FeedbackPO po);

	public PageResult<FeedbackPO> findFeedbackOfPage(FeedbackPO po,Pagination page);

	public List<FeedbackPO> findFeedbackAll(FeedbackPO po);
}
	