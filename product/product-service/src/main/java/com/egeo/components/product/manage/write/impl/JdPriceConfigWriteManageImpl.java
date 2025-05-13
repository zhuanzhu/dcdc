package com.egeo.components.product.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.write.JdPriceConfigWriteManage;
import com.egeo.components.product.dao.write.JdPriceConfigWriteDAO;
import com.egeo.components.product.po.JdPriceConfigPO;
import com.egeo.exception.BusinessException;

@Service
public class JdPriceConfigWriteManageImpl implements JdPriceConfigWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private JdPriceConfigWriteDAO jdPriceConfigWriteDAO;

	@Override
	public Long insertJdPriceConfigWithTx(JdPriceConfigPO po) {
		
		int i ;
		try {
				jdPriceConfigWriteDAO.deleteByPara(new JdPriceConfigPO());
				i = jdPriceConfigWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateJdPriceConfigWithTx(JdPriceConfigPO po) {
		int i;
		i = jdPriceConfigWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteJdPriceConfigWithTx(JdPriceConfigPO po) {
		int i;
		i = jdPriceConfigWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}	
}
	