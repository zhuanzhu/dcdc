package com.egeo.components.cms.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.cms.manage.write.NavigationBarLabelWriteManage;
import com.egeo.components.cms.dao.write.NavigationBarLabelWriteDAO;
import com.egeo.components.cms.po.NavigationBarLabelPO;
import com.egeo.exception.BusinessException;

@Service
public class NavigationBarLabelWriteManageImpl implements NavigationBarLabelWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private NavigationBarLabelWriteDAO navigationBarLabelWriteDAO;

	@Override
	public Long insertNavigationBarLabelWithTx(NavigationBarLabelPO po) {
		
		int i ;
		try {
				i = navigationBarLabelWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateNavigationBarLabelWithTx(NavigationBarLabelPO po) {
		int i;
		i = navigationBarLabelWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteNavigationBarLabelWithTx(NavigationBarLabelPO po) {
		int i;
		i = navigationBarLabelWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}	
}
	