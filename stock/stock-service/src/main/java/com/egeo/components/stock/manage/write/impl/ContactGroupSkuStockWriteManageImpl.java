package com.egeo.components.stock.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.stock.manage.write.ContactGroupSkuStockWriteManage;
import com.egeo.components.stock.dao.write.ContactGroupSkuStockWriteDAO;
import com.egeo.components.stock.po.ContactGroupSkuStockPO;
import com.egeo.exception.BusinessException;

import java.util.List;

@Service
public class ContactGroupSkuStockWriteManageImpl implements ContactGroupSkuStockWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ContactGroupSkuStockWriteDAO contactGroupSkuStockWriteDAO;

	@Override
	public Long insertContactGroupSkuStockWithTx(ContactGroupSkuStockPO po) {
		
		int i ;
		try {
				i = contactGroupSkuStockWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateContactGroupSkuStockWithTx(ContactGroupSkuStockPO po) {
		int i;
		i = contactGroupSkuStockWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteContactGroupSkuStockWithTx(ContactGroupSkuStockPO po) {
		int i;
		i = contactGroupSkuStockWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}

	@Override
	public void deleteContactGroupSkuStockByParaWithTx(ContactGroupSkuStockPO po) {
		contactGroupSkuStockWriteDAO.deleteByPara(po);
	}

	@Override
	public void insertContactGroupSkuStockListWithTx(List<ContactGroupSkuStockPO> poList) {
		contactGroupSkuStockWriteDAO.insertContactGroupSkuStockListWithTx(poList);
	}
}
	