package com.egeo.components.order.manage.write.impl;

import com.egeo.components.order.dao.write.SoWriteDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.order.manage.write.SoThirdpartyWriteManage;
import com.egeo.components.order.dao.write.SoThirdpartyWriteDAO;
import com.egeo.components.order.po.SoThirdpartyPO;
import com.egeo.exception.BusinessException;

@Service
public class SoThirdpartyWriteManageImpl implements SoThirdpartyWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private SoThirdpartyWriteDAO soThirdpartyWriteDAO;
	@Autowired
	private SoWriteDAO soWriteDao;

	@Override
	public Long insertSoThirdpartyWithTx(SoThirdpartyPO po) {
		
		int i ;
		try {
				i = soThirdpartyWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateSoThirdpartyWithTx(SoThirdpartyPO po) {
		int i;
		i = soThirdpartyWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteSoThirdpartyWithTx(SoThirdpartyPO po) {
		int i;
		i = soThirdpartyWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}

	@Override
	public int updateSoThirdpartyAndSoWithTx(SoThirdpartyPO po) {
		int i;
		i = soThirdpartyWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public void updateSoThirdpartyByCodeWithTx(SoThirdpartyPO po) {
		int i;
		i = soThirdpartyWriteDAO.updateSoThirdpartyByCodeWithTx(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");

	}

	@Override
	public void updateSoThirdpartyByThirdIdWithTx(SoThirdpartyPO po) {
		int i;
		i = soThirdpartyWriteDAO.updateSoThirdpartyByThirdIdWithTx(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
	}
}
	