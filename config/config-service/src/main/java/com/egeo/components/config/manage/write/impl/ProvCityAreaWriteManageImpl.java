package com.egeo.components.config.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.config.dao.write.ProvCityAreaWriteDAO;
import com.egeo.components.config.manage.write.ProvCityAreaWriteManage;
import com.egeo.components.config.po.ProvCityAreaPO;
import com.egeo.exception.BusinessException;

@Service
public class ProvCityAreaWriteManageImpl implements ProvCityAreaWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ProvCityAreaWriteDAO provCityAreaWriteDAO;

	@Override
	public Long insertProvCityAreaWithTx(ProvCityAreaPO po) {
		
		int i ;
		try {
				i = provCityAreaWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateProvCityAreaWithTx(ProvCityAreaPO po) {
		int i;
		i = provCityAreaWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteProvCityAreaWithTx(ProvCityAreaPO po) {
		int i;
		i = provCityAreaWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}	
}
	