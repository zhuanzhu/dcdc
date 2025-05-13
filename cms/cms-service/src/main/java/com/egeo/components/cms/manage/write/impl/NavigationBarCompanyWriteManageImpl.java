package com.egeo.components.cms.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.cms.manage.write.NavigationBarCompanyWriteManage;
import com.egeo.components.cms.dao.write.NavigationBarCompanyWriteDAO;
import com.egeo.components.cms.po.NavigationBarCompanyPO;
import com.egeo.exception.BusinessException;

@Service
public class NavigationBarCompanyWriteManageImpl implements NavigationBarCompanyWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private NavigationBarCompanyWriteDAO navigationBarCompanyWriteDAO;

	@Override
	public Long insertNavigationBarCompanyWithTx(NavigationBarCompanyPO po) {
		
		int i ;
		try {
				i = navigationBarCompanyWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateNavigationBarCompanyWithTx(NavigationBarCompanyPO po) {
		int i;
		i = navigationBarCompanyWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteNavigationBarCompanyWithTx(NavigationBarCompanyPO po) {
		int i;
		i = navigationBarCompanyWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}	
}
	