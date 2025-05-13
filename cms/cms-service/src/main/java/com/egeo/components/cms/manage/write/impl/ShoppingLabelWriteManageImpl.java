package com.egeo.components.cms.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.cms.manage.write.ShoppingLabelWriteManage;
import com.egeo.components.cms.dao.write.ShoppingLabelWriteDAO;
import com.egeo.components.cms.po.ShoppingLabelPO;
import com.egeo.exception.BusinessException;

@Service
public class ShoppingLabelWriteManageImpl implements ShoppingLabelWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ShoppingLabelWriteDAO shoppingLabelWriteDAO;

	@Override
	public Long insertShoppingLabelWithTx(ShoppingLabelPO po) {
		
		int i ;
		try {
				i = shoppingLabelWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateShoppingLabelWithTx(ShoppingLabelPO po) {
		int i;
		i = shoppingLabelWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteShoppingLabelWithTx(ShoppingLabelPO po) {
		int i;
		i = shoppingLabelWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}	
}
	