package com.egeo.components.order.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.order.manage.write.SoPackageItemWriteManage;
import com.egeo.components.order.dao.write.SoPackageItemWriteDAO;
import com.egeo.components.order.po.SoPackageItemPO;
import com.egeo.exception.BusinessException;

@Service
public class SoPackageItemWriteManageImpl implements SoPackageItemWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private SoPackageItemWriteDAO soPackageItemWriteDAO;

	@Override
	public int insertSoPackageItemWithTx(SoPackageItemPO po) {
		
		int i ;
		try {
				i = soPackageItemWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return i;
	}

	@Override
	public int updateSoPackageItemWithTx(SoPackageItemPO po) {
		int i;
		i = soPackageItemWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteSoPackageItemWithTx(SoPackageItemPO po) {
		int i;
		i = soPackageItemWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}	
}
	