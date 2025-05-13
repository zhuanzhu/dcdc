package com.egeo.components.product.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.write.MerchantWriteManage;
import com.egeo.components.product.dao.write.MerchantWriteDAO;
import com.egeo.components.product.po.MerchantPO;
import com.egeo.exception.BusinessException;

@Service
public class MerchantWriteManageImpl implements MerchantWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private MerchantWriteDAO merchantWriteDAO;

	@Override
	public Long insertMerchantWithTx(MerchantPO po) {
		
		int i ;
		try {
				i = merchantWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateMerchantWithTx(MerchantPO po) {
		int i;
		i = merchantWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteMerchantWithTx(MerchantPO po) {
		int i;
		i = merchantWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}	
}
	