package com.egeo.components.cms.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.cms.manage.write.IconWriteManage;
import com.egeo.components.cms.dao.write.IconWriteDAO;
import com.egeo.components.cms.po.IconPO;
import com.egeo.exception.BusinessException;

@Service
public class IconWriteManageImpl implements IconWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private IconWriteDAO iconWriteDAO;

	@Override
	public Long insertIconWithTx(IconPO po) {
		
		int i ;
		try {
				i = iconWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateIconWithTx(IconPO po) {
		int i;
		i = iconWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteIconWithTx(IconPO po) {
		int i;
		i = iconWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}	
}
	