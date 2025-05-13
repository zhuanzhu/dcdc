package com.egeo.components.product.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.write.CardThirdpartyAttValueWriteManage;
import com.egeo.components.product.dao.write.CardThirdpartyAttValueWriteDAO;
import com.egeo.components.product.po.CardThirdpartyAttValuePO;
import com.egeo.exception.BusinessException;

@Service
public class CardThirdpartyAttValueWriteManageImpl implements CardThirdpartyAttValueWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CardThirdpartyAttValueWriteDAO cardThirdpartyAttValueWriteDAO;

	@Override
	public Long insertCardThirdpartyAttValueWithTx(CardThirdpartyAttValuePO po) {
		
		int i ;
		try {
				i = cardThirdpartyAttValueWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateCardThirdpartyAttValueWithTx(CardThirdpartyAttValuePO po) {
		int i;
		i = cardThirdpartyAttValueWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteCardThirdpartyAttValueWithTx(CardThirdpartyAttValuePO po) {
		int i;
		i = cardThirdpartyAttValueWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}	
}
	