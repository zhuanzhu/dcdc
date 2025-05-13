package com.egeo.components.product.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.write.MerchantProductFeedbackWriteManage;
import com.egeo.components.product.dao.write.MerchantProductFeedbackWriteDAO;
import com.egeo.components.product.po.MerchantProductFeedbackPO;
import com.egeo.exception.BusinessException;

@Service
public class MerchantProductFeedbackWriteManageImpl implements MerchantProductFeedbackWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private MerchantProductFeedbackWriteDAO merchantProductFeedbackWriteDAO;

	@Override
	public Long insertMerchantProductFeedbackWithTx(MerchantProductFeedbackPO po) {
		
		int i ;
		try {
				i = merchantProductFeedbackWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateMerchantProductFeedbackWithTx(MerchantProductFeedbackPO po) {
		int i;
		i = merchantProductFeedbackWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteMerchantProductFeedbackWithTx(MerchantProductFeedbackPO po) {
		int i;
		i = merchantProductFeedbackWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}	
}
	