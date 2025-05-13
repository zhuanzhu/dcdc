package com.egeo.components.promotion.manage.write.impl;

import com.egeo.components.promotion.dao.write.UserCardRecordWriteDAO;
import com.egeo.components.promotion.manage.write.UserCardRecordWriteManage;
import com.egeo.components.promotion.po.UserCardRecordPO;
import com.egeo.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class UserCardRecordWriteManageImpl implements UserCardRecordWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private UserCardRecordWriteDAO userCardRecordWriteDAO;

	@Override
	public Long insertUserCardRecordWithTx(UserCardRecordPO po) {

		int i ;
		try {
			i = userCardRecordWriteDAO.insert(po);
			if (i == 0)
				throw new BusinessException("未能成功插入数据!");
		} catch (DuplicateKeyException e) {
			logger.error("", e);
			throw new BusinessException("路径必须唯一!");
		}
		return po.getId();
	}

	@Override
	public int updateUserCardRecordWithTx(UserCardRecordPO po) {
		int i;
		i = userCardRecordWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteUserCardRecordWithTx(UserCardRecordPO po) {
		int i;
		i = userCardRecordWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}

	@Override
	public Boolean useCardWithTx(Long id, BigDecimal amount,Integer useState){
		int i =  userCardRecordWriteDAO.useCardWithTx(id,amount,useState);
		if(i>0){
			return true;
		}
		return false;
	}

	@Override
	public Boolean refundUserCardRecordWithTx(Long id, BigDecimal amount,Integer useState){
		int i =  userCardRecordWriteDAO.refundCardWithTx(id,amount,useState);
		if(i>0){
			return true;
		}
		return false;
	}
}
