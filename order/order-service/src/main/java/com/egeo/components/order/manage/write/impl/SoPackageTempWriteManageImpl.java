package com.egeo.components.order.manage.write.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import com.egeo.components.order.manage.write.SoPackageTempWriteManage;
import com.egeo.components.order.dao.write.SoPackageTempWriteDAO;
import com.egeo.components.order.po.SoPackageTempPO;
import com.egeo.exception.BusinessException;

@Service
public class SoPackageTempWriteManageImpl implements SoPackageTempWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private SoPackageTempWriteDAO soPackageTempWriteDAO;

	@Override
	public Long insertSoPackageTempWithTx(SoPackageTempPO po) {
		
		int i ;
		try {
				i = soPackageTempWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateSoPackageTempWithTx(SoPackageTempPO po) {
		int i;
		i = soPackageTempWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteSoPackageTempWithTx(SoPackageTempPO po) {
		int i;
		i = soPackageTempWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}

	@Override
	public String insertSoPackageTempListWithTx(List<SoPackageTempPO> po) {
		String soPackageTempIds="";
		
		for (SoPackageTempPO soPackageTempPO : po) {
			int soPackageTempId;
			try {
				soPackageTempId = soPackageTempWriteDAO.insert(soPackageTempPO);
				if (soPackageTempId == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
			soPackageTempIds = soPackageTempIds+","+soPackageTempId;
		}
		return soPackageTempIds;
	}	
}
	