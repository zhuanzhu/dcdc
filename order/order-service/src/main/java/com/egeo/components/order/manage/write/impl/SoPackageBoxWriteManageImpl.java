package com.egeo.components.order.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.order.manage.write.SoPackageBoxWriteManage;
import com.egeo.components.order.dao.write.SoPackageBoxWriteDAO;
import com.egeo.components.order.po.SoPackageBoxPO;
import com.egeo.exception.BusinessException;

@Service
public class SoPackageBoxWriteManageImpl implements SoPackageBoxWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private SoPackageBoxWriteDAO soPackageBoxWriteDAO;

	@Override
	public Long insertSoPackageBoxWithTx(SoPackageBoxPO po) {
		
		int i ;
		try {
				i = soPackageBoxWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateSoPackageBoxWithTx(SoPackageBoxPO po) {
		int i;
		i = soPackageBoxWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteSoPackageBoxWithTx(SoPackageBoxPO po) {
		int i;
		i = soPackageBoxWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}	
}
	