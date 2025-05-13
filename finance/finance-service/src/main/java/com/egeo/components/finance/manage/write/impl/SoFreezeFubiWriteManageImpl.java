package com.egeo.components.finance.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.finance.common.BusinessException;
import com.egeo.components.finance.dao.write.SoFreezeFubiWriteDAO;
import com.egeo.components.finance.manage.write.SoFreezeFubiWriteManage;
import com.egeo.components.finance.po.SoFreezeFubiPO;

@Service
public class SoFreezeFubiWriteManageImpl implements SoFreezeFubiWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private SoFreezeFubiWriteDAO soFreezeFubiWriteDAO;

	@Override
	public Long insertSoFreezeFubiWithTx(SoFreezeFubiPO po) {
		
		int i ;
		try {
				i = soFreezeFubiWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateSoFreezeFubiWithTx(SoFreezeFubiPO po) {
		int i;
		i = soFreezeFubiWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteSoFreezeFubiWithTx(SoFreezeFubiPO po) {
		int i;
		i = soFreezeFubiWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}
	/**
	 * 根据订单id删除订单冻结积分
	 * @param soId
	 * @return
	 */
	@Override
	public int delBySoId(Long soId) {
		SoFreezeFubiPO po = new SoFreezeFubiPO();
		po.setSoId(soId);
		return soFreezeFubiWriteDAO.deleteByPara(po);
	}	
}
	