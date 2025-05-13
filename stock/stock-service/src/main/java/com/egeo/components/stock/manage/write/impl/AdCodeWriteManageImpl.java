package com.egeo.components.stock.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.stock.manage.write.AdCodeWriteManage;
import com.egeo.components.stock.dao.write.AdCodeWriteDAO;
import com.egeo.components.stock.po.AdCodePO;
import com.egeo.exception.BusinessException;

@Service
public class AdCodeWriteManageImpl implements AdCodeWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private AdCodeWriteDAO adCodeWriteDAO;

	@Override
	public Long insertAdCodeWithTx(AdCodePO po) {
		
		int i ;
		try {
				i = adCodeWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateAdCodeWithTx(AdCodePO po) {
		int i;
		i = adCodeWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteAdCodeWithTx(AdCodePO po) {
		int i;
		i = adCodeWriteDAO.delete(po);
		if (i == 1)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}	
}
	