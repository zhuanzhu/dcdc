package com.egeo.components.pay.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.pay.dao.write.AlipayNativeSignLogWriteDAO;
import com.egeo.components.pay.manage.write.AlipayNativeSignLogWriteManage;
import com.egeo.components.pay.po.AlipayNativeSignLogPO;
import com.egeo.exception.BusinessException;

@Service
public class AlipayNativeSignLogWriteManageImpl implements AlipayNativeSignLogWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private AlipayNativeSignLogWriteDAO alipayNativeSignLogWriteDAO;

	@Override
	public Long insertAlipayNativeSignLogWithTx(AlipayNativeSignLogPO po) {
		
		int i ;
		try {
				i = alipayNativeSignLogWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateAlipayNativeSignLogWithTx(AlipayNativeSignLogPO po) {
		int i;
		i = alipayNativeSignLogWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteAlipayNativeSignLogWithTx(AlipayNativeSignLogPO po) {
		int i;
		i = alipayNativeSignLogWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}	
}
	