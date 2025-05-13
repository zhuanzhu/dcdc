package com.egeo.components.user.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.user.manage.read.FeedbackReadManage;
import com.egeo.components.user.dao.read.FeedbackReadDAO;
import com.egeo.components.user.po.FeedbackPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class FeedbackReadManageImpl implements FeedbackReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private FeedbackReadDAO feedbackReadDAO;
	
	public FeedbackPO findFeedbackById(FeedbackPO po) {
		FeedbackPO feedbackpo = new FeedbackPO();
		feedbackpo.setId(po.getId());
		return feedbackReadDAO.findById(feedbackpo);
	}

	public PageResult<FeedbackPO> findFeedbackOfPage(FeedbackPO po, Pagination page) {
		
		PageResult<FeedbackPO> pageResult = new PageResult<FeedbackPO>();
		List<FeedbackPO> list = null;

		int cnt = feedbackReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = feedbackReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<FeedbackPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<FeedbackPO> findFeedbackAll(FeedbackPO po) {

		return feedbackReadDAO.findAll(po,null);
	}
	
}
	