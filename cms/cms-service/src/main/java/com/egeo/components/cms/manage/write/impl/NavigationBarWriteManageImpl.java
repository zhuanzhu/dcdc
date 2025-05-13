package com.egeo.components.cms.manage.write.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.cms.manage.write.NavigationBarWriteManage;
import com.egeo.components.cms.dao.write.NavigationBarCompanyWriteDAO;
import com.egeo.components.cms.dao.write.NavigationBarLabelWriteDAO;
import com.egeo.components.cms.dao.write.NavigationBarWriteDAO;
import com.egeo.components.cms.dao.write.NavigationLabelWriteDAO;
import com.egeo.components.cms.po.NavigationBarCompanyPO;
import com.egeo.components.cms.po.NavigationBarLabelPO;
import com.egeo.components.cms.po.NavigationBarPO;
import com.egeo.components.cms.po.NavigationLabelPO;
import com.egeo.exception.BusinessException;

@Service
public class NavigationBarWriteManageImpl implements NavigationBarWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private NavigationBarWriteDAO navigationBarWriteDAO;

	@Autowired
	private NavigationBarCompanyWriteDAO navigationBarCompanyWriteDAO;

	@Autowired
	private NavigationLabelWriteDAO navigationLabelWriteDAO;

	@Override
	public Long insertNavigationBarWithTx(NavigationBarPO po) {

		int i;
		try {
			i = navigationBarWriteDAO.insert(po);
			if (i == 0)
				throw new BusinessException("未能成功插入数据!");
		} catch (DuplicateKeyException e) {
			logger.error("", e);
			throw new BusinessException("路径必须唯一!");
		}

		// 新建关系
		insertRelation(po);

		return po.getId();
	}

	@Override
	public int updateNavigationBarWithTx(NavigationBarPO po) {
		int i;
		po.setUpdateTime(new Date());
		i = navigationBarWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");

		// 删除关系
		deleteRelation(po);
		// 新建关系
		insertRelation(po);

		return i;

	}

	@Override
	public int deleteNavigationBarWithTx(NavigationBarPO po) {
		int i;
		i = navigationBarWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");

		// 删除关系
		deleteRelation(po);

		return i;
	}

	/**
	 * 新建关系
	 * 
	 * @param po
	 */
	private void insertRelation(NavigationBarPO po) {
		int i;
		for (Long companyId : po.getCompanyIdList()) {
			NavigationBarCompanyPO navigationBarCompanyPO = new NavigationBarCompanyPO();
			navigationBarCompanyPO.setCompanyId(companyId);
			navigationBarCompanyPO.setNavigationBarId(po.getId());
			i = navigationBarCompanyWriteDAO.insert(navigationBarCompanyPO);
			if (i == 0)
				throw new BusinessException("未能成功插入数据!");
		}

		for (Long navigationLabelId : po.getNavigationLabelIdList()) {
			NavigationLabelPO navigationLabelPO = new NavigationLabelPO();
			navigationLabelPO.setNavigationBarId(po.getId());
			navigationLabelPO.setId(navigationLabelId);
			i = navigationLabelWriteDAO.update(navigationLabelPO);
			if (i == 0)
				throw new BusinessException("未能成功更新数据!");
		}
	}

	/**
	 * 删除关系
	 * 
	 * @param po
	 */
	private void deleteRelation(NavigationBarPO po) {
		int i;
		NavigationBarCompanyPO navigationBarCompanyPO = new NavigationBarCompanyPO();
		navigationBarCompanyPO.setNavigationBarId(po.getId());
		i = navigationBarCompanyWriteDAO.deleteByPara(navigationBarCompanyPO);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");

		// 解除导航栏和导航栏标签的关系
		NavigationLabelPO navigationLabelPO = new NavigationLabelPO();
		navigationLabelPO.setNavigationBarId(po.getId());
		navigationLabelWriteDAO.resetNavigationLabelRel(navigationLabelPO);

	}

}
