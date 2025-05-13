package com.egeo.components.product.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.write.FreightRegulationWriteManage;
import com.egeo.components.product.dao.write.FreightRegulationWriteDAO;
import com.egeo.components.product.po.FreightRegulationPO;
import com.egeo.exception.BusinessException;

@Service
public class FreightRegulationWriteManageImpl implements FreightRegulationWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private FreightRegulationWriteDAO freightRegulationWriteDAO;

	@Override
	public Long insertFreightRegulationWithTx(FreightRegulationPO po) {
		
		int i ;
		try {
				i = freightRegulationWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateFreightRegulationWithTx(FreightRegulationPO po) {
		int i;
		i = freightRegulationWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteFreightRegulationWithTx(FreightRegulationPO po) {
		int i;
		i = freightRegulationWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}	
}
	