package com.egeo.components.stock.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.stock.manage.write.ContactGroupStockWriteManage;
import com.egeo.components.stock.dao.write.ContactGroupStockWriteDAO;
import com.egeo.components.stock.po.ContactGroupStockPO;
import com.egeo.exception.BusinessException;

@Service
public class ContactGroupStockWriteManageImpl implements ContactGroupStockWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ContactGroupStockWriteDAO contactGroupStockWriteDAO;

	@Override
	public Long insertContactGroupStockWithTx(ContactGroupStockPO po) {
		
		int i ;
		try {
				i = contactGroupStockWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateContactGroupStockWithTx(ContactGroupStockPO po) {
		int i;
		i = contactGroupStockWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteContactGroupStockWithTx(ContactGroupStockPO po) {
		int i;
		i = contactGroupStockWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}

	@Override
	public void increaseOneCountWithTx(ContactGroupStockPO po) {
		contactGroupStockWriteDAO.increaseOneCountWithTx(po);
	}

	@Override
	public void clearCountWithTx(ContactGroupStockPO po) {
		contactGroupStockWriteDAO.clearCountWithTx(po);
	}

	@Override
	public void reduceOneCountWithTx(ContactGroupStockPO po) {
		contactGroupStockWriteDAO.reduceOneCountWithTx(po);
	}
}
	