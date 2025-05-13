package com.egeo.components.user.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.user.manage.write.FeedbackWriteManage;
import com.egeo.components.user.dao.write.FeedbackWriteDAO;
import com.egeo.components.user.po.FeedbackPO;
import com.egeo.exception.BusinessException;

@Service
public class FeedbackWriteManageImpl implements FeedbackWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private FeedbackWriteDAO feedbackWriteDAO;

	@Override
	public Long insertFeedbackWithTx(FeedbackPO po) {
		
		int i ;
		try {
				i = feedbackWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateFeedbackWithTx(FeedbackPO po) {
		int i;
		i = feedbackWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteFeedbackWithTx(FeedbackPO po) {
		int i;
		i = feedbackWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}	
}
	