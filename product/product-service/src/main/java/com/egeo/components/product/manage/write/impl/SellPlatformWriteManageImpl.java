package com.egeo.components.product.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.write.SellPlatformWriteManage;
import com.egeo.components.product.dao.write.SellPlatformWriteDAO;
import com.egeo.components.product.po.SellPlatformPO;
import com.egeo.exception.BusinessException;

@Service
public class SellPlatformWriteManageImpl implements SellPlatformWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private SellPlatformWriteDAO sellPlatformWriteDAO;

	@Override
	public Long insertSellPlatformWithTx(SellPlatformPO po) {
		
		int i ;
		try {
				i = sellPlatformWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateSellPlatformWithTx(SellPlatformPO po) {
		int i;
		//如果是设置为停用,将sort_value设置为-1
		if(po.getStatus()==0){
			po.setSortValue(-1);
		}
		i = sellPlatformWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteSellPlatformWithTx(SellPlatformPO po) {
		int i;
		i = sellPlatformWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}	
}
	