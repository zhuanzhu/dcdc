package com.egeo.components.order.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.order.manage.write.SoDeviceWriteManage;
import com.egeo.components.order.dao.write.SoDeviceWriteDAO;
import com.egeo.components.order.po.SoDevicePO;
import com.egeo.exception.BusinessException;

@Service
public class SoDeviceWriteManageImpl implements SoDeviceWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private SoDeviceWriteDAO soDeviceWriteDAO;

	@Override
	public Long insertSoDeviceWithTx(SoDevicePO po) {
		
		int i ;
		try {
				i = soDeviceWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateSoDeviceWithTx(SoDevicePO po) {
		int i;
		i = soDeviceWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteSoDeviceWithTx(SoDevicePO po) {
		int i;
		i = soDeviceWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}	
}
	