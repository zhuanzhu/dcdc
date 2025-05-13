package com.egeo.components.stock.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.stock.manage.write.ContactGroupPuStockWriteManage;
import com.egeo.components.stock.dao.write.ContactGroupPuStockWriteDAO;
import com.egeo.components.stock.po.ContactGroupPuStockPO;
import com.egeo.exception.BusinessException;

import java.util.List;

@Service
public class ContactGroupPuStockWriteManageImpl implements ContactGroupPuStockWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ContactGroupPuStockWriteDAO contactGroupPuStockWriteDAO;

	@Override
	public Long insertContactGroupPuStockWithTx(ContactGroupPuStockPO po) {
		
		int i ;
		try {
				i = contactGroupPuStockWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateContactGroupPuStockWithTx(ContactGroupPuStockPO po) {
		int i;
		i = contactGroupPuStockWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteContactGroupPuStockWithTx(ContactGroupPuStockPO po) {
		int i;
		i = contactGroupPuStockWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}

	@Override
	public void deleteContactGroupPuStockByParaWithTx(ContactGroupPuStockPO po) {
		contactGroupPuStockWriteDAO.deleteByPara(po);
	}

	@Override
	public void insertContactGroupPuListStockWithTx(List<ContactGroupPuStockPO> poList) {
		contactGroupPuStockWriteDAO.insertContactGroupPuListStockWithTx(poList);
	}


}
	